package fhv.at.mwi.sql_model;

public class ExtraEquipment {
    private CarType _type;
    private String _extraDescription;
    private int _extraPrice;

    public CarType get_type() {
        return _type;
    }

    public void set_type(CarType _type) {
        this._type = _type;
    }

    public String get_extraDescription() {
        return _extraDescription;
    }

    public void set_extraDescription(String _extraDescription) {
        this._extraDescription = _extraDescription;
    }

    public int get_extraPrice() {
        return _extraPrice;
    }

    public void set_extraPrice(int _extraPrice) {
        this._extraPrice = _extraPrice;
    }
}
