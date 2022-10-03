package org.dobrodey.sender;

import org.dobrodey.sender.model.Lector;
import org.dobrodey.sender.model.Report;
import org.dobrodey.sender.pdf.ReportCreatorPDF;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        java.util.List<Report> list = Arrays.asList(
                new Report(1, "task1", "Phome1",
                        "Name", Timestamp.valueOf(LocalDateTime.now())),
                new Report(3, "task3", "Phome3", "Name", Timestamp.valueOf(LocalDateTime.now())));

        List<Lector> listLector = Arrays.asList(new Lector("Lector1"),
                new Lector("Lector2"));
        ReportCreatorPDF pdf = new ReportCreatorPDF();
        for (Lector lector : listLector) {
            System.out.println(pdf.generate(list));
        }
    }
}

