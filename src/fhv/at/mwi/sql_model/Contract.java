package fhv.at.mwi.sql_model;

public class Contract {
    private Reservation _reservation;
    private int _kmStart;
    private int _kmEnd;
    private Car _car;

    public Reservation get_reservation() {
        return _reservation;
    }

    public void set_reservation(Reservation _reservation) {
        this._reservation = _reservation;
    }

    public int get_kmStart() {
        return _kmStart;
    }

    public void set_kmStart(int _kmStart) {
        this._kmStart = _kmStart;
    }

    public int get_kmEnd() {
        return _kmEnd;
    }

    public void set_kmEnd(int _kmEnd) {
        this._kmEnd = _kmEnd;
    }

    public Car get_car() {
        return _car;
    }

    public void set_car(Car _car) {
        this._car = _car;
    }
}
