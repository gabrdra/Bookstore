package br.ufrn.imd.controller.data;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.ufrn.imd.controller.database.ConnectionJDBC;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Tag;

public class BookDAOJDBC implements BookDAO{
	
	private Connection connection;

	public BookDAOJDBC() {
		this.connection= new ConnectionJDBC().getCon();
	}
	
	@Override
	public void addBook(Book bo) {
		String sql="INSERT INTO public.book (id, description, price, author, name, tags) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			stmt.setInt(1, bo.getId());
			stmt.setString(2, bo.getDescription());
			stmt.setDouble(3, bo.getPrice());
			stmt.setString(4, bo.getAuthor());
			stmt.setString(5, bo.getName());
			Array tempArray = connection.createArrayOf("INTEGER", bo.getTagsId().toArray());
			stmt.setArray(6, tempArray);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeBook() {
		// TODO Auto-generated method stub
	}

	@Override
	public Book consultBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBook() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Book> listBooks() {
		
		ArrayList<Book> listBooks = new ArrayList<Book>();
		
		try {
			TagDAOJDBC tagDAOJDBC = new TagDAOJDBC();
			String sql = "SELECT * FROM public.book";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				Book book = new Book();
				
				book.setId(resultSet.getInt("id"));
				book.setName(resultSet.getString("name"));
				book.setAuthor(resultSet.getString("author"));
				book.setDescription(resultSet.getString("description"));
				book.setPrice(resultSet.getDouble("price"));
				Integer[] tempArray = (Integer[])resultSet.getArray("tags").getArray();
				ArrayList<Tag> localTags = new ArrayList<Tag>();
				for(Integer i:tempArray) {
					Tag tag = tagDAOJDBC.getTagById(i);
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
	
	public Book getBookById(int id) {
		try {
			TagDAOJDBC tagDAOJDBC = new TagDAOJDBC();
			String bookSql = "SELECT * FROM public.book WHERE id="+id;
			PreparedStatement prepstmt = connection.prepareStatement(bookSql);
			ResultSet result = prepstmt.executeQuery();
			Book book = new Book();
			while(result.next()) {
				book.setId(result.getInt("id"));
				book.setName(result.getString("name"));
				book.setPrice(result.getDouble("price"));
				book.setAuthor(result.getString("author"));
				book.setDescription(result.getString("description"));
				Integer[] tempArray = (Integer[])result.getArray("tags").getArray();
				ArrayList<Tag> localTags = new ArrayList<Tag>();
				for(Integer i:tempArray) {
					Tag tag = tagDAOJDBC.getTagById(i);
					if(tag != null) {
						localTags.add(tag);
					}
				}
				book.setTags(localTags);
			}
			prepstmt.close();
			return book;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
