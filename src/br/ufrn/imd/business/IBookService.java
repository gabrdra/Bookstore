package br.ufrn.imd.business;

import java.util.ArrayList;

import br.ufrn.imd.model.Book;

public interface IBookService {
	
	public void addBook(Book bo);
	
	public void removeBook();
	
	public void updateBook();
	
	public ArrayList<Book> listBooks();

	public Book retrieveBookById(int id);
}
