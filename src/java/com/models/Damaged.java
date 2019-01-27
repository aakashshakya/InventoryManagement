/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.models;

import com.db.DBConnection;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sun-J
 */
public class Damaged implements Serializable{
    
    private int damagedId;
    private int productId;
    private String damagedDescription;
    private int damagedQuantity;
    private DBConnection conn = new DBConnection();
    private String sql = "";

    public Damaged() {
    }

    //Damaged constructor
    public Damaged(int damagedId, int productId, String damagedDescription, int damagedQuantity) {
        this.damagedId = damagedId;
        this.productId = productId;
        this.damagedDescription = damagedDescription;
        this.damagedQuantity = damagedQuantity;
    }

    /**
     * @return the damagedId
     */
    public int getDamagedId() {
        return damagedId;
    }

    /**
     * @param damagedId the damagedId to set
     */
    public void setDamagedId(int damagedId) {
        this.damagedId = damagedId;
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
     * @return the damagedDescription
     */
    public String getDamagedDescription() {
        return damagedDescription;
    }

    /**
     * @param damagedDescription the damagedDescription to set
     */
    public void setDamagedDescription(String damagedDescription) {
        this.damagedDescription = damagedDescription;
    }

    /**
     * @return the damagedQuantity
     */
    public int getDamagedQuantity() {
        return damagedQuantity;
    }

    /**
     * @param damagedQuantity the damagedQuantity to set
     */
    public void setDamagedQuantity(int damagedQuantity) {
        this.damagedQuantity = damagedQuantity;
    }
    
    public void save() throws IOException, SQLException
    {
            conn.open();      
            if(getDamagedId()==0)
                sql="insert into damaged(product_id,damaged_description,damaged_quantity) value('"+ getProductId()+"','"+getDamagedDescription()+"','"+getDamagedQuantity()+ "')";   
            else
                sql="Update damaged SET product_id = '"+ getProductId() +"' , damaged_description = '"+getDamagedDescription()+"' , damaged_quantity = '"+getDamagedQuantity()+ "' where damaged_id="+ getDamagedId();
            conn.execute(sql);
            conn.close();    
    }

    public static Damaged getById(int id)
    {
        Damaged damaged=new Damaged();
         String sql="SELECT * from damaged where damaged_id="+ id;
        try{
            DBConnection db=new DBConnection();
            db.open();

            ResultSet resultSet=db.fetch(sql);
            while(resultSet.next())
            {
              damaged=mapData(resultSet);
            }
            db.close();

        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return damaged;
    }
    
    public static DamagedList getAll()
    {
        DamagedList dlist=new DamagedList();
         String sql="SELECT * from damaged";
        try{
            DBConnection db=new DBConnection();
            db.open();

            ResultSet resultSet=db.fetch(sql);
            while(resultSet.next())
            {
              dlist.add(mapData(resultSet));
            }
            db.close();

        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return dlist;
    }
    
    public static Damaged mapData(ResultSet rs) throws SQLException
    {
        Damaged damaged=new Damaged();
        damaged.setDamagedId((Integer)rs.getInt("damaged_id"));
        damaged.setProductId((Integer)rs.getInt("product_id"));
        damaged.setDamagedDescription(rs.getString("damaged_description"));
        damaged.setDamagedQuantity((Integer)rs.getInt("damaged_quantity"));
        return damaged;
    }
    
    public static void deleteDamaged(int id) throws SQLException
    {
        DBConnection conn = new DBConnection();
        conn.open();
        String sql="Delete from damaged where damaged_id=" + id ;
        conn.execute(sql);
        conn.close();
    }    
}
