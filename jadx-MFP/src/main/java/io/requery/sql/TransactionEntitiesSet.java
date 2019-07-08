package io.requery.sql;

import io.requery.EntityCache;
import io.requery.meta.Type;
import io.requery.proxy.EntityProxy;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

class TransactionEntitiesSet extends LinkedHashSet<EntityProxy<?>> {
    private final EntityCache cache;
    private final Set<Type<?>> types = new HashSet();

    TransactionEntitiesSet(EntityCache entityCache) {
        this.cache = entityCache;
    }

    public boolean add(EntityProxy<?> entityProxy) {
        if (!super.add(entityProxy)) {
            return false;
        }
        this.types.add(entityProxy.type());
        return true;
    }

    public void clear() {
        super.clear();
        this.types.clear();
    }

    /* access modifiers changed from: 0000 */
    public void clearAndInvalidate() {
        Iterator it = iterator();
        while (it.hasNext()) {
            EntityProxy entityProxy = (EntityProxy) it.next();
            entityProxy.unlink();
            Object key = entityProxy.key();
            if (key != null) {
                this.cache.invalidate(entityProxy.type().getClassType(), key);
            }
        }
        clear();
    }

    /* access modifiers changed from: 0000 */
    public Set<Type<?>> types() {
        return this.types;
    }
}
