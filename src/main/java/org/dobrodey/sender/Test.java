package org.dobrodey.sender;

import org.dobrodey.sender.service.ReportCreatorService;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        new ReportCreatorService().run();
    }
}

