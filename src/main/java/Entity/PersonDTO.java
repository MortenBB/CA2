package Entity;

/**
 *
 * @author Morten
 */
public class PersonDTO {

    private int id;
    private String email;
    private String firstname;
    private String lastname;

    public PersonDTO(Person person) {
        this.email = person.getEmail();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
    }

    public PersonDTO(String email, String firstname, String lastname) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
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

}
