package com.example.backend_security.domain.helpers;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.extend.FontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class PDFHelper {

    public static String htmlToXhtml(String html) {
        Document document = Jsoup.parse(html);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document.html();
    }

    public static void xhtmlToPdf(String xhtml, String outFileName) throws IOException {
        Path filePath = Paths.get("src/main/resources/templates", "output.pdf");
        File output = new File(filePath.toString());
        ITextRenderer iTextRenderer = new ITextRenderer();
        /* FontResolver resolver = iTextRenderer.getFontResolver(); */
        /* iTextRenderer.getFontResolver().addFont("MyFont.ttf", true); */
        iTextRenderer.setDocumentFromString(xhtml);
        iTextRenderer.layout();
        OutputStream os = new FileOutputStream(output);
        iTextRenderer.createPDF(os);
        os.close();
    }

}
