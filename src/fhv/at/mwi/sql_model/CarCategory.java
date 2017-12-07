package fhv.at.mwi.sql_model;

public class CarCategory {

    private char _categoryType;
    private int _basePrice;
    private int _kmPrice;

    public char get_categoryType() {
        return _categoryType;
    }

    public void set_categoryType(char _categoryType) {
        this._categoryType = _categoryType;
    }

    public int get_basePrice() {
        return _basePrice;
    }

    public void set_basePrice(int _basePrice) {
        this._basePrice = _basePrice;
    }

    public int get_kmPrice() {
        return _kmPrice;
    }

    public void set_kmPrice(int _kmPrice) {
        this._kmPrice = _kmPrice;
    }
}
