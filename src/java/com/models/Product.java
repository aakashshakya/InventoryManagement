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
public class Product implements Serializable {
    private int productId;
    private String productName;
    private String productDescription;
    private int productPrice;
    private int reorderLevel;
    private int productQuantity;
    private Date addedDate;
    private int categoryId;
    private int status;
    DBConnection conn = new DBConnection();
    private String sql = "";

    public Product() {
    }

    public Product(int productId, String productName, String productDescription, int productPrice, int reorderLevel, int productQuantity, Date addedDate, int categoryId, int status) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.reorderLevel = reorderLevel;
        this.productQuantity = productQuantity;
        this.addedDate = addedDate;
        this.categoryId = categoryId;
        this.status = status;
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
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productDescription
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * @param productDescription the productDescription to set
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * @return the productPrice
     */
    public int getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * @return the reorderLevel
     */
    public int getReorderLevel() {
        return reorderLevel;
    }

    /**
     * @param reorderLevel the reorderLevel to set
     */
    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    /**
     * @return the productQuantity
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     * @param productQuantity the productQuantity to set
     */
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * @return the addedDate
     */
    public Date getAddedDate() {
        return addedDate;
    }

    /**
     * @param addedDate the addedDate to set
     */
    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
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
            if(getProductId()==0)
                sql="insert into products(product_name,product_description,product_price,reorder_level,product_quantity,added_date,category_id,status) value('"+ getProductName()+"','"+getProductDescription()+"','"+getProductPrice()+"','"+getReorderLevel()+"','"+getProductQuantity()+"','"+getAddedDate()+"','"+getCategoryId()+"','"+getStatus()+ "')";   
            else
                sql="Update products SET product_name = '"+ getProductName() +"' , product_description = '"+getProductDescription()+"' , product_price = '"+getProductPrice()+ "' , reorder_level = '"+getReorderLevel()+ "' , product_quantity = '"+getProductQuantity()+ "' , added_date = '"+getAddedDate()+ "' , category_id = '"+getCategoryId()+ "' , status = '"+getStatus()+ "' where product_id="+ getProductId();
            conn.execute(sql);
            conn.close();    
    }

    public static Product getById(int id)
    {
        Product product=new Product();
         String sql="SELECT * from products where product_id="+ id;
        try{
            DBConnection db=new DBConnection();
            db.open();

            ResultSet resultSet=db.fetch(sql);
            while(resultSet.next())
            {
              product=mapData(resultSet);
            }
            db.close();

        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return product;
    }
    
    public static ProductList getAll()
    {
        ProductList plist=new ProductList();
         String sql="SELECT * from products";
        try{
            DBConnection db=new DBConnection();
            db.open();

            ResultSet resultSet=db.fetch(sql);
            while(resultSet.next())
            {
              plist.add(mapData(resultSet));
            }
            db.close();

        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return plist;
    }
    
    public static Product mapData(ResultSet rs) throws SQLException
    {
        Product product=new Product();
        product.setProductId((Integer)rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setProductDescription(rs.getString("product_description"));
        product.setProductPrice((Integer)rs.getInt("product_price"));
        product.setReorderLevel((Integer)rs.getInt("reorder_level"));
        product.setProductQuantity((Integer)rs.getInt("product_quantity"));
        product.setAddedDate((Date)rs.getDate("added_date"));
        product.setCategoryId((Integer)rs.getInt("category_id"));
        product.setStatus((Integer)rs.getInt("status"));
        return product;
    }
    
    public static void deleteProduct(int id) throws SQLException
    {
        DBConnection conn = new DBConnection();
        conn.open();
        String sql="Delete from products where product_id=" + id ;
        conn.execute(sql);
        conn.close();
    }
    
    
}
