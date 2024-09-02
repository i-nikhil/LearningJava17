public record CarRec(String model, String color, int engine, Boolean isConvertible) {
}

// Can't inherit a record as its final in nature
//class Tesla extends CarRec
//{
//    public Tesla(String model, String color, int engine, Boolean isConvertible) {
//        super(model, color, engine, isConvertible);
//    }
//}