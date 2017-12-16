package fhv.at.mwi.sql_model;

import javax.persistence.*;

@Entity
@Table(name="autokategorie")
public class CarCategory {

    @Id
    @Column(name="kategorie_bezeichnung")
    private char _categoryType;
    @Column(name="grundtarif")
    private int _basePrice;
    @Column(name="km_preis")
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
