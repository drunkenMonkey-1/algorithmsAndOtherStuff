package fhv.at.mwi.hash_table;

import java.util.*;

public class MHashTable<K, V> implements Map<K, V> {

    private List _values;
    private Object _dummyElement;
    private int _numOfElements;
    private int _currentMaxSize;
    private int jumpOffset;
    private int _collisions = 0;
    private int jumpMultiplicator;
    private int _initHash;
    private AltHash _altHash;
    private CollisionStrategy _strategy;

    /**
     *
     */
    public MHashTable(){
        _dummyElement = new Object();
        _numOfElements = 0;
        _currentMaxSize = 16;
        jumpOffset = 0;
        _values = new ArrayList<V>(Collections.nCopies(_currentMaxSize, null));
        _strategy = CollisionStrategy.DOUBLE_HASHING;
        //Default must be 1 or sth.
        _altHash = hashValue -> 1;
    }

    /**
     *
     * @param c
     */
    public MHashTable(CollisionStrategy c){
        this();
        _strategy = c;
        setJumpOffsets();

    }

    @Override
    public int size() {
        return _numOfElements;
    }

    @Override
    public boolean isEmpty() {
        return (_numOfElements == 0);
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        _initHash = key.hashCode();
        int insertIndex = getNextFreePos(0, _dummyElement);
        _values.set(insertIndex, value);
        _numOfElements++;
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private void setJumpOffsets(){
        switch(_strategy){
            case LINEAR:
                jumpMultiplicator = 1;
                break;
            case QUADRATIC:
                jumpMultiplicator = 2;
                break;
            case DOUBLE_HASHING:
                jumpMultiplicator = 1;
                _altHash = hashValue -> (1 + getPosByHash(hashValue, _currentMaxSize-1));
                break;
        }
    }

    private int getPosByHash(int hashCode, int m){
        return Math.abs(hashCode) % m;
    }

    private int getNextFreePos(int offset, Object dummy){
        System.out.println("Jumping by " + offset);
        int realPos = getPosByHash(_initHash + offset, _currentMaxSize);
        if(_values.get(realPos) == null || _values.get(realPos) == dummy){
            _collisions = 0;
            return realPos;
        } else {
            _collisions++;
            jumpOffset = power(_collisions, jumpMultiplicator) * _altHash.getPosBySHash(_initHash);
            return getNextFreePos(jumpOffset, dummy);
        }
    }

    private int power(int base, int expo){
        int result = 1;
        for(int i = 0; i < expo; i++){
            result *= base;
        }
        return result;
    }

    @Override
    public String toString(){
        return "";
    }

    public void debugList(){
        Iterator i = _values.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
        System.out.println("-- debug end --");
    }
}
