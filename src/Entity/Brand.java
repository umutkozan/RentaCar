package Entity;

public class Brand {
    private int id;
    private String name;

    // Boş Constructor
    public Brand() {
    }

    // Tüm Alanları İçeren Constructor
    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter ve Setter Metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
