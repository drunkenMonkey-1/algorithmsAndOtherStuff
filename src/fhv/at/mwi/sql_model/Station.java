package fhv.at.mwi.sql_model;

import java.util.List;

public class Station {

    private City _city;
    private String _stationName;
    private String _address;
    private int _employeeCount;
    private List<Car> _cars;
    private List<Reservation> _reservations;

    public City get_city() {
        return _city;
    }

    public void set_city(City _city) {
        this._city = _city;
    }

    public String get_stationName() {
        return _stationName;
    }

    public void set_stationName(String _stationName) {
        this._stationName = _stationName;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public int get_employeeCount() {
        return _employeeCount;
    }

    public void set_employeeCount(int _employeeCount) {
        this._employeeCount = _employeeCount;
    }

    public List<Car> get_cars() {
        return _cars;
    }

    public void set_cars(List<Car> _cars) {
        this._cars = _cars;
    }

    public List<Reservation> get_reservations() {
        return _reservations;
    }

    public void set_reservations(List<Reservation> _reservations) {
        this._reservations = _reservations;
    }
}
