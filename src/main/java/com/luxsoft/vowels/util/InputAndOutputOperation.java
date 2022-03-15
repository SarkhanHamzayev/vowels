package com.luxsoft.vowels.util;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class InputAndOutputOperation {

    public static List<String> loadWordsFromInput(Scanner scanner)
    {
        List<String> words = new LinkedList<>();

        while (scanner.hasNext())
            words.add(scanner.next());

        return words;
    }


    public static void writeContentToOutput(PrintWriter printWriter, String content)
    {
        printWriter.print(content);
        printWriter.close();
    }
}
