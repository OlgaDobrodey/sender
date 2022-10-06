package org.dobrodey.sender.service;

import org.dobrodey.sender.model.Report;
import org.dobrodey.sender.pdf.ReportCreatorPDF;
import org.dobrodey.sender.saop.SenderRouterService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReportCreatorService implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("START CREATE REPORT");
            System.out.println("INIT SERVICE");
            SenderRouterService service = new SenderRouterService();
            service.init();

            System.out.println("GET ALL REPORTS LIST");
            List<Report> reportList = service.getReportsToday();

            System.out.println("CREATE PDF");
            ReportCreatorPDF pdf = new ReportCreatorPDF();
            String path = pdf.generate(reportList);

            byte[] pdfBytes = Files.readAllBytes(Paths.get(path));

            System.out.println("SEND REPORT");
            service.pdfForLector(pdfBytes);

            System.out.println("END CREATE REPORT");
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
    }
}
