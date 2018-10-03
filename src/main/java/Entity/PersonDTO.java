/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Morten
 */
public class PersonDTO {

    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private List<String> hobbies = new ArrayList();
    private List<String> phones = new ArrayList();

    public PersonDTO(Person person) {
        this.email = person.getEmail();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        for (int i = 0; i < person.getHobbies().size(); i++) {
            this.hobbies.add(person.getHobbies().get(i).toString());
        }
        for (int i = 0; i < person.getPhones().size(); i++) {
            this.phones.add(person.getPhones().get(i).toString());
        }
    }

    public PersonDTO(int id, String email, String firstname, String lastname) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "PersonDTO{" + "email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", hobbies=" + hobbies + ", phones=" + phones + '}';
    }

}
