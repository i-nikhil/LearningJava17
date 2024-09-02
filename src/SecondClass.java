import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SecondClass {
    public static void main(String[] args) {
        System.out.println("java");

        double d = 10.011111111111111111d;
        System.out.println(10.56d);

        int a = (int) (Math.random() * 10);

        switch (a) {
            case 1 -> System.out.println("one");
            case 2 -> System.out.println("two");
            case 3, 4, 5, 6, 7, 8 -> System.out.println("Meh! another single digit");
            case 9 -> {
                System.out.println("its 9");
                System.out.println("last single digit number");
            }
            default -> System.out.println("wrong choice");
        }

        System.out.println(nameOfMonth(a));

//        String msg = System.console().readLine("Enter your name");
//        System.out.println("Hi "+msg);
//
//        int n = Integer.parseInt(System.console().readLine());
//        System.out.println("Num = "+n);

        Scanner sc = new Scanner(System.in);

//        String msg = sc.nextLine();
//        System.out.println("Hi " + msg);
//
//        int n = sc.nextInt();
//        System.out.println("Num = "+n);

        Car c1 = new Car();
        System.out.println(c1);

        CarRec r = new CarRec("Tesla", "Red", 1235, true);
        System.out.println(r);
        System.out.println(r.model());

        CredentialDto cred1 = new CredentialDto("48X", 12, 0, BillingType.Basic);
        CredentialDto cred2 = new CredentialDto("26A", 0, 12, BillingType.Premium);
        System.out.println(cred1);
        System.out.println(cred2.cardFormat() + " is " + cred2.billingType());

        String textBlock = """
                                
                My Intro:
                    Name: Nikhil
                    Age: 25
                    Address: Bilekahalli,
                        J P Nagar,
                        Bangalore,
                        India""";
        System.out.println(textBlock);

        System.out.println("""
                Text box:
                    indentation 1""");

        System.out.printf("Your age is %d, year of birth is %d", 25, 1998); // formatter
        System.out.printf("\ndecimal: %.2f", d);

        System.out.println();

        for (int i = 1; i < 100000; i *= 10)
            System.out.printf("%6d%n", i);

        int age = 25;
        String formatted = String.format("Your age is %d", age);
        String formatted2 = "Your age is %d".formatted(age);

        new Car().setColor("Red").setConvertible(false); //Extension method works

        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");

        System.out.println(s1 == s2); // T, both points to the same string literal
        System.out.println(s1 == s3); // F, different objects in memory
        System.out.println(s1.equals(s3)); // T, content is the same

        // hashcode remains same
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());

        String date = String.join("/", "15", "07", "2024");
        System.out.println(date);
        date = date.replace('/', '-');
        System.out.println(date);

        // String builder
        StringBuilder s = new StringBuilder("Hello");
        s.append(" World"); //mutates the actual string, where as String.concat() generates new string
        System.out.println(s);

        var generic = new CredentialDto("26A", 20, 1, BillingType.Premium);
        var str = "string";
        var a1 = 22.99;
        var n1 = -12;

        Object unknownObject = new CredentialDto("34S", 0, 0, BillingType.Basic);
        System.out.println(unknownObject instanceof CredentialDto);
        System.out.println(unknownObject instanceof Car);

        if(unknownObject instanceof CredentialDto cred)
            System.out.println(cred);

        // Generics practice

        GenericTeam<Cricket> cricketTeam = new GenericTeam<>("India");
        cricketTeam.addTeamMember(new Cricket("Virat Kolhi", "India", 345));
        cricketTeam.addTeamMember(new Cricket("Suresh Raina", "India", 209));
        cricketTeam.addTeamMember(new Cricket("Rohit Sharma", "India", 450));
        System.out.println(cricketTeam);

        GenericTeam<Football> footballTeam = new GenericTeam<>("Australia");
        footballTeam.addTeamMember(new Football("Lance Franklin", "Australia", 1066));
        footballTeam.addTeamMember(new Football("Tony Lockett", "Australia", 1306));
        footballTeam.addTeamMember(new Football("Jason Dunstall", "Australia", 1254));
        System.out.println(footballTeam);

        var list = new ArrayList<Integer>(List.of(1,2,3));
        var list2 = new ArrayList<Integer>(List.of(23,-11));
        var list3 = new ArrayList<String>(List.of("xed", "ede", "wee"));
    }

    public static String nameOfMonth(int n) {
        return switch (n) {
            case 1 -> "Jan";
            case 2 -> "Feb";
            default -> {
                System.out.println("Other operations");
                yield "Bad request: " + n;
            }
        };
    }
}
