package io.requery.proxy;

interface EntityStateListener {
    public static final EntityStateListener EMPTY = new EntityStateListener() {
        public void postInsert() {
        }

        public void postLoad() {
        }

        public void postUpdate() {
        }

        public void preInsert() {
        }

        public void preUpdate() {
        }
    };

    void postInsert();

    void postLoad();

    void postUpdate();

    void preInsert();

    void preUpdate();
}
