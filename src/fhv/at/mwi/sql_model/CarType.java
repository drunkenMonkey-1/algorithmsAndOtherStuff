package fhv.at.mwi.sql_model;

public class CarType {
    private String _typeDescription;
    private int _fuelConsumption;
    private CarCategory _categoryType;

    public String get_typeDescription() {
        return _typeDescription;
    }

    public void set_typeDescription(String _typeDescription) {
        this._typeDescription = _typeDescription;
    }

    public int get_fuelConsumption() {
        return _fuelConsumption;
    }

    public void set_fuelConsumption(int _fuelConsumption) {
        this._fuelConsumption = _fuelConsumption;
    }

    public CarCategory get_categoryType() {
        return _categoryType;
    }

    public void set_categoryType(CarCategory _categoryType) {
        this._categoryType = _categoryType;
    }
}
