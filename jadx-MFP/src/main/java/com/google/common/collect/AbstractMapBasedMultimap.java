package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection.WrappedIterator;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
abstract class AbstractMapBasedMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 2447537837011683357L;
    /* access modifiers changed from: private */
    public transient Map<K, Collection<V>> map;
    /* access modifiers changed from: private */
    public transient int totalSize;

    private class AsMap extends ViewCachingAbstractMap<K, Collection<V>> {
        final transient Map<K, Collection<V>> submap;

        class AsMapEntries extends EntrySet<K, Collection<V>> {
            AsMapEntries() {
            }

            /* access modifiers changed from: 0000 */
            public Map<K, Collection<V>> map() {
                return AsMap.this;
            }

            public Iterator<Entry<K, Collection<V>>> iterator() {
                return new AsMapIterator();
            }

            public boolean contains(Object obj) {
                return Collections2.safeContains(AsMap.this.submap.entrySet(), obj);
            }

            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                AbstractMapBasedMultimap.this.removeValuesForKey(((Entry) obj).getKey());
                return true;
            }
        }

        class AsMapIterator implements Iterator<Entry<K, Collection<V>>> {
            @NullableDecl
            Collection<V> collection;
            final Iterator<Entry<K, Collection<V>>> delegateIterator = AsMap.this.submap.entrySet().iterator();

            AsMapIterator() {
            }

            public boolean hasNext() {
                return this.delegateIterator.hasNext();
            }

            public Entry<K, Collection<V>> next() {
                Entry entry = (Entry) this.delegateIterator.next();
                this.collection = (Collection) entry.getValue();
                return AsMap.this.wrapEntry(entry);
            }

            public void remove() {
                CollectPreconditions.checkRemove(this.collection != null);
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.this.totalSize = AbstractMapBasedMultimap.this.totalSize - this.collection.size();
                this.collection.clear();
                this.collection = null;
            }
        }

        AsMap(Map<K, Collection<V>> map) {
            this.submap = map;
        }

        /* access modifiers changed from: protected */
        public Set<Entry<K, Collection<V>>> createEntrySet() {
            return new AsMapEntries();
        }

        public boolean containsKey(Object obj) {
            return Maps.safeContainsKey(this.submap, obj);
        }

        public Collection<V> get(Object obj) {
            Collection collection = (Collection) Maps.safeGet(this.submap, obj);
            if (collection == null) {
                return null;
            }
            return AbstractMapBasedMultimap.this.wrapCollection(obj, collection);
        }

        public Set<K> keySet() {
            return AbstractMapBasedMultimap.this.keySet();
        }

        public int size() {
            return this.submap.size();
        }

        public Collection<V> remove(Object obj) {
            Collection collection = (Collection) this.submap.remove(obj);
            if (collection == null) {
                return null;
            }
            Collection<V> createCollection = AbstractMapBasedMultimap.this.createCollection();
            createCollection.addAll(collection);
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize - collection.size();
            collection.clear();
            return createCollection;
        }

        public boolean equals(@NullableDecl Object obj) {
            return this == obj || this.submap.equals(obj);
        }

        public int hashCode() {
            return this.submap.hashCode();
        }

        public String toString() {
            return this.submap.toString();
        }

        public void clear() {
            if (this.submap == AbstractMapBasedMultimap.this.map) {
                AbstractMapBasedMultimap.this.clear();
            } else {
                Iterators.clear(new AsMapIterator());
            }
        }

        /* access modifiers changed from: 0000 */
        public Entry<K, Collection<V>> wrapEntry(Entry<K, Collection<V>> entry) {
            Object key = entry.getKey();
            return Maps.immutableEntry(key, AbstractMapBasedMultimap.this.wrapCollection(key, (Collection) entry.getValue()));
        }
    }

    private abstract class Itr<T> implements Iterator<T> {
        @MonotonicNonNullDecl
        Collection<V> collection = null;
        @NullableDecl
        K key = null;
        final Iterator<Entry<K, Collection<V>>> keyIterator;
        Iterator<V> valueIterator = Iterators.emptyModifiableIterator();

        /* access modifiers changed from: 0000 */
        public abstract T output(K k, V v);

        Itr() {
            this.keyIterator = AbstractMapBasedMultimap.this.map.entrySet().iterator();
        }

        public boolean hasNext() {
            return this.keyIterator.hasNext() || this.valueIterator.hasNext();
        }

        public T next() {
            if (!this.valueIterator.hasNext()) {
                Entry entry = (Entry) this.keyIterator.next();
                this.key = entry.getKey();
                this.collection = (Collection) entry.getValue();
                this.valueIterator = this.collection.iterator();
            }
            return output(this.key, this.valueIterator.next());
        }

        public void remove() {
            this.valueIterator.remove();
            if (this.collection.isEmpty()) {
                this.keyIterator.remove();
            }
            
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0014: INVOKE  (wrap: com.google.common.collect.AbstractMapBasedMultimap
                  0x0012: IGET  (r0v3 com.google.common.collect.AbstractMapBasedMultimap) = (r1v0 'this' com.google.common.collect.AbstractMapBasedMultimap$Itr A[THIS]) com.google.common.collect.AbstractMapBasedMultimap.Itr.this$0 com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$210(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC in method: com.google.common.collect.AbstractMapBasedMultimap.Itr.remove():void, dex: classes2.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                	... 22 more
                Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                	at java.base/java.lang.Class.forName0(Native Method)
                	at java.base/java.lang.Class.forName(Unknown Source)
                	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                	... 40 more
                */
            /*
                this = this;
                java.util.Iterator<V> r0 = r1.valueIterator
                r0.remove()
                java.util.Collection<V> r0 = r1.collection
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto L_0x0012
                java.util.Iterator<java.util.Map$Entry<K, java.util.Collection<V>>> r0 = r1.keyIterator
                r0.remove()
            L_0x0012:
                com.google.common.collect.AbstractMapBasedMultimap r0 = com.google.common.collect.AbstractMapBasedMultimap.this
                
                // error: 0x0014: INVOKE  (r0 I:com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$210(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.Itr.remove():void");
        }
    }

    private class KeySet extends KeySet<K, Collection<V>> {
        KeySet(Map<K, Collection<V>> map) {
            super(map);
        }

        public Iterator<K> iterator() {
            final Iterator it = map().entrySet().iterator();
            return new Iterator<K>() {
                @NullableDecl
                Entry<K, Collection<V>> entry;

                public boolean hasNext() {
                    return it.hasNext();
                }

                public K next() {
                    this.entry = (Entry) it.next();
                    return this.entry.getKey();
                }

                public void remove() {
                    CollectPreconditions.checkRemove(this.entry != null);
                    Collection collection = (Collection) this.entry.getValue();
                    it.remove();
                    AbstractMapBasedMultimap.this.totalSize = AbstractMapBasedMultimap.this.totalSize - collection.size();
                    collection.clear();
                    this.entry = null;
                }
            };
        }

        public boolean remove(Object obj) {
            int i;
            Collection collection = (Collection) map().remove(obj);
            if (collection != null) {
                i = collection.size();
                collection.clear();
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize - i;
            } else {
                i = 0;
            }
            if (i > 0) {
                return true;
            }
            return false;
        }

        public void clear() {
            Iterators.clear(iterator());
        }

        public boolean containsAll(Collection<?> collection) {
            return map().keySet().containsAll(collection);
        }

        public boolean equals(@NullableDecl Object obj) {
            return this == obj || map().keySet().equals(obj);
        }

        public int hashCode() {
            return map().keySet().hashCode();
        }
    }

    class NavigableAsMap extends SortedAsMap implements NavigableMap<K, Collection<V>> {
        NavigableAsMap(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        /* access modifiers changed from: 0000 */
        public NavigableMap<K, Collection<V>> sortedMap() {
            return (NavigableMap) super.sortedMap();
        }

        public Entry<K, Collection<V>> lowerEntry(K k) {
            Entry lowerEntry = sortedMap().lowerEntry(k);
            if (lowerEntry == null) {
                return null;
            }
            return wrapEntry(lowerEntry);
        }

        public K lowerKey(K k) {
            return sortedMap().lowerKey(k);
        }

        public Entry<K, Collection<V>> floorEntry(K k) {
            Entry floorEntry = sortedMap().floorEntry(k);
            if (floorEntry == null) {
                return null;
            }
            return wrapEntry(floorEntry);
        }

        public K floorKey(K k) {
            return sortedMap().floorKey(k);
        }

        public Entry<K, Collection<V>> ceilingEntry(K k) {
            Entry ceilingEntry = sortedMap().ceilingEntry(k);
            if (ceilingEntry == null) {
                return null;
            }
            return wrapEntry(ceilingEntry);
        }

        public K ceilingKey(K k) {
            return sortedMap().ceilingKey(k);
        }

        public Entry<K, Collection<V>> higherEntry(K k) {
            Entry higherEntry = sortedMap().higherEntry(k);
            if (higherEntry == null) {
                return null;
            }
            return wrapEntry(higherEntry);
        }

        public K higherKey(K k) {
            return sortedMap().higherKey(k);
        }

        public Entry<K, Collection<V>> firstEntry() {
            Entry firstEntry = sortedMap().firstEntry();
            if (firstEntry == null) {
                return null;
            }
            return wrapEntry(firstEntry);
        }

        public Entry<K, Collection<V>> lastEntry() {
            Entry lastEntry = sortedMap().lastEntry();
            if (lastEntry == null) {
                return null;
            }
            return wrapEntry(lastEntry);
        }

        public Entry<K, Collection<V>> pollFirstEntry() {
            return pollAsMapEntry(entrySet().iterator());
        }

        public Entry<K, Collection<V>> pollLastEntry() {
            return pollAsMapEntry(descendingMap().entrySet().iterator());
        }

        /* access modifiers changed from: 0000 */
        public Entry<K, Collection<V>> pollAsMapEntry(Iterator<Entry<K, Collection<V>>> it) {
            if (!it.hasNext()) {
                return null;
            }
            Entry entry = (Entry) it.next();
            Collection createCollection = AbstractMapBasedMultimap.this.createCollection();
            createCollection.addAll((Collection) entry.getValue());
            it.remove();
            return Maps.immutableEntry(entry.getKey(), AbstractMapBasedMultimap.this.unmodifiableCollectionSubclass(createCollection));
        }

        public NavigableMap<K, Collection<V>> descendingMap() {
            return new NavigableAsMap(sortedMap().descendingMap());
        }

        public NavigableSet<K> keySet() {
            return (NavigableSet) super.keySet();
        }

        /* access modifiers changed from: 0000 */
        public NavigableSet<K> createKeySet() {
            return new NavigableKeySet(sortedMap());
        }

        public NavigableSet<K> navigableKeySet() {
            return keySet();
        }

        public NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        public NavigableMap<K, Collection<V>> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        public NavigableMap<K, Collection<V>> subMap(K k, boolean z, K k2, boolean z2) {
            return new NavigableAsMap(sortedMap().subMap(k, z, k2, z2));
        }

        public NavigableMap<K, Collection<V>> headMap(K k) {
            return headMap(k, false);
        }

        public NavigableMap<K, Collection<V>> headMap(K k, boolean z) {
            return new NavigableAsMap(sortedMap().headMap(k, z));
        }

        public NavigableMap<K, Collection<V>> tailMap(K k) {
            return tailMap(k, true);
        }

        public NavigableMap<K, Collection<V>> tailMap(K k, boolean z) {
            return new NavigableAsMap(sortedMap().tailMap(k, z));
        }
    }

    class NavigableKeySet extends SortedKeySet implements NavigableSet<K> {
        NavigableKeySet(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        /* access modifiers changed from: 0000 */
        public NavigableMap<K, Collection<V>> sortedMap() {
            return (NavigableMap) super.sortedMap();
        }

        public K lower(K k) {
            return sortedMap().lowerKey(k);
        }

        public K floor(K k) {
            return sortedMap().floorKey(k);
        }

        public K ceiling(K k) {
            return sortedMap().ceilingKey(k);
        }

        public K higher(K k) {
            return sortedMap().higherKey(k);
        }

        public K pollFirst() {
            return Iterators.pollNext(iterator());
        }

        public K pollLast() {
            return Iterators.pollNext(descendingIterator());
        }

        public NavigableSet<K> descendingSet() {
            return new NavigableKeySet(sortedMap().descendingMap());
        }

        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        public NavigableSet<K> headSet(K k) {
            return headSet(k, false);
        }

        public NavigableSet<K> headSet(K k, boolean z) {
            return new NavigableKeySet(sortedMap().headMap(k, z));
        }

        public NavigableSet<K> subSet(K k, K k2) {
            return subSet(k, true, k2, false);
        }

        public NavigableSet<K> subSet(K k, boolean z, K k2, boolean z2) {
            return new NavigableKeySet(sortedMap().subMap(k, z, k2, z2));
        }

        public NavigableSet<K> tailSet(K k) {
            return tailSet(k, true);
        }

        public NavigableSet<K> tailSet(K k, boolean z) {
            return new NavigableKeySet(sortedMap().tailMap(k, z));
        }
    }

    private class RandomAccessWrappedList extends WrappedList implements RandomAccess {
        RandomAccessWrappedList(K k, @NullableDecl List<V> list, WrappedCollection wrappedCollection) {
            super(k, list, wrappedCollection);
        }
    }

    private class SortedAsMap extends AsMap implements SortedMap<K, Collection<V>> {
        @MonotonicNonNullDecl
        SortedSet<K> sortedKeySet;

        SortedAsMap(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        /* access modifiers changed from: 0000 */
        public SortedMap<K, Collection<V>> sortedMap() {
            return (SortedMap) this.submap;
        }

        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        public K firstKey() {
            return sortedMap().firstKey();
        }

        public K lastKey() {
            return sortedMap().lastKey();
        }

        public SortedMap<K, Collection<V>> headMap(K k) {
            return new SortedAsMap(sortedMap().headMap(k));
        }

        public SortedMap<K, Collection<V>> subMap(K k, K k2) {
            return new SortedAsMap(sortedMap().subMap(k, k2));
        }

        public SortedMap<K, Collection<V>> tailMap(K k) {
            return new SortedAsMap(sortedMap().tailMap(k));
        }

        public SortedSet<K> keySet() {
            SortedSet<K> sortedSet = this.sortedKeySet;
            if (sortedSet != null) {
                return sortedSet;
            }
            SortedSet<K> createKeySet = createKeySet();
            this.sortedKeySet = createKeySet;
            return createKeySet;
        }

        /* access modifiers changed from: 0000 */
        public SortedSet<K> createKeySet() {
            return new SortedKeySet(sortedMap());
        }
    }

    private class SortedKeySet extends KeySet implements SortedSet<K> {
        SortedKeySet(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        /* access modifiers changed from: 0000 */
        public SortedMap<K, Collection<V>> sortedMap() {
            return (SortedMap) super.map();
        }

        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        public K first() {
            return sortedMap().firstKey();
        }

        public SortedSet<K> headSet(K k) {
            return new SortedKeySet(sortedMap().headMap(k));
        }

        public K last() {
            return sortedMap().lastKey();
        }

        public SortedSet<K> subSet(K k, K k2) {
            return new SortedKeySet(sortedMap().subMap(k, k2));
        }

        public SortedSet<K> tailSet(K k) {
            return new SortedKeySet(sortedMap().tailMap(k));
        }
    }

    class WrappedCollection extends AbstractCollection<V> {
        @NullableDecl
        final WrappedCollection ancestor;
        @NullableDecl
        final Collection<V> ancestorDelegate;
        Collection<V> delegate;
        @NullableDecl
        final K key;

        class WrappedIterator implements Iterator<V> {
            final Iterator<V> delegateIterator;
            final Collection<V> originalDelegate = WrappedCollection.this.delegate;

            WrappedIterator() {
                this.delegateIterator = AbstractMapBasedMultimap.iteratorOrListIterator(WrappedCollection.this.delegate);
            }

            WrappedIterator(Iterator<V> it) {
                this.delegateIterator = it;
            }

            /* access modifiers changed from: 0000 */
            public void validateIterator() {
                WrappedCollection.this.refreshIfEmpty();
                if (WrappedCollection.this.delegate != this.originalDelegate) {
                    throw new ConcurrentModificationException();
                }
            }

            public boolean hasNext() {
                validateIterator();
                return this.delegateIterator.hasNext();
            }

            public V next() {
                validateIterator();
                return this.delegateIterator.next();
            }

            public void remove() {
                this.delegateIterator.remove();
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0009: INVOKE  (wrap: com.google.common.collect.AbstractMapBasedMultimap
                      0x0007: IGET  (r0v2 com.google.common.collect.AbstractMapBasedMultimap) = (wrap: com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection
                      0x0005: IGET  (r0v1 com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection) = (r1v0 'this' com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection$WrappedIterator A[THIS]) com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection.WrappedIterator.this$1 com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection) com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection.this$0 com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$210(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC in method: com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection.WrappedIterator.remove():void, dex: classes2.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                    	... 25 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 43 more
                    */
                /*
                    this = this;
                    java.util.Iterator<V> r0 = r1.delegateIterator
                    r0.remove()
                    com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection r0 = com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection.this
                    com.google.common.collect.AbstractMapBasedMultimap r0 = com.google.common.collect.AbstractMapBasedMultimap.this
                    
                    // error: 0x0009: INVOKE  (r0 I:com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$210(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC
                    com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection r0 = com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection.this
                    r0.removeIfEmpty()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection.WrappedIterator.remove():void");
            }

            /* access modifiers changed from: 0000 */
            public Iterator<V> getDelegateIterator() {
                validateIterator();
                return this.delegateIterator;
            }
        }

        WrappedCollection(K k, @NullableDecl Collection<V> collection, WrappedCollection wrappedCollection) {
            Collection<V> collection2;
            this.key = k;
            this.delegate = collection;
            this.ancestor = wrappedCollection;
            if (wrappedCollection == null) {
                collection2 = null;
            } else {
                collection2 = wrappedCollection.getDelegate();
            }
            this.ancestorDelegate = collection2;
        }

        /* access modifiers changed from: 0000 */
        public void refreshIfEmpty() {
            WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.refreshIfEmpty();
                if (this.ancestor.getDelegate() != this.ancestorDelegate) {
                    throw new ConcurrentModificationException();
                }
            } else if (this.delegate.isEmpty()) {
                Collection<V> collection = (Collection) AbstractMapBasedMultimap.this.map.get(this.key);
                if (collection != null) {
                    this.delegate = collection;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void removeIfEmpty() {
            WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.removeIfEmpty();
            } else if (this.delegate.isEmpty()) {
                AbstractMapBasedMultimap.this.map.remove(this.key);
            }
        }

        /* access modifiers changed from: 0000 */
        public K getKey() {
            return this.key;
        }

        /* access modifiers changed from: 0000 */
        public void addToMap() {
            WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.addToMap();
            } else {
                AbstractMapBasedMultimap.this.map.put(this.key, this.delegate);
            }
        }

        public int size() {
            refreshIfEmpty();
            return this.delegate.size();
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            refreshIfEmpty();
            return this.delegate.equals(obj);
        }

        public int hashCode() {
            refreshIfEmpty();
            return this.delegate.hashCode();
        }

        public String toString() {
            refreshIfEmpty();
            return this.delegate.toString();
        }

        /* access modifiers changed from: 0000 */
        public Collection<V> getDelegate() {
            return this.delegate;
        }

        public Iterator<V> iterator() {
            refreshIfEmpty();
            return new WrappedIterator();
        }

        public boolean add(V v) {
            refreshIfEmpty();
            boolean isEmpty = this.delegate.isEmpty();
            boolean add = this.delegate.add(v);
            if (add) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: INVOKE  (wrap: com.google.common.collect.AbstractMapBasedMultimap
                      0x0011: IGET  (r1v1 com.google.common.collect.AbstractMapBasedMultimap) = (r2v0 'this' com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection A[THIS]) com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection.this$0 com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$208(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC in method: com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection.add(java.lang.Object):boolean, dex: classes2.dex
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
                    r2.refreshIfEmpty()
                    java.util.Collection<V> r0 = r2.delegate
                    boolean r0 = r0.isEmpty()
                    java.util.Collection<V> r1 = r2.delegate
                    boolean r3 = r1.add(r3)
                    if (r3 == 0) goto L_0x001b
                    com.google.common.collect.AbstractMapBasedMultimap r1 = com.google.common.collect.AbstractMapBasedMultimap.this
                    
                    // error: 0x0013: INVOKE  (r1 I:com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$208(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC
                    if (r0 == 0) goto L_0x001b
                    r2.addToMap()
                L_0x001b:
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection.add(java.lang.Object):boolean");
            }

            /* access modifiers changed from: 0000 */
            public WrappedCollection getAncestor() {
                return this.ancestor;
            }

            public boolean addAll(Collection<? extends V> collection) {
                if (collection.isEmpty()) {
                    return false;
                }
                int size = size();
                boolean addAll = this.delegate.addAll(collection);
                if (addAll) {
                    int size2 = this.delegate.size();
                    AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                    abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize + (size2 - size);
                    if (size == 0) {
                        addToMap();
                    }
                }
                return addAll;
            }

            public boolean contains(Object obj) {
                refreshIfEmpty();
                return this.delegate.contains(obj);
            }

            public boolean containsAll(Collection<?> collection) {
                refreshIfEmpty();
                return this.delegate.containsAll(collection);
            }

            public void clear() {
                int size = size();
                if (size != 0) {
                    this.delegate.clear();
                    AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                    abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize - size;
                    removeIfEmpty();
                }
            }

            public boolean remove(Object obj) {
                refreshIfEmpty();
                boolean remove = this.delegate.remove(obj);
                if (remove) {
                    
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000d: INVOKE  (wrap: com.google.common.collect.AbstractMapBasedMultimap
                          0x000b: IGET  (r0v1 com.google.common.collect.AbstractMapBasedMultimap) = (r1v0 'this' com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection A[THIS]) com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection.this$0 com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$210(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC in method: com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection.remove(java.lang.Object):boolean, dex: classes2.dex
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
                        r1.refreshIfEmpty()
                        java.util.Collection<V> r0 = r1.delegate
                        boolean r2 = r0.remove(r2)
                        if (r2 == 0) goto L_0x0013
                        com.google.common.collect.AbstractMapBasedMultimap r0 = com.google.common.collect.AbstractMapBasedMultimap.this
                        
                        // error: 0x000d: INVOKE  (r0 I:com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$210(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC
                        r1.removeIfEmpty()
                    L_0x0013:
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection.remove(java.lang.Object):boolean");
                }

                public boolean removeAll(Collection<?> collection) {
                    if (collection.isEmpty()) {
                        return false;
                    }
                    int size = size();
                    boolean removeAll = this.delegate.removeAll(collection);
                    if (removeAll) {
                        int size2 = this.delegate.size();
                        AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                        abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize + (size2 - size);
                        removeIfEmpty();
                    }
                    return removeAll;
                }

                public boolean retainAll(Collection<?> collection) {
                    Preconditions.checkNotNull(collection);
                    int size = size();
                    boolean retainAll = this.delegate.retainAll(collection);
                    if (retainAll) {
                        int size2 = this.delegate.size();
                        AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                        abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize + (size2 - size);
                        removeIfEmpty();
                    }
                    return retainAll;
                }
            }

            class WrappedList extends WrappedCollection implements List<V> {

                private class WrappedListIterator extends WrappedIterator implements ListIterator<V> {
                    WrappedListIterator() {
                        super();
                    }

                    public WrappedListIterator(int i) {
                        super(WrappedList.this.getListDelegate().listIterator(i));
                    }

                    private ListIterator<V> getDelegateListIterator() {
                        return (ListIterator) getDelegateIterator();
                    }

                    public boolean hasPrevious() {
                        return getDelegateListIterator().hasPrevious();
                    }

                    public V previous() {
                        return getDelegateListIterator().previous();
                    }

                    public int nextIndex() {
                        return getDelegateListIterator().nextIndex();
                    }

                    public int previousIndex() {
                        return getDelegateListIterator().previousIndex();
                    }

                    public void set(V v) {
                        getDelegateListIterator().set(v);
                    }

                    public void add(V v) {
                        boolean isEmpty = WrappedList.this.isEmpty();
                        getDelegateListIterator().add(v);
                        
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0011: INVOKE  (wrap: com.google.common.collect.AbstractMapBasedMultimap
                              0x000f: IGET  (r3v2 com.google.common.collect.AbstractMapBasedMultimap) = (wrap: com.google.common.collect.AbstractMapBasedMultimap$WrappedList
                              0x000d: IGET  (r3v1 com.google.common.collect.AbstractMapBasedMultimap$WrappedList) = (r2v0 'this' com.google.common.collect.AbstractMapBasedMultimap$WrappedList$WrappedListIterator A[THIS]) com.google.common.collect.AbstractMapBasedMultimap.WrappedList.WrappedListIterator.this$1 com.google.common.collect.AbstractMapBasedMultimap$WrappedList) com.google.common.collect.AbstractMapBasedMultimap.WrappedList.this$0 com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$208(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC in method: com.google.common.collect.AbstractMapBasedMultimap.WrappedList.WrappedListIterator.add(java.lang.Object):void, dex: classes2.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                            	... 25 more
                            Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                            	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                            	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                            	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                            	at java.base/java.lang.Class.forName0(Native Method)
                            	at java.base/java.lang.Class.forName(Unknown Source)
                            	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                            	... 43 more
                            */
                        /*
                            this = this;
                            com.google.common.collect.AbstractMapBasedMultimap$WrappedList r0 = com.google.common.collect.AbstractMapBasedMultimap.WrappedList.this
                            boolean r0 = r0.isEmpty()
                            java.util.ListIterator r1 = r2.getDelegateListIterator()
                            r1.add(r3)
                            com.google.common.collect.AbstractMapBasedMultimap$WrappedList r3 = com.google.common.collect.AbstractMapBasedMultimap.WrappedList.this
                            com.google.common.collect.AbstractMapBasedMultimap r3 = com.google.common.collect.AbstractMapBasedMultimap.this
                            
                            // error: 0x0011: INVOKE  (r3 I:com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$208(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC
                            if (r0 == 0) goto L_0x001b
                            com.google.common.collect.AbstractMapBasedMultimap$WrappedList r3 = com.google.common.collect.AbstractMapBasedMultimap.WrappedList.this
                            r3.addToMap()
                        L_0x001b:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.WrappedList.WrappedListIterator.add(java.lang.Object):void");
                    }
                }

                WrappedList(K k, @NullableDecl List<V> list, WrappedCollection wrappedCollection) {
                    super(k, list, wrappedCollection);
                }

                /* access modifiers changed from: 0000 */
                public List<V> getListDelegate() {
                    return (List) getDelegate();
                }

                public boolean addAll(int i, Collection<? extends V> collection) {
                    if (collection.isEmpty()) {
                        return false;
                    }
                    int size = size();
                    boolean addAll = getListDelegate().addAll(i, collection);
                    if (addAll) {
                        int size2 = getDelegate().size();
                        AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                        abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize + (size2 - size);
                        if (size == 0) {
                            addToMap();
                        }
                    }
                    return addAll;
                }

                public V get(int i) {
                    refreshIfEmpty();
                    return getListDelegate().get(i);
                }

                public V set(int i, V v) {
                    refreshIfEmpty();
                    return getListDelegate().set(i, v);
                }

                public void add(int i, V v) {
                    refreshIfEmpty();
                    boolean isEmpty = getDelegate().isEmpty();
                    getListDelegate().add(i, v);
                    
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0014: INVOKE  (wrap: com.google.common.collect.AbstractMapBasedMultimap
                          0x0012: IGET  (r3v1 com.google.common.collect.AbstractMapBasedMultimap) = (r2v0 'this' com.google.common.collect.AbstractMapBasedMultimap$WrappedList A[THIS]) com.google.common.collect.AbstractMapBasedMultimap.WrappedList.this$0 com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$208(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC in method: com.google.common.collect.AbstractMapBasedMultimap.WrappedList.add(int, java.lang.Object):void, dex: classes2.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                        	... 22 more
                        Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                        	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                        	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.Class.forName0(Native Method)
                        	at java.base/java.lang.Class.forName(Unknown Source)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                        	... 40 more
                        */
                    /*
                        this = this;
                        r2.refreshIfEmpty()
                        java.util.Collection r0 = r2.getDelegate()
                        boolean r0 = r0.isEmpty()
                        java.util.List r1 = r2.getListDelegate()
                        r1.add(r3, r4)
                        com.google.common.collect.AbstractMapBasedMultimap r3 = com.google.common.collect.AbstractMapBasedMultimap.this
                        
                        // error: 0x0014: INVOKE  (r3 I:com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$208(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC
                        if (r0 == 0) goto L_0x001c
                        r2.addToMap()
                    L_0x001c:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.WrappedList.add(int, java.lang.Object):void");
                }

                public V remove(int i) {
                    refreshIfEmpty();
                    V remove = getListDelegate().remove(i);
                    
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000d: INVOKE  (wrap: com.google.common.collect.AbstractMapBasedMultimap
                          0x000b: IGET  (r0v1 com.google.common.collect.AbstractMapBasedMultimap) = (r1v0 'this' com.google.common.collect.AbstractMapBasedMultimap$WrappedList A[THIS]) com.google.common.collect.AbstractMapBasedMultimap.WrappedList.this$0 com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$210(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC in method: com.google.common.collect.AbstractMapBasedMultimap.WrappedList.remove(int):V, dex: classes2.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
                        	... 22 more
                        Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                        	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                        	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                        	at java.base/java.lang.Class.forName0(Native Method)
                        	at java.base/java.lang.Class.forName(Unknown Source)
                        	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                        	... 40 more
                        */
                    /*
                        this = this;
                        r1.refreshIfEmpty()
                        java.util.List r0 = r1.getListDelegate()
                        java.lang.Object r2 = r0.remove(r2)
                        com.google.common.collect.AbstractMapBasedMultimap r0 = com.google.common.collect.AbstractMapBasedMultimap.this
                        
                        // error: 0x000d: INVOKE  (r0 I:com.google.common.collect.AbstractMapBasedMultimap) com.google.common.collect.AbstractMapBasedMultimap.access$210(com.google.common.collect.AbstractMapBasedMultimap):int type: STATIC
                        r1.removeIfEmpty()
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.WrappedList.remove(int):java.lang.Object");
                }

                public int indexOf(Object obj) {
                    refreshIfEmpty();
                    return getListDelegate().indexOf(obj);
                }

                public int lastIndexOf(Object obj) {
                    refreshIfEmpty();
                    return getListDelegate().lastIndexOf(obj);
                }

                public ListIterator<V> listIterator() {
                    refreshIfEmpty();
                    return new WrappedListIterator();
                }

                public ListIterator<V> listIterator(int i) {
                    refreshIfEmpty();
                    return new WrappedListIterator(i);
                }

                public List<V> subList(int i, int i2) {
                    refreshIfEmpty();
                    return AbstractMapBasedMultimap.this.wrapList(getKey(), getListDelegate().subList(i, i2), getAncestor() == null ? this : getAncestor());
                }
            }

            class WrappedNavigableSet extends WrappedSortedSet implements NavigableSet<V> {
                WrappedNavigableSet(K k, @NullableDecl NavigableSet<V> navigableSet, WrappedCollection wrappedCollection) {
                    super(k, navigableSet, wrappedCollection);
                }

                /* access modifiers changed from: 0000 */
                public NavigableSet<V> getSortedSetDelegate() {
                    return (NavigableSet) super.getSortedSetDelegate();
                }

                public V lower(V v) {
                    return getSortedSetDelegate().lower(v);
                }

                public V floor(V v) {
                    return getSortedSetDelegate().floor(v);
                }

                public V ceiling(V v) {
                    return getSortedSetDelegate().ceiling(v);
                }

                public V higher(V v) {
                    return getSortedSetDelegate().higher(v);
                }

                public V pollFirst() {
                    return Iterators.pollNext(iterator());
                }

                public V pollLast() {
                    return Iterators.pollNext(descendingIterator());
                }

                private NavigableSet<V> wrap(NavigableSet<V> navigableSet) {
                    return new WrappedNavigableSet(this.key, navigableSet, getAncestor() == null ? this : getAncestor());
                }

                public NavigableSet<V> descendingSet() {
                    return wrap(getSortedSetDelegate().descendingSet());
                }

                public Iterator<V> descendingIterator() {
                    return new WrappedIterator(getSortedSetDelegate().descendingIterator());
                }

                public NavigableSet<V> subSet(V v, boolean z, V v2, boolean z2) {
                    return wrap(getSortedSetDelegate().subSet(v, z, v2, z2));
                }

                public NavigableSet<V> headSet(V v, boolean z) {
                    return wrap(getSortedSetDelegate().headSet(v, z));
                }

                public NavigableSet<V> tailSet(V v, boolean z) {
                    return wrap(getSortedSetDelegate().tailSet(v, z));
                }
            }

            class WrappedSet extends WrappedCollection implements Set<V> {
                WrappedSet(K k, @NullableDecl Set<V> set) {
                    super(k, set, null);
                }

                public boolean removeAll(Collection<?> collection) {
                    if (collection.isEmpty()) {
                        return false;
                    }
                    int size = size();
                    boolean removeAllImpl = Sets.removeAllImpl((Set) this.delegate, collection);
                    if (removeAllImpl) {
                        int size2 = this.delegate.size();
                        AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                        abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize + (size2 - size);
                        removeIfEmpty();
                    }
                    return removeAllImpl;
                }
            }

            class WrappedSortedSet extends WrappedCollection implements SortedSet<V> {
                WrappedSortedSet(K k, @NullableDecl SortedSet<V> sortedSet, WrappedCollection wrappedCollection) {
                    super(k, sortedSet, wrappedCollection);
                }

                /* access modifiers changed from: 0000 */
                public SortedSet<V> getSortedSetDelegate() {
                    return (SortedSet) getDelegate();
                }

                public Comparator<? super V> comparator() {
                    return getSortedSetDelegate().comparator();
                }

                public V first() {
                    refreshIfEmpty();
                    return getSortedSetDelegate().first();
                }

                public V last() {
                    refreshIfEmpty();
                    return getSortedSetDelegate().last();
                }

                public SortedSet<V> headSet(V v) {
                    refreshIfEmpty();
                    return new WrappedSortedSet(getKey(), getSortedSetDelegate().headSet(v), getAncestor() == null ? this : getAncestor());
                }

                public SortedSet<V> subSet(V v, V v2) {
                    refreshIfEmpty();
                    return new WrappedSortedSet(getKey(), getSortedSetDelegate().subSet(v, v2), getAncestor() == null ? this : getAncestor());
                }

                public SortedSet<V> tailSet(V v) {
                    refreshIfEmpty();
                    return new WrappedSortedSet(getKey(), getSortedSetDelegate().tailSet(v), getAncestor() == null ? this : getAncestor());
                }
            }

            /* access modifiers changed from: 0000 */
            public abstract Collection<V> createCollection();

            protected AbstractMapBasedMultimap(Map<K, Collection<V>> map2) {
                Preconditions.checkArgument(map2.isEmpty());
                this.map = map2;
            }

            /* access modifiers changed from: 0000 */
            public final void setMap(Map<K, Collection<V>> map2) {
                this.map = map2;
                this.totalSize = 0;
                for (Collection collection : map2.values()) {
                    Preconditions.checkArgument(!collection.isEmpty());
                    this.totalSize += collection.size();
                }
            }

            /* access modifiers changed from: 0000 */
            public Collection<V> createUnmodifiableEmptyCollection() {
                return unmodifiableCollectionSubclass(createCollection());
            }

            /* access modifiers changed from: 0000 */
            public Collection<V> createCollection(@NullableDecl K k) {
                return createCollection();
            }

            /* access modifiers changed from: 0000 */
            public Map<K, Collection<V>> backingMap() {
                return this.map;
            }

            public int size() {
                return this.totalSize;
            }

            public boolean containsKey(@NullableDecl Object obj) {
                return this.map.containsKey(obj);
            }

            public boolean put(@NullableDecl K k, @NullableDecl V v) {
                Collection collection = (Collection) this.map.get(k);
                if (collection == null) {
                    Collection createCollection = createCollection(k);
                    if (createCollection.add(v)) {
                        this.totalSize++;
                        this.map.put(k, createCollection);
                        return true;
                    }
                    throw new AssertionError("New Collection violated the Collection spec");
                } else if (!collection.add(v)) {
                    return false;
                } else {
                    this.totalSize++;
                    return true;
                }
            }

            private Collection<V> getOrCreateCollection(@NullableDecl K k) {
                Collection<V> collection = (Collection) this.map.get(k);
                if (collection != null) {
                    return collection;
                }
                Collection<V> createCollection = createCollection(k);
                this.map.put(k, createCollection);
                return createCollection;
            }

            public Collection<V> replaceValues(@NullableDecl K k, Iterable<? extends V> iterable) {
                Iterator it = iterable.iterator();
                if (!it.hasNext()) {
                    return removeAll(k);
                }
                Collection orCreateCollection = getOrCreateCollection(k);
                Collection createCollection = createCollection();
                createCollection.addAll(orCreateCollection);
                this.totalSize -= orCreateCollection.size();
                orCreateCollection.clear();
                while (it.hasNext()) {
                    if (orCreateCollection.add(it.next())) {
                        this.totalSize++;
                    }
                }
                return unmodifiableCollectionSubclass(createCollection);
            }

            public Collection<V> removeAll(@NullableDecl Object obj) {
                Collection collection = (Collection) this.map.remove(obj);
                if (collection == null) {
                    return createUnmodifiableEmptyCollection();
                }
                Collection createCollection = createCollection();
                createCollection.addAll(collection);
                this.totalSize -= collection.size();
                collection.clear();
                return unmodifiableCollectionSubclass(createCollection);
            }

            /* access modifiers changed from: 0000 */
            public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
                return Collections.unmodifiableCollection(collection);
            }

            public void clear() {
                for (Collection clear : this.map.values()) {
                    clear.clear();
                }
                this.map.clear();
                this.totalSize = 0;
            }

            public Collection<V> get(@NullableDecl K k) {
                Collection collection = (Collection) this.map.get(k);
                if (collection == null) {
                    collection = createCollection(k);
                }
                return wrapCollection(k, collection);
            }

            /* access modifiers changed from: 0000 */
            public Collection<V> wrapCollection(@NullableDecl K k, Collection<V> collection) {
                return new WrappedCollection(k, collection, null);
            }

            /* access modifiers changed from: 0000 */
            public final List<V> wrapList(@NullableDecl K k, List<V> list, @NullableDecl WrappedCollection wrappedCollection) {
                return list instanceof RandomAccess ? new RandomAccessWrappedList(k, list, wrappedCollection) : new WrappedList(k, list, wrappedCollection);
            }

            /* access modifiers changed from: private */
            public static <E> Iterator<E> iteratorOrListIterator(Collection<E> collection) {
                if (collection instanceof List) {
                    return ((List) collection).listIterator();
                }
                return collection.iterator();
            }

            /* access modifiers changed from: 0000 */
            public Set<K> createKeySet() {
                return new KeySet(this.map);
            }

            /* access modifiers changed from: 0000 */
            public final Set<K> createMaybeNavigableKeySet() {
                Map<K, Collection<V>> map2 = this.map;
                if (map2 instanceof NavigableMap) {
                    return new NavigableKeySet((NavigableMap) map2);
                }
                if (map2 instanceof SortedMap) {
                    return new SortedKeySet((SortedMap) map2);
                }
                return new KeySet(map2);
            }

            /* access modifiers changed from: private */
            public void removeValuesForKey(Object obj) {
                Collection collection = (Collection) Maps.safeRemove(this.map, obj);
                if (collection != null) {
                    int size = collection.size();
                    collection.clear();
                    this.totalSize -= size;
                }
            }

            public Collection<V> values() {
                return super.values();
            }

            /* access modifiers changed from: 0000 */
            public Collection<V> createValues() {
                return new Values();
            }

            /* access modifiers changed from: 0000 */
            public Iterator<V> valueIterator() {
                return new Itr<V>() {
                    /* access modifiers changed from: 0000 */
                    public V output(K k, V v) {
                        return v;
                    }
                };
            }

            /* access modifiers changed from: 0000 */
            public Multiset<K> createKeys() {
                return new Keys(this);
            }

            public Collection<Entry<K, V>> entries() {
                return super.entries();
            }

            /* access modifiers changed from: 0000 */
            public Collection<Entry<K, V>> createEntries() {
                if (this instanceof SetMultimap) {
                    return new EntrySet();
                }
                return new Entries();
            }

            /* access modifiers changed from: 0000 */
            public Iterator<Entry<K, V>> entryIterator() {
                return new Itr<Entry<K, V>>() {
                    /* access modifiers changed from: 0000 */
                    public Entry<K, V> output(K k, V v) {
                        return Maps.immutableEntry(k, v);
                    }
                };
            }

            /* access modifiers changed from: 0000 */
            public Map<K, Collection<V>> createAsMap() {
                return new AsMap(this.map);
            }

            /* access modifiers changed from: 0000 */
            public final Map<K, Collection<V>> createMaybeNavigableAsMap() {
                Map<K, Collection<V>> map2 = this.map;
                if (map2 instanceof NavigableMap) {
                    return new NavigableAsMap((NavigableMap) map2);
                }
                if (map2 instanceof SortedMap) {
                    return new SortedAsMap((SortedMap) map2);
                }
                return new AsMap(map2);
            }
        }
