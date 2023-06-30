package tools;

import com.aspose.words.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TxtToPDF {
    public static void convert(String txtFilePath, String pdfFilePath, String imageFilePath) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        String[] lines = getTextLines(txtFilePath);

        Table table = builder.startTable();

        builder.insertImage(imageFilePath);

        // Add table headers
        builder.insertCell();
        builder.write("Tool");
        builder.insertCell();
        builder.write("Category");
        builder.insertCell();
        builder.write("Result");
        builder.endRow();

        // Add table data
        for (String line : lines) {
            String[] parts = line.split(", ");

            builder.insertCell();
            builder.write(parts[0].split("@@ ")[1]);

            builder.insertCell();
            builder.write(parts[1].split("@@ ")[1]);

            builder.insertCell();
            builder.write(parts[2].split("@@ ")[1]);

            builder.endRow();
        }
        builder.endTable();
        doc.save(pdfFilePath);
    }

    private static String[] getTextLines(String txtFilePath) throws Exception {
        Path path = Paths.get(txtFilePath);
        return Files.lines(path).toArray(String[]::new);
    }
}

