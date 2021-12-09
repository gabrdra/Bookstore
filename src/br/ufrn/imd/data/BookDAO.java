package br.ufrn.imd.data;

import java.util.ArrayList;

import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Book;

public interface BookDAO {
	
	public void addBook(Book bo) throws DataException;
	
	public void removeBook();
	
	public void updateBook();
	
	public ArrayList<Book> listBooks() throws DataException;

	public Book retrieveBookById(int id) throws DataException;
	
	public Book retrieveBookByBarcode(String barcode) throws DataException;

}
