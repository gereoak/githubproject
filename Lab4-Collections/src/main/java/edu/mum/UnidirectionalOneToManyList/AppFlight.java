package cmp1.UnidirectionalOneToManyList;

import java.util.Collection;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AppFlight {

    private static SessionFactory sessionFactory;

    /* Reads hibernate.cfg.xml and prepares Hibernate for use     */
    protected static void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate-list.cfg.xml")
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    protected static void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public static void main(String[] args) throws Exception {
        setUp();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Flight flight1 = new Flight(10034, "Amsterdam", "Boston", new Date());
        Flight flight2 = new Flight(10045, "Amsterdam", "Chicago", new Date());
        Flight flight3 = new Flight(10072, "Chicago", "Paris", new Date());
        Passenger passenger = new Passenger("Frank Brown");

        // please note, the order in which they are added here 
        // should also be the order in which they are printed
        passenger.addFlight(flight1);
        passenger.addFlight(flight3);
        passenger.addFlight(flight2);

        session.persist(passenger);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        Collection<Passenger> passengerList = session.createQuery("from Passenger").list();
        for (Passenger p : passengerList) {
            System.out.println("passenger name = " + p.getName());
            for (Flight flight : p.getFlightlist()) {
                System.out.println("flight nr= " + flight.getFlightnumber()
                        + " from= " + flight.getFrom() + " to= " + flight.getTo());
            }
        }
        session.getTransaction().commit();

        // Close the SessionFactory (best practice)
        tearDown();
    }
}
