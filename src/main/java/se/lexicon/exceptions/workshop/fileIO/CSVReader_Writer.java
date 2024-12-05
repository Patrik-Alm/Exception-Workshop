package se.lexicon.exceptions.workshop.fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader_Writer {
    /**
     * This method getMaleFirstNames should use a try-catch-finally without resources
     * Should catch NoSuchFileException and IOException
     * You should also close the Buffered reader in the 'finally' block.
     *
     * @return List<String>of male firstnames
     */
    public static List<String> getMaleFirstNames() {

        BufferedReader reader = null;
        List<String> names = null;

        try {
            reader = Files.newBufferedReader(Paths.get("firstname_males.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        }

        catch (NoSuchFileException e) {

            System.out.println("Sorry, file 'firstname_males.txt' not found");
        }
        catch (IOException e1) {
            System.out.println("Sorry, an IO Exception has occurred");
        }
        finally {

            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return names;
    }


    /**
     * This method getFemaleFirstNames should make use of a try-catch with resources
     *
     * @return List<String>of female firstnames
     */
    public static List<String> getFemaleFirstNames() {

        List<String> names = null;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("firstname_female.txt")))
        {
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Sorry, we got an IO Exception ");
            e.printStackTrace();
        }
        return names;
    }


    /**
     * This method fetches strings from a file and put them into a list
     * This method might throw IOException which due to the throws clause need to
     * be handled by the caller.
     *
     * @return List <String> of last names
     * @throws IOException
     */
    public static List<String> getLastNames() throws IOException {

        List<String> names = null;
        BufferedReader reader = null;

        try {
            reader = Files.newBufferedReader(Paths.get("lastnames.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());


        }

        finally {
            if (reader != null) {
                reader.close();
            }
        }
        return names;
    }


    public static void saveLastNames(List<String> lastNames) {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("lastnames.txt"))) {
            for (String toWrite : lastNames) {
                writer.append(toWrite + ",");
            }

            writer.flush();
        }
           catch (IOException e) {
               System.out.println("Sorry, an IO Exception occurred ");
               e.printStackTrace();
        }
    }

    public static void saveFemaleNames(List<String> femaleNames) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_female.txt"))) {
            for (String toWrite : femaleNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Sorry, an IO Exception occurred ");
            e.printStackTrace();
        }
    }


    public static void saveMaleNames(List<String> maleNames) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_males.txt"))) {
            for (String toWrite : maleNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Sorry, an IO Exception occurred ");
            e.printStackTrace();
        }
    }


}
