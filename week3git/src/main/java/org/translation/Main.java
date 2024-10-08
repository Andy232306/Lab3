package org.translation;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for this program.
 * Complete the code according to the "to do" notes.<br/>
 * The system will:<br/>
 * - prompt the user to pick a country name from a list<br/>
 * - prompt the user to pick the language they want it translated to from a list<br/>
 * - output the translation<br/>
 * - at any time, the user can type quit to quit the program<br/>
 */
public class Main {

    /**
     * This is the main entry point of our Translation System!<br/>
     * A class implementing the Translator interface is created and passed into a call to runProgram.
     * @param args not used by the program
     */
    public static void main(String[] args) {
        Translator translator = new JSONTranslator("sample.json");


        runProgram(translator);
    }

    /**
     * This is the method which we will use to test your overall program, since
     * it allows us to pass in whatever translator object that we want!
     * See the class Javadoc for a summary of what the program will do.
     * @param translator the Translator implementation to use in the program
     */
    public static void runProgram(Translator translator) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String country = promptForCountry(translator);
            if (country.equalsIgnoreCase("quit")) {
                break;
            }
            String language = promptForLanguage(translator, country);
            if (language.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println(country + " in " + language + " is " + translator.translate(country, language));
            System.out.println("Press enter to continue or quit to exit.");
            String input = scanner.nextLine();

            if ("quit".equalsIgnoreCase(input)) {
                break;
            }
        }
        System.out.println("Goodbye!");
        scanner.close();
    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForCountry(Translator translator) {
        List<String> countries = translator.getCountries();
        Collections.sort(countries);
        for (String country : countries) {
            System.out.println(country);
        }

        System.out.println("select a country from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();

    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForLanguage(Translator translator, String country) {

        List<String> languages = translator.getCountryLanguages(country);

        Collections.sort(languages);

        for (String language : languages) {
            System.out.println(language);
        }

        System.out.println("Select a language from above (or type 'quit' to exit):");


        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
