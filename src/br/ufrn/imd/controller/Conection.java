package br.ufrn.imd.controller;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Conection {
	private String url;
	private String user;
	private String password;
	private Connection con;
	
	public Conection(){
		url="jdbc:postgresql://localhost:5432/postgres";
		user = "eduardo";
		password= "123";
		
		try {
			
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url,user,password);
			System.out.println("Conexão Realizada com Sucesso!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int execSQL(String sql) {
		try {
			Statement stm = con.createStatement();
			int res = stm.executeUpdate(sql);
			con.close();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public ResultSet execSearch(String sql) {
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			con.close();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Connection getCon() {
		return con;
	}
	
	
}
