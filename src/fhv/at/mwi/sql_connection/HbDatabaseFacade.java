package fhv.at.mwi.sql_connection;

import fhv.at.mwi.sql_model.Car;
import fhv.at.mwi.sql_model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class HbDatabaseFacade {

    private Session _session;

    public HbDatabaseFacade(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Car.class);

        StandardServiceRegistry serviceRegistry = new
                StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
        _session = factory.openSession();
    }

    public List<Customer> getAllCustomers(){
        Query<Customer> userQuery = _session.createQuery("FROM Customer", Customer.class);
      //  Criteria crit = _session.createCriteria(Customer.class);
      //  crit.add(Restrictions.eq("_licenseId", "00000000"));
        return userQuery.list();
    }

    public List<Car> getAllCars(){
        Query<Car> carQuery = _session.createQuery("FROM Car", Car.class);
        return carQuery.list();
    }

    public void closeSession(){
        _session.close();
    }

    public static void main(String[] args){
        HbDatabaseFacade test = new HbDatabaseFacade();
        Iterator i = test.getAllCars().iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
    }
}
