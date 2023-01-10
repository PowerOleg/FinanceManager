package ru.netology;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TSV_Parser {
    public String[] parse(File tsvFile, int column) throws FileNotFoundException {
        String[] sArray;
        StringTokenizer stringTokenizer;
        int turn = 0;

        int count = 0;
        try (Scanner scanner = new Scanner(tsvFile)) {
            while (scanner.hasNext()) {
                scanner.nextLine();
                count++;
            }

            try (Scanner scanner1 = new Scanner(tsvFile)) {
                sArray = new String[count];
                while (scanner1.hasNext()) {
                    String s = scanner1.nextLine();
                    stringTokenizer = new StringTokenizer(s, "\\t");

                    switch (column) {
                        case 2: {
                            stringTokenizer.nextToken();
                            sArray[turn] = stringTokenizer.nextToken();
                            turn++;
                            break;
                        }
                        case 3: {
                            stringTokenizer.nextToken();
                            stringTokenizer.nextToken();
                            sArray[turn] = stringTokenizer.nextToken();
                            turn++;
                            break;
                        }
                        default:
                            System.out.println("Неверный номер колонки");
                            break;
                    }
                }
            }
            return sArray;
        }
    }
}
