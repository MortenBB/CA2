package Facade;

import Entity.Address;
import Entity.Cityinfo;
import Entity.Person;
import Entity.PersonDTO;
import Entity.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

    public PersonDTO getPerson(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            PersonDTO pdto = new PersonDTO(p);
            em.getTransaction().commit();
            return pdto;

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
    public PersonDTO findFromPhone(int phnr){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Phone ph = em.find(Phone.class, phnr);           
            PersonDTO p = new PersonDTO(em.find(Person.class, ph.getPerson().getId()));
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
    public ArrayList<PersonDTO> findFromCity(int zip){
        EntityManager em = emf.createEntityManager();
        ArrayList<PersonDTO> pdto = new ArrayList();
        try {
            em.getTransaction().begin();
            Query nQuery = em.createNamedQuery("Person.findByCity");
            nQuery.setParameter("zip", zip);
            List<Person> persons = nQuery.getResultList();
            for (int i = 0; i < persons.size(); i++) {
               pdto.add(new PersonDTO(persons.get(i)));
            }
            em.getTransaction().commit();
            return pdto;
        } finally {
            em.close();
        }
    }
}
