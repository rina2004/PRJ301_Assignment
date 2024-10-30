/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Plan;
import model.PlanCampaign;
import model.Product;

/**
 *
 * @author Rinaaaa
 */
public class PlanDBContext extends DBContext<Plan> {

    @Override
    public void insert(Plan model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_plan = "INSERT INTO [Plan]\n"
                    + "           ([did]\n"
                    + "           ,[startDate]\n"
                    + "           ,[endDate])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            String sql_select_plan = "SELECT @@IDENTITY as plid";
            String sql_insert_campaign = "INSERT INTO [PlanCampaign]\n"
                    + "           ([plid]\n"
                    + "           ,[pid]\n"
                    + "           ,[quantity]\n"
                    + "           ,[estimatedEffort])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            
            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan);
            stm_insert_plan.setInt(1, model.getDept().getId());
            stm_insert_plan.setDate(2, model.getStart());
            stm_insert_plan.setDate(3, model.getEnd());
            stm_insert_plan.executeUpdate();
            
            PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
            ResultSet rs = stm_select_plan.executeQuery();
            if(rs.next())
            {
                model.setId(rs.getInt("plid"));
            }
            for (PlanCampaign campaign : model.getCampaigns()) {
                PreparedStatement stm_insert_campaign = connection.prepareStatement(sql_insert_campaign);
                stm_insert_campaign.setInt(1, model.getId());
                stm_insert_campaign.setInt(2, campaign.getProduct().getId());
                stm_insert_campaign.setInt(3, campaign.getQuantity());
                stm_insert_campaign.setFloat(4, campaign.getEstimatedEffort());
                stm_insert_campaign.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(Plan model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Plan model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Plan> list() {
        ArrayList<Plan> plans = new ArrayList<>();
        DepartmentDBContext dpmDB = new DepartmentDBContext();
        
        PreparedStatement stm = null;
        try {
            String sql = """
                         SELECT [id]
                               ,[did]
                               ,[startDate]
                               ,[endDate]
                           FROM [dbo].[Plan]""";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Plan pl = new Plan();
                pl.setId(rs.getInt("id"));
                pl.setDept(dpmDB.get(rs.getInt("did")));
                pl.setStart(rs.getDate("startDate"));
                pl.setEnd(rs.getDate("endDate"));
                plans.add(pl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return plans;
    }

    @Override
    public Plan get(int id) {
        Plan plan = null;
        DepartmentDBContext dpmDB = new DepartmentDBContext();
        ProductDBContext prdDB = new ProductDBContext();
        
        String sql = """
            SELECT p.id AS plan_id, p.did AS dept_id, p.startDate, p.endDate,
                   pl.id AS campaign_id, pl.pid AS product_id, pl.quantity, pl.estimatedEffort
            FROM dbo.[Plan] p INNER JOIN dbo.[PlanCampaign] pl ON p.id = pl.plid
            WHERE p.id = ?
        """;
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                if (plan == null) {
                    plan = new Plan();
                    plan.setId(rs.getInt("plan_id"));

                    plan.setDept(dpmDB.get(rs.getInt("dept_id")));

                    plan.setStart(rs.getDate("startDate"));
                    plan.setEnd(rs.getDate("endDate"));
                    plan.setCampaigns(new ArrayList<>());
                }

                PlanCampaign camp = new PlanCampaign();
                camp.setId(rs.getInt("campaign_id"));

                camp.setProduct(prdDB.get(rs.getInt("product_id")));

                camp.setQuantity(rs.getInt("quantity"));
                camp.setEstimatedEffort(rs.getFloat("estimatedEffort"));

                plan.getCampaigns().add(camp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return plan;
    }
}
