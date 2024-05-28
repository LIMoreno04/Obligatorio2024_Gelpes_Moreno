package uy.edu.um.adt.hash;

import java.util.Objects;

public class ValueStash<K,V> {
    private K key;
    private V value;

    public ValueStash(){
        this.key = null;
        this.value = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueStash<?, ?> that = (ValueStash<?, ?>) o;
        return Objects.equals(key, that.key);
    }

}
