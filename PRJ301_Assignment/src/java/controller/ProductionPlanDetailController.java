/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import controller_accesscontrol.BaseRBACController;
import dal.PlanDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import model.Plan;
import model_accesscontrol.User;

/**
 *
 * @author Rinaaaa
 */
public class ProductionPlanDetailController extends BaseRBACController {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("../view/productionplan/list.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        try {
            int planID = Integer.parseInt(request.getParameter("id"));

            PlanDBContext planDB = new PlanDBContext();
            Plan plan = planDB.get(planID);
            
            if (plan != null) {
                request.setAttribute("plan", plan);
                request.getRequestDispatcher("../view/productionplan/detail.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Plan not found.");
                request.getRequestDispatcher("../view/error.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Plan ID.");
            request.getRequestDispatcher("../view/error.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            request.setAttribute("error", "An unexpected error occurred.");
            request.getRequestDispatcher("../view/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        doGet(request, response);
    }
}
