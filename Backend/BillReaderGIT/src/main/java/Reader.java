import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Reader {
    private InputStream stream;
    private int limit;

    public Reader(int limit) {
        this.limit = limit;
    }

    public void read() {


        for (int i = 1; i <= limit; i++) {
            String check = "";
            try {
                String zero = "";
                int zeros = 4 - Integer.toString(i).length();
                for (int j = 0; j < zeros; j++) {
                    zero += "0";
                }
                PDDocument document;

                try {
                    String url = "https://le.utah.gov/~2023/bills/hbillenr/HB" + zero + i + ".pdf";
                    check = url;
                    stream = new URL(url).openStream();
                    document = PDDocument.load(stream);
                } catch (FileNotFoundException e) {
                    String url = "https://le.utah.gov/~2023/bills/hbillint/HB" + zero + i + ".pdf";
                    stream = new URL(url).openStream();
                    document = PDDocument.load(stream);
                }

                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                document.close();
                BillConnector bc = new BillConnector();
                JSONBuilder jb = new JSONBuilder();
                bc.connect(jb.build(text,i));
            } catch (FileNotFoundException e) {
                System.out.println("FILE NOT FOUND for number: " + i);
                System.out.println(check);
            } catch (IOException e) {
                System.out.println("EXCEPTION did not send Number: " + i);
                System.out.println(check);
            }

        }
    }


    public void testRead() throws IOException {
        PDDocument document;
        try {
            String url = "https://le.utah.gov/~2023/bills/hbillenr/HB0495.pdf";
            stream = new URL(url).openStream();
            document = PDDocument.load(stream);
        } catch (Exception e) {
            String url = "https://le.utah.gov/~2023/bills/hbillint/HB0495.pdf";
            stream = new URL(url).openStream();
            document = PDDocument.load(stream);
        }
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();
        System.out.println(text);
    }

}
