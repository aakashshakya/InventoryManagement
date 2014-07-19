/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.admin;

import com.models.Sales;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shaw Nam
 */
public class SalesHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SalesHandler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SalesHandler at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Sales s = new Sales();
        String action = request.getParameter("action");
        if (!action.equals(null) && !request.getParameter("id").equals(null) && action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                s.deleteSales(id);
                response.sendRedirect("sales/index.jsp");
            } catch (Exception ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println(ex.getMessage());
                }

            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Sales s = new Sales();

        String DateStr = request.getParameter("sales_date");
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date d;
       

        if (request.getParameter("sales_id") != null) {
            try {
                d = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(DateStr); //converted String to date in the desired format
            java.sql.Date d1 = new java.sql.Date(d.getTime());
                s.setSalesId(Integer.parseInt(request.getParameter("sales_id")));
                s.setProductId(Integer.parseInt(request.getParameter("product_id")));
                s.setSalesQuantity(Integer.parseInt((request.getParameter("quantity"))));
                s.setSalesDate(d);

                s.save();
                response.sendRedirect("category/index.jsp");
            } catch (Exception ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println(ex.getMessage());
                }
            }
        } else {
            try {
                d = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(DateStr); //converted String to date in the desired format
            java.sql.Date d1 = new java.sql.Date(d.getTime());
                s.setProductId(Integer.parseInt(request.getParameter("product_id")));
                s.setSalesQuantity(Integer.parseInt((request.getParameter("quantity"))));
                s.setSalesDate(d);
                s.save();
                String redirectURL = "sales/index.jsp";
                response.sendRedirect(redirectURL);
            } catch (Exception ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println(ex.getMessage());
                }
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
