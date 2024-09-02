import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Maps {
    public static void main(String[] args) {

        List<String> names = List.of(
                "John", "Bob", "Mark", "Sarah", "David","Sarah", "David",
                "Alice", "Bob", "Mark", "Sarah", "David", "Sam", "Nikhil", "Nikhil",
                "John", "Alice", "David","John", "Alice", "David",
                "John", "Alice", "Bob", "Mark", "Sarah", "David",
                "John", "Alice", "Bob", "Mark", "Sarah", "John", "Alice", "Bob", "Mark", "Sarah"
        );

        Map<String, Integer> frequency = new TreeMap<>(); // sorted by keys

        names.forEach(s -> frequency.put(s, frequency.getOrDefault(s, 0) + 1));
        System.out.println(frequency);

        frequency.clear();

        names.forEach(s -> frequency.merge(s, 1 , Integer::sum));
        System.out.println(frequency);
    }
}
