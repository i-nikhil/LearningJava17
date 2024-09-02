import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OptionalClass {

    /*
    Optional is a generic class whose purpose is to be a container for a value which
    may or may not be null.

    It was created by Java's engineer, to address the problem of NullPointerException.
     */

    public static void main(String[] args) {

        Optional<Car> c = getCar();

        System.out.println(c);
        System.out.println(c.isEmpty());
        System.out.println(c.isPresent());

        // Example 1: Creating an Optional with a value
        String value = "Hello, World!";
        Optional<String> optionalValue = Optional.of(value);

        // Example 2: Creating an empty Optional
        Optional<String> emptyOptional = Optional.empty();

        // Example 3: Creating an Optional that might be null
        String nullableString = null;
        Optional<String> optionalNullable = Optional.ofNullable(nullableString);

        // Example 4: Checking if a value is present
        if (optionalValue.isPresent()) {
            System.out.println("Value is present: " + optionalValue.get());
        } else {
            System.out.println("Value is not present.");
        }

        // Example 5: Providing a default value if the Optional is empty
        String result = emptyOptional.orElse("Default Value");
        System.out.println("Result from empty Optional: " + result);

        // Example 6: Using orElseGet to provide a default value using a Supplier
        result = optionalNullable.orElseGet(() -> "Generated Default Value");
        System.out.println("Result from nullable Optional: " + result);

        // Example 7: Throwing an exception if the value is not present
        try {
            String riskyResult = emptyOptional.orElseThrow(() -> new RuntimeException("Value not found"));
        } catch (RuntimeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        // Example 8: Performing an action if the value is present
        optionalValue.ifPresent(val -> System.out.println("Optional contains: " + val));

        // Example 9: Mapping the value if present
        Optional<Integer> optionalLength = optionalValue.map(String::length);
        optionalLength.ifPresent(length -> System.out.println("Length of value: " + length));

        List<Car> cars = Stream.generate(Car::new)
                .limit(20)
                .toList();

        System.out.println(cars);

        cars.stream()
                .filter(car->car.getEngine()<100)
                .findAny()
                .ifPresentOrElse(System.out::println, ()-> System.out.println("N/A"));

    }

    private static Optional<Car> getCar() {
        return Optional.empty();
    }
}
