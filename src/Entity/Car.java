package Entity;

public class Car {
    private int id;
    private int modelId;
    private String plate;
    private String model;
    private String brand;
    private String color;
    private int kilometers;
    private boolean isAvailable;

    public Car() {}

    public Car(int id, int modelId, String plate, String model, String brand, String color, int kilometers, boolean isAvailable) {
        this.id = id;
        this.modelId = modelId;
        this.plate = plate;
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.kilometers = kilometers;
        this.isAvailable = isAvailable;
    }

    // Getter and Setter methods...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return model + " - " + plate;  // Model adı ve plaka
    }
}
