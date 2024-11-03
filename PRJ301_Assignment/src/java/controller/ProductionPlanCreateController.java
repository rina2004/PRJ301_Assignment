/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import controller_accesscontrol.BaseRBACController;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.Plan;
import model.Product;
import model.Department;
import model.PlanCampaign;
import dal.ProductDBContext;
import dal.DepartmentDBContext;
import dal.PlanDBContext;
import model_accesscontrol.User;

/**
 *
 * @author Rinaaaa
 */
public class ProductionPlanCreateController extends BaseRBACController {
   
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
    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        ProductDBContext dbProduct = new ProductDBContext();
        DepartmentDBContext dbDept = new DepartmentDBContext();
        request.setAttribute("products", dbProduct.list());
        request.setAttribute("depts", dbDept.get("workshop"));
        request.getRequestDispatcher("../view/productionplan/create.jsp").forward(request, response);    
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        String[] pids = request.getParameterValues("pid");
        
        Plan plan = new Plan();
        plan.setStart(Date.valueOf(request.getParameter("from")));
        plan.setEnd(Date.valueOf(request.getParameter("to")));
        
        Department d = new Department();
        d.setId(Integer.parseInt(request.getParameter("did")));
        
        plan.setDept(d);
        plan.setCampaigns(new ArrayList<>());
        
        for (String pid : pids) {
            Product p = new Product();
            p.setId(Integer.parseInt(pid));
            
            PlanCampaign c = new PlanCampaign();
            c.setProduct(p);
            String raw_quantity = request.getParameter("quantity"+pid);
            String raw_effort = request.getParameter("effort"+pid);
            c.setQuantity(raw_quantity != null && raw_quantity.length()>0?Integer.parseInt(raw_quantity):0);
            c.setEstimatedEffort(raw_effort != null && raw_effort.length()>0?Float.parseFloat(raw_effort):0);
            c.setPlan(plan);
            if(c.getQuantity()!=0 && c.getEstimatedEffort()!=0)
                plan.getCampaigns().add(c);
        }
        
        if(!plan.getCampaigns().isEmpty())
        {
            PlanDBContext db = new PlanDBContext();
            db.insert(plan);
            response.getWriter().println("Created a new plan!");
        }
        else
        {
            response.getWriter().println("Your plan did not have any campaigns!");
        }
    }
}