package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Transaction;

public interface ITransactionService {
	
	public void addTransaction(Transaction transaction) throws DataException, BusinessException;
	
	public void removeTransaction();
	
	public void updateTransaction(Transaction transaction) throws DataException, BusinessException;
	
	public List<Transaction> listTransactions() throws DataException;
	
	public ArrayList<Transaction> retrieveTransactionsByClient(int client) throws BusinessException, DataException;

	public Transaction retrieveTransactionById(int id) throws BusinessException, DataException;	
}
