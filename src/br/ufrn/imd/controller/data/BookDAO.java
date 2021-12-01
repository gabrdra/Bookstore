package br.ufrn.imd.controller.data;

import java.util.ArrayList;

import br.ufrn.imd.model.Book;

public interface BookDAO {
	
	public void addBook(Book bo);
	
	public void removeBook();
	
	public Book consultBook();
	
	public void updateBook();
	
	public ArrayList<Book> listBooks();

	public Book getBookById(int id);

}
