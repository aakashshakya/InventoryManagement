/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.models;

import com.db.DBConnection;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sun-J
 */
public class Sales implements Serializable{
    private int salesId;
    private int productId;
    private int salesQuantity;
    private Date salesDate;
    DBConnection conn = new DBConnection();
    private String sql = "";

    public Sales() {
    }

    public Sales(int salesId, int productId, int salesQuantity, Date salesDate) {
        this.salesId = salesId;
        this.productId = productId;
        this.salesQuantity = salesQuantity;
        this.salesDate = salesDate;
    }

    /**
     * @return the salesId
     */
    public int getSalesId() {
        return salesId;
    }

    /**
     * @param salesId the salesId to set
     */
    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the salesQuantity
     */
    public int getSalesQuantity() {
        return salesQuantity;
    }

    /**
     * @param salesQuantity the salesQuantity to set
     */
    public void setSalesQuantity(int salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    /**
     * @return the salesDate
     */
    public Date getSalesDate() {
        return salesDate;
    }

    /**
     * @param salesDate the salesDate to set
     */
    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }
    
    
    public void save() throws IOException, SQLException
    {
            conn.open();      
            if(getSalesId()==0)
                sql="insert into sales(product_id,sales_quantity,sales_date) value('"+ getProductId()+"','"+getSalesQuantity()+"','"+getSalesDate()+ "')";   
            else
                sql="Update sales SET product_id = '"+ getProductId() +"' , sales_quantity = '"+getSalesQuantity()+"' , sales_date = '"+getSalesDate()+ "' where sales_id="+ getSalesId();
            conn.execute(sql);
            conn.close();    
    }

    public static Sales getById(int id)
    {
        Sales sales=new Sales();
         String sql="SELECT * from sales where sales_id="+ id;
        try{
            DBConnection db=new DBConnection();
            db.open();

            ResultSet resultSet=db.fetch(sql);
            while(resultSet.next())
            {
              sales=mapData(resultSet);
            }
            db.close();

        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return sales;
    }
    
    public static SalesList getAll()
    {
        SalesList slist=new SalesList();
         String sql="SELECT * from sales";
        try{
            DBConnection db=new DBConnection();
            db.open();

            ResultSet resultSet=db.fetch(sql);
            while(resultSet.next())
            {
              slist.add(mapData(resultSet));
            }
            db.close();

        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return slist;
    }
    
    public static Sales mapData(ResultSet rs) throws SQLException
    {
        Sales sales=new Sales();
        sales.setSalesId((Integer)rs.getInt("sales_id"));
        sales.setProductId((Integer)rs.getInt("product_id"));
        sales.setSalesQuantity((Integer)rs.getInt("sales_quantity"));
        sales.setSalesDate((Date)rs.getDate("sales_date"));
        return sales;
    }
    
    public static void deleteSales(int id) throws SQLException
    {
        DBConnection conn = new DBConnection();
        conn.open();
        String sql="Delete from sales where sales_id=" + id ;
        conn.execute(sql);
        conn.close();
    }
    
    
}
