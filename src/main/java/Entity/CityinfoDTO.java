package Entity;

import java.util.ArrayList;
import java.util.List;

public class CityinfoDTO {

    private Integer zip;
    private String city;

    private List<String> addresses = new ArrayList();
    private List<String> persons = new ArrayList();

    public CityinfoDTO(Cityinfo ci) {
        this.zip = ci.getZip();
        this.city = ci.getCity();
        for (int i = 0; i < ci.getAddresses().size(); i++) {
            this.addresses.add(ci.getAddresses().get(i).toString());
        }
        for (int i = 0; i < ci.getAddresses().size(); i++) {
            this.persons.add(ci.getAddresses().get(i).getPersons().toString());
        }
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
