package org.dobrodey.sender.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.dobrodey.sender.Properties;
import org.dobrodey.sender.model.Report;

import java.awt.*;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ReportCreatorPDF {

    private final String HEADER_PDF = "ASTON INTERNSHIP";
    private final String REPORT_TITLE_PDF = "TRACKING REPORT";

    private final String TIME_OF_TRACK_COLUMN = "TIME OF TRACK";
    private final String NAME_USER_COLIMN = "NAME_USER";
    private final String TASK_COLUMN = "TASK";

    public String generate(List<Report> reportList) throws IOException {
        System.out.println("PAGE CREATE");
        PDDocument document = new PDDocument();
        PDPage firstPage = new PDPage(PDRectangle.A4);
        document.addPage(firstPage);
        int pageHeight = (int) firstPage.getTrimBox().getHeight();

        PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);
        TextPDFCreator myTextClass = new TextPDFCreator(document, contentStream);
        PDFont font = PDType1Font.TIMES_ROMAN;

        System.out.println("ADDED TEXT AND DATE");
        //adding HEADER PDF
        myTextClass.addSingleLineText(HEADER_PDF, 25, pageHeight - 100, font,
                35, new Color(137, 207, 240));
        //adding Title for
        myTextClass.addSingleLineText(REPORT_TITLE_PDF, 25, pageHeight - 250, font, 16, Color.BLACK);

        createDateTimeTitle(pageHeight, myTextClass, font);

        System.out.println("CREATE TABLE");
        TablePDFCreator myTable = createTable(document, contentStream, font, pageHeight);

        Color tableHeadColor = new Color(137, 207, 240);
        Color tableBodyColor = new Color(173, 216, 230);

        //create title for table
        myTable.addCall(TIME_OF_TRACK_COLUMN, tableHeadColor);
        myTable.addCall(NAME_USER_COLIMN, tableHeadColor);
        myTable.addCall(TASK_COLUMN, tableHeadColor);

        //add records to table
        for (Report report : reportList) {
            myTable.addCall(report.getTimeOfTrack().toString(), tableBodyColor);
            myTable.addCall(report.getNickName(), tableBodyColor);
            myTable.addCall(report.getTask(), tableBodyColor);
        }

        contentStream.close();
        return documentSave(document);
    }

    private String documentSave(PDDocument document) throws IOException {
        String pathRecord = createReportName(Properties.REPORT_NAME);
        System.out.println("DOCUMENT SAVE");
        document.save(pathRecord);
        document.close();
        return pathRecord;
    }

    private void createDateTimeTitle(int pageHeight, TextPDFCreator myTextClass, PDFont font) throws IOException {
        Format d_format = new SimpleDateFormat("dd/MM/yyyy");
        Format t_format = new SimpleDateFormat("HH:mm");

        myTextClass.addSingleLineText("Date: " + d_format.format(new Date()),
                25, pageHeight - 274, font, 16, Color.BLACK);

        String time = t_format.format(new Date());
        myTextClass.addSingleLineText("Time: " + time, 25,
                pageHeight - 298, font, 16, Color.BLACK);
    }

    private TablePDFCreator createTable(PDDocument document, PDPageContentStream contentStream, PDFont font, int pageHeight) {
        TablePDFCreator myTable = new TablePDFCreator(document, contentStream);
        int[] cellWidths = {110, 120, 310};
        myTable.setTable(cellWidths, 30, 25, pageHeight - 350);
        myTable.setTableFont(font, 10, Color.BLACK);

        return myTable;
    }

    private String createReportName(String reportName) {
        return reportName + "_" + LocalDate.now().getDayOfWeek().getValue() + ".pdf";
    }
}
