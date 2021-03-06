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

package lombok.core.handlers;

import lombok.ast.*;
import lombok.core.BaseConstants;
import lombok.core.util.MethodDescriptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static lombok.ast.AST.*;
import static lombok.core.util.Names.capitalize;

/**
 * @author Andres Almiray
 */
public abstract class AbstractHandler<TYPE_TYPE extends IType<? extends IMethod<?, ?, ?, ?>, ?, ?, ?, ?, ?>> implements BaseConstants {
    protected TypeRef asTypeRef(MethodDescriptor.Type type) {
        if (type instanceof MethodDescriptor.Wildcard) {
            MethodDescriptor.Wildcard wildcard = (MethodDescriptor.Wildcard) type;
            if (wildcard.isExtends()) {
                return Wildcard(Wildcard.Bound.EXTENDS, Type(wildcard.parameters[0].type));
            } else if (wildcard.isSuper()) {
                return Wildcard(Wildcard.Bound.SUPER, Type(wildcard.parameters[0].type));
            } else {
                return Wildcard();
            }
        }

        TypeRef typeRef = Type(type.type);
        if (type.parameters.length > 0) {
            List<TypeRef> types = new ArrayList<TypeRef>();
            for (int i = 0; i < type.parameters.length; i++) {
                types.add(asTypeRef(type.parameters[i]));
            }
            typeRef.withTypeArguments(types);
        }
        if (type.dimensions > 0) typeRef.withDimensions(type.dimensions);
        return typeRef;
    }

    protected TypeParam asTypeParam(MethodDescriptor.TypeParam tp) {
        TypeParam typeParam = TypeParam(tp.type);
        if (tp.isBound()) {
            typeParam.withBound(asTypeRef(tp.bound));
        }
        return typeParam;
    }

    public Expression<?> getApplicationExpression() {
        return Call(Name("griffon.util.ApplicationHolder"), "getApplication");
    }

    public FieldDecl addField(final TYPE_TYPE type, String fieldType, String fieldName) {
        return addField(type, fieldType, fieldName, null);
    }

    public FieldDecl addField(final TYPE_TYPE type, String fieldType, String fieldName, Expression<?> initialization) {
        final FieldDecl fieldDecl = FieldDecl(Type(fieldType), fieldName)
            .makePrivate();
        if (initialization != null) fieldDecl.withInitialization(initialization);
        type.editor().injectField(fieldDecl);
        return fieldDecl;
    }

    public FieldDecl addField(final TYPE_TYPE type, MethodDescriptor.Type fieldType, String fieldName) {
        return addField(type, fieldType, fieldName, null);
    }

    public FieldDecl addField(final TYPE_TYPE type, MethodDescriptor.Type fieldType, String fieldName, Expression<?> initialization) {
        final FieldDecl fieldDecl = FieldDecl(asTypeRef(fieldType), fieldName)
            .makePrivate();
        if (initialization != null) fieldDecl.withInitialization(initialization);
        type.editor().injectField(fieldDecl);
        return fieldDecl;
    }

    public FieldDecl addField(final TYPE_TYPE type, TypeRef fieldType, String fieldName) {
        return addField(type, fieldType, fieldName, null);
    }

    public FieldDecl addField(final TYPE_TYPE type, TypeRef fieldType, String fieldName, Expression<?> initialization) {
        final FieldDecl fieldDecl = FieldDecl(fieldType, fieldName)
            .makePrivate();
        if (initialization != null) fieldDecl.withInitialization(initialization);
        type.editor().injectField(fieldDecl);
        return fieldDecl;
    }

    public void delegateMethodsTo(TYPE_TYPE type, MethodDescriptor[] methodDescriptors, Expression<?> receiver) {
        delegateMethodsTo(type, methodDescriptors, receiver, Collections.<String, String>emptyMap());
    }

    public void delegateMethodsTo(TYPE_TYPE type, MethodDescriptor[] methodDescriptors, Expression<?> receiver, Map<String, String> methodMapper) {
        for (MethodDescriptor methodDesc : methodDescriptors) {
            List<Argument> methodArgs = new ArrayList<Argument>();
            List<Expression<?>> callArgs = new ArrayList<Expression<?>>();
            int argCounter = 0;
            for (MethodDescriptor.Type arg : methodDesc.arguments) {
                String argName = "arg" + argCounter++;
                methodArgs.add(Arg(asTypeRef(arg), argName));
                callArgs.add(Name(argName));
            }

            String delegateMethodName = methodMapper.get(methodDesc.methodName);
            if (delegateMethodName == null) delegateMethodName = methodDesc.methodName;

            Call methodCall = Call(receiver, delegateMethodName)
                .withArguments(callArgs);
            MethodDecl methodDecl = MethodDecl(asTypeRef(methodDesc.returnType), methodDesc.methodName)
                .makePublic()
                .withArguments(methodArgs);
            if (VOID.equals(methodDesc.returnType.type)) {
                methodDecl.withStatement(methodCall);
            } else {
                methodDecl.withStatement(Return(methodCall));
            }
            if (methodDesc.typeParameters.length > 0) {
                List<TypeParam> types = new ArrayList<TypeParam>();
                for (int i = 0; i < methodDesc.typeParameters.length; i++) {
                    types.add(asTypeParam(methodDesc.typeParameters[i]));
                }
                methodDecl.withTypeParameters(types);
            }
            if (methodDesc.exceptions.length > 0) {
                List<TypeRef> exceptions = new ArrayList<TypeRef>();
                for (int i = 0; i < methodDesc.exceptions.length; i++) {
                    exceptions.add(asTypeRef(methodDesc.exceptions[i]));
                }
                methodDecl.withThrownExceptions(exceptions);
            }

            type.editor().injectMethod(methodDecl);
        }
    }

    public static String getGetterName(String propertyName) {
        return "get" + capitalize(propertyName);
    }

    public static String getSetterName(String propertyName) {
        return "set" + capitalize(propertyName);
    }
}
