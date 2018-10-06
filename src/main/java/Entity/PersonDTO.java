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
    private String address;
    private String cityinfo;
    private int zip;
    private List<String> hobbies = new ArrayList();
    private List<String> phones = new ArrayList();

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        this.address = person.getAddress().getStreet();
        this.cityinfo = person.getAddress().getCityinfo().getCity();
        this.zip = person.getAddress().getCityinfo().getZip();
        
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
        return "PersonDTO{" + "id=" + id + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", address=" + address + ", cityinfo=" + cityinfo + ", hobbies=" + hobbies + ", phones=" + phones + '}';
    }

    

}