package org.dobrodey.sender.service;

import lombok.extern.slf4j.Slf4j;
import org.dobrodey.sender.model.Report;
import org.dobrodey.sender.pdf.ReportCreatorPDF;
import org.dobrodey.sender.saop.SenderRouterService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
@Slf4j
public class ReportCreatorService implements Runnable {

    @Override
    public void run() {
        try {
            log.info("START CREATE REPORT");
            log.info("INIT SERVICE");
            SenderRouterService service = new SenderRouterService();
            service.init();

            log.info("GET ALL REPORTS LIST");
            List<Report> reportList = service.getReportsToday();
            log.info("CREATE PDF");
            ReportCreatorPDF pdf = new ReportCreatorPDF();
            String path = pdf.generate(reportList);

            byte[] pdfBytes = Files.readAllBytes(Paths.get(path));

            log.info("SEND REPORT");
            service.pdfForLector(pdfBytes);

            log.info("END CREATE REPORT");
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
    }
}
