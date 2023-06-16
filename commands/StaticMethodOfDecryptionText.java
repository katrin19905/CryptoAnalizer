package commands;

import constants.CryptographicAlphabet;

import java.io.*;

public class StaticMethodOfDecryptionText {
    public static double[] getNumberOfChars(char[] chars) { // int[]
        // создадим array с числовыми значениями и заполним значения нулями, размер листа равен размеру алфавита
        // индексы ячеек array's будут совпадать с индексами букв алфавита
        // поэтому в ячейки array при нахождении буквы будем прибавлять 1, так найдем самую часто встречающуюся букву
        double[] countOfChars = new double[32];
        for (int i = 0; i < countOfChars.length; i++) {
            countOfChars[i] = 0;
        }
        // метод возвращает массив с подсчитанными символами
        return countCharsInText(chars, countOfChars);
    }

    public static double[] countCharsInText(char[] chars, double[] countOfChars) {
        // метод заполняет переданный массив числами - сколько раз встречается каждый символ.
        // индексу соответствует буква в исходном алфавите
        for (char oneChar : chars) {
            //проверим наличие символа в нашем алфавите
            if (CryptographicAlphabet.cryptographicAlphabetForStatic.contains(Character.toLowerCase(oneChar))) {
                // индекс - это индекс буквы в алфавите. прибавляем к значению в ячейке с данным индексом 1
                int index = CryptographicAlphabet.cryptographicAlphabetForStatic.indexOf(Character.toLowerCase(oneChar));
                countOfChars[index] += 1;
            }
        }
        int sumOfNumbers = 0;
        for (double countOfChar : countOfChars) {
            sumOfNumbers += countOfChar;
        }
        for (int i = 0; i < countOfChars.length; i++) {
            countOfChars[i] = countOfChars[i] / (sumOfNumbers * 1.0 / 100);
        }
        return countOfChars;
    }

    public static void staticDecryptMethod(String pathText, String pathCryptedText, String pathResult) throws IOException {
        try (BufferedReader bufferedReaderText = new BufferedReader(new FileReader(pathText));
             BufferedReader bufferedReaderCryptedText = new BufferedReader(new FileReader(pathCryptedText))) {
            // считаем массивы char из pathText и pathCryptedText, найдем самые часто встречающиеся буквы
            char[] nocryptText = new char[45000];
            bufferedReaderText.read(nocryptText);
            char[] cryptText = new char[45000];
            bufferedReaderCryptedText.read(cryptText);
            String cryptedString = String.valueOf(cryptText).trim(); // put in string crypted text
            double[] numberOfCharsInNoCryptText = getNumberOfChars(nocryptText);
            double[] numberOfCharsInCryptText = getNumberOfChars(cryptText);
            String decryptedString = checkingString(cryptedString, numberOfCharsInNoCryptText, numberOfCharsInCryptText);
            char[] decryptedStringCharArray = decryptedString.toCharArray();
            String result = checkingDecryptedString(decryptedString, decryptedStringCharArray);
            writeString(result, pathResult);
        }
    }

    private static String checkingDecryptedString(String decryptedString, char[] decryptedStringCharArray) {
        String result = null;
        for (int i = 0; i < decryptedStringCharArray.length-5; i++) {
            if ((decryptedStringCharArray[i] == ' ')
                    && (decryptedStringCharArray[i + 1] == 'г')
                    && (decryptedStringCharArray[i + 4] == 'а')
                    && (decryptedStringCharArray[i + 5] == ' ')) {
                String tempStr1 = decryptedString.replace(decryptedStringCharArray[i + 2], 'Ы');
                String tempStr2 = tempStr1.replace('о', decryptedStringCharArray[i + 2]);
                String tempStr3 = tempStr2.replace('Ы', 'о');
                String tempStr4 = tempStr3.replace(decryptedStringCharArray[i + 3], 'Ы');
                String tempStr5 = tempStr4.replace('д', decryptedStringCharArray[i + 3]);
                result = tempStr5.replace('Ы', 'д');
            }
        }
        char[] resultAsChars = result.toCharArray();
        for (int i = 0; i < resultAsChars.length-5; i++) {
            if ((resultAsChars[i]==' ')
                    && (resultAsChars[i+1]=='з')
                    && (resultAsChars[i+3]=='а')
                    && (resultAsChars[i+4]=='ю')
                    && (resultAsChars[i+5]==' ')) {
               String tempStr1 = result.replace(resultAsChars[i+2], 'Ы');
               String tempStr2 = tempStr1.replace('н', resultAsChars[i+2]);
               result = tempStr2.replace('Ы', 'н');
            }
        }
        char[] resultAsChars1 = result.toCharArray();
        for (int i = 0; i < resultAsChars1.length-7; i++) {
            if ((resultAsChars1[i] == ' ')
                    && (resultAsChars1[i+1]==resultAsChars1[i+8])
                    && (resultAsChars1[i+8]==resultAsChars1[i+9])
                    && (resultAsChars1[i+4]=='Р')
                    && (resultAsChars1[i+5]=='о')
                    && (resultAsChars1[i+6]==resultAsChars1[i+7])) {
                String tempStr1 = result.replace(resultAsChars1[i+1], 'Ы');
                String tempStr2 = tempStr1.replace('и', resultAsChars1[i+1]);
                String tempStr3 = tempStr2.replace('Ы', 'и');
                String tempStr4 = tempStr3.replace(resultAsChars1[i+2], 'Ы');
                String tempStr5 = tempStr4.replace('з', resultAsChars1[i+2]);
                String tempStr6 = tempStr5.replace('Ы', 'з');
                String tempStr7 = tempStr6.replace(resultAsChars1[i+6], 'Ы');
                String tempStr8 = tempStr7.replace('с', resultAsChars1[i+6]);
                result = tempStr8.replace('Ы', 'с');
            }
        }
        char[] resultAsChars2 = result.toCharArray();
        for (int i = 0; i < resultAsChars2.length-7; i++) {
            if ((resultAsChars2[i] == ' ')
                    && (resultAsChars2[i+1]=='с') // 2=п 3=р
                    && (resultAsChars2[i+4]=='о')
                    && (resultAsChars2[i+5]=='с')
                    && (resultAsChars2[i+6]=='и')
                    && (resultAsChars2[i+7]=='л')) {
                String tempStr1 = result.replace(resultAsChars2[i+2], 'Ы');
                String tempStr2 = tempStr1.replace('п', resultAsChars2[i+2]);
                String tempStr3 = tempStr2.replace('Ы', 'п');
                String tempStr4 = tempStr3.replace(resultAsChars2[i+3], 'Ы');
                String tempStr5 = tempStr4.replace('р', resultAsChars2[i+3]);
                result = tempStr5.replace('Ы', 'р');
            }
        }
        return result;
    }

    public static void writeString(String decryptedString, String pathResult) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathResult))) {
            bufferedWriter.write(decryptedString);
        }
    }

    private static String checkingString(String cryptedString, double[] numberOfCharsInNoCryptText, double[] numberOfCharsInCryptText) {
        char[] charsForSwap = new char[32];
        String result = null;
        for (int i = 0; i < numberOfCharsInCryptText.length; i++) {
            for (int i1 = 0; i1 < numberOfCharsInNoCryptText.length; i1++) {
                // find same double value
                if (Math.abs(numberOfCharsInCryptText[i] - numberOfCharsInNoCryptText[i1]) < 0.05) {
                    charsForSwap[i] = CryptographicAlphabet.cryptographicAlphabetForStatic.get(i1);
                    charsForSwap[i1] = CryptographicAlphabet.cryptographicAlphabetForStatic.get(i);
                }
            }
        }
        for (int i = 0; i < charsForSwap.length / 2; i++) {
            result = decryptString(cryptedString, charsForSwap, 0);
            // тут доделать рекурсию
            // в метод передаем строку, а также старый и новый символ (старый меняем на новый)
        }
        return result;


    }

    private static String decryptString(String cryptedString, char[] charsForSwap, int i) {
        String tempStr1 = cryptedString.replace(CryptographicAlphabet.cryptographicAlphabetForStatic.get(i),
                'Ы');
        String tempStr2 = tempStr1.replace(charsForSwap[i],
                CryptographicAlphabet.cryptographicAlphabetForStatic.get(i));
        String result = tempStr2.replace('Ы',
                CryptographicAlphabet.cryptographicAlphabetForStatic.get(i));
        i++;
        if (i < CryptographicAlphabet.cryptographicAlphabetForStatic.size() / 2) {
            result = decryptString(result, charsForSwap, i);
        }
        return result;
    }
}




