public class Car {
    private String model;
    private String color;
    private int engine;
    private Boolean isConvertible;

    public Car()
    {
        this("Default model", "White", 230, false);
    }

    public Car(String model, String color, int engine, Boolean isConvertible) {
        this.model = model;
        this.color = color;
        this.engine = engine;
        this.isConvertible = isConvertible;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public Car setColor(String color) {
        this.color = color;
        return this;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public Boolean getConvertible() {
        return isConvertible;
    }

    public void setConvertible(Boolean convertible) {
        isConvertible = convertible;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", engine=" + engine +
                ", isConvertible=" + isConvertible +
                '}';
    }
}
