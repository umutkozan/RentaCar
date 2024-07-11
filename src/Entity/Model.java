package Entity;

public class Model {
    private int id;
    private int brandId;
    private String name;
    private String type;
    private String year;
    private String fuel;
    private String gear;
    private String brandName;

    // Boş Constructor
    public Model() {}

    // Tüm Alanları İçeren Constructor
    public Model(int id, int brandId, String name, String type, String year, String fuel, String gear, String brandName) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.type = type;
        this.year = year;
        this.fuel = fuel;
        this.gear = gear;
        this.brandName = brandName;
    }

    // Getter ve Setter Metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return name;  // Model ismi
    }
}
