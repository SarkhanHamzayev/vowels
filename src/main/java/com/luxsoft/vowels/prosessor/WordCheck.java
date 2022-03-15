package com.luxsoft.vowels.prosessor;

import com.luxsoft.vowels.config.PropertiesConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class WordCheck {

    private static final String EMPTY_STRING = "";
    private PropertiesConfiguration configuration;

    public List<String> removePunctuation(List<String> words) {
        return words
                .stream()
                .map(wordWithPunctuation -> wordWithPunctuation
                        .replaceAll(configuration.getPunctuationCharacters(), EMPTY_STRING))
                .filter(word -> !word.equals(EMPTY_STRING))
                .distinct()
                .map(String::toLowerCase)
                .collect(toList());
    }


}
