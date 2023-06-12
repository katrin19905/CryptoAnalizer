import java.io.*;

public class StaticMethodOfDecryptionText {
    public static Character getChar(char[] chars) {
        char result = ' ';
        // создадим array с числовыми значениями и заполним значения нулями, размер листа равен размеру алфавита
        // индексы ячеек array's будут совпадать с индексами букв алфавита
        // поэтому в ячейки array при нахождении буквы будем прибавлять 1, так найдем самую часто встречающуюся букву
        int[] countOfChars = new int[33];

        for (int i=0; i<countOfChars.length; i++) {
            countOfChars[i]=0;
        }
        countCharsInText(chars, countOfChars);
        // находим максимальное число в нашем массиве и его индекс
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < countOfChars.length; i++) {
            if (countOfChars[i]>max) {
                max = countOfChars[i];
                index = i;
            }
        }

        result = CryptographicAlphabet.cryptographicAlphabet.get(index);
        return result;
    }

    public static void countCharsInText(char[] chars, int[] countOfChars) {
        for (char oneChar : chars) {
            //проверим наличие символа в нашем алфавите
            if (CryptographicAlphabet.cryptographicAlphabetForStatic.contains(Character.toLowerCase(oneChar))) {
                // индекс - это индекс буквы в алфавите. прибавляем к значению в ячейке с данным индексом 1
                int index = CryptographicAlphabet.cryptographicAlphabetForStatic.indexOf(Character.toLowerCase(oneChar));
                countOfChars[index] += 1;

            }
        }
    }

    public static void staticDecryptMethod (String pathText, String pathCryptedText, String pathResult) throws IOException {
        int rotate = 0;
        try (BufferedReader bufferedReaderText = new BufferedReader(new FileReader(pathText));
             BufferedReader bufferedReader = new BufferedReader(new FileReader(pathCryptedText))) {
            // считаем массивы char из pathText и pathCryptedText, найдем самые часто встречающиеся буквы
            char[] nocryptText = new char[65536];
            bufferedReaderText.read(nocryptText);
            char[] cryptText = new char[65536];
            bufferedReader.read(cryptText);
            Character charInNoCryptText = getChar(nocryptText);
            Character charInCryptText = getChar(cryptText);
            // найдем индексы char'ов в алфавите и вычислим сдвиг. далее вызовем метод cryptText класса DecryptionText
            int indexOfCharInNoCrypText = CryptographicAlphabet.cryptographicAlphabetForStatic.indexOf(Character.toLowerCase(charInNoCryptText));
            int indexOfCharInCryptText = CryptographicAlphabet.cryptographicAlphabetForStatic.indexOf(Character.toLowerCase(charInCryptText));
            rotate = indexOfCharInCryptText - indexOfCharInNoCrypText;
            if (rotate<0) {
                rotate *= -1;
            }
            System.out.println("rotate is "+rotate);
            System.out.println("самая частая буква в тексте - "+ charInNoCryptText);
            System.out.println("самая частая буква в зашифрованном тексте - "+charInCryptText);


        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathCryptedText));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathResult))) {
            DecryptionText.decryptTextForStaticMethod(bufferedReader, bufferedWriter, rotate);
        }
    }



}
