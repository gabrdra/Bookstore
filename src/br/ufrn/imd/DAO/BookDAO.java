package br.ufrn.imd.DAO;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.postgresql.jdbc.PgArray;

import br.ufrn.imd.controller.Conection;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Tag;

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
	
public List<Book> listBooks() {
		
		List<Book> listBooks = new ArrayList<Book>();
		
		try {
			TagDAO tagDAO = new TagDAO();
			String sql = "SELECT * FROM public.book";
			
			PreparedStatement stmt = c.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				Book book = new Book();
				
				book.setId(resultSet.getInt("id"));
				book.setName(resultSet.getString("name"));
				book.setAuthor(resultSet.getString("author"));
				book.setDescription(resultSet.getString("description"));
				book.setPrice(resultSet.getDouble("price"));
				Integer[] tempArray = (Integer[])resultSet.getArray("tags").getArray();
				//book.setTags((ArrayList<Integer>) Arrays.stream(tempArray).collect(Collectors.toList()));
				ArrayList<Tag> localTags = new ArrayList<Tag>();
				for(Integer i:tempArray) {
					Tag tag = tagDAO.getTagById(i);
					if(tag != null) {
						localTags.add(tag);
					}
				}
				book.setTags(localTags);
				listBooks.add(book);
			}
			stmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listBooks;
	}
	
	
}
