package com.luxsoft.vowels.prosessor;

import com.luxsoft.vowels.config.PropertiesConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static com.luxsoft.vowels.util.InputAndOutputOperation.loadWordsFromInput;
import static com.luxsoft.vowels.util.InputAndOutputOperation.writeContentToOutput;
import static com.luxsoft.vowels.util.OutputFormatter.convertMapToString;
import static java.io.File.separator;
import static java.nio.file.Paths.get;

@Log4j2
@Component
@AllArgsConstructor
public class VowelsExecutor {

    public static final String CANNOT_SAVE = "Cannot save file: ";
    public static final String CANNOT_OPEN = "Cannot open file: ";
    public static final String MAIN_RESOURCES_PATH = "src" + separator + "main" + separator + "resources" + separator;


    private CalculateVowelsAverageNumber averageNumber;
    private WordCheck wordCheck;


    public void process(PropertiesConfiguration configuration) throws IOException {

        Path inputFilePathAndName = get(MAIN_RESOURCES_PATH + configuration.getInputFileName());
        String outputFilePathAndName = MAIN_RESOURCES_PATH + configuration.getOutputFileName();

        try {
            List<String> words = wordCheck.removePunctuation(loadWordsFromInput(new Scanner(inputFilePathAndName)));

            PrintWriter printWriterFile = new PrintWriter(new File(outputFilePathAndName));
            String content = convertMapToString(averageNumber.calculateVowelsAverageNumberPerVowelsSetAndWordLength(words),configuration);
            writeContentToOutput(printWriterFile, content);
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException(CANNOT_SAVE + outputFilePathAndName);
        }
        catch (IOException e) {
            throw new IOException(CANNOT_OPEN + inputFilePathAndName.toString());
        }
    }
}
