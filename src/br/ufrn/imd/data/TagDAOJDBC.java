package br.ufrn.imd.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.data.connection.ConnectionJDBC;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Tag;


public class TagDAOJDBC implements TagDAO{
	private Connection connection;
	
	public TagDAOJDBC() {
		this.connection= new ConnectionJDBC().getCon();
	}

	@Override
	public void addTag(Tag tag) throws DataException {
		String sql="INSERT INTO public.tag (id, name) VALUES (?, ?);";
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			stmt.setInt(1, tag.getId());
			stmt.setString(2, tag.getName());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DataException("Erro ao tentar inserir a tag no banco de dados \n");
			//e.printStackTrace();
		}		
	}

	@Override
	public void removeTag() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tag consultTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTag() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tag> listTags() throws DataException{
		List<Tag> listTags = new ArrayList<Tag>();
		
		try {
			String sql = "SELECT * FROM public.tag";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				Tag Tag = new Tag();
				Tag.setId(resultSet.getInt("id"));
				Tag.setName(resultSet.getString("name"));
			}
			stmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listTags;
	}

	@Override
	public Tag getTagById(int id) throws DataException{
		try {
			String tagSql = "SELECT * FROM public.tag WHERE id="+id;
			PreparedStatement prepstmt = connection.prepareStatement(tagSql);
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
