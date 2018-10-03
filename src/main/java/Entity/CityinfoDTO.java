package Entity;

public class CityinfoDTO {

    private Integer zip;
    private String city;

    public CityinfoDTO(Cityinfo cityinfo) {
        this.zip = cityinfo.getZip();
        this.city = cityinfo.getCity();
    }
    
    public CityinfoDTO(Integer zip, String city) {
        this.zip = zip;
        this.city = city;
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
