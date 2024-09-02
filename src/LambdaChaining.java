import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaChaining {
    public static void main(String[] args) {

        Function<String, String> uCase = String::toUpperCase;

        // andThen
        Function<String, String> uCaseLName = uCase.andThen(s -> "Mr. " + s.split(" ")[1]);
        System.out.println(uCaseLName.apply("Nikhil Prakash"));

        // compose
        Function<String, String> lNameUCase = uCase.compose(s -> "Mr. " + s.split(" ")[1]);
        System.out.println(lNameUCase.apply("Nikhil Prakash"));

        Function<String, Integer> f = uCase
                .andThen(s -> s.concat("Something"))
                .andThen(s -> s.split(" "))
                .andThen(s -> s[1].toUpperCase() + ", " + s[0])
                .andThen(s -> {
                    System.out.println(s);
                    return s;
                })
                .andThen(String::length);
        System.out.println(f.apply("Nikhil Prakash"));

        // for predicates
        Predicate<String> p1 = s -> s.equals("Tom");
        Predicate<String> p2 = s -> s.equalsIgnoreCase("Tom");
        Predicate<String> p3 = s -> s.startsWith("T");
        Predicate<String> p4 = s -> s.endsWith("e");

        System.out.println(p1.or(p2).test("TOm"));
        System.out.println(p1.and(p2).or(p3).test("Tom"));
        System.out.println(p1.and(p2).and(p3).and(p4).test("Tom"));
        System.out.println(p1.and(p2).or(p3).negate().test("Tom"));

        // Chaining comparator
        record Person(String f_name, String l_name) {}

        List<Person> list = new ArrayList<>(List.of(
                new Person("Nikhil", "Prakash"),
                new Person("Deepak", "Kumar"),
                new Person("Chandan", "Yadav"),
                new Person("Ritesh", "Kumar"),
                new Person("Shubham", "Prakash")
        ));

        list.sort(Comparator.comparing(Person::l_name)
                .thenComparing(Person::f_name)
                .reversed());

        list.forEach(System.out::println);
    }
}