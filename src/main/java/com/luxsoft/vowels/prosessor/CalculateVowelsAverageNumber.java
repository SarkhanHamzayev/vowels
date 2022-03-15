package com.luxsoft.vowels.prosessor;

import com.luxsoft.vowels.config.PropertiesConfiguration;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;


@Component
public class CalculateVowelsAverageNumber {

    private PropertiesConfiguration configuration;

    public CalculateVowelsAverageNumber(PropertiesConfiguration configuration) {
        this.configuration = configuration;
    }

    public Map<String, Double> calculateVowelsAverageNumberPerVowelsSetAndWordLength(List<String> words){
        return words
                .stream()
                .sorted(getReversed())
                .collect(calculateVowelsAverageMapCollector());
    }



    private Collector<String, ?, LinkedHashMap<String, Double>> calculateVowelsAverageMapCollector() {
        return groupingBy(
                word -> getUniqueSortedListOfVowels(word) + ", " + word.length(),
                LinkedHashMap::new,
                averagingLong(this::getCountOfVowels)
        );
    }

    private Comparator<String> getReversed() {
        return comparing(String::length).reversed();
    }

    private List<Character> getUniqueSortedListOfVowels(String word)
    {
        return getVowelsStream(word)
                .distinct()
                .sorted()
                .collect(toList());
    }

    private Long getCountOfVowels(String word)
    {
        return getVowelsStream(word)
                .count();
    }

    private Stream<Character> getVowelsStream(String word)
    {
        return word
                .chars()
                .mapToObj(integerValueOfChar -> (char) integerValueOfChar)
                .filter(checkVowelsCharacter());
    }

    private Predicate<Character> checkVowelsCharacter() {
        return letter -> configuration.getVowels()
                .toLowerCase()
                .contains(letter.toString());
    }
}
