/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	private Connection conn=null; //Global connection to access database 
	private Statement statement=null;
	private String connString="jdbc:mysql://localhost/inventory_mgmt";
	private String user="root";
	private String password="";
	private String sql;
	
	public DBConnection() 
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ce)
		{
			
		}
	}
	
	public void open() throws SQLException
	{
		conn=DriverManager.getConnection(connString,user,password);
		
	}
	
	public boolean execute(String sql) throws SQLException
	{
		statement=conn.createStatement();
		this.sql=sql;
		return statement.execute(sql);
		
	}
	
	public ResultSet fetch(String sql) throws SQLException
	{
		statement=conn.createStatement();
		this.sql=sql;
		return statement.executeQuery(sql);
	}
	
	public String lastQuery()
	{
		return sql;
	}
	
	public void close() throws SQLException
	{
		if(conn!=null)
		{
			conn.close();
			conn=null;
		}
	}
	

}
