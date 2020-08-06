package edu.mum.F_office;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppF_Office {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();


        Employee employee1 = new Employee("Frank Brown", "062341234");
        Employee employee2 = new Employee("John Doe", "064362738");
        Office office = new Office("M290", "McLaughlin");



        System.out.println("---- now the reverse -----");
        @SuppressWarnings("unchecked")
        Collection<Employee> employeeList = em.createQuery("from Employee").getResultList();
        for (Employee empl : employeeList) {
            System.out.println("employee name= " + empl.getName());
            System.out.println("Office Location: " + empl.getOffice().getRoomnumber()
                    + " in building: " + empl.getOffice().getBuilding());
        }

        em.getTransaction().commit();
                em.close();
            }
        }

