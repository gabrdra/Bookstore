package br.ufrn.imd.data;

import java.util.ArrayList;

import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Book;

public interface BookDAO {
	
	public void addBook(Book book) throws DataException;
	
	public void removeBook(Book book) throws DataException;
	
	public void updateBook(Book book) throws DataException;
	
	public ArrayList<Book> listBooks() throws DataException;

	public Book retrieveBookById(int id) throws DataException;
	
	public Book retrieveBookByBarcode(String barcode) throws DataException;

}
