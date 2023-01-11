package ru.netology;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TSV_Parser {
    public String[] parse(File tsvFile, int column) throws IOException {
        String[] sArray;
        StringTokenizer stringTokenizer;
        int turn = 0;

        int count = 0;
        try (Scanner scanner = new Scanner(tsvFile)) {
            while (scanner.hasNext()) {
                scanner.nextLine();
                count++;
            }

            try (BufferedReader in = new BufferedReader(new FileReader(tsvFile))) {
                sArray = new String[count];
                String line;
                while ((line = in.readLine()) != null) {
                    stringTokenizer = new StringTokenizer(line, "\\t");

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
