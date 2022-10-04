package org.dobrodey.sender.service;

import lombok.SneakyThrows;
import org.dobrodey.sender.model.ReportSender;
import org.dobrodey.sender.pdf.ReportCreatorPDF;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReportCreatorService implements Runnable {

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("START CREATE REPORT");
        System.out.println("INIT SERVICE");
        SenderFromRouterService service = new SenderFromRouterService();
        service.init();
        System.out.println("Get list all track today");
        //Get list all track today
        List<ReportSender> reportList = service.getReportsToday();
        System.out.println("generate PDF");
        // createPDF();
        ReportCreatorPDF pdf = new ReportCreatorPDF();
        String path = pdf.generate(reportList);

        byte[] pdfBytes = Files.readAllBytes(Paths.get(path));
        System.out.println("sendReport");
        //sendReport();
        service.pdfForLector(pdfBytes);

        System.out.println("END CREATE REPORT");
    }
}
