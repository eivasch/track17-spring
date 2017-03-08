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
    private boolean isNumber(String line) {
        boolean isnumber;
        try {
            Integer.parseInt(line);
            isnumber = true;
        } catch (NumberFormatException n) {
            isnumber = false;
        }
        return isnumber;
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
            if (isNumber(line)) {
                Integer lineToInt = Integer.parseInt(line);
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
            if ((! isNumber(line)) && (! line.equals("")) && (! isSpaces(line))) {
                resLine += line + " ";
            }
        }
        return resLine.substring(0, resLine.length() - 1);
    }
}
