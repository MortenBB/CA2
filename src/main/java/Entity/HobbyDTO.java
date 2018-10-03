package Entity;

import java.util.ArrayList;
import java.util.List;

public class HobbyDTO {

    private String description;
    private List<String> persons = new ArrayList();

    public HobbyDTO(Hobby h) {
        this.description = h.getDescription();
         for (int i = 0; i < h.getPersons().size(); i++) {
            PersonDTO pdto = new PersonDTO(h.getPersons().get(i));
            this.persons.add(pdto.toString());
        }
    }

}
