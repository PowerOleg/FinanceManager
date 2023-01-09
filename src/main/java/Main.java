//внутри сервера принимать json, парсить в покупку и в дату
//пробегаться по всем словам из файла categories и если не найдено - категория другое
//написать клиент для тестирования
//написать серверу ответ
//Сервер должен запускаться в main класса Main (без пакета) и слушать запросы, приходяшие на порт 8989.
//логика подсчёта максимальных категорий (отдельный класс)

//класс напишите юнит-тесты. Добавление тестов (и исправление найденных ими дефектов, если таковые будут)
//        сделайте в отдельной ветке и смёржите в основную ветку через пулл-реквест (закрывая пулл-реквест,
//        не удаляйте созданную ветку).
//        Все дополнительные задания также должны делаться через ветки и закрытые пулл-реквесты в основную ветку.

//переписать TSV_Parser без библиотеки.

import ru.netology.ServerResponseApp;
import ru.netology.TSV_Parser;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TSV_Parser tsv_parser = new TSV_Parser();
        List<String[]> categoriesList = tsv_parser.parse(new File("categories.tsv"));
//        categoriesList.forEach(n -> System.out.println(Arrays.toString(n)));

        Thread thread1 = new Thread(new ServerResponseApp());
        thread1.start();

    }
}
