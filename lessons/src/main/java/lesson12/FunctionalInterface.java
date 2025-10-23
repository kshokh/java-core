package lesson12;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.*;

/**
 * @author Shohjahon
 * @version 1
 */

public class FunctionalInterface {
    public static void main(String[] args) {
        //ManualGenerate();
        //ManualForEach();
        //ManualMap();
        //ManualFilter();
        //UnaryOperator();
        //BiFunctionAndThen();
        //Predicate();
        //ConsumerAndThen();
        //FunctionCombine();
        //SumBiFunction();
        //UpperCaseConsumer();
        //UUIDSupplier();
        //StringLength();
        //StringPredicate();
    }

    private static void ManualGenerate() {
        Supplier<Integer> randomIntSupplier = () -> (int) (Math.random() * 100);

        List<Integer> randomNumbers = generate(randomIntSupplier, 5);

        System.out.println(randomNumbers);

        Supplier<String> uuidSupplier = () -> UUID.randomUUID().toString();
        List<String> uuids = generate(uuidSupplier, 3);
        uuids.forEach(System.out::println);
    }

    public static <T> List<T> generate(Supplier<T> supplier, int n) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(supplier.get());
        }
        return result;
    }

    private static void ManualForEach() {
        List<String> words = List.of("apple", "pear", "banana", "kiwi");

        Consumer<String> print = System.out::println;

        forEach(words, print);
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T element : list) {
            consumer.accept(element);
        }
    }

    private static void ManualMap() {
        List<String> words = List.of("apple", "pear", "banana", "kiwi");

        Function<String, Integer> stringLength = String::length;

        List<Integer> lengths = map(words, stringLength);

        System.out.println(lengths);
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        List<R> result = new ArrayList<>();
        for (T element : list) {
            result.add(mapper.apply(element));
        }
        return result;
    }

    private static void ManualFilter() {
        List<String> words = List.of("apple", "pear", "banana", "kiwi");

        Predicate<String> longerThanFour = s -> s.length() > 4;

        List<String> filteredWords = filter(words, longerThanFour);

        System.out.println(filteredWords);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T element : list) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        return result;
    }

    private static void UnaryOperator() {
        UnaryOperator<String> addExclamations = s -> s + "!!!";

        String[] testStrings = {"Hello", "Java", "Shohjahon"};

        for (String str : testStrings) {
            System.out.println(addExclamations.apply(str));
        }
    }

    private static void BiFunctionAndThen() {
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        Function<Integer, String> toStr = x -> "Result: " + x;

        Function<Integer[], String> multiplyThenToStr = arr -> toStr.apply(multiply.apply(arr[0], arr[1]));

        int a = 5, b = 3;
        System.out.println(multiplyThenToStr.apply(new Integer[]{a, b}));

        a = 7;
        b = 4;
        System.out.println(multiplyThenToStr.apply(new Integer[]{a, b}));
    }

    private static void Predicate() {
        Predicate<Integer> isEven = n -> n % 2 == 0;

        Predicate<Integer> isPositive = n -> n > 0;

        Predicate<Integer> evenAndPositive = isEven.and(isPositive);

        Predicate<Integer> oddOrNegative = evenAndPositive.negate();

        int[] numbers = {-3, -2, 0, 1, 2, 3, 4};

        for (int n : numbers) {
            System.out.println(n + " -> evenAndPositive: " + evenAndPositive.test(n)
                    + ", oddOrNegative: " + oddOrNegative.test(n));
        }
    }

    private static void ConsumerAndThen() {
        Consumer<String> printString = s -> System.out.println("String: " + s);

        Consumer<String> printLength = s -> System.out.println("Length: " + s.length());

        Consumer<String> combined = printString.andThen(printLength);

        String[] testStrings = {"Hello", "Java", "Shohjahon"};

        for (String str : testStrings) {
            combined.accept(str);
        }
    }

    private static void FunctionCombine() {
        Function<String, String> trim = String::trim;

        Function<String, String> toUpperCase = String::toUpperCase;

        Function<String, String> trimThenUpper = trim.andThen(toUpperCase);

        String[] testStrings = {"  hello  ", "  Java ", " Shohjahon "};

        for (String str : testStrings) {
            System.out.println("'" + trimThenUpper.apply(str) + "'");
        }
    }

    private static void SumBiFunction() {
        BiFunction<Integer, Integer, Integer> sum = Integer::sum;

        System.out.println("5 + 3 = " + sum.apply(5, 3));
        System.out.println("10 + 20 = " + sum.apply(10, 20));
        System.out.println("-2 + 7 = " + sum.apply(-2, 7));
    }

    private static void UpperCaseConsumer() {
        Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());

        String[] testStrings = {"hello", "java", "shohjahon"};

        for (String str : testStrings) {
            printUpperCase.accept(str);
        }
    }

    private static void UUIDSupplier() {
        Supplier<UUID> uuidSupplier = UUID::randomUUID;

        for (int i = 0; i < 3; i++) {
            System.out.println(uuidSupplier.get());
        }
    }

    private static void StringLength() {
        Function<String, Integer> stringLength = String::length;

        String[] testStrings = {"Hello", "Java", "Shohjahon", ""};

        for (String str : testStrings) {
            System.out.println(str + " -> " + stringLength.apply(str));
        }
    }

    private static void StringPredicate() {
        Predicate<String> isValid = s -> s.length() > 3;

        String[] testStrings = {"", "Hi", "Java", "Shohjahon"};

        for (String str : testStrings) {
            System.out.println(str + " -> " + isValid.test(str));
        }
    }
}
