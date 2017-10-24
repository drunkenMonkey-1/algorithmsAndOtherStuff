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
    private float _resizeTrigger = 0.75f;
    private AltHash _altHash;
    private CollisionStrategy _strategy;

    /**
     *
     */
    public MHashTable(){
        _dummyElement = new Object();
        _numOfElements = 0;
        _currentMaxSize = 13;       // first prime
        jumpOffset = 0;
        _values = new ArrayList<TableEntry<K, V>>(Collections.nCopies(_currentMaxSize, null));
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
        _initHash = key.hashCode();
        int insertIndex = getNextFreePos(0, null);
        System.out.println("Should be at: " + insertIndex);
        return null;
    }

    @Override
    public V put(K key, V value) {
        float filledBy = (float) _numOfElements / _currentMaxSize;
        if(filledBy >= _resizeTrigger){
            resizeDataStructure();
        }
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

    private boolean isPrime(int num){
        if((num % 2) == 0 || (num % 3) == 0){
            return false;
        }
        for(int i = 5; i < Math.sqrt(num); i+=2){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * find the next bigger prime number starting from the passed parameter
     * @param start Start search at this number
     * @return the next prime number after start
     */
    private int findNextPrime(int start){
        if(start % 2 == 0){
            start+=1;
        }
        if(isPrime(start)){
            return start;
        }
        for(int i = 2; i <= start * 2; i++){
            if(isPrime(start + i)){
                return (start + i);
            }
        }
        return findNextPrime((start * 2) +1 );
    }

    private void resizeDataStructure(){
        _currentMaxSize = findNextPrime(_currentMaxSize * 2);
        List tempList = new ArrayList<V>(Collections.nCopies(_currentMaxSize, null));
        Iterator i = _values.iterator();
        int iteratorCount = 0;
        while(i.hasNext()){
            tempList.set(iteratorCount, i.next());
            iteratorCount++;
        }
        _values = tempList;

    }

    private int getPosByHash(int hashCode, int m){
        return Math.abs(hashCode) % m;
    }

    private int getNextFreePos(int offset, Object dummy){
        int realPos = getPosByHash(_initHash + offset, _currentMaxSize);
        System.out.println("Jumping by " + offset + " from " + realPos);
        if(_values.get(realPos) == null || _values.get(realPos) == dummy){
            _collisions = 0;
            System.out.println(" - - ");
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
        return "Current size: " + _currentMaxSize + " | n. of elements: " + _numOfElements;
    }

    public void debugList(){
        Iterator i = _values.iterator();
        while(i.hasNext()){
            System.out.printf("%s|", i.next());
        }
        System.out.println("-- debug end --");
    }

}
