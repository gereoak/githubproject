package cmp1.map;

import java.util.Collection;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AppStudent {

    private static SessionFactory sessionFactory;

    /* Reads hibernate.cfg.xml and prepares Hibernate for use     */
    protected static void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate-map.cfg.xml")
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

        Student student1 = new Student(1, "Frank", "Brown");
        Student student2 = new Student(2, "John", "Doe");
        School school = new School("Happy HighSchool");
        school.addStudent(student1);
        school.addStudent(student2);

        session.persist(school);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        Collection<School> schoolList = session.createQuery("from School").list();
        for (School s : schoolList) {
            System.out.println("school name = " + s.getName());
            for (Map.Entry<Long, Student> enty : s.getStudentlist().entrySet()) {
                System.out.println(" key = " + enty.getKey() + " studentname = "
                        + ((Student) enty.getValue()).getFirstname());
            }

        }
        session.getTransaction().commit();

        // Close the SessionFactory (best practice)
        tearDown();
    }
}
