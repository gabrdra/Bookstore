package br.ufrn.imd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.ufrn.imd.controller.Conection;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Transaction;

public class TransactionDAO {
	private Connection c;
	
	public TransactionDAO(){
		this.c = new Conection().getCon();
	}
	
	public ArrayList<Transaction> getTransactionsByClient(int client) {
		try {
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			BookDAO bookDAO = new BookDAO();
			String sql = "SELECT * FROM public.transaction WHERE client = "+ client;
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();		
			while(resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(resultSet.getInt("id"));
				transaction.setClient(resultSet.getInt("client"));
				Integer[] tempArray = (Integer[])resultSet.getArray("books").getArray();
				ArrayList<Book> books = new ArrayList<Book>();
				for(Integer i:tempArray) {
					Book book = bookDAO.getBookById(i);
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
