package com.vietnamroller.ranking.util;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AbstractElement;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.MalformedURLException;


@Slf4j
public class PDFUtils {

    public static PdfDocument createEmptyPDF(PageSize pageSize, String filePath) {
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDocument = new PdfDocument(writer);
            pdfDocument.addNewPage(pageSize);
            return pdfDocument;
        } catch (IOException e) {
            log.error("Error creating PDF: " + e.getMessage());
            return null;
        }
    }


    public static void addImageToPDF(PdfDocument pdfDocument, String imagePath, float xPercent, float yPercent) throws MalformedURLException {

        Document document = new Document(pdfDocument);
        float xPos = getWPosByPercentage(pdfDocument, xPercent);
        float yPos = getHPosByPercentage(pdfDocument, yPercent);

        // Load image
        ImageData imageData = ImageDataFactory.create(imagePath);
        Image image = new Image(imageData);

        // Position image using fixed coordinates
        image.setFixedPosition(xPos, yPos);

        // Add image to document
        document.add(image);

        log.info("✅ Image added at (" + xPos + ", " + yPos + ") [" + xPercent + "%, " + yPercent + "%]");
    }

    public static void addTextToPDF(PdfDocument pdfDocument, String text, float xPercent, float yPercent) {

        Document document = new Document(pdfDocument);
        // Convert percentage to absolute points
        float xPos = getWPosByPercentage(pdfDocument, xPercent);
        float yPos = getHPosByPercentage(pdfDocument, yPercent);

        // Create and position the paragraph
        Paragraph paragraph = new Paragraph(text)
                .setFixedPosition(xPos, yPos, pdfDocument.getFirstPage().getPageSize().getWidth() - xPos)
                .setTextAlignment(TextAlignment.LEFT);

        document.add(paragraph);
        log.info("✅ Text added at (" + xPos + ", " + yPos + ") [" + xPercent + "%, " + yPercent + "%]");
    }
    
    public static float getWPosByPercentage (PdfDocument pdfDocument, float percentage) {
        var pageSize = pdfDocument.getFirstPage().getPageSize();
        return (percentage / 100) * pageSize.getWidth();
    }
    
    public static float getHPosByPercentage (PdfDocument pdfDocument, float percentage) {
        var pageSize = pdfDocument.getFirstPage().getPageSize();
        return (percentage / 100) * pageSize.getHeight();
    }
}
