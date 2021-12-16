package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.data.TransactionDAOJDBC;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Transaction;

public class TransactionService implements ITransactionService {

	@Override
	public void addTransaction(Transaction transaction) throws DataException, BusinessException {
		String exceptions = "";
		IClientService clientService = new ClientService();
		if(clientService.retrieveClientById(transaction.getClient()).getCpf() == null) {
			exceptions += "Cliente inexistente \n";
		}
		IBookService bookService = new BookService();
		ArrayList<Book>books = transaction.getBooks();
		for(Book book : books) {
			if(bookService.retrieveBookByBarcode(book.getBarcode()).getBarcode() == null) {
				exceptions += "Livro inexistente \n";
			}
		}
		if(books.size()<1) {
			exceptions += "Insira ao menos um livro na compra \n";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new TransactionDAOJDBC().addTransaction(transaction);
	}

	@Override
	public void removeTransaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTransaction(Transaction transaction) throws DataException, BusinessException {
		String exceptions = "";
		IClientService clientService = new ClientService();
		if(retrieveTransactionById(transaction.getId()).getId() == 0) {
			exceptions += "Transaction inexistente \n";
		}
		if(clientService.retrieveClientById(transaction.getClient()).getCpf() == null) {
			exceptions += "Cliente inexistente \n";
		}
		IBookService bookService = new BookService();
		ArrayList<Book>books = transaction.getBooks();
		for(Book book : books) {
			if(bookService.retrieveBookByBarcode(book.getBarcode()).getBarcode() == null) {
				exceptions += "Livro inexistente \n";
			}
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new TransactionDAOJDBC().updateTransaction(transaction);

	}

	@Override
	public List<Transaction> listTransactions() throws DataException {
		return new TransactionDAOJDBC().listTransactions();
	}

	@Override
	public ArrayList<Transaction> retrieveTransactionsByClient(int client) throws BusinessException, DataException {
		if(client < 1) {
			throw new BusinessException("id do cliente deve ser maior do que 0 \n");
		}
		return new TransactionDAOJDBC().retrieveTransactionsByClient(client);
	}
	
	public Transaction retrieveTransactionById(int id) throws BusinessException, DataException{
		if(id < 1) {
			throw new BusinessException("id da transação deve ser um número maior do que 0 \n");
		}
		return new TransactionDAOJDBC().retrieveTransactionById(id);
	}
	
}
