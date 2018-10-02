package Facade;

import Entity.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gonners
 */
public class PersonFacade {

    private EntityManagerFactory emf;

    public PersonFacade() {
        emf = Persistence.createEntityManagerFactory("pu", null);
    }

    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Person getPerson(int id) {
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            em.getTransaction().commit();
            return p;

        } finally {
            em.close();
        }
    }

    public Person addPerson(Person p) {
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;

        } finally {
            em.close();
        }
    }

    public Person editPerson(Person p) {
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            Person pers = em.find(Person.class, p.getId());

            if (pers != null) {
                pers = p;
                em.merge(pers);
                em.getTransaction().commit();
                return pers;
            }
        } finally {
            em.close();
        }
        return null;
    }

public Person deletePerson(int id) {
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            em.remove(p);
            em.getTransaction().commit();
            return p;

        } finally {
            em.close();
        }
    }
}
