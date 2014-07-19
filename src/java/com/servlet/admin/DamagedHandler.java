/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servlet.admin;

import com.models.Damaged;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shaw Nam
 */
public class DamagedHandler extends HttpServlet {

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
            out.println("<title>Servlet DamagedHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DamagedHandler at " + request.getContextPath() + "</h1>");
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
     Damaged d= new Damaged();
        String action=request.getParameter("action");
        if(!action.equals(null) && !request.getParameter("id").equals(null) && action.equals("delete"))
        {
            int id=Integer.parseInt(request.getParameter("id"));
            try {
                d.deleteDamaged(id);
                response.sendRedirect("damaged/index.jsp");
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
        Damaged d = new Damaged();
        if (request.getParameter("damaged_id") != null) {
            try {
                d.setDamagedId(Integer.parseInt(request.getParameter("damaged_id")));
                d.setDamagedDescription(request.getParameter("damaged_description"));
                d.setDamagedQuantity(Integer.parseInt(request.getParameter("damaged_quantity")));
                d.setProductId(Integer.parseInt(request.getParameter("product_id")));
                d.save();
                response.sendRedirect("damaged/index.jsp");
            } catch (Exception ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println(ex.getMessage());
                }
            }
        } else {
            try {
                d.setDamagedDescription(request.getParameter("damaged_description"));
                d.setDamagedQuantity(Integer.parseInt(request.getParameter("damaged_quantity")));
                d.setProductId(Integer.parseInt(request.getParameter("product_id")));
                d.save();
                String redirectURL = "damaged/index.jsp";
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
