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
		for(Book book : transaction.getBooks()) {
			if(bookService.retrieveBookByBarcode(book.getBarcode()).getBarcode() == null) {
				exceptions += "Livro inexistente \n";
			}
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		new TransactionService().addTransaction(transaction);
	}

	@Override
	public void removeTransaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTransaction() {
		// TODO Auto-generated method stub

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

}
