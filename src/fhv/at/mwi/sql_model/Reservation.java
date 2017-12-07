package fhv.at.mwi.sql_model;

import java.sql.Date;

public class Reservation {
    private Station station;
    private String _reservationNo;
    private Date _startDate;
    private Date _endDate;
    private Customer _customer;
    private CarCategory _carCategory;
    private Contract _contract;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String get_reservationNo() {
        return _reservationNo;
    }

    public void set_reservationNo(String _reservationNo) {
        this._reservationNo = _reservationNo;
    }

    public Date get_startDate() {
        return _startDate;
    }

    public void set_startDate(Date _startDate) {
        this._startDate = _startDate;
    }

    public Date get_endDate() {
        return _endDate;
    }

    public void set_endDate(Date _endDate) {
        this._endDate = _endDate;
    }

    public Customer get_customer() {
        return _customer;
    }

    public void set_customer(Customer _customer) {
        this._customer = _customer;
    }

    public CarCategory get_carCategory() {
        return _carCategory;
    }

    public void set_carCategory(CarCategory _carCategory) {
        this._carCategory = _carCategory;
    }

    public Contract get_contract() {
        return _contract;
    }

    public void set_contract(Contract _contract) {
        this._contract = _contract;
    }
}
