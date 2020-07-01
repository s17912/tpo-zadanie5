public class CarModel {
    String type;
    String brand;
    String model;
    int production;
    String color;

    public CarModel(String type, String brand, String model, int production, String color) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.production = production;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public String toString() {
        return "CarModel{" +
                "type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", production=" + production +
                ", color='" + color + '\'' +
                '}';
    }
}
