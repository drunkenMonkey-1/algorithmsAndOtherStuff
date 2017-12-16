package fhv.at.mwi.sql_model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="auto")
public class Car implements Serializable{

    @Id
    @Column(name="kennzeichen")
    private String _licensePlate;
    @Column(name="km_stand")
    private int _kmDriven;
    @Column(name="farbe")
    private String _color;
    @Column(name="stations_name")
    private Station _station;
    @Column(name="typ_bezeichnung")
    private CarCategory _carCategory;
  //  private List<ExtraEquipment> _extraEquipment;

    public String get_licensePlate() {
        return _licensePlate;
    }

    public void set_licensePlate(String _licensePlate) {
        this._licensePlate = _licensePlate;
    }

    public int get_kmDriven() {
        return _kmDriven;
    }

    public void set_kmDriven(int _kmDriven) {
        this._kmDriven = _kmDriven;
    }

    public String get_color() {
        return _color;
    }

    public void set_color(String _color) {
        this._color = _color;
    }

    public Station get_station() {
        return _station;
    }

    public void set_station(Station _station) {
        this._station = _station;
    }

    public CarCategory get_carCategory() {
        return _carCategory;
    }

    public void set_carCategory(CarCategory _carCategory) {
        this._carCategory = _carCategory;
    }

//    public List<ExtraEquipment> get_extraEquipment() {
//        return _extraEquipment;
//    }
//
//    public void set_extraEquipment(List<ExtraEquipment> _extraEquipment) {
//        this._extraEquipment = _extraEquipment;
//    }
}
