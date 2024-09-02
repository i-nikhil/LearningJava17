import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class ArrayTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(arr));

        Object[] arr1 = new Object[5];
        arr1[0] = Arrays.toString(arr);
        arr1[1] = 2.9;
        arr1[2] = "string";
        arr1[3] = arr;
        arr1[4] = false;
        System.out.println(Arrays.toString(arr1));

        Random r = new Random();
        int[] random = new int[100];
        for (int i = 0; i < random.length; i++)
            random[i] = r.nextInt(100);

        Arrays.sort(random);
        System.out.println(Arrays.toString(random));

        Integer[] random2 = {2, 0, -4, 6, 23, -98};

        Arrays.sort(random2, Collections.reverseOrder());
        System.out.println(Arrays.toString(random2));

        // to reverse
        Collections.reverse(Arrays.asList(random2));

        int[] arr2 = new int[50];
        System.out.println(Arrays.toString(arr2));
        Arrays.fill(arr2, -7);
        System.out.println(Arrays.toString(arr2));

        int[] copy = Arrays.copyOf(arr2, arr2.length);
        copy[0] = 1000;
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(copy));

        System.out.println(Arrays.binarySearch(copy, -7)); //not the 1st match

        // test arrays are equal
        int[] s1 = {1, 2, 3, 4, 5};
        int[] s2 = {1, 2, 3, 4, 5};
        System.out.println(Arrays.equals(s1, s2)); // correct way
        System.out.println(s1.equals(s1));
        System.out.println(s1 == s2);

        String[] strArr = "Hello world again".split(" ");
        String str = "hello! ".repeat(10);
        String joined = String.join("/", strArr);

        System.out.println(Arrays.toString(strArr));
        System.out.println(str);
        System.out.println(joined);

        mutateMe(strArr);
        mutateMe(str, "apple");
        mutateMe(joined);

        System.out.println(Arrays.toString(strArr));
        System.out.println(str);
        System.out.println(joined);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter comma separated integers:");
        String[] input = sc.nextLine().split(",");
        int[] int_input = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            int_input[i] = Integer.parseInt(input[i].trim());
        }

        System.out.println(Arrays.toString(int_input));

        int[][] arr2D = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println(Arrays.deepToString(arr2D));
    }

    public static void mutateMe(String... args) {
        args[0] = "Changed";
        System.out.println(Arrays.toString(args));
    }
}

