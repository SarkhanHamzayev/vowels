package com.luxsoft.vowels.prosessor;

import com.luxsoft.vowels.config.PropertiesConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static com.luxsoft.vowels.prosessor.VowelsExecutor.MAIN_RESOURCES_PATH;
import static com.luxsoft.vowels.util.OutputFormatter.convertMapToString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
public class VowelsExecutorTest {

    PropertiesConfiguration configuration ;

    @Mock
    private WordCheck wordCheck;

    @Mock
    private CalculateVowelsAverageNumber averageNumber;

    @InjectMocks
    private VowelsExecutor vowelsExecutor;

    List<String > inputWord;
    List<String> outputWord;
    Map<String, Double> averageNumberMap;
    @Before
    public void setUp()  {
        configuration = PropertiesConfiguration.builder()
                .inputFileName("INPUT.TXT")
                .outputFileName("OUTPUT.TXT")
                .punctuationCharacters("\\pP")
                .averagePrecision("2")
                .averageRoundingMode("HALF_UP")
                .vowels("AeIoUy").build();

        inputWord = new ArrayList<>();
        inputWord.addAll(Arrays.asList("test","demo.","test1"));

        outputWord = new ArrayList<>();
        inputWord.addAll(Arrays.asList("test","demo","test1"));
        averageNumberMap = new HashMap<>();
        averageNumberMap.put("a",1d);

    }


    @Test(expected= IOException.class)
    public void processIOException() throws IOException {
        configuration.setInputFileName("INPUT1.TXT");
        when(wordCheck.removePunctuation(inputWord)).thenReturn(outputWord);
        when(averageNumber.calculateVowelsAverageNumberPerVowelsSetAndWordLength(outputWord)).thenReturn(averageNumberMap);
        vowelsExecutor.process(configuration);

    }

    @Test()
    public void process() throws IOException {
        when(wordCheck.removePunctuation(inputWord)).thenReturn(outputWord);
        when(averageNumber.calculateVowelsAverageNumberPerVowelsSetAndWordLength(outputWord)).thenReturn(averageNumberMap);
        String content = convertMapToString(averageNumberMap,configuration);
        vowelsExecutor.process(configuration);
        assertEquals(content, new String(Files.readAllBytes(Paths.get(MAIN_RESOURCES_PATH+configuration.getOutputFileName()))));
        ;


    }


}