package commands;

import constants.CryptographicAlphabet;

import java.io.*;
import java.util.ArrayList;

public class DecryptionText {

    public static void decryptText(BufferedReader bufferedReader, BufferedWriter bufferedWriter, int position) throws IOException {

            while (bufferedReader.ready()) {
                // read one char:
                Character oneSymbol = (char)bufferedReader.read();
                // получим индекс, преобразуя char к нижнему регистру на случай если он в верхнем регистре:
                int index = CryptographicAlphabet.cryptographicAlphabet.indexOf(Character.toLowerCase(oneSymbol));
                checkChar(bufferedWriter, oneSymbol, index, position, CryptographicAlphabet.cryptographicAlphabet);
            }
    }

    public static void decryptTextForStaticMethod(BufferedReader bufferedReader, BufferedWriter bufferedWriter, int position) throws IOException {
        while (bufferedReader.ready()) {
            // read one char:
            Character oneSymbol = (char)bufferedReader.read();
            if (oneSymbol == ' ' || oneSymbol == '\n') {
                bufferedWriter.write(oneSymbol);
                continue;
            }
            // получим индекс, преобразуя char к нижнему регистру на случай если он в верхнем регистре:
            int index = CryptographicAlphabet.cryptographicAlphabetForStatic.indexOf(Character.toLowerCase(oneSymbol));
            checkChar(bufferedWriter, oneSymbol, index, position, CryptographicAlphabet.cryptographicAlphabetForStatic);
        }
    }

    public static void checkChar(BufferedWriter bufferedWriter, Character oneSymbol, int index, int position, ArrayList<Character> alphabet) throws IOException {
    /*
     теперь проверим без преобразования к нижнему регистру наши char-ы на идентичность
     если идентичны то записываем новый char смещаясь по алфавиту на 3 буквы вправо
     если не идентичны то записываем новый char в верхнем регистре (аналогично смещаясь на 3 буквы вправо)
     и в if и в else добавим проверку на выход за пределы границ алфавита при смещении
    */

            if (oneSymbol.equals(alphabet.get(index))) {
                decryptChar(bufferedWriter, index, position, alphabet);
            }
        /*
         случай когда char-ы не идентичны и записать нужно в верхнем регистре
         преобразуем к верхнему регистру, сначала получив char.
         два случая - сдвиг индекса не входит и входит в границы алфавита:
        */
            else {
                decryptCharInUpperCase(bufferedWriter, index, position, alphabet);
            }


    }

    public static void decryptCharInUpperCase(BufferedWriter bufferedWriter, int index, int position, ArrayList<Character> alphabet) throws IOException {
        /*
         проверяем условие: разность индекса и сдвига меньше нуля (выход за пределы массива)
         если да -получаем новый индекс - результат разности прибавляем к размеру массива.
        */
        if ((index -position)<0) {
            int newIndex = alphabet.size()+(index - position);
            Character newChar = alphabet.get(newIndex);
            bufferedWriter.write(Character.toUpperCase(newChar));
        } else {
            Character newChar = alphabet.get(index - position);
            bufferedWriter.write(Character.toUpperCase(newChar));
        }
    }

    public static void decryptChar(BufferedWriter bufferedWriter, int index, int position, ArrayList<Character> alphabet) throws IOException {
        /*
         проверяем условие: разность индекса и сдвига меньше нуля (выход за пределы массива)
         если да -получаем новый индекс - результат разности прибавляем к размеру массива.
        */
        if ((index - position)<0) {
            int newIndex = alphabet.size()+(index - position) ;
            bufferedWriter.write((int)alphabet.get(newIndex));
        } else {
            bufferedWriter.write((int)alphabet.get(index - position));
        }
    }
}
