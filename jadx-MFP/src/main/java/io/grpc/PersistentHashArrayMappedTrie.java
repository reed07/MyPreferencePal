package io.grpc;

final class PersistentHashArrayMappedTrie<K, V> {
    private final Node<K, V> root;

    interface Node<K, V> {
        V get(K k, int i, int i2);
    }

    PersistentHashArrayMappedTrie() {
        this(null);
    }

    private PersistentHashArrayMappedTrie(Node<K, V> node) {
        this.root = node;
    }

    public V get(K k) {
        Node<K, V> node = this.root;
        if (node == null) {
            return null;
        }
        return node.get(k, k.hashCode(), 0);
    }
}
