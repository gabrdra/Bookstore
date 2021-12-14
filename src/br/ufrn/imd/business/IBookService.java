package br.ufrn.imd.business;

import java.util.ArrayList;

import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Book;

public interface IBookService {
	
	public void addBook(Book book) throws BusinessException, DataException;
	
	public void removeBook(Book book) throws DataException, BusinessException;
	
	public void updateBook(Book book) throws DataException, BusinessException;
	
	public ArrayList<Book> listBooks() throws DataException;

	public Book retrieveBookById(int id) throws BusinessException, DataException;
	
	public Book retrieveBookByBarcode(String barcode) throws BusinessException, DataException;
}
