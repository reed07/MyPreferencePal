package net.minidev.json.parser;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FakeContainerFactory implements ContainerFactory {
    public FackList list;
    public FackMap map;

    static class FackList extends ArrayList<Object> {
        public boolean add(Object obj) {
            return false;
        }

        public Object get(int i) {
            return null;
        }

        public int size() {
            return 0;
        }

        FackList() {
        }
    }

    static class FackMap extends AbstractMap<String, Object> {
        public Set<Entry<String, Object>> entrySet() {
            return null;
        }

        public Object put(String str, Object obj) {
            return null;
        }

        FackMap() {
        }
    }

    public List<Object> createArrayContainer() {
        if (this.list == null) {
            this.list = new FackList();
        }
        return this.list;
    }

    public Map<String, Object> createObjectContainer() {
        if (this.map == null) {
            this.map = new FackMap();
        }
        return this.map;
    }
}
