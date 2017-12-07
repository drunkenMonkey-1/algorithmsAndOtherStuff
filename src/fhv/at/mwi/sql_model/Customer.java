package fhv.at.mwi.sql_model;

import java.util.List;

public class Customer {

    public String get_licenseId() {
        return _licenseId;
    }

    public void set_licenseId(String _licenseId) {
        this._licenseId = _licenseId;
    }

    public String get_customerName() {
        return _customerName;
    }

    public void set_customerName(String _customerName) {
        this._customerName = _customerName;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public List<Reservation> get_myReservations() {
        return _myReservations;
    }

    public void set_myReservations(List<Reservation> _myReservations) {
        this._myReservations = _myReservations;
    }

    private String _licenseId;
    private String _customerName;
    private String _address;
    private List<Reservation> _myReservations;
}
