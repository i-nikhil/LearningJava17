import java.util.Arrays;
import java.util.Comparator;

public class ComparableVsComparator {
    public static void main(String[] args) {
        Student[] s = {
                new Student("Tim", 123),
                new Student("Ron", 45),
                new Student("Jim", 66)
        };
        Arrays.sort(s);
        System.out.println(Arrays.toString(s));

        Student[] s1 = {
                new Student("Tim", 123),
                new Student("Ron", 45),
                new Student("Jim", 66)
        };
//        Arrays.sort(s1, new StudentComparator());
        Arrays.sort(s1, new StudentComparator().reversed());
        System.out.println(Arrays.toString(s1));
    }
}

class Student implements Comparable<Student> {

    String name;
    int rollNumber;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    @Override
    public int compareTo(Student o) {
        return rollNumber - o.rollNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                '}';
    }
}

class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.rollNumber - o2.rollNumber;
    }
}
