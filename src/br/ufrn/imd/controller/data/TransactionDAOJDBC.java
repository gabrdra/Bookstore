package br.ufrn.imd.controller.data;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import br.ufrn.imd.controller.database.ConnectionJDBC;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Transaction;

public class TransactionDAOJDBC implements TransactionDAO{
	private Connection connection;
	
	public TransactionDAOJDBC(){
		this.connection = new ConnectionJDBC().getCon();
	}

	@Override
	public void addTransaction(Transaction transaction) {
		String sql="INSERT INTO public.transaction (id, client, books) VALUES (?, ?, ?);";
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			stmt.setInt(1, transaction.getId());
			stmt.setInt(2, transaction.getClient());
			Array tempArray = connection.createArrayOf("INTEGER", transaction.getBooksId().toArray());
			stmt.setArray(3, tempArray);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void removeTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Transaction consultTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Transaction> listTransactions() {
		
		List<Transaction> listTransactions = new ArrayList<Transaction>();
		
		try {
			BookDAOJDBC bookDAOJDBC = new BookDAOJDBC();
			String sql = "SELECT * FROM public.Transaction";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				Transaction Transaction = new Transaction();
				
				Transaction.setId(resultSet.getInt("id"));


				Integer[] tempArray = (Integer[])resultSet.getArray("Books").getArray();
				ArrayList<Book> localBooks = new ArrayList<Book>();
				for(Integer i:tempArray) {
					Book Book = bookDAOJDBC.getBookById(i);
					if(Book != null) {
						localBooks.add(Book);
					}
				}
				Transaction.setBooks(localBooks);
				listTransactions.add(Transaction);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listTransactions;
	}

	@Override
	public ArrayList<Transaction> getTransactionsByClient(int client) {
		try {
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			BookDAOJDBC bookDAOJDBC = new BookDAOJDBC();
			String sql = "SELECT * FROM public.transaction WHERE client = "+ client;
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();		
			while(resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(resultSet.getInt("id"));
				transaction.setClient(resultSet.getInt("client"));
				Integer[] tempArray = (Integer[])resultSet.getArray("books").getArray();
				ArrayList<Book> books = new ArrayList<Book>();
				for(Integer i:tempArray) {
					Book book = bookDAOJDBC.getBookById(i);
					if(book != null) {
						books.add(book);
					}
				}
				transaction.setBooks(books);
				transactions.add(transaction);
			}
			return transactions;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
