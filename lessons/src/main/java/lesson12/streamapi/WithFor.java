package lesson12.streamapi;

import java.util.*;

/**
 * @author Shohjahon
 * @version 1
 */

public class WithFor {
    public static void main(String[] args) {
        //EvenSquares();
        //CountLongWords();
        //FindMinMax();
        //AverageStringLength();
        //RemoveDuplicatesAndSort();
        //ToMap();
        //GroupByFirstLetter();
        //JoinNames();
        //SplitSentences();
        //MostExpensive();
    }

    private static void MostExpensive() {
        List<Product> products = List.of(
                new Product("Phone", "Electronics", 1200),
                new Product("TV", "Electronics", 1800),
                new Product("Apple", "Fruits", 2.5),
                new Product("Mango", "Fruits", 4.0)
        );

        Map<String, Product> mostExpensive = new HashMap<>();

        for (Product p : products) {
            if (!mostExpensive.containsKey(p.category()) ||
                    p.price() > mostExpensive.get(p.category()).price()) {
                mostExpensive.put(p.category(), p);
            }
        }

        for (String category : mostExpensive.keySet()) {
            Product product = mostExpensive.get(category);
            System.out.println(category + " -> " + product.name() + " ($" + product.price() + ")");
        }
    }

    private static void SplitSentences() {
        List<String> sentences = List.of("Java is cool", "Streams are powerful");

        List<String> words = new ArrayList<>();

        for (String sentence : sentences) {
            String[] splitWords = sentence.split(" ");
            words.addAll(Arrays.asList(splitWords));
        }

        System.out.println(words);
    }

    private static void JoinNames() {
        List<String> names = List.of("Tom", "Jerry", "Spike");

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            result.append(names.get(i));
            if (i < names.size() - 1) {
                result.append(", ");
            }
        }

        System.out.println(result);
    }

    private static void GroupByFirstLetter() {
        List<String> names = List.of("Alice", "Andrew", "Bob", "Charlie", "Catherine");

        Map<Character, List<String>> grouped = new HashMap<>();

        for (String name : names) {
            char firstLetter = name.charAt(0);

            if (!grouped.containsKey(firstLetter)) {
                grouped.put(firstLetter, new ArrayList<>());
            }

            grouped.get(firstLetter).add(name);
        }

        System.out.println(grouped);
    }

    private static void ToMap() {
        List<String> fruits = List.of("apple", "banana", "kiwi");

        Map<String, Integer> fruitMap = new HashMap<>();

        for (String fruit : fruits) {
            fruitMap.put(fruit, fruit.length());
        }

        System.out.println(fruitMap);
    }

    private static void RemoveDuplicatesAndSort() {
        List<String> input = List.of("apple", "pear", "apple", "banana", "pear");

        Set<String> uniqueSet = new HashSet<>(input);

        List<String> uniqueList = new ArrayList<>(uniqueSet);

        for (int i = 0; i < uniqueList.size() - 1; i++) {
            for (int j = i + 1; j < uniqueList.size(); j++) {
                if (uniqueList.get(i).length() > uniqueList.get(j).length()) {
                    String temp = uniqueList.get(i);
                    uniqueList.set(i, uniqueList.get(j));
                    uniqueList.set(j, temp);
                }
            }
        }

        System.out.println("Result: " + uniqueList);
    }

    private static void AverageStringLength() {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        int totalLength = 0;

        for (String name : names) {
            totalLength += name.length();
        }

        double average = (double) totalLength / names.size();

        System.out.println("Average length: " + average);
    }

    private static void FindMinMax() {
        List<Integer> nums = List.of(10, 2, 33, 4, 25);

        int max = nums.getFirst();
        int min = nums.getFirst();

        for (int num : nums) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        System.out.println("Maximum: " + max);
        System.out.println("Minimum: " + min);
    }

    private static void CountLongWords() {
        List<String> words = List.of("apple", "banana", "pear", "pineapple");

        int count = 0;
        for (String word : words) {
            if (word.length() > 5) {
                count++;
            }
        }

        System.out.printf("Number of words longer than 5 characters: %s.", count);
    }

    private static void EvenSquares() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        for (int number : numbers) {
            if (number % 2 == 0) {
                int square = number * number;
                System.out.println(square);
            }
        }
    }
}
