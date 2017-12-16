package fhv.at.mwi.sql_model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="kunde")
public class Customer implements Serializable{

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

//    public List<Reservation> get_myReservations() {
//        return _myReservations;
//    }
//
//    public void set_myReservations(List<Reservation> _myReservations) {
//        this._myReservations = _myReservations;
//    }

    @Id
    @Column(name="fuehrerschein_nummer")
    private String _licenseId;
    @Column(name="kunde_name")
    private String _customerName;
    @Column(name="anschrift")
    private String _address;


    //private List<Reservation> _myReservations;

    @Override
    public String toString(){
        return _licenseId + " | " + _customerName + " | " + _address;
    }
}
