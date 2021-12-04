package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.model.Transaction;

public interface ITransactionService {
	
	public void addTransaction(Transaction transaction);
	
	public void removeTransaction();
	
	public void updateTransaction();
	
	public List<Transaction> listTransactions();
	
	public ArrayList<Transaction> retrieveTransactionsByClient(int client);
}
