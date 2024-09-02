import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LambdaExpression {
    record Person(String firstName, String lastName) {


        @Override
        public String toString() {
            return "Person{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
        }
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(List.of(
                new Person("Nikhil", "Prakash"),
                new Person("Deepak", "Kumar"),
                new Person("Annapurna", "Roy"))
        );

        //without lambda expression, Using anonymous class
        people.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName.compareTo(o2.lastName);
            }
        });

        // with lambda expression
        people.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));

        // A functional interface is an interface this has one and only one abstract method
        // A functional interface is a target type for a lambda expression

        String text = "## ";
        people.forEach(p -> System.out.println(text+p));

        // text = "**"; // variable used in lambda should be final or effectively final

        people.replaceAll(p -> new Person("Mr."+p.firstName, p.lastName));
        System.out.println(people);

        people.removeIf(p -> p.lastName.contains("R"));
        System.out.println(people);

        Consumer<String> separateWords = sent -> Arrays.asList(sent.split(" ")).forEach(System.out::println); // List.of & Arrays.asList
        separateWords.accept("Let us split the words using lambda");

        UnaryOperator<String> getChars = string -> string.replaceAll("i", "")+"_X60Eo";
        System.out.println(getChars.apply("Nikhil"));

        Function<String, String> getWorkDone = a -> getChars.apply(a)+"_function";
        System.out.println(getWorkDone.apply("Nikhil Nikhil"));

        Supplier<String> java = () -> "I love java!";
        System.out.println(java.get());


    }
}
