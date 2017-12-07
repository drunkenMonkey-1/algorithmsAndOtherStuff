package fhv.at.mwi.sql_connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public abstract class BrokerBase<T> {

    protected Connection _connection;
    protected List<T> _pojoList;

    public BrokerBase(Connection c){
        _connection = c;
    }

    public abstract void insert(T pojo);
    public abstract void delete(T pojo);
    public abstract void update(T pojo);

    public abstract List<T> getAll();

    public List<T> getAllExisting(){
        if(_pojoList.isEmpty()){
            getAll();
        }
        return _pojoList;
    }

    protected void savePojoObjects(List<T> _pojos){
        _pojoList = _pojos;
    }
}
