package lesson12.streamapi;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Shohjahon
 * @version 1
 */

public class WithStream {
    public static void main(String[] args) {
        //MostExpensive();
        //WordsFromSentences();
        //JoinNames();
        //GroupByFirstLetter();
        //ListToMap();
        //RemoveDuplicatesAndSort();
        //AverageStringLength();
        //MinMax();
        //CountLongWords();
        //EvenSquares();
    }

    private static void MostExpensive() {
        List<Product> products = List.of(
                new Product("Phone", "Electronics", 1200),
                new Product("TV", "Electronics", 1800),
                new Product("Apple", "Fruits", 2.5),
                new Product("Mango", "Fruits", 4.0)
        );

        Map<String, Optional<Product>> mostExpensive = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.maxBy(Comparator.comparingDouble(Product::price))
                ));

        mostExpensive.forEach((category, productOpt) ->
                productOpt.ifPresent(product ->
                        System.out.println(category + " -> " + product.name() + " ($" + product.price() + ")")
                )
        );
    }

    private static void WordsFromSentences() {
        List<String> sentences = List.of("Java is cool", "Streams are powerful");

        List<String> words = sentences.stream()
                .flatMap(sentence -> Stream.of(sentence.split(" ")))
                .collect(Collectors.toList());

        System.out.println(words);
    }

    private static void JoinNames() {
        List<String> names = List.of("Tom", "Jerry", "Spike");

        String result = names.stream()
                .collect(Collectors.joining(", "));

        System.out.println(result);
    }

    private static void GroupByFirstLetter() {
        List<String> names = List.of("Alice", "Andrew", "Bob", "Charlie", "Catherine");

        Map<Character, List<String>> grouped = names.stream()
                .collect(Collectors.groupingBy(name -> name.charAt(0)));

        System.out.println(grouped);
    }

    private static void ListToMap() {
        List<String> fruits = List.of("apple", "banana", "kiwi");

        Map<String, Integer> fruitLengthMap = fruits.stream()
                .collect(Collectors.toMap(
                        fruit -> fruit,
                        String::length
                ));

        System.out.println(fruitLengthMap);
    }

    private static void RemoveDuplicatesAndSort() {
        List<String> input = List.of("apple", "pear", "apple", "banana", "pear");

        List<String> result = input.stream()
                .distinct()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println(result);
    }

    private static void AverageStringLength() {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        double average = names.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0);

        System.out.println("Average length: " + average);
    }

    private static void MinMax() {
        List<Integer> nums = List.of(10, 2, 33, 4, 25);

        Optional<Integer> max = nums.stream().max(Integer::compareTo);
        Optional<Integer> min = nums.stream().min(Integer::compareTo);

        max.ifPresent(m -> System.out.println("Maximum: " + m));
        min.ifPresent(m -> System.out.println("Minimum: " + m));
    }

    private static void CountLongWords() {
        List<String> words = List.of("apple", "banana", "pear", "pineapple");

        long count = words.stream()
                .filter(word -> word.length() > 5)
                .count();

        System.out.println("Number of words longer than 5 characters: " + count);
    }

    private static void EvenSquares() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .forEach(System.out::println);
    }
}
