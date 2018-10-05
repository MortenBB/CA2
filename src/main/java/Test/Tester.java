/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entity.Hobby;
import Entity.Person;
import Entity.Phone;
import Facade.PersonFacade;
import java.util.ArrayList;
import java.util.*;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Morten
 */
public class Tester {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Person p = new Person("Per123", "Per", "Hansen");
        Phone ph = new Phone(123456);
        ph.setPerson(p);
        p.addPhone(ph);
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        Hobby h = new Hobby("fiske");
        h.addPersons(p);
        populatePeople(12);
        PersonFacade pf = new PersonFacade();
        //System.out.println(pf.getPersonByID(8));
      //  pf.deletePerson(8);
        System.out.println(pf.getPersonByPhone(123456));
    }

    private static void populatePeople(int amount) {
        PersonFacade pf = new PersonFacade();
        Random rng = new Random();
        ArrayList<String> fName = new ArrayList(
                Arrays.asList("Jerry", "James", "Jacob", "Jakob", "Maisie", "Sophie", "Sofie", "Sophia", "Sofia", "Mary", "Sachiko", "Seiji", "Seijo", "Kurosaki", "Charles", "George", "Bruce", "Logan", "Linsey", "Lindsey", "Martin", "Simon", "Stefan", "Caroline", "Karoline", "Jessica", "Michel", "Michelle", "Michael", "Naja", "Maja", "Marie", "Frodo", "Gimli", "Luke", "Gandalf", "Legolas", "Louise", "Louis", "Doug", "Jeremy", "Jamie", "Ove", "Ole", "Karl", "Brian", "Robin", "Clark", "Daenerys"));
        ArrayList<String> lName = new ArrayList(
                Arrays.asList("Smith", "Jackson", "Jacobsen", "Gregersen", "Hood", "Lannister", "Targeryan", "Stark", "Williams", "Miller", "Wilson", "Brown", "Davis", "Wright", "Lopez", "Hall", "Allen", "Anderson", "Garcia", "Evans", "Edwards", "Parker", "Collins", "Robinson", "Turner", "Harris", "Mitchell", "Nelson", "Baker"));
        ArrayList<String> mail = new ArrayList(
                Arrays.asList("@gmail.com", "@hotmail.com", "@webmail.com", "@hotmail.dk"));

        for (int i = 0; i < amount; i++) {
            String getFName = fName.get(rng.nextInt(fName.size()));
            String getLName = lName.get(rng.nextInt(lName.size()));
            int min = 10000000;
            int max = 99999999;
            int phone = rng.nextInt(max - min) + min;
            String email = getFName.substring(0, 2) + "." + getLName + mail.get(rng.nextInt(mail.size()));
            pf.addPerson(new Person(email, getFName, getLName));
        }
    }
}
