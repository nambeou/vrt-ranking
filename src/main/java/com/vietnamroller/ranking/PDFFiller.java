package com.vietnamroller.ranking;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import java.io.File;
import java.util.Map;
public class PDFFiller {
    public static void main(String[] args) {
        try {
            String src = "/Users/mac-Z28NHOAN/Documents/CHOPPER/backend/shipperbox/shipper-service-khm-apollo/src/main/resources/pdf/template.pdf";
            String dest = "filled_form.pdf";

            PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);

            // Fill the form fields
            Map<String, PdfFormField> fields = form.getFormFields();
            if (fields.containsKey("name")) {
                fields.get("name").setValue("John Doe");
            }
            if (fields.containsKey("date")) {
                fields.get("date").setValue("2025-02-05");
            }

            pdfDoc.close();
            System.out.println("PDF Form filled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
