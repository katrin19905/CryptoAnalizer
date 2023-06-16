import commands.BruteForce;
import commands.CryptionText;
import commands.DecryptionText;
import commands.StaticMethodOfDecryptionText;
import constants.CryptographicAlphabet;

import java.io.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! It's program of crypt/decrypt text. \n" +
                "Please enter any command: \n" +
                "If you want to crypt any text, \n" +
                "please enter CRYPT \n" +
                "If you want to decrypt any text, \n" +
                "please enter DECRYPT  \n" +
                "If you want to see cryptographic alphabet, please enter ALPHABET "
        );
        String selection = scanner.nextLine();
        switch (selection) {
            case "CRYPT" -> {
                System.out.println("Please choose type of crypt:\n" +
                        "for Caesar's crypt enter CRYPT, \n" +
                        "to crypt text for static decrypt method enter STATIC");
                String cryptChoise = scanner.nextLine();
                switch (cryptChoise) {
                    case "CRYPT" -> {
                        System.out.println("Please enter one path to .txt file for crypt, \n" +
                                "second path to .txt empty file which will be result,\n" +
                                "and count of rotate");
                        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(scanner.nextLine()));
                             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(scanner.nextLine()))) {
                            CryptionText.cryptText(bufferedReader, bufferedWriter, scanner.nextInt(), CryptographicAlphabet.cryptographicAlphabet);
                        }
                        System.out.println("Cryption is success! Check your file");
                    }
                    case "STATIC" -> {
                        System.out.println("Please enter one path to .txt file for crypt, \n" +
                                "second path to .txt empty file which will be result");
                        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(scanner.nextLine()));
                             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(scanner.nextLine()))) {
                            CryptionText.cryptTextWithWhitespaces(bufferedReader, bufferedWriter, CryptographicAlphabet.cryptographicAlphabetForStatic);
                        }
                        System.out.println("Cryption is success! Now you can to use static decrypt method");
                    }
                }

            }
            case "DECRYPT" -> {
                System.out.println("Please choose one method of decryption:\n" +
                        "to decrypt by Caesar's method enter DECRYPT\n" +
                        "to decrypt by Brute Force method enter BRUTE,\n" +
                        "to decrypt by static method enter STATIC\n");
                String decryptChoise = scanner.nextLine();
                switch (decryptChoise) {
                    case "DECRYPT" -> {
                        System.out.println("Please enter one path to .txt file for decrypt,\n" +
                                "second path to .txt empty file which will be result,\n" +
                                "and enter count of rotate");
                        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(scanner.nextLine()));
                             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(scanner.nextLine()))) {
                            DecryptionText.decryptText(bufferedReader, bufferedWriter, scanner.nextInt());
                        }
                        System.out.println("Decryption is success! Please check file");
                    }
                    case "BRUTE" -> {
                        System.out.println("Please enter one path to .txt file for decrypt\n" +
                                "and second path to .txt empty file which will be result\n");
                        BruteForce.bruteForce(scanner.nextLine(), scanner.nextLine());
                        System.out.println("Brute force finished! Please check file.");
                    }
                    case "STATIC" -> {
                        System.out.println("Please enter one path to .txt file with text for analyze,\n" +
                                "enter second path to .txt file for decrypt,\n" +
                                "third path to .txt empty file which will be result.\n" +
                                "Please use same text for analyze and for decrypt\n");
                        StaticMethodOfDecryptionText.staticDecryptMethod(scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
                        System.out.println("Static decrypt method finished! Check file");
                    }
                }


            }

            case "ALPHABET" -> {
                System.out.println(CryptographicAlphabet.cryptographicAlphabet);
            }
        }
    }
}
