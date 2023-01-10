package ru.netology;



//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;

//public class Tsv_read{
//
//    public static void main(String[] arg) throws Exception {
//
//        StringTokenizer st ;
//        BufferedReader TSVFile = new BufferedReader(new FileReader("users.tsv"));
//        String dataRow = TSVFile.readLine(); // Read first line.
//
//        while (dataRow != null){
//            st = new StringTokenizer(dataRow,"\t");
//            List<String>dataArray = new ArrayList<String>() ;
//            while(st.hasMoreElements()){
//                dataArray.add(st.nextElement().toString());
//            }
//            for (String item:dataArray) {
//                System.out.print(item + "  ");
//            }
//            System.out.println(); // Print the data line.
//            dataRow = TSVFile.readLine(); // Read next line of data.
//        }
//        // Close the file once all data has been read.
//        TSVFile.close();
//
//        // End the printout with a blank line.
//        System.out.println();
//
//    } //main()
//} // TSVRead

//while (dataRow != null){
//        st = new StringTokenizer(dataRow,"\\t");
//        while(st.hasMoreElements()){
//        dataArray.add(st.nextElement().toString());
//        }


import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TSV_Parser {

//    TsvParserSettings settings = new TsvParserSettings(); // MANY options here
//    TsvParser parser = new TsvParser(settings);
//
//    public List<String[]> parse(File TsvFile) {
////        List<String[]> allRows =
//               return parser.parseAll(TsvFile); // parses all rows in a list.
//
//    }




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
