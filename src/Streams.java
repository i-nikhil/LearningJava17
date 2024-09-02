import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// static import
import static java.util.stream.Collectors.*;

public class Streams {
    public static void main(String[] args) {
        List<String> bingoPool = new ArrayList<>(75);

        int start = 1;

        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++) {
                bingoPool.add("" + c + i);
            }
            start += 15;
        }

        Collections.shuffle(bingoPool);
        System.out.println(bingoPool);

        bingoPool.stream()
                .limit(15)
                .filter(s -> s.charAt(0) == 'G' || s.charAt(0) == 'O')
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted()
                .forEach(System.out::println);

        System.out.println(bingoPool); // no effect on actual data

        var temp = bingoPool.stream()
                .limit(15)
                .filter(s -> s.charAt(0) == 'G' || s.charAt(0) == 'O')
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted();

        temp.forEach(System.out::println);
//        temp.forEach(System.out::println);

        String[] strings = {"one", "two", "three"};

        var s1 = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder())
                .map(String::toUpperCase);

        var s2 = Stream.of("Ten", "Eleven", "Twelve")
                .map(String::toLowerCase);

        Stream.concat(s1, s2)
                .map(s -> s.charAt(0) + " -> " + s)
                .forEach(System.out::println);

        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int ballNumber = 1;
        for (char c : "BINGO".toCharArray()) {
            int[] num = new int[15];
            int finalBallNumber = ballNumber;
            Arrays.setAll(num, i -> i + finalBallNumber);
            myMap.put(c, num);
            ballNumber += 15;
        }

        myMap.entrySet()
                .stream()
                .map(e -> e.getKey() + " -> " + Arrays.toString(e.getValue()))
                .forEach(System.out::println);

        Random r = new Random();

        // infinite streams

        Stream.generate(() -> r.nextInt(5))
//                .distinct() // infinite situation
                .limit(10)
                .distinct()
                .forEach(System.out::print);

        System.out.println();

        IntStream.iterate(1, n -> n + 1)
                .limit(20)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();

        IntStream.iterate(1, n -> n <= 20, n -> n + 1)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();

        // finite streams

        IntStream.range(1, 21)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();

        IntStream.rangeClosed(1, 20)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();

        //

        IntStream.rangeClosed('A', 'z')
                .filter(Character::isAlphabetic)
                .dropWhile(i -> i <= 'E')
                .takeWhile(i -> i < 'e')
                .forEach(c -> System.out.printf("%c ", c));

         /*
         Primitive Streams:
         1. DoubleStream -> mapToDouble() -> mapToObj() / boxed().map()
         2. IntStream    -> mapToInt()    -> mapToObj() / boxed().map()
         3. LongStream   -> mapToLong()   -> mapToObj() / boxed().map()
         */

        // peek() - prints intermediate execution steps, for debugging

        record Seat(char rowMaker, int seatNumber, boolean isReserved) {
            public Seat(char rowMaker, int seatNumber) {
                this(rowMaker, seatNumber, new Random().nextBoolean());
            }
        }

        Seat[] seats = new Seat[100];
        Arrays.setAll(seats, i -> new Seat((char) ('A' + i / 10), i % 10 + 1));
        // Arrays.asList(seats).forEach(System.out::println);

        System.out.println("\nReservation count: " + Stream.of(seats)
                .filter(Seat::isReserved)
                .count() + "/" + seats.length);

        System.out.println("Has Bookings: " + Stream.of(seats)
                .anyMatch(Seat::isReserved));

        System.out.println("Fully Booked: " + Stream.of(seats)
                .allMatch(Seat::isReserved));

        System.out.println("Booking not started: " + Stream.of(seats)
                .noneMatch(Seat::isReserved));

        Stream.of(seats)
                .filter(s -> !s.isReserved)
                .limit(10)
                .sorted(Comparator.comparing(s -> s.seatNumber))
                .toList().forEach(System.out::println);

        List<Seat> list = Stream.of(seats)
                .filter(Seat::isReserved)
                .sorted(Comparator.comparing(s -> s.seatNumber))
                .toList(); // immutable list

        // Collections.shuffle(list); //error
        System.out.println("toList -> " + list);

        List<Seat> list1 = Stream.of(seats)
                .filter(Seat::isReserved)
                .sorted(Comparator.comparing(s -> s.seatNumber))
                .collect(Collectors.toList()); // mutable list

        Collections.shuffle(list1);
        System.out.println("collect -> " + list1);

        // Filter rows with available seats count
        Map<Character, List<Seat>> availableSeatsByRow = Stream.of(seats)
                .filter(s -> !s.isReserved())
                .collect(groupingBy(Seat::rowMaker));

        System.out.println("HashMap of seats per row:");
        availableSeatsByRow.forEach((key, value) -> System.out.println(key + "->" + value.size()));

        // Get rows with 8 or more available seats and sort them
        availableSeatsByRow.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 8)
                .flatMap(entry -> entry.getValue().stream())
                .sorted(Comparator.comparing(Seat::seatNumber))
                .limit(10)
                .forEach(System.out::println);

        var booked = Stream.of(seats)
                .collect(partitioningBy(Seat::isReserved));
        System.out.println("HashMap of reserved and vacant seats:");
        booked.forEach((key, value) -> System.out.println(key + "->" + value.size()));

        Seat[] array = Stream.of(seats)
                .filter(Seat::isReserved)
                .sorted(Comparator.comparing(s -> s.seatNumber))
                .toArray(Seat[]::new); // .toArray(size -> new Seat[size]);

        /*
        Flat Map:
        The flat map intermediate operation performs one to many transformations, on elements in stream.
        It flattens the results from a hierarchical collection into one stream of uniformly typed elements.
         */

        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );

        // Using flatMap to flatten the list of lists
        List<Integer> flattenedList = listOfLists.stream()
                .flatMap(List::stream) // Flattens the stream of lists into a stream of integers
                .collect(Collectors.toList()); // Collects the flattened stream into a list

        // Printing the flattened list
        System.out.println(flattenedList); // Output: [1, 2, 3, 4, 5, 6, 7, 8, 9]

        List<String> sentences = Arrays.asList(
                "Hello world",
                "Java streams are powerful",
                "FlatMap is useful"
        );

        List<String> words = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" "))) // Split each sentence into words and flatten
                .collect(Collectors.toList());

        // Printing the list of words
        System.out.println(words); // Output: [Hello, world, Java, streams, are, powerful, FlatMap, is, useful]
    }
}
