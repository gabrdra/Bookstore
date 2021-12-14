package br.ufrn.imd.data;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.ufrn.imd.data.connection.ConnectionJDBC;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Tag;

public class BookDAOJDBC implements BookDAO{
	
	private Connection connection;

	public BookDAOJDBC() {
		this.connection= ConnectionJDBC.getInstance().getCon();
	}
	
	@Override
	public void addBook(Book bo) throws DataException{
		String sql="INSERT INTO public.book (description, price, author, name, tags, barcode) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			//stmt.setInt(1, bo.getId());
			stmt.setString(1, bo.getDescription());
			stmt.setDouble(2, bo.getPrice());
			stmt.setString(3, bo.getAuthor());
			stmt.setString(4, bo.getName());
			Array tempArray = connection.createArrayOf("INTEGER", bo.getTagsId().toArray());
			stmt.setArray(5, tempArray);
			stmt.setString(6, bo.getBarcode());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DataException("Erro ao tentar inserir o livro no banco de dados \n");
			//e.printStackTrace();
		}
	}

	@Override
	public void removeBook() {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateBook(Book bo) throws DataException{
		String sql="UPDATE public.book SET description = (?), price = (?), author = (?), name = (?), tags = (?), barcode = (?) WHERE id="+bo.getId();
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			//stmt.setInt(1, bo.getId());
			stmt.setString(1, bo.getDescription());
			stmt.setDouble(2, bo.getPrice());
			stmt.setString(3, bo.getAuthor());
			stmt.setString(4, bo.getName());
			Array tempArray = connection.createArrayOf("INTEGER", bo.getTagsId().toArray());
			stmt.setArray(5, tempArray);
			stmt.setString(6, bo.getBarcode());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DataException("Erro ao tentar atualizar o livro no banco de dados \n");
			//e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Book> listBooks() throws DataException {
		
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
				book.setBarcode(resultSet.getString("barcode"));
				Integer[] tempArray = (Integer[])resultSet.getArray("tags").getArray();
				ArrayList<Tag> localTags = new ArrayList<Tag>();
				for(Integer i:tempArray) {
					Tag tag = tagDAOJDBC.retrieveTagById(i);
					if(tag != null) {
						localTags.add(tag);
					}
				}
				book.setTags(localTags);
				listBooks.add(book);
			}
			stmt.close();
			
		} catch (Exception e) {
			//e.printStackTrace();
			throw new DataException("Erro ao tentar listar os livros armazenados no banco de dados \n");
		}
		
		
		return listBooks;
	}
	
	public Book retrieveBookById(int id) throws DataException {
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
				book.setBarcode(result.getString("barcode"));
				book.setDescription(result.getString("description"));
				Integer[] tempArray = (Integer[])result.getArray("tags").getArray();
				ArrayList<Tag> localTags = new ArrayList<Tag>();
				for(Integer i:tempArray) {
					Tag tag = tagDAOJDBC.retrieveTagById(i);
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
			throw new DataException("Erro ao tentar recuperar o livro usando id \n");
			//return null;
		}
	}

	@Override
	public Book retrieveBookByBarcode(String barcode) throws DataException{
		try {
			TagDAOJDBC tagDAOJDBC = new TagDAOJDBC();
			String bookSql = "SELECT * FROM public.book WHERE barcode='"+barcode+"'";
			PreparedStatement prepstmt = connection.prepareStatement(bookSql);
			ResultSet result = prepstmt.executeQuery();
			Book book = new Book();
			while(result.next()) {
				book.setId(result.getInt("id"));
				book.setName(result.getString("name"));
				book.setPrice(result.getDouble("price"));
				book.setAuthor(result.getString("author"));
				book.setBarcode(result.getString("barcode"));
				book.setDescription(result.getString("description"));
				Integer[] tempArray = (Integer[])result.getArray("tags").getArray();
				ArrayList<Tag> localTags = new ArrayList<Tag>();
				for(Integer i:tempArray) {
					Tag tag = tagDAOJDBC.retrieveTagById(i);
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
			throw new DataException("Erro ao tentar recuperar o livro usando o código de barras \n");
			//return null;
		}
	}


}
