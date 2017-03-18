package track.lessons.lesson1;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Задание 1: Реализовать два метода
 *
 * Формат файла: текстовый, на каждой его строке есть (или/или)
 * - целое число (int)
 * - текстовая строка
 * - пустая строка (пробелы)
 *
 *
 * Пример файла - words.txt в корне проекта
 *
 * ******************************************************************************************
 *  Пожалуйста, не меняйте сигнатуры методов! (название, аргументы, возвращаемое значение)
 *
 *  Можно дописывать новый код - вспомогательные методы, конструкторы, поля
 *
 * ******************************************************************************************
 *
 */
public class CountWords {
    private Integer stringToNumber(String line) {
        Integer result;
        try {
            result = Integer.parseInt(line);
        } catch (NumberFormatException n) {
            result = null;
        }
        return result;
    }

    private boolean isSpaces(String line) {
        boolean isspaces = true;
        int index;
        for (index = 0; index < line.length(); index++) {
            if (line.charAt(index) != ' ') {
                isspaces = false;
            }
        }
        return isspaces;
    }

    /**
     * Метод на вход принимает объект File, изначально сумма = 0
     * Нужно пройти по всем строкам файла, и если в строке стоит целое число,
     * то надо добавить это число к сумме
     * @param file - файл с данными
     * @return - целое число - сумма всех чисел из файла
     */
    public long countNumbers(File file) throws Exception {
        String line;
        long countRes = 0;
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while ((line = bufferedReader.readLine()) != null) {
            Integer lineToInt = stringToNumber(line);
            if (lineToInt != null) {
                countRes += lineToInt;
            }
        }
        return countRes;
    }


    /**
     * Метод на вход принимает объект File, изначально результат= ""
     * Нужно пройти по всем строкам файла, и если в строка не пустая и не число
     * то надо присоединить ее к результату через пробел
     * @param file - файл с данными
     * @return - результирующая строка
     */
    public String concatWords(File file) throws Exception {
        String line;
        String resLine = "";
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while ((line = bufferedReader.readLine()) != null) {
            Integer lineToInt = stringToNumber(line);
            if ((lineToInt == null) && (! line.equals("")) && (! isSpaces(line))) {
                resLine += line + " ";
            }
        }
        return resLine.substring(0, resLine.length() - 1);
    }
}
