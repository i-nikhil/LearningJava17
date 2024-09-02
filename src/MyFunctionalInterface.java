import java.util.Date;

@FunctionalInterface
public interface MyFunctionalInterface {
    void execute();
}

@FunctionalInterface
interface Calculate<T> {
    T operate(T a, T b);
}

//important functional interfaces
//
//Consumer - void accept (T t)
//BiConsumer - void accept(T t, U u)
//Predicate - boolean test(T t)
//BiPredicate - boolean test(T t, U u)
//Function - R apply(T t)
//BiFunction - R apply(T t, U u)
//UnaryOperator - T apply(T t)
//BinaryOperator - T apply(T t1, T t2)
//Supplier - T get()

class CustomFunctionalInterfaceExample {
    public static void main(String[] args) {
        MyFunctionalInterface myFunc = () -> System.out.println("Executing...");// anonymous class implementation
        myFunc.execute();  // Prints "Executing..."

        MyFunctionalInterface func = () -> {
            int a = 10, b = 20;
            System.out.println("sum = " + (a + b));
        };

        func.execute();

        // default methods in interface
        Printer p = new HPPrinter();
        p.print();
        p.scan();
        p.loadInk(200);
        p.loadPaper(100);

        Printer.log("HP printer methods are called");

        System.out.println("Int diff: "+calculator((a, b) -> a - b, 10, 20));
        System.out.println("Str sum: "+calculator((a, b) -> a + b, "apple", " banana"));
    }

    public static <T> T calculator(Calculate<T> function, T a, T b) {
        return function.operate(a, b);
    }
}

interface Printer {
    void print();

    void scan();

    default void loadInk(int volume) {
        System.out.println(volume + " ml of ink loaded");
    }

    default void loadPaper(int pages) {
        System.out.println(pages + " number of pages loaded");
    }

    static void log(String description) {
        System.out.println(new Date() + ": " + description);
    }
}

class HPPrinter implements Printer {
    @Override
    public void print() {
        System.out.println(this.getClass().getName() + " is printing");
    }

    @Override
    public void scan() {
        System.out.println(this.getClass().getName() + " is scanning");
    }
}