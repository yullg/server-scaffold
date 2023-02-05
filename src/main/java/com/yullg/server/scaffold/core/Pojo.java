package com.yullg.server.scaffold.core;

import java.util.*;

public final class Pojo {

    private final Map<String, Object> map;

    public Pojo() {
        this.map = new HashMap<>();
    }

    public Pojo(Pojo pojo) {
        this.map = new HashMap<>(pojo.map);
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public int size() {
        return this.map.size();
    }

    public Set<String> names() {
        return this.map.keySet();
    }

    public Collection<Object> values() {
        return this.map.values();
    }

    public boolean containsName(String name) {
        return this.map.containsKey(name);
    }

    public void remove(String name) {
        this.map.remove(name);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) this.map.get(name);
    }

    public <T> void set(String name, T value) {
        this.map.put(name, value);
    }

    public Map<String, Object> unwrap() {
        return Collections.unmodifiableMap(this.map);
    }

    @Override
    public int hashCode() {
        return this.map.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pojo other = (Pojo) obj;
        return this.map.equals(other.map);
    }

    @Override
    public String toString() {
        return "Pojo " + map;
    }

}