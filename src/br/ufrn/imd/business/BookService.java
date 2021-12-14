package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.data.BookDAOJDBC;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Tag;
import br.ufrn.imd.model.Transaction;

public class BookService implements IBookService {

	@Override
	public void addBook(Book book) throws BusinessException, DataException {
		String exceptions = "";
		if(retrieveBookByBarcode(book.getBarcode()).getBarcode() != null) {
			exceptions += "Código de barras já foi cadastrado em outro livro \n";
		}
		if(book.getName().length() >= 63) {
			exceptions += "Nome do livro muito longo \n";
		}
		else if(book.getName().length() <= 1) {
			exceptions += "Nome do livro muito curto \n";
		}
		if(book.getAuthor().length() >= 63) {
			exceptions += "Nome do(a) autor(a) muito longo \n";
		}
		else if(book.getAuthor().length() <= 1) {
			exceptions += "Nome do(a) autor(a) muito curto \n";
		}
		if(book.getPrice() < 0) {
			exceptions += "Preço do livro não pode ser negativo \n";
		}
		ITagService tagService = new TagService();
		for(Tag tag: book.getTags()) {
			if(tagService.retrieveTagById(tag.getId()).getName() == null) {
				exceptions += "Tag inexistente \n";
			}
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new BookDAOJDBC().addBook(book);
	}

	@Override
	public void removeBook(Book book) throws DataException, BusinessException{
		String exceptions = "";
		List<Transaction> transactions = new TransactionService().listTransactions();
		for(Transaction transaction: transactions) {
			boolean found = false;
			for(Book bookOnTransaction: transaction.getBooks()) {
				if(bookOnTransaction.getId() == book.getId()) {
					exceptions += "O livro não pode ser removido pois existe dentro de ao menos uma transação \n";
					found = true;
					break;
				}
			}
			if(found) {
				break;
			}
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new BookDAOJDBC().removeBook(book);

	}

	@Override
	public void updateBook(Book book) throws DataException, BusinessException{
		String exceptions = "";
		Book bookDb = retrieveBookByBarcode(book.getBarcode());
		if(book.getId() != bookDb.getId() && bookDb.getBarcode()!= null) {
			System.out.println(retrieveBookById(book.getId()).getId());
			if(retrieveBookById(book.getId()).getId()==0) {
				exceptions += "Livro inexistente \n";
			}
			else {
				exceptions += "Código de barras já foi cadastrado em outro livro \n";
			}
		}
		if(book.getName().length() >= 63) {
			exceptions += "Nome do livro muito longo \n";
		}
		else if(book.getName().length() <= 1) {
			exceptions += "Nome do livro muito curto \n";
		}
		if(book.getAuthor().length() >= 63) {
			exceptions += "Nome do(a) autor(a) muito longo \n";
		}
		else if(book.getAuthor().length() <= 1) {
			exceptions += "Nome do(a) autor(a) muito curto \n";
		}
		if(book.getPrice() < 0) {
			exceptions += "Preço do livro não pode ser negativo \n";
		}
		ITagService tagService = new TagService();
		for(Tag tag: book.getTags()) {
			if(tagService.retrieveTagById(tag.getId()).getName() == null) {
				exceptions += "Tag inexistente \n";
			}
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new BookDAOJDBC().updateBook(book);

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
