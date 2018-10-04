/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Cityinfo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Morten
 */
public class UtilFacade {

    private EntityManagerFactory emf;

    public UtilFacade() {
        emf = Persistence.createEntityManagerFactory("pu", null);
    }

    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ArrayList<String> getAllZip() {
        EntityManager em = emf.createEntityManager();
        List<Cityinfo> cityinfo = new ArrayList();
        ArrayList<String> cities = new ArrayList();
        try {
            em.getTransaction().begin();
            cityinfo = em.createNamedQuery("Cityinfo.findAll").getResultList();
            for (int i = 0; i < cityinfo.size(); i++) {
                cities.add(cityinfo.get(i).getCity());
            }
            return cities;
        } finally {
            em.close();
        }
    }

}
