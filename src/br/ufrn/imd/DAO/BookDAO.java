package br.ufrn.imd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.ufrn.imd.controller.Conection;
import br.ufrn.imd.model.Book;

public class BookDAO {
	private Connection c;
	
	public BookDAO() {
		this.c= new Conection().getCon();
	}
	
	public void adiciona(Book bo) {
		String sql="INSERT INTO public.book (id, description, price, author, name, tags) VALUES (?, ?, ?, ?, ?, '{1,2}')";
		try {
			PreparedStatement stmt=c.prepareStatement(sql);
			stmt.setInt(1, bo.getId());
			stmt.setString(2, bo.getDescription());
			stmt.setDouble(3, bo.getPrice());
			stmt.setString(4, bo.getAuthor());
			stmt.setString(5, bo.getName());
			//stmt.setInt(6, 1);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
