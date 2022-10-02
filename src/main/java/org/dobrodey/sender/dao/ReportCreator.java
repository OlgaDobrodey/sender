package org.dobrodey.sender.dao;

import lombok.SneakyThrows;
import org.dobrodey.sender.JSONParser;
import org.dobrodey.sender.Properties;
import org.dobrodey.sender.model.Lector;
import org.dobrodey.sender.model.Report;
import org.dobrodey.sender.pdf.ReportCreatorPDF;

import java.io.File;
import java.net.URL;
import java.util.List;

public class ReportCreator implements Runnable {

    private final String TRACK_LIST_FOR_DAY_URL = "http://ip.jsontest.com/";
    private final String LECTOR_LIST_URL = "http://ip.jsontest.com/";

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("START CREATE REPORT");

        //Get list all track today
        List<Report> reportList = JSONParser.fromUrlToJSON(new URL(TRACK_LIST_FOR_DAY_URL), new Report());
        //Get list of all Lectors
        List<Lector> lectorList = JSONParser.fromUrlToJSON(new URL(LECTOR_LIST_URL), new Lector());
        // createPDF();

        ReportCreatorPDF pdf = new ReportCreatorPDF();
        String path= Properties.REPORT_URL+ File.separator+pdf.generate(reportList);
        for (Lector lector : lectorList) {

        }
//        sendReport();
    }
}
