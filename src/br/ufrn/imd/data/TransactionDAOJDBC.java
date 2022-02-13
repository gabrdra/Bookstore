package br.ufrn.imd.data;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.data.connection.ConnectionJDBC;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Transaction;

public class TransactionDAOJDBC implements TransactionDAO{
	private Connection connection;
	
	public TransactionDAOJDBC(){
		this.connection = ConnectionJDBC.getInstance().getCon();
	}

	@Override
	public void addTransaction(Transaction transaction) throws DataException{
		String sql="INSERT INTO public.transaction (client, products, value) VALUES (?, ?, ?);";
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			//stmt.setInt(1, transaction.getId());
			stmt.setInt(1, transaction.getClient());
			Array tempArray = connection.createArrayOf("INTEGER", transaction.getProductsId().toArray());
			stmt.setArray(2, tempArray);
			stmt.setDouble(3, transaction.getValue());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new DataException("Erro ao tentar inserir a transa��o no banco de dados \n");
			//e.printStackTrace();
		}	
	}

	@Override
	public void removeTransaction() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateTransaction(Transaction transaction) throws DataException {
		String sql="UPDATE public.transaction SET client = (?), products = (?), value = (?) WHERE id="+transaction.getId();
		try {
			PreparedStatement stmt=connection.prepareStatement(sql);
			//stmt.setInt(1, transaction.getId());
			stmt.setInt(1, transaction.getClient());
			Array tempArray = connection.createArrayOf("INTEGER", transaction.getProductsId().toArray());
			stmt.setArray(2, tempArray);
			stmt.setDouble(3, transaction.getValue());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new DataException("Erro ao tentar atualizar a transa��o no banco de dados \n");
			
		}	
		
	}

	@Override
	public List<Transaction> listTransactions() throws DataException{
		
		List<Transaction> listTransactions = new ArrayList<Transaction>();
		
		try {
			//ProductBookDAOJDBC bookDAOJDBC = new ProductBookDAOJDBC();
			String sql = "SELECT * FROM public.Transaction";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				Transaction transaction = new Transaction();
				
				transaction.setId(resultSet.getInt("id"));
				transaction.setClient(resultSet.getInt("client"));
				transaction.setValue(resultSet.getDouble("value"));
				Integer[] tempArray = (Integer[])resultSet.getArray("products").getArray();
				ArrayList<Integer> localProductsId = new ArrayList<Integer>();
				for (Integer i: tempArray) {
					localProductsId.add(i);
				}
				/*ArrayList<ProductBook> localBooks = new ArrayList<ProductBook>();
				for(Integer i:tempArray) {
					ProductBook Book = bookDAOJDBC.retrieveBookById(i);
					if(Book != null) {
						localBooks.add(Book);
					}
				}*/
				//transaction.setBooks(localBooks);
				transaction.setProductsId(localProductsId);
				listTransactions.add(transaction);
			}
			stmt.close();
		} catch (Exception e) {
			throw new DataException("Erro ao tentar listar as transa��es armazenados no banco de dados \n");
			//e.printStackTrace();
		}
		
		return listTransactions;
	}

	@Override
	public ArrayList<Transaction> retrieveTransactionsByClient(int client) throws DataException{
		try {
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			//ProductBookDAOJDBC bookDAOJDBC = new ProductBookDAOJDBC();
			String sql = "SELECT * FROM public.transaction WHERE client = "+ client;
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();		
			while(resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(resultSet.getInt("id"));
				transaction.setClient(resultSet.getInt("client"));
				transaction.setValue(resultSet.getDouble("value"));
				Integer[] tempArray = (Integer[])resultSet.getArray("products").getArray();
				ArrayList<Integer> localProductsId = new ArrayList<Integer>();
				for (Integer i: tempArray) {
					localProductsId.add(i);
				}
				transaction.setProductsId(localProductsId);
				/*ArrayList<ProductBook> books = new ArrayList<ProductBook>();
				for(Integer i:tempArray) {
					ProductBook book = bookDAOJDBC.retrieveBookById(i);
					if(book != null) {
						books.add(book);
					}
				}
				transaction.setBooks(books);*/
				
				transactions.add(transaction);
			}
			return transactions;
		}catch (Exception e) {
			throw new DataException("Erro ao tentar recuperar as transa��es para o cliente passado \n");
			//e.printStackTrace();
			//return null;
		}
	}

	public Transaction retrieveTransactionById(int id) throws DataException{
		try {
			Transaction transaction = new Transaction();
			//ProductBookDAOJDBC bookDAOJDBC = new ProductBookDAOJDBC();
			String sql = "SELECT * FROM public.transaction WHERE id = "+ id;
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();		
			while(resultSet.next()) {
				transaction.setId(resultSet.getInt("id"));
				transaction.setClient(resultSet.getInt("client"));
				transaction.setValue(resultSet.getDouble("value"));
				Integer[] tempArray = (Integer[])resultSet.getArray("products").getArray();
				/*ArrayList<ProductBook> books = new ArrayList<ProductBook>();
				for(Integer i:tempArray) {
					ProductBook book = bookDAOJDBC.retrieveBookById(i);
					if(book != null) {
						books.add(book);
					}
				}
				transaction.setBooks(books);*/
				ArrayList<Integer> localProductsId = new ArrayList<Integer>();
				for (Integer i: tempArray) {
					localProductsId.add(i);
				}
				transaction.setProductsId(localProductsId);
				//transactions.add(transaction);
			}
			return transaction;
		}catch (Exception e) {
			throw new DataException("Erro ao tentar recuperar as transa��es para o cliente passado \n");
			//e.printStackTrace();
			//return null;
		}
	}
	
	
}
