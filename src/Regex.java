public class Regex {
    public static void main(String[] args) {

        // string format
        String helloWorld = "%s %s".formatted("Hello", "World");
        System.out.println(helloWorld);

        String helloWorld2 = String.format("%s %s", "Hello", "World");
        System.out.println(helloWorld2);

        String helloWorld3 = Regex.format("%s %s", "Hello", "World");
        System.out.println(helloWorld3);

        String test = "Anyone can learn abc's and 123's";

        // character classes

        System.out.println(test.replaceFirst("abc", "@"));
        System.out.println(test.replaceFirst("[abc]", "@")); // first match of anyone char
        System.out.println(test.replaceAll("[abc]", "@")); // first match of anyone char
        System.out.println(test.replaceFirst("123", "@"));
        System.out.println(test.replaceFirst("[123]", "@")); // first match of anyone char

        System.out.println(test.replaceFirst("a|b|c", "@")); // same as [abc]
        System.out.println(test.replaceFirst("ab|bc", "@")); // matches any one group

        System.out.println(test.replaceAll("[a-z]", "@")); // matches any in range
        System.out.println(test.replaceAll("[0-9]", "@")); // matches any in range
        System.out.println(test.replaceAll("[A-z0-9]", "@")); // matches any in range

        /*
        Quantifiers:
        b* -> "", "b", "bb", "bb...b"
        b+ -> "b", "bb", "bb...b"
        colou?r -> "color", "colour"
        b{3} -> "bbb"
        b{2,} -> "bb", "bb...b"
        b{2,4} -> "bb", "bbb", "bbbb"
         */

        System.out.println(test.replaceFirst("[A-z]*", "@"));
        System.out.println(test.replaceFirst("[0-9]*", "@")); // not match then replaces "" in 1st word
        System.out.println(test.replaceFirst("[0-9]+", "@")); // not matches ""
        System.out.println(test.replaceFirst("[0-9]{2}", "@"));

        /*
        Boundary
        "^." -> matches 1st character
        ".$" -> matches last character
        "\\b" -> matches 1st word
         */

        System.out.println(test.replaceFirst("[A-z]*$", "@"));
        System.out.println(test.replaceFirst("^[A-z]{3}", "@"));
        System.out.println(test.replaceFirst("[aA]nd\\b", "@"));
    }

    private static String format(String expression, String... args) {
        int i=0;
        //while(expression.contains("%s")) {
        while(expression.matches(".*%s.*")) { //. -> single character match, * -> any number of chars
            expression = expression.replaceFirst("%s", args[i++]);
        }
        return expression;
    }
}
