import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PdfWriter {
    public static void main(String args[]) {
        try {
            var document = new PDDocument();

            var page = new PDPage();
            document.addPage(page);

            var contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();

            var file = new java.io.File("C:/Windows/Fonts/msmincho.ttc");
            var collection = new TrueTypeCollection(file);
            var font = PDType0Font.load(document, collection.getFontByName("MS-Mincho"), true);
            contentStream.setFont(font, 12);

            contentStream.newLineAtOffset(0f, 755f);

            contentStream.showText("書き込み用テスト");
            contentStream.endText();
            contentStream.close();

            var out = new java.io.ByteArrayOutputStream();
            document.save(out);
            document.save("sample.pdf");
            document.close();

            var streamData = new java.io.ByteArrayInputStream(out.toByteArray());
            System.out.println("streamData" + streamData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
