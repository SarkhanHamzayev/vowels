package com.luxsoft.vowels.util;

import com.luxsoft.vowels.config.PropertiesConfiguration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;


public class OutputFormatter
{
    private static final String EMPTY_STRING = "";

    public static String convertMapToString(Map<String, Double> vowelsAverageNumberPerVowelsSetAndWordLengthMap, PropertiesConfiguration configuration)
    {
        StringBuilder fileContentBuilder = new StringBuilder();

        vowelsAverageNumberPerVowelsSetAndWordLengthMap
            .forEach(
                (vowelsSetAndWordLength, vowelsAverageNumber) ->
                    fileContentBuilder
                        .append("(")
                        .append(vowelsSetAndWordLength)
                        .append(") -> ")
                        .append(formatAverage(vowelsAverageNumber,configuration))
                        .append(System.lineSeparator())
            );
        return fileContentBuilder
            .toString()
            .replaceFirst(System.lineSeparator() + "$", EMPTY_STRING)
            .replaceAll("\\[", "{")
            .replaceAll("]", "}");
    }

    private static BigDecimal formatAverage(Double average , PropertiesConfiguration configuration)
    {
        return BigDecimal.valueOf(average)
            .setScale(Integer.valueOf(configuration.getAveragePrecision()),
                RoundingMode.valueOf(configuration.getAverageRoundingMode()));
    }
}
