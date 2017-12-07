package fhv.at.mwi.sql_connection;


import fhv.at.mwi.sql_model.Reservation;
import fhv.at.mwi.sql_model.Station;

import java.sql.*;
import java.util.List;

public class DatabaseFacade {

    private Connection _conn;

    public DatabaseFacade(String user, String pass, String db, String port, String ip){
        try {
            Connection c = DriverManager.getConnection("jdbc:postgresql://" + ip +":" + port + "/" + db + "", user, pass);
            _conn = c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return _conn;
    }

    public List<Reservation> getAllReservations(){
        ReservationBroker rb = new ReservationBroker(_conn);
        return rb.getAll();
    }

    public static void main(String[] args){
        DatabaseFacade df = new DatabaseFacade("carstation", "test", "carstation_db", "65432", "localhost");

    }
}
