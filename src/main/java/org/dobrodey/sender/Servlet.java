package org.dobrodey.sender;

import org.dobrodey.sender.model.Report;
import org.dobrodey.sender.saop.SenderRouterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlet", value = "/test")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SenderRouterService service = new SenderRouterService();
        service.init();
        List<Report> reportSenders = service.getReportsToday();
        request.setAttribute("listBooks", reportSenders);
        getServletContext().getRequestDispatcher("/test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
