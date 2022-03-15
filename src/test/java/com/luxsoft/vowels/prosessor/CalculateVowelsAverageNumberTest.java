package com.luxsoft.vowels.prosessor;

import com.luxsoft.vowels.config.PropertiesConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class CalculateVowelsAverageNumberTest {


    @Spy
    PropertiesConfiguration configuration = PropertiesConfiguration.builder()
            .inputFileName("INPUT.TXT")
            .outputFileName("OUTPUT.TXT")
            .punctuationCharacters("\\pP")
            .averagePrecision("2")
            .averageRoundingMode("HALF_UP")
            .vowels("AeIoUy").build();

    @InjectMocks
    private CalculateVowelsAverageNumber averageNumber;

    List<String> inputWord;

    @Before
    public void setUp()  {

        inputWord = new ArrayList<>();
        inputWord.addAll(Arrays.asList("test","demo."));
    }

    @Test
    public void calculateVowelsAverageNumberPerVowelsSetAndWordLength() {

        assertThat(averageNumber.calculateVowelsAverageNumberPerVowelsSetAndWordLength(inputWord)).isNotNull();
    }
}