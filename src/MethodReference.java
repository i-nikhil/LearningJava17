import java.util.List;
import java.util.function.*;

public class MethodReference {

    public static void main(String[] args) {

        // static method: (p1, p2) -> p1+p2
        BinaryOperator<Integer> f1 = Integer::sum;

        // Instance method of bounded/unbounded object
        Consumer<String> f2 = System.out::println; // (s) -> System.out.println(s);
        BinaryOperator<String> f3 = String::concat; // (s1, s2) -> s1.concat(s2);
        Predicate<String> f4 = String::isEmpty; // (s) -> s.isEmpty();
        Consumer<List<String>> f5 = List::clear; // (l) -> l.clear();
        Function<String, Integer> f6 = String::length; // (s) -> s.length();

        // Constructor
        Supplier<Car> f7 = Car::new; // () -> new Car();
    }
}