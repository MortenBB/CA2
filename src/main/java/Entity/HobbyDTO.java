package Entity;

public class HobbyDTO {

    private String description;
    
    public HobbyDTO(Hobby hobby) {
        this.description = hobby.getDescription();
    }
    
    public HobbyDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
