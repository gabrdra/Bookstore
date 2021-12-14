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
		this.connection= ConnectionJDBC.getInstance().getCon();
	}

	@Override
	public void addTag(Tag tag) throws DataException {
		String sql="INSERT INTO public.tag (name) VALUES (?);";
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			stmt.setString(1, tag.getName());
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
	public void updateTag(Tag tag) throws DataException{
		String sql="UPDATE public.tag SET name = (?) WHERE id="+tag.getId();
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			stmt.setString(1, tag.getName());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException("Erro ao tentar atualizar a tag no banco de dados \n");
		}
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
				listTags.add(Tag);
			}
			stmt.close();
			return listTags;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new DataException("Erro ao tentar listar as tags armazenados no banco de dados \n");
		}
	}
	@Override
	public Tag retrieveTagById(int id) throws DataException{
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
			throw new DataException("Erro ao tentar pegar a tag usando a id \n");
			//e.printStackTrace();
			//return null;
		}
		
	}

	@Override
	public Tag retrieveTagByName(String name) throws DataException {
		try {
			String tagSql = "SELECT * FROM public.tag WHERE upper(name)='"+name.toUpperCase()+"'";
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
			throw new DataException("Erro ao tentar pegar a tag usando o nome \n");
			//return null;
		}
	}
	
	
	

}
