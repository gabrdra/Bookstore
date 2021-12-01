package br.ufrn.imd.controller.data;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.model.Transaction;


public interface TransactionDAO {

	public void addTransaction(Transaction transaction);
	
	public void removeTransaction();
	
	public Transaction consultTransaction();
	
	public void updateTransaction();
	
	public List<Transaction> listTransactions();
	
	public ArrayList<Transaction> getTransactionsByClient(int client); 
}
