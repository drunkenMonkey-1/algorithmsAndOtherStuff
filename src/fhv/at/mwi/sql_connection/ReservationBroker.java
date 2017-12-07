package fhv.at.mwi.sql_connection;

import fhv.at.mwi.sql_model.Reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ReservationBroker extends BrokerBase<Reservation>{

    public ReservationBroker(Connection c) {
        super(c);
    }

    @Override
    public void insert(Reservation pojo) {

    }

    @Override
    public void delete(Reservation pojo) {

    }

    @Override
    public void update(Reservation pojo) {

    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> _reservation = new LinkedList<>();
        try {
            Statement reservationStatement = _connection.createStatement();
            ResultSet reservationSet = reservationStatement.executeQuery("SELECT * FROM reservierung");
            while(reservationSet.next()){
                Reservation tempPojo = new Reservation();
                tempPojo.setStation(null);
                tempPojo.set_reservationNo(reservationSet.getString("reservierungs_nr"));
                tempPojo.set_startDate(reservationSet.getDate("anfangsdatum"));
                tempPojo.set_endDate(reservationSet.getDate("enddatum"));
                tempPojo.set_contract(null);
                tempPojo.set_customer(null);
                tempPojo.set_carCategory(null);
                _reservation.add(tempPojo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        savePojoObjects(_reservation);
        return _reservation;
    }
}
