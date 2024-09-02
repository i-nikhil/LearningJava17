import java.util.Arrays;

public class ShallowDeepCopy {
    public static void main(String[] args) {
        Person[] people = {
                new Person("Nikhil", 25),
                new Person("Jazz", 27)
        };

        // Shallow copy
        Person[] copyPeople1 = Arrays.copyOf(people, people.length);
        System.out.println(people[1] == copyPeople1[1]);

        Person[] copyPeople2 = people.clone();
        System.out.println(people[1] == copyPeople2[1]);

        // Deep copy
        Person[] deepCopy = new Person[people.length];
        Arrays.setAll(deepCopy, i-> new Person(people[i].name(), people[i].age()));
        System.out.println(people[1] == deepCopy[1]);
    }
}

record Person(String name, int age) {
}
