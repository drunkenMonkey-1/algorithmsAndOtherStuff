package fhv.at.mwi.matrix;

public class Matrix2D<T> {

    private T[][] _matrix;

    public Matrix2D(T[][] matrix){
        _matrix = matrix;
    }

    public T get(int x, int y){
        return _matrix[x][y];
    }

    public T[] getRow(int x){
        return _matrix[x];
    }
    public T[] getCol(int y){

    }
    public T[][] getMatrix() {
        return _matrix;
    }

    public T rowSum(int x){
       _matrix[0][0] += _matrix[1][1];
    }

    public T colSum(int y){

    }


}
