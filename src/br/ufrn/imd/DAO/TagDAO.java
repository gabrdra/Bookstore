package br.ufrn.imd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.ufrn.imd.controller.Conection;
import br.ufrn.imd.model.Tag;

public class TagDAO {
	private Connection c;
	
	public TagDAO() {
		this.c= new Conection().getCon();
	}
	
	public void adiciona(Tag ta) {
		String sql="INSERT INTO public.tag (id, name) VALUES (?, ?);";
		try {
			PreparedStatement stmt=c.prepareStatement(sql);
			stmt.setInt(1, ta.getId());
			stmt.setString(2, ta.getName());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}