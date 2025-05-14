package com.code_red.phc_attendance_system.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import com.code_red.phc_attendance_system.entities.Attendance;

public class PdfGenerator {

    public static byte[] generateAttendanceReport(List<Attendance> data, LocalDate date) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Attendance Report - " + date.toString()).setBold().setFontSize(14));

        float[] columnWidths = {100F, 80F, 60F, 60F, 80F, 80F};
        Table table = new Table(columnWidths);

        table.addHeaderCell("Doctor");
        table.addHeaderCell("Status");
        table.addHeaderCell("Check-in");
        table.addHeaderCell("Check-out");
        table.addHeaderCell("Missed Counts");
        table.addHeaderCell("Date");

        for (Attendance att : data) {
            table.addCell(att.getDoctor().getFullName());
            table.addCell(att.getStatus().toString());
            table.addCell(att.getCheckInTime() != null ? att.getCheckInTime().toString() : "-");
            table.addCell(att.getCheckOutTime() != null ? att.getCheckOutTime().toString() : "-");
            table.addCell(att.getMissedCounts() != null ? att.getMissedCounts().toString() : "0");
            table.addCell(att.getDate().toString());
        }

        document.add(table);
        document.close();

        return baos.toByteArray();
    }
}

