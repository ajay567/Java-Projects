
public class HashEntry<K,V> {
    private K key;
    private V value;
    
    public HashEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public boolean matchesKey(K key) {
        return this.key.equals(key);
    }
    
    public V getValue() {
        return this.value;
    }
    
    public K getKey() {
        return this.key;
    }
    
    public void setValue(V value) {
        this.value = value;
    }
}
