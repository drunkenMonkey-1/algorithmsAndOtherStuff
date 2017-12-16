package fhv.at.mwi.sql_connection;

import fhv.at.mwi.sql_model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateTestMain {

    public static void main(String[] args){

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(Customer.class);

        StandardServiceRegistry serviceRegistry = new
                StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
        Session session = factory.openSession();
        Customer b = session.get(Customer.class, "00000000");

        System.out.println(b.get_customerName());
        session.close();
    }

}
