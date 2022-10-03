package org.dobrodey.sender.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.dobrodey.sender.Properties;
import org.dobrodey.sender.model.Lector;
import org.dobrodey.sender.model.Report;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.io.File.separator;

public class ReportCreatorPDF {

    private final String HEADER_PDF = "ASTON INTERNSHIP";
    private final String REPORT_TITLE_PDF = "TRACKING REPORT";

    private final String TIME_OF_TRACK_COLUMN = "TIME OF TRACK";
    private final String NAME_USER_COLIMN = "NAME_USER";
    private final String PHONE_NUMBER_COLUMN = "PHONE_NUMBER";
    private final String TASK_COLUMN = "TASK";


    public String generate(List<Report> reportList) throws IOException {

        //generating and customizing the page
        PDDocument document = new PDDocument();
        PDPage firstPage = new PDPage(PDRectangle.A4);
        document.addPage(firstPage);
        int pageHeight = (int) firstPage.getTrimBox().getHeight();

        PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);
        TextPDFCreator myTextClass = new TextPDFCreator(document, contentStream);
        PDFont font = PDType1Font.TIMES_ROMAN;

        //adding HEADER PDF
        myTextClass.addSingleLineText(HEADER_PDF, 25, pageHeight - 100, font,
                35, new Color(137, 207, 240));
        //adding Title for
        myTextClass.addSingleLineText(REPORT_TITLE_PDF, 25, pageHeight - 250, font, 16, Color.BLACK);

        createDateTimeTitle(pageHeight, myTextClass, font);
        TablePDFCreator myTable = createTable(document, contentStream, font, pageHeight);

        Color tableHeadColor = new Color(137, 207, 240);
        Color tableBodyColor = new Color(173, 216, 230);

        //create title for table
        myTable.addCall(TIME_OF_TRACK_COLUMN, tableHeadColor);
        myTable.addCall(NAME_USER_COLIMN, tableHeadColor);
        myTable.addCall(PHONE_NUMBER_COLUMN, tableHeadColor);
        myTable.addCall(TASK_COLUMN, tableHeadColor);

        //add records to table
        for (Report report : reportList) {
            myTable.addCall(report.getTimeOfTrack().toString().substring(0, 19), tableBodyColor);
            myTable.addCall(report.getUserName(), tableBodyColor);
            myTable.addCall(report.getNickName(), tableBodyColor);
            myTable.addCall(report.getTask(), tableBodyColor);
        }

        contentStream.close();
        String pathRecord = createReportName(Properties.REPORT_NAME);
        document.save(Properties.PATH_SAVE_REPORT + separator + pathRecord);
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
        int[] cellWidths = {110, 120, 120, 190};
        myTable.setTable(cellWidths, 30, 25, pageHeight - 350);
        myTable.setTableFont(font, 10, Color.BLACK);

        return myTable;
    }

    private String createReportName(String reportName) {
        return reportName + "_" + LocalDate.now().getDayOfWeek().getValue() + ".pdf";
    }

    public static void main(String[] args) throws IOException {
        List<Report> list = Arrays.asList(
                new Report(1, "task1", "Phome1", "Name", Timestamp.valueOf(LocalDateTime.now())),
                new Report(2, "task2", "Phome2", "Name", Timestamp.valueOf(LocalDateTime.now())),
                new Report(3, "task3", "Phome3", "Name", Timestamp.valueOf(LocalDateTime.now()))
        );

        List<Lector> listLector = Arrays.asList(
                new Lector("Lector1"),
                new Lector("Lector2")
        );
        ReportCreatorPDF pdf = new ReportCreatorPDF();
        for (Lector lector : listLector) {
            System.out.println( Properties.REPORT_URL+ File.separator+pdf.generate(list));
        }

    }
}
