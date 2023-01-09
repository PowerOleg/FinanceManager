package ru.netology;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.File;
import java.util.List;

public class TSV_Parser {
    TsvParserSettings settings = new TsvParserSettings(); // MANY options here
    TsvParser parser = new TsvParser(settings);

    public List<String[]> parse(File TsvFile) {
//        List<String[]> allRows =
               return parser.parseAll(TsvFile); // parses all rows in a list.

    }

}
