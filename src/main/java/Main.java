import ru.netology.TSV_Parser;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TSV_Parser tsv_parser = new TSV_Parser();
        List<String[]> arrayList = tsv_parser.parse(new File("categories.tsv"));
        arrayList.forEach(n -> System.out.println(Arrays.toString(n)));

    }
}
