package com.luxsoft.vowels.prosessor;

import com.luxsoft.vowels.config.PropertiesConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
public class WordCheckTest {

    @Spy
    PropertiesConfiguration configuration = PropertiesConfiguration.builder()
            .inputFileName("INPUT.TXT")
            .outputFileName("OUTPUT.TXT")
            .punctuationCharacters("\\pP")
            .averagePrecision("2")
            .averageRoundingMode("HALF_UP")
            .vowels("AeIoUy").build();

    @InjectMocks
    private WordCheck wordCheck;

    List<String > inputWord;
    List<String> outputWord;

    @Before
    public void setUp()  {


        inputWord = new ArrayList<>();
        inputWord.addAll(Arrays.asList("test","demo.","test1"));

        outputWord = new ArrayList<>();
        outputWord.addAll(Arrays.asList("test","demo","test1"));

    }

    @Test
    public void removePunctuation() {
        assertThat(wordCheck.removePunctuation(inputWord)).isNotNull();
        assertThat(wordCheck.removePunctuation(inputWord)).isEqualTo(outputWord);

    }
}