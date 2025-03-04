package com.vietnamroller.ranking;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Image;

import java.net.MalformedURLException;

import static com.vietnamroller.ranking.util.PDFUtils.*;

public class PDFGeneration {
    public static void main(String[] args) throws MalformedURLException {
        String outPath = "src/main/resources/pdf/";
        String imagePath = outPath + "images.png"; // Change this to your image path

        PdfDocument pdfDocument = createEmptyPDF(PageSize.A6, outPath + "sample.pdf" );
        System.out.println("PDF created with page size: " + pdfDocument.getFirstPage().getPageSize());

        ImageData imageData = ImageDataFactory.create(imagePath);
        Image image = new Image(imageData);

        addImageToPDF(pdfDocument, imagePath, 4, 70);
        addTextToPDF (pdfDocument, "www.decathlon.com.kh | 096 4 777 519", 20, 82);
        addTextToPDF (pdfDocument, "ព័ត៌មានអតិថិជន | Customer Information", 20, 80);


        pdfDocument.close();
    }
}
