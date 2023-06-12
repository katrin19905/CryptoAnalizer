import java.io.*;
import java.util.ArrayList;

public class CryptionText {

    public static void cryptText(BufferedReader bufferedReader, BufferedWriter bufferedWriter, int position, ArrayList<Character> alphabet) throws IOException {

            while (bufferedReader.ready()) {
                // read one char:
                Character oneSymbol = (char)bufferedReader.read();
                // получим индекс, преобразуя char к нижнему регистру на случай если он в верхнем регистре:
                int index = alphabet.indexOf(Character.toLowerCase(oneSymbol));
                // индекс = -1 если char нет в нашем алфавите и элемент нужно пропустить
                if (index != -1) {
                    checkingChar(bufferedWriter, oneSymbol, index, position, alphabet);
                }
            }
    }

    public static void cryptTextWithWhitespaces (BufferedReader bufferedReader, BufferedWriter bufferedWriter, int position, ArrayList<Character> alphabet) throws IOException {
        while (bufferedReader.ready()) {
            // read one char:
            Character oneSymbol = (char)bufferedReader.read();
            if (oneSymbol == ' ' || oneSymbol == '\n') {
                bufferedWriter.write(oneSymbol);
                continue;
            }
            // получим индекс, преобразуя char к нижнему регистру на случай если он в верхнем регистре:
            int index = alphabet.indexOf(Character.toLowerCase(oneSymbol));
            // индекс = -1 если char нет в нашем алфавите и элемент нужно пропустить
            if (index != -1) {
                checkingChar(bufferedWriter, oneSymbol, index, position, alphabet);
            }
        }
    }


    public static void checkingChar(BufferedWriter bufferedWriter, Character oneSymbol, int index, int position, ArrayList<Character> alphabet) throws IOException {
        /*
         проверим без преобразования к нижнему регистру наши char-ы на идентичность
         если идентичны то записываем новый char смещаясь по алфавиту на position букв вправо
         если не идентичны то записываем новый char в верхнем регистре (аналогично смещаясь на position букв вправо)
         и в if и в else добавим проверку на выход за пределы границ алфавита при смещении
        */
        if (oneSymbol.equals(alphabet.get(index))) {
            cryptChar(bufferedWriter, index, position, alphabet);

        }
        /*
         случай когда char-ы не идентичны и записать нужно в верхнем регистре
         преобразуем к верхнему регистру, сначала получив char.
         два случая - сдвиг индекса не входит и входит в границы алфавита:
        */
        else {
            cryptCharInUpperCase(bufferedWriter, index, position, alphabet);
        }
    }

    public static void cryptCharInUpperCase(BufferedWriter bufferedWriter, int index, int position, ArrayList<Character> alphabet) throws IOException {
        // два случая - сдвиг индекса не входит и входит в границы алфавита:
        if (alphabet.size()<=(index + position)) {
            int newIndex = index + position - alphabet.size();
            Character newChar = alphabet.get(newIndex);
            bufferedWriter.write((int)Character.toUpperCase(newChar));

        } else {
            Character newChar = alphabet.get(index + position);
            bufferedWriter.write((int)Character.toUpperCase(newChar));

        }
    }

    public static void cryptChar(BufferedWriter bufferedWriter, int index, int position, ArrayList<Character> alphabet) throws IOException {
    /*
     проверяем условие: размер алфавита меньше чем сумма индекса и сдвига (position)
     если да, то получаем новый индекс - разность размера и суммы индекса и сдвига
    */
        if (alphabet.size()<=(index + position)) {
            int newIndex = index + position - alphabet.size();
            bufferedWriter.write((int)alphabet.get(newIndex));

        } else {
            bufferedWriter.write((int)alphabet.get(index + position));

        }
    }
}
