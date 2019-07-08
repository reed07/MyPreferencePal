package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
public final class Parameter implements AnnotatedElement {
    private final ImmutableList<Annotation> annotations;
    private final Invokable<?, ?> declaration;
    private final int position;
    private final TypeToken<?> type;

    Parameter(Invokable<?, ?> invokable, int i, TypeToken<?> typeToken, Annotation[] annotationArr) {
        this.declaration = invokable;
        this.position = i;
        this.type = typeToken;
        this.annotations = ImmutableList.copyOf((E[]) annotationArr);
    }

    public TypeToken<?> getType() {
        return this.type;
    }

    public Invokable<?, ?> getDeclaringInvokable() {
        return this.declaration;
    }

    public boolean isAnnotationPresent(Class<? extends Annotation> cls) {
        return getAnnotation(cls) != null;
    }

    @NullableDecl
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        Preconditions.checkNotNull(cls);
        UnmodifiableIterator it = this.annotations.iterator();
        while (it.hasNext()) {
            Annotation annotation = (Annotation) it.next();
            if (cls.isInstance(annotation)) {
                return (Annotation) cls.cast(annotation);
            }
        }
        return null;
    }

    public Annotation[] getAnnotations() {
        return getDeclaredAnnotations();
    }

    public <A extends Annotation> A[] getAnnotationsByType(Class<A> cls) {
        return getDeclaredAnnotationsByType(cls);
    }

    public Annotation[] getDeclaredAnnotations() {
        ImmutableList<Annotation> immutableList = this.annotations;
        return (Annotation[]) immutableList.toArray(new Annotation[immutableList.size()]);
    }

    @NullableDecl
    public <A extends Annotation> A getDeclaredAnnotation(Class<A> cls) {
        Preconditions.checkNotNull(cls);
        return (Annotation) FluentIterable.from((Iterable<E>) this.annotations).filter(cls).first().orNull();
    }

    public <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> cls) {
        return (Annotation[]) FluentIterable.from((Iterable<E>) this.annotations).filter(cls).toArray(cls);
    }

    public boolean equals(@NullableDecl Object obj) {
        boolean z = false;
        if (!(obj instanceof Parameter)) {
            return false;
        }
        Parameter parameter = (Parameter) obj;
        if (this.position == parameter.position && this.declaration.equals(parameter.declaration)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return this.position;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.type);
        sb.append(" arg");
        sb.append(this.position);
        return sb.toString();
    }
}
