/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entity.Address;
import Entity.Cityinfo;
import Entity.Hobby;
import Entity.Person;
import Entity.PersonDTO;
import Entity.Phone;
import Facade.PersonFacade;
import Facade.UtilFacade;
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

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Person p = new Person("Per123", "Per", "Hansen");
        Person p2 = new Person("Karl456", "Karl", "Johnson");
        Phone ph = new Phone(123456);
        Phone ph2 = new Phone(987654);
        Cityinfo ci = new Cityinfo(2820);
        Address add = new Address("Gentoftegade");
        Hobby h = new Hobby("fiske");
        p.addHobby(h);
        p2.addHobby(h);
        h.addPersons(p);
        h.addPersons(p);
        add.setCityinfo(ci);
        ci.addAddresses(add);
        add.addPerson(p2);
        add.addPerson(p);
        p2.setAddress(add);
        p.setAddress(add);
        ph2.setPerson(p2);
        ph.setPerson(p);
        p2.addPhone(ph2);
        p.addPhone(ph);
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        h.addPersons(p);
        populatePeople(12);
        PersonFacade pf = new PersonFacade();
        UtilFacade uf = new UtilFacade();
        System.out.println(pf.getPerson(8));
        pf.deletePerson(8);
        System.out.println(pf.findFromPhone(123456));
        //System.out.println(p);
        PersonDTO pdto = new PersonDTO(p);
        //System.out.println(pdto);
        //System.out.println("ALL ZIP:");
        //System.out.println(uf.getAllZip());
        System.out.println(pf.findFromCity(2820));
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
