package commands;

import constants.CryptographicAlphabet;

import java.io.*;

public class BruteForce {
    public static void bruteForce(String pathInput, String pathOutput) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathInput));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathOutput))) {

                // считаем массив char:

                char[] cryptText = new char[65536];
                bufferedReader.read(cryptText);
                /*
                вызываем метод : проверяем регистр + вызываем новые методы - делаем сдвиги и получаем новые массивы.
                с новыми массивами работаем здесь
                проверку на правильно подобранный сдвиг - в новом методе.
                Все же понадобится цикл - для того чтобы попробовать разные сдвиги (от 1 до размера алфавита) с индексом.
                но это нужно делать не в отдельном методе, а здесь - чтобы потом вызывать проверки полученных массивов
                */
                for (int i = 1; i <= CryptographicAlphabet.cryptographicAlphabet.size(); i++) {
                    char[] leftRotateCryptText = checkCharWithLeftRotate(cryptText, i);
                    char[] rightRotateCryptText = checkCharWithRightRotate(cryptText, i);

                    boolean leftRotate = checking(leftRotateCryptText);
                    boolean rightRotate = checking(rightRotateCryptText);

                    if (leftRotate) {
                        bufferedWriter.write(leftRotateCryptText);
                        break;
                    }
                    else if (rightRotate) {
                        bufferedWriter.write(rightRotateCryptText);
                        break;
                    }
                }
            }

    }

    public static boolean checking(char[] rotateCryptText) {
        boolean result = false;
        for (int i = 0; i < rotateCryptText.length-2; i++) {
            if ((rotateCryptText[i]=='.' && rotateCryptText[i+1]==' ' && Character.isUpperCase(rotateCryptText[i+2])) || (rotateCryptText[i]=='?' && rotateCryptText[i+1]==' ' && Character.isUpperCase(rotateCryptText[i+2])) || (rotateCryptText[i]=='!' && rotateCryptText[i+1]==' ' && Character.isUpperCase(rotateCryptText[i+2]))) {
                result = true;
            }
        }

        return result;
    }

    public static char[] checkCharWithRightRotate(char[] cryptText, int rotate) {
        char[] newTextWithRightRotate = new char[65536];
        int i = 0; // индексы нового массива
        for (Character newChar : cryptText) {

            // получим индекс из алфавита, в нижнем регистре на случай заглавной буквы
            int index = CryptographicAlphabet.cryptographicAlphabet.indexOf(Character.toLowerCase(newChar));
            if (index == -1) {
                continue;
            }
            // проверим на регист и вызываем методы проверки границ - они должны вернуть новый char
            if (newChar.equals(CryptographicAlphabet.cryptographicAlphabet.get(index))) {
                newTextWithRightRotate[i] = getNewCharWithRightRotate(index, rotate);
            }
            else {
                newTextWithRightRotate[i] = getNewCharInUpperCaseWithRightRotate(index, rotate);
            }
            i++;
        }
        return newTextWithRightRotate;
        }

    public static char getNewCharInUpperCaseWithRightRotate(int index, int rotate) {
        char result = ' ';
        // два случая - сдвиг индекса не входит и входит в границы алфавита:
        if (CryptographicAlphabet.cryptographicAlphabet.size()<=(index + rotate)) {
            int newIndex = index + rotate - CryptographicAlphabet.cryptographicAlphabet.size();
            Character newChar = CryptographicAlphabet.cryptographicAlphabet.get(newIndex);
            result = Character.toUpperCase(newChar);

        } else {
            Character newChar = CryptographicAlphabet.cryptographicAlphabet.get(index + rotate);
            result = Character.toUpperCase(newChar);
        }
        return result;
    }

    public static char getNewCharWithRightRotate(int index, int rotate) {
        char result = ' ';
        /*
        проверяем условие: размер алфавита меньше чем сумма индекса и сдвига (rotate)
        если да, то получаем новый индекс - разность размера и суммы индекса и сдвига
        */
        if (CryptographicAlphabet.cryptographicAlphabet.size()<=(index + rotate)) {
            int newIndex = index + rotate - CryptographicAlphabet.cryptographicAlphabet.size();
            result = CryptographicAlphabet.cryptographicAlphabet.get(newIndex);

        } else {
            result = CryptographicAlphabet.cryptographicAlphabet.get(index + rotate);
        }
        return result;
    }


    public static char[] checkCharWithLeftRotate(char[] cryptText, int rotate) {
        char[] newTextWithLeftRotate = new char[65536];
        int i = 0; // индексы нового массива
        for (Character newChar : cryptText) {

            //получим индекс из алфавита, в нижнем регистре на случай заглавной буквы:
            int index = CryptographicAlphabet.cryptographicAlphabet.indexOf(Character.toLowerCase(newChar));
            // проверим на регист и вызываем методы проверки границ - они должны вернуть новый char
            if (index == -1) {
                continue;
            }

            if (newChar.equals(CryptographicAlphabet.cryptographicAlphabet.get(index))) {
                newTextWithLeftRotate[i] = getNewCharWithLeftRotate(index, rotate);
            }
            else {
                newTextWithLeftRotate[i] = getNewCharInUpperCaseWithLeftRotate(index, rotate);
            }
            i++;
        }
        return newTextWithLeftRotate;
    }

    public static char getNewCharInUpperCaseWithLeftRotate(int index, int rotate) {
        /*
         проверяем условие: разность индекса и сдвига меньше нуля (выход за пределы массива)
         если да -получаем новый индекс - результат разности прибавляем к размеру массива.
        */
        char result = ' ';
        if ((index - rotate)<0) {
            int newIndex = CryptographicAlphabet.cryptographicAlphabet.size()+(index - rotate);
            Character newChar = CryptographicAlphabet.cryptographicAlphabet.get(newIndex);
            result = Character.toUpperCase(newChar);
        } else {
            Character newChar = CryptographicAlphabet.cryptographicAlphabet.get(index - rotate);
            result = Character.toUpperCase(newChar);
        }
        return result;
    }

    public static char getNewCharWithLeftRotate(int index, int rotate) {
        /*
         проверяем условие: разность индекса и сдвига меньше нуля (выход за пределы массива)
         если да -получаем новый индекс - результат разности прибавляем к размеру массива.
        */
        char result = ' ';
        if ((index - rotate)<0) {
            int newIndex = CryptographicAlphabet.cryptographicAlphabet.size()+(index - rotate);
            result = CryptographicAlphabet.cryptographicAlphabet.get(newIndex);
        } else {
            result = CryptographicAlphabet.cryptographicAlphabet.get(index - rotate);
        }
        return result;

    }
}
