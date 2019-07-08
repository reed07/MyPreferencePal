package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset.Entry;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Enum;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
public final class EnumMultiset<E extends Enum<E>> extends AbstractMultiset<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public transient int[] counts = new int[this.enumConstants.length];
    /* access modifiers changed from: private */
    public transient int distinctElements;
    /* access modifiers changed from: private */
    public transient E[] enumConstants;
    /* access modifiers changed from: private */
    public transient long size;
    private transient Class<E> type;

    abstract class Itr<T> implements Iterator<T> {
        int index = 0;
        int toRemove = -1;

        /* access modifiers changed from: 0000 */
        public abstract T output(int i);

        Itr() {
        }

        public boolean hasNext() {
            while (this.index < EnumMultiset.this.enumConstants.length) {
                int[] access$100 = EnumMultiset.this.counts;
                int i = this.index;
                if (access$100[i] > 0) {
                    return true;
                }
                this.index = i + 1;
            }
            return false;
        }

        public T next() {
            if (hasNext()) {
                T output = output(this.index);
                int i = this.index;
                this.toRemove = i;
                this.index = i + 1;
                return output;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.toRemove >= 0);
            if (EnumMultiset.this.counts[this.toRemove] > 0) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: INVOKE  (wrap: com.google.common.collect.EnumMultiset
                      0x0017: IGET  (r0v6 com.google.common.collect.EnumMultiset) = (r6v0 'this' com.google.common.collect.EnumMultiset$Itr A[THIS]) com.google.common.collect.EnumMultiset.Itr.this$0 com.google.common.collect.EnumMultiset) com.google.common.collect.EnumMultiset.access$210(com.google.common.collect.EnumMultiset):int type: STATIC in method: com.google.common.collect.EnumMultiset.Itr.remove():void, dex: classes2.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                    	at jadx.core.codegen.ClassGen.addInnerClasses(ClassGen.java:237)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:224)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                    	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                    Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                    	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                    	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                    	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                    	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                    	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                    	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                    	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                    	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                    	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:24)
                    	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:9)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 27 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 45 more
                    */
                /*
                    this = this;
                    int r0 = r6.toRemove
                    r1 = 0
                    if (r0 < 0) goto L_0x0007
                    r0 = 1
                    goto L_0x0008
                L_0x0007:
                    r0 = 0
                L_0x0008:
                    com.google.common.collect.CollectPreconditions.checkRemove(r0)
                    com.google.common.collect.EnumMultiset r0 = com.google.common.collect.EnumMultiset.this
                    int[] r0 = r0.counts
                    int r2 = r6.toRemove
                    r0 = r0[r2]
                    if (r0 <= 0) goto L_0x003b
                    com.google.common.collect.EnumMultiset r0 = com.google.common.collect.EnumMultiset.this
                    
                    // error: 0x0019: INVOKE  (r0 I:com.google.common.collect.EnumMultiset) com.google.common.collect.EnumMultiset.access$210(com.google.common.collect.EnumMultiset):int type: STATIC
                    com.google.common.collect.EnumMultiset r0 = com.google.common.collect.EnumMultiset.this
                    long r2 = r0.size
                    com.google.common.collect.EnumMultiset r4 = com.google.common.collect.EnumMultiset.this
                    int[] r4 = r4.counts
                    int r5 = r6.toRemove
                    r4 = r4[r5]
                    long r4 = (long) r4
                    long r2 = r2 - r4
                    r0.size = r2
                    com.google.common.collect.EnumMultiset r0 = com.google.common.collect.EnumMultiset.this
                    int[] r0 = r0.counts
                    int r2 = r6.toRemove
                    r0[r2] = r1
                L_0x003b:
                    r0 = -1
                    r6.toRemove = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.EnumMultiset.Itr.remove():void");
            }
        }

        public /* bridge */ /* synthetic */ boolean contains(@NullableDecl Object obj) {
            return super.contains(obj);
        }

        public /* bridge */ /* synthetic */ Set elementSet() {
            return super.elementSet();
        }

        public /* bridge */ /* synthetic */ Set entrySet() {
            return super.entrySet();
        }

        public /* bridge */ /* synthetic */ boolean isEmpty() {
            return super.isEmpty();
        }

        public static <E extends Enum<E>> EnumMultiset<E> create(Class<E> cls) {
            return new EnumMultiset<>(cls);
        }

        public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> iterable) {
            Iterator it = iterable.iterator();
            Preconditions.checkArgument(it.hasNext(), "EnumMultiset constructor passed empty Iterable");
            EnumMultiset<E> enumMultiset = new EnumMultiset<>(((Enum) it.next()).getDeclaringClass());
            Iterables.addAll(enumMultiset, iterable);
            return enumMultiset;
        }

        public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> iterable, Class<E> cls) {
            EnumMultiset<E> create = create(cls);
            Iterables.addAll(create, iterable);
            return create;
        }

        private EnumMultiset(Class<E> cls) {
            this.type = cls;
            Preconditions.checkArgument(cls.isEnum());
            this.enumConstants = (Enum[]) cls.getEnumConstants();
        }

        private boolean isActuallyE(@NullableDecl Object obj) {
            boolean z = false;
            if (!(obj instanceof Enum)) {
                return false;
            }
            E e = (Enum) obj;
            int ordinal = e.ordinal();
            E[] eArr = this.enumConstants;
            if (ordinal < eArr.length && eArr[ordinal] == e) {
                z = true;
            }
            return z;
        }

        /* access modifiers changed from: 0000 */
        public void checkIsE(@NullableDecl Object obj) {
            Preconditions.checkNotNull(obj);
            if (!isActuallyE(obj)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Expected an ");
                sb.append(this.type);
                sb.append(" but got ");
                sb.append(obj);
                throw new ClassCastException(sb.toString());
            }
        }

        /* access modifiers changed from: 0000 */
        public int distinctElements() {
            return this.distinctElements;
        }

        public int size() {
            return Ints.saturatedCast(this.size);
        }

        public int count(@NullableDecl Object obj) {
            if (obj == null || !isActuallyE(obj)) {
                return 0;
            }
            return this.counts[((Enum) obj).ordinal()];
        }

        @CanIgnoreReturnValue
        public int add(E e, int i) {
            checkIsE(e);
            CollectPreconditions.checkNonnegative(i, "occurrences");
            if (i == 0) {
                return count(e);
            }
            int ordinal = e.ordinal();
            int i2 = this.counts[ordinal];
            long j = (long) i;
            long j2 = ((long) i2) + j;
            Preconditions.checkArgument(j2 <= 2147483647L, "too many occurrences: %s", j2);
            this.counts[ordinal] = (int) j2;
            if (i2 == 0) {
                this.distinctElements++;
            }
            this.size += j;
            return i2;
        }

        @CanIgnoreReturnValue
        public int remove(@NullableDecl Object obj, int i) {
            if (obj == null || !isActuallyE(obj)) {
                return 0;
            }
            Enum enumR = (Enum) obj;
            CollectPreconditions.checkNonnegative(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            int ordinal = enumR.ordinal();
            int[] iArr = this.counts;
            int i2 = iArr[ordinal];
            if (i2 == 0) {
                return 0;
            }
            if (i2 <= i) {
                iArr[ordinal] = 0;
                this.distinctElements--;
                this.size -= (long) i2;
            } else {
                iArr[ordinal] = i2 - i;
                this.size -= (long) i;
            }
            return i2;
        }

        @CanIgnoreReturnValue
        public int setCount(E e, int i) {
            checkIsE(e);
            CollectPreconditions.checkNonnegative(i, "count");
            int ordinal = e.ordinal();
            int[] iArr = this.counts;
            int i2 = iArr[ordinal];
            iArr[ordinal] = i;
            this.size += (long) (i - i2);
            if (i2 == 0 && i > 0) {
                this.distinctElements++;
            } else if (i2 > 0 && i == 0) {
                this.distinctElements--;
            }
            return i2;
        }

        public void clear() {
            Arrays.fill(this.counts, 0);
            this.size = 0;
            this.distinctElements = 0;
        }

        /* access modifiers changed from: 0000 */
        public Iterator<E> elementIterator() {
            return new Itr<E>() {
                /* access modifiers changed from: 0000 */
                public E output(int i) {
                    return EnumMultiset.this.enumConstants[i];
                }
            };
        }

        /* access modifiers changed from: 0000 */
        public Iterator<Entry<E>> entryIterator() {
            return new Itr<Entry<E>>() {
                /* access modifiers changed from: 0000 */
                public Entry<E> output(final int i) {
                    return new AbstractEntry<E>() {
                        public E getElement() {
                            return EnumMultiset.this.enumConstants[i];
                        }

                        public int getCount() {
                            return EnumMultiset.this.counts[i];
                        }
                    };
                }
            };
        }

        public Iterator<E> iterator() {
            return Multisets.iteratorImpl(this);
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.type);
            Serialization.writeMultiset(this, objectOutputStream);
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.type = (Class) objectInputStream.readObject();
            this.enumConstants = (Enum[]) this.type.getEnumConstants();
            this.counts = new int[this.enumConstants.length];
            Serialization.populateMultiset(this, objectInputStream);
        }
    }
