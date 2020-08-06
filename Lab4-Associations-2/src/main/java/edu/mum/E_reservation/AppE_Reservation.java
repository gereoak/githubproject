package edu.mum.E_reservation;

import edu.mum.D_customer.Customer;
import edu.mum.B_book.Book;
import edu.mum.D_customer.Reservation;

import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppE_Reservation {

    private static EntityManagerFactory emf;


    public static void main(String[] args) throws Exception {
       emf= Persistence.createEntityManagerFactory("cs544");
        EntityManager em= emf.createEntityManager();

        Customer c = new Customer("Jack");

        em.persist(c);
        em.getTransaction().commit();
        em.close();
        em = emf.createEntityManager();
        em.getTransaction().begin();
       @SuppressWarnings("unchecked")
        Collection<Customer> custs = em.createQuery("from Customer").getResultList();
        for (Customer cust : custs) {
            System.out.println(c.getName());
            for (Reservation r : cust.getReservations()) {
                System.out.println(r.getDate());

            }
        }

        em.getTransaction().commit();
        em.close();
    }
}
