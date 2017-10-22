package fhv.at.mwi.analysis;

public class AlgorithmAnalysis {

    private int _comparisonOperations;
    private int _memoryOperations;
    private int _size_n;
    private String _conclusion;

    public int get_comparisonOperations() {
        return _comparisonOperations;
    }

    public void set_comparisonOperations(int _comparisonOperations) {
        this._comparisonOperations = _comparisonOperations;
    }

    public int get_memoryOperations() {
        return _memoryOperations;
    }

    public void set_memoryOperations(int _memoryOperations) {
        this._memoryOperations = _memoryOperations;
    }

    public int get_size_n() {
        return _size_n;
    }

    public void set_size_n(int _size_n) {
        this._size_n = _size_n;
    }
    public void incrementMemoryOperations(int x){
        _memoryOperations+=x;
    }
    public void incrementComparisonOperations(int x){
        _comparisonOperations+=x;
    }

    public AlgorithmAnalysis(int size) {
        set_comparisonOperations(0);
        set_memoryOperations(0);
        _conclusion = "";
        set_size_n(size);
    }


    @Override
    public String toString(){
        _conclusion = "Comparisions: " + _comparisonOperations +
                   " | Memory Operations: " + _memoryOperations + " " +
                   " | for n = " + _size_n;
        return  _conclusion;
    }
}
