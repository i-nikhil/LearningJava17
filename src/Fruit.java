public class Fruit {
    String name;
    String taste;
    String color;

    public Fruit(String name, String taste, String color) {
        this.name = name;
        this.taste = taste;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", taste='" + taste + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

class Apple extends Fruit
{
    public Apple() {
        super("Apple", "Sweet", "Red");
    }

    @Override
    public String toString() {
        return "Apple{} " + super.toString();
    }
}