package fhv.at.mwi.hash_table;

import java.util.*;

public class MHashTable<K, V> implements Map<K, V> {

    private ArrayList<TableEntry<K, V>> _values;
    private TableEntry<K, V> _dummyElement;
    private int _numOfElements;
    private int _currentMaxSize;
    private int jumpOffset;
    private int _collisions = 0;
    private int jumpMultiplicator;
    private int _initHash;
    private int _collisionCount = 0;
    private float _resizeTrigger = 0.75f;
    private AltHash _altHash;
    private CollisionStrategy _strategy;

    /**
     * Simple Hash table with initial size of 13 and default collision strategy "DOUBLE_HASHING"
     * For a user-defined collision strategy, please use the appropriate constructor
     */
    public MHashTable(){
        _dummyElement = new TableEntry(null, null);
        _numOfElements = 0;
        _currentMaxSize = 13;       // first prime
        jumpOffset = 0;
        _values = new ArrayList<>(Collections.nCopies(_currentMaxSize, null));
        _strategy = CollisionStrategy.DOUBLE_HASHING;
        //Default must be 1 or sth.
        _altHash = hashValue -> 1;
    }

    /**
     * Init hash list with user-defined collision strategy
     * @param c Choose a collision strategy (LINEAR, QUADRATIC, DOUBLE_HASHING)
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
        int sIndex = getPos((K)key);
        if(_values.get(sIndex) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    /**
     * Only for searching Elements by key
     * @param key Key to be extracted
     * @return Position of key in table
     */
    private int getPos(K key){
        _initHash = key.hashCode();
        int insertIndex = getNextFreePos(0, new TableEntry(key, null), _values);
        return insertIndex;
    }

    /**
     * Get Value by key
     * @param key Key you'd like the get the value of
     * @return Value of the data associated with the key
     */
    @Override
    public V get(Object key) {
        int insertIndex = getPos((K)key);
        TableEntry<K, V> elem = _values.get(insertIndex);
        if(elem != null) {
            return elem.getValue();
        }
        return null;
    }

    /**
     * Insert key and value to the hash map
     * @param key The key, that the value is associated with
     * @param value The value of the key
     * @return The value of the key
     */
    @Override
    public V put(K key, V value) {
        TableEntry entry = new TableEntry<K, V>(key, value);
        float filledBy = (float) _numOfElements / _currentMaxSize;
        if(filledBy >= _resizeTrigger){
            resizeDataStructure();
        }
        _initHash = key.hashCode();
        int insertIndex = getNextFreePos(0, _dummyElement,  _values);
        _values.set(insertIndex, entry);
        _numOfElements++;
        return null;
    }

    /**
     * Remove an element by key
     * @param key The key of the element you'd like to remove
     * @return The remove value
     */
    @Override
    public V remove(Object key) {
        int removeIndex = getPos((K)key);
        V tempVal = _values.get(removeIndex).getValue();
        _values.set(removeIndex, _dummyElement);
        _numOfElements--;
        return tempVal;
    }


    /**
     * Add a map to the MHashTable
     * @param m The map you would like to add
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for(Map.Entry<? extends K, ? extends V> mapentry : m.entrySet()){
            this.put(mapentry.getKey(), mapentry.getValue());
        }
    }

    /**
     * Clear under lying data structure and reset stats.
     */
    @Override
    public void clear() {
        _values.clear();
        _currentMaxSize = 13;
        _numOfElements = 0;
        _collisionCount = 0;
        _values = new ArrayList<>(Collections.nCopies(_currentMaxSize, null));

    }



    @Override
    public Set<K> keySet() { return null; }

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

    /**
     * Resize the data structure, so more elements can be added. Therefore
     * a prime number is calculated, that is twice as large as the previous size.
     * Every already existing Element needs to be recalculated for the new size
     * and will then be inserted at it's correct position in the new data structure
     */
    private void resizeDataStructure(){
        System.out.println("Resizing data structure");
        _currentMaxSize = findNextPrime(_currentMaxSize * 2);
        ArrayList tempList = new ArrayList<TableEntry<K, V>>(Collections.nCopies(_currentMaxSize, null));
        for(int i = 0; i < _values.size(); i++){
            if(_values.get(i) != null){
                _initHash = _values.get(i).getKey().hashCode();
                int insertIndex = getNextFreePos(0, _dummyElement, tempList);
                tempList.set(insertIndex, _values.get(i));
            }
        }
        _values = tempList;

    }

    /**
     * Simply calculate the position of an Element by calculation the hash value modulo with the current max. Table size.
     * @param hashCode Int hashcode of a key
     * @param m Modulo value
     * @return Position between 0 and m
     */
    private int getPosByHash(int hashCode, int m){
        return Math.abs(hashCode) % m;
    }

    /**
     * Get the next available position in the table. Available positions are either null or the dummy Object
     * @param offset Offset to be calculated to the initial hash value (init with 0)
     * @param dummy Reference to the dummy element is compared with a possible position. Dummy elements will be overwritten,
     * @param searchList List that searched for a free position
     * @return Index of an available position with no collision
     */
    private int getNextFreePos(int offset, TableEntry<K, V> dummy, ArrayList<TableEntry<K, V>> searchList){
        int realPos = getPosByHash(_initHash + offset, _currentMaxSize);
        if(searchList.get(realPos) == null || searchList.get(realPos).getKey() == dummy.getKey()){
            _collisionCount += _collisions;
            _collisions = 0;
            return realPos;
        } else {
            _collisions++;
            jumpOffset = power(_collisions, jumpMultiplicator) * _altHash.getPosBySHash(_initHash);
            return getNextFreePos(jumpOffset, dummy, searchList);
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
        return "Current size: " + _currentMaxSize + " | n. of elements: " + _numOfElements + " | collisions: " + _collisionCount;
    }

    /**
     * Print the under lying data structure of this hash table and show some statistics.
     */
    public void debugList(){
        for(int i = 0; i < _currentMaxSize; i++){
            if(_values.get(i) != null) {
                System.out.printf("%s|", _values.get(i).getValue());
            } else{
                System.out.printf("null|");
            }

        }
        System.out.printf("\n Summary - %s\n" , toString());
    }

}
