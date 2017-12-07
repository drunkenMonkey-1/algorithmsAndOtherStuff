package fhv.at.mwi.sql_model;

import java.util.List;

public class City {

    private String _cityCode;
    private String _cityName;
    private int _populationCount;
    private List<Station> _stations;

    public String get_cityCode() {
        return _cityCode;
    }

    public void set_cityCode(String _cityCode) {
        this._cityCode = _cityCode;
    }

    public String get_cityName() {
        return _cityName;
    }

    public void set_cityName(String _cityName) {
        this._cityName = _cityName;
    }

    public int get_populationCount() {
        return _populationCount;
    }

    public void set_populationCount(int _populationCount) {
        this._populationCount = _populationCount;
    }

    public List<Station> get_stations() {
        return _stations;
    }

    public void set_stations(List<Station> _stations) {
        this._stations = _stations;
    }


}
