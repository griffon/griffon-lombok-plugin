/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lombok.core;

import lombok.core.util.MethodDescriptor;

import static lombok.core.util.MethodDescriptor.*;

/**
 * @author Andres Almiray
 */
public interface ResourceResolverAwareConstants extends BaseConstants {
    String NO_SUCH_RESOURCE_EXCEPTION_TYPE = "griffon.core.resources.NoSuchResourceException";
    String RESOURCE_RESOLVER_TYPE = "griffon.core.resources.ResourceResolver";
    String JAVA_UTIL_LOCALE = "java.util.Locale";
    String METHOD_RESOLVE_RESOURCE = "resolveResource";
    String METHOD_RESOLVE_RESOURCE_VALUE = "resolveResourceValue";
    String METHOD_FORMAT_RESOURCE = "formatResource";

    MethodDescriptor[] METHODS = new MethodDescriptor[]{
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE_VALUE,
            args(type(JAVA_LANG_STRING), type(JAVA_UTIL_LOCALE)),
            throwing(type(NO_SUCH_RESOURCE_EXCEPTION_TYPE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING)),
            throwing(type(NO_SUCH_RESOURCE_EXCEPTION_TYPE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_UTIL_LOCALE)),
            throwing(type(NO_SUCH_RESOURCE_EXCEPTION_TYPE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_LANG_OBJECT, 1)),
            throwing(type(NO_SUCH_RESOURCE_EXCEPTION_TYPE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_LANG_OBJECT, 1), type(JAVA_UTIL_LOCALE)),
            throwing(type(NO_SUCH_RESOURCE_EXCEPTION_TYPE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_UTIL_LIST)),
            throwing(type(NO_SUCH_RESOURCE_EXCEPTION_TYPE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_UTIL_LIST), type(JAVA_UTIL_LOCALE)),
            throwing(type(NO_SUCH_RESOURCE_EXCEPTION_TYPE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT)),
            throwing(type(NO_SUCH_RESOURCE_EXCEPTION_TYPE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT), type(JAVA_UTIL_LOCALE)),
            throwing(type(NO_SUCH_RESOURCE_EXCEPTION_TYPE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_LANG_OBJECT))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_LANG_OBJECT), type(JAVA_UTIL_LOCALE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_LANG_OBJECT, 1), type(JAVA_LANG_OBJECT))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_LANG_OBJECT, 1), type(JAVA_LANG_OBJECT), type(JAVA_UTIL_LOCALE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_UTIL_LIST), type(JAVA_LANG_OBJECT))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_UTIL_LIST), type(JAVA_LANG_OBJECT), type(JAVA_UTIL_LOCALE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(type(JAVA_LANG_STRING), type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT), type(JAVA_LANG_OBJECT))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_OBJECT),
            METHOD_RESOLVE_RESOURCE,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(JAVA_LANG_OBJECT),
                type(JAVA_UTIL_LOCALE))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_STRING),
            METHOD_FORMAT_RESOURCE,
            args(
                type(JAVA_LANG_OBJECT, 1),
                type(JAVA_UTIL_MAP))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_STRING),
            METHOD_FORMAT_RESOURCE,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_UTIL_LIST))
        ),
        MethodDescriptor.method(
            type(JAVA_LANG_STRING),
            METHOD_FORMAT_RESOURCE,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_UTIL_MAP))
        )
    };
}
