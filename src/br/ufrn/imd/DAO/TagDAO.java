package br.ufrn.imd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	public Tag getTagById(int id) {
		try {
			String tagSql = "SELECT * FROM public.tag WHERE id="+id;
			PreparedStatement prepstmt = c.prepareStatement(tagSql);
			ResultSet result = prepstmt.executeQuery();
			Tag tag = new Tag();
			while(result.next()) {
				tag.setId(result.getInt("id"));
				tag.setName(result.getString("name"));
			}
			prepstmt.close();
			return tag;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}