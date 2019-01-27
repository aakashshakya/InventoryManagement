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
public class Category implements Serializable{
    private int categoryId;
    private String categoryName;
    private String categoryDescription;
    private int status;
    DBConnection conn = new DBConnection();
    private String sql = "";

    public Category() {
    }

    //Constructor added for category.
    public Category(int categoryId, String categoryName, String categoryDescription, int status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.status = status;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the categoryDescription
     */
    public String getCategoryDescription() {
        return categoryDescription;
    }

    /**
     * @param categoryDescription the categoryDescription to set
     */
    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    
    public void save() throws IOException, SQLException
    {
            conn.open();      
            if(getCategoryId()==0)
                sql="insert into categories(category_name,category_description,status) value('"+ getCategoryName()+"','"+getCategoryDescription()+"','"+getStatus()+ "')";   
            else
                sql="Update categories SET category_name = '"+ getCategoryName() +"' , category_description = '"+getCategoryDescription()+"' , status = '"+getStatus()+ "' where category_id="+ getCategoryId();
            conn.execute(sql);
            conn.close();    
    }

    public static Category getById(int id)
    {
        Category category=new Category();
         String sql="SELECT * from categories where category_id="+ id;
        try{
            DBConnection db=new DBConnection();
            db.open();

            ResultSet resultSet=db.fetch(sql);
            while(resultSet.next())
            {
              category=mapData(resultSet);
            }
            db.close();

        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return category;
    }
    
    public static CategoryList getAll()
    {
        CategoryList clist=new CategoryList();
         String sql="SELECT * from categories";
        try{
            DBConnection db=new DBConnection();
            db.open();

            ResultSet resultSet=db.fetch(sql);
            while(resultSet.next())
            {
              clist.add(mapData(resultSet));
            }
            db.close();

        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return clist;
    }
    
    public static Category mapData(ResultSet rs) throws SQLException
    {
        Category category=new Category();
        category.setCategoryId((Integer)rs.getInt("category_id"));
        category.setCategoryName(rs.getString("category_name"));
        category.setCategoryDescription(rs.getString("category_description"));
        category.setStatus((Integer)rs.getInt("status"));
        return category;
    }
    
    public static void deleteCategory(int id) throws SQLException
    {
        DBConnection conn = new DBConnection();
        conn.open();
        String sql="Delete from categories where category_id=" + id ;
        conn.execute(sql);
        conn.close();
    }    
    
}
