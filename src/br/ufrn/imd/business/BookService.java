package br.ufrn.imd.business;

import java.util.ArrayList;

import br.ufrn.imd.data.BookDAOJDBC;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Book;

public class BookService implements IBookService {

	@Override
	public void addBook(Book bo) throws BusinessException, DataException {
		//NEEDS TO ADD: check if all the tags are valid
		String exceptions = "";
		if(retrieveBookByBarcode(bo.getBarcode()).getBarcode() != null) {
			exceptions += "Código de barras já foi cadastrado em outro livro \n";
		}
		if(bo.getName().length() >= 63) {
			exceptions += "Nome do livro muito longo \n";
		}
		else if(bo.getName().length() <= 1) {
			exceptions += "Nome do livro muito curto \n";
		}
		if(bo.getAuthor().length() >= 63) {
			exceptions += "Nome do(a) autor(a) muito longo \n";
		}
		else if(bo.getAuthor().length() <= 1) {
			exceptions += "Nome do(a) autor(a) muito curto \n";
		}
		if(bo.getPrice() < 0) {
			exceptions += "Preço do livro não pode ser negativo \n";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new BookDAOJDBC().addBook(bo);
	}

	@Override
	public void removeBook() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBook() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Book> listBooks() throws DataException {
		return new BookDAOJDBC().listBooks();
	}

	@Override
	public Book retrieveBookById(int id) throws BusinessException, DataException {
		if(id < 1) {
			throw new BusinessException("id deve ser um número maior do que 0 \n");
		}
		return new BookDAOJDBC().retrieveBookById(id);
	}

	@Override
	public Book retrieveBookByBarcode(String barcode) throws BusinessException, DataException {
		String exceptions = "";
		if(!barcode.matches("[0-9]+")) {
			exceptions += "O código de barras deve conter somente números \n";
		}
		if(barcode.length()!= 13) {
			exceptions += "O código de barras deve conter exatamente 13 números \n";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}		
		return new BookDAOJDBC().retrieveBookByBarcode(barcode);
	}

}
