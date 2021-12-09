package br.ufrn.imd.data;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Transaction;


public interface TransactionDAO {

	public void addTransaction(Transaction transaction) throws DataException;
	
	public void removeTransaction();
	
	public Transaction consultTransaction();
	
	public void updateTransaction();
	
	public List<Transaction> listTransactions() throws DataException;
	
	public ArrayList<Transaction> retrieveTransactionsByClient(int client) throws DataException; 
}
