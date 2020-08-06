package cmp1.BidirectionalonetomanyManytooneSet;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AppEmployee {

    private static SessionFactory sessionFactory;

    /* Reads hibernate.cfg.xml and prepares Hibernate for use     */
    protected static void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate-set.cfg.xml")
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

        Employee emp1 = new Employee("Frank Brown");
        Laptop laptop1 = new Laptop("Acer", "Aspire");
        Laptop laptop2 = new Laptop("DELL", "Studio");
        emp1.addLaptop(laptop1); // automatically sets bi-directional association
        emp1.addLaptop(laptop2);

        session.persist(emp1);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        Collection<Employee> emps = session.createQuery("from Employee").list();
        for (Employee emp : emps) {
            System.out.println("Employe name = " + emp.getName());
            for (Laptop laptop : emp.getLaptoplist()) {
                System.out.println("laptop brand= " + laptop.getBrand() + " type= " + laptop.getType());
            }
        }

        session.getTransaction().commit();

        // Close the SessionFactory (best practice)
        tearDown();
    }
}
