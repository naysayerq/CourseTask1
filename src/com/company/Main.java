/**
 *
 * @classname Main
 *
 * @version 1.0
 *
 * @copyright Skryp Andriy
 *
 *  1.              GLOSSARY
 *  1.1. Download a text about Harry Potter.
 *  1.2. For each distinct word in the text calculate the number of occurrence.
 *  1.3. Use RegEx.
 *  1.4. Sort in the DESC mode by the number of occurrence..
 *  1.5. Find  the first 20 pairs.
 *  1.6  Find all the proper names
 *  1.7. Count them and arrange in alphabetic order.
 *  1.8. First 20 pairs and names write into to a file test.txt
 *  1.9. Create a fine header for the file
 *  1.10 Use Java  Collections to demonstrate your experience (Map, List )
 *
 */

package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class Main {

    public static void main(String[] args) throws IOException {


        // 1.1. Download a text about Harry Potter.

        String text = new String(Files.readAllBytes(Paths.get("C:\\Users\\Intel\\Desktop\\harry.txt")));


        // 1.3. Use RegEx.

        String cleanedText = text.replaceAll("\\.", "")
                .replaceAll(",", "")
                .replaceAll("\"", "")
                .replaceAll("�", "")
                .replaceAll("\\?", "")
                .replaceAll("\\!", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .replaceAll("\\.", "")
                .replaceAll("\\;", "")
                .replaceAll("\\:", "")
                .replaceAll("--", "");

        // split array

        String[] words = cleanedText.split(" ");


        // 1.2. For each distinct word in the text calculate the number of occurrence. Upper case matters.


        // Using hashmap, counting the number of occurence

        Arrays.stream(words).collect(Collectors
                .groupingBy(Function.<String>identity(), HashMap::new, counting())).entrySet()
                .forEach(System.out:: println);


        /* 1.4. Sort in the DESC mode by the number of occurrence
        *
        * (Full text)
        *
        * +++
        *
        * 1.10. Using Map and Streams */

        /**
         * @param Map <String, Long> Using map and streams, sorting by value in reverse mode
         *
         * @return sorted keys and values
         */

        Map<String, Long> result = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.<String>identity(),
                        counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long> comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (l,r) -> l,
                        LinkedHashMap::new));



        /* 1.5. Find  the first 20 pairs.
        *
        * (Only 20 pairs key-value)
        *
        * +++
        *
        * 1.10. Using Map and Streams */


        /**
         * @param Map <String, Long> Using map and streams, sorting, etc
         *            finding first 20 pairs (with .limit(20))
         *
         * @return first 20 pairs
         */

        Map<String, Long> result1 = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.<String>identity(),
                        counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long> comparingByValue().reversed())
                .limit(20)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (l,r) -> l,
                        LinkedHashMap::new));


        // Creating String and converting results to String

        String twenty2 = result1.toString();


        /* 1.8. Writing first 20 pairs in file harry20.txt
        *
        * +++
        *
        * 1.9.  Create a fine header for the file */



        // Creating file harry20.txt

        try {

            // Our Header

            String content = "Hello. You can find first 20 pairs key-value of glossary of Harry Potter \n"
                    + twenty2; // Creating header and adding content in variable


            // Creating file

            File f = new File("C:\\Users\\Intel\\IdeaProjects\\CourseTask1\\src\\com\\company\\harry20.txt");

            FileWriter fw = new FileWriter(f.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        /*                        OUTCOME
        *
        * Hello. You can find first 20 pairs key-value of glossary of Harry Potter
        * {the=3311, to=1845, and=1799, a=1577, of=1242, was=1178, he=1029, Harry=969, in=933, his=896,
        * it=804, said=789, you=734, had=697, on=617, at=581, that=580, I=537, him=495}
        *
        *                         OUTCOME*/


    }
}