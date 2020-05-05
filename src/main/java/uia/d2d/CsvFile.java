package uia.d2d;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The CSV file.
 *
 * @author Kyle K. Lin
 *
 */
public final class CsvFile {

    private CsvFile() {
    }

    /**
     * Read data.
     *
     * @param file The excel file.
     * @param firstRow The first row.
     * @param columnCount The column count.
     * @return Result.
     * @throws Exception Failed to read.
     */
    public static List<String[]> read(String file, int firstRow, int columnCount) throws Exception {
        ArrayList<String[]> result = new ArrayList<>();
        try (FileReader fr = new FileReader(new File(file))) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String line;
                for (int i = 0; i < firstRow; i++) {
                    line = br.readLine();
                }
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length < columnCount) {
                        String[] row = new String[columnCount];
                        for (int x = 0; x < values.length; x++) {
                            row[x] = values[x];
                        }
                        result.add(row);
                    }
                    else {
                        result.add(values);
                    }
                }
            }
        }
        return result;
    }
}
