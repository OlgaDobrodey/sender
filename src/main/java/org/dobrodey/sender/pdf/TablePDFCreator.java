package org.dobrodey.sender.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.awt.*;
import java.io.IOException;

public class TablePDFCreator {
    PDDocument document;
    PDPageContentStream contentStream;
    private int[] colWidths;
    private int cellHeight;
    private int yPosition;
    private int xPosition;
    private int colPosition = 0;
    private int xInitialPosition;
    private float frontSize;
    private PDFont font;
    private Color fontColor;

    public TablePDFCreator(PDDocument document, PDPageContentStream contentStream) {
        this.document = document;
        this.contentStream = contentStream;
    }

    void setTable(int[] colWidths, int cellHeight, int xPosition, int yPosition) {
        this.colWidths = colWidths;
        this.cellHeight = cellHeight;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        xInitialPosition = xPosition;
    }

    void setTableFont(PDFont font, float frontSize, Color fontColor) {
        this.font = font;
        this.frontSize = frontSize;
        this.fontColor = fontColor;
    }

    void addCall(String text, Color fillColor) throws IOException {
        contentStream.setStrokingColor(1f);
        contentStream.setFont(font, frontSize);
        if (fillColor != null) {
            contentStream.setNonStrokingColor(fillColor);
        }
        contentStream.addRect(xPosition, yPosition, colWidths[colPosition], cellHeight);
        if (fillColor == null) {
            contentStream.stroke();
        } else contentStream.fillAndStroke();
        contentStream.beginText();
        contentStream.setNonStrokingColor(fontColor);
        contentStream.newLineAtOffset(xPosition + 2, yPosition + 10);
        contentStream.showText(text);
        contentStream.endText();

        xPosition = xPosition + colWidths[colPosition];
        colPosition++;

        if (colPosition == colWidths.length) {
            colPosition = 0;
            xPosition = xInitialPosition;
            yPosition -= cellHeight;
        }
    }
}
