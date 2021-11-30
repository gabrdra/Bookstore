package br.ufrn.imd.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.DAO.BookDAO;
import br.ufrn.imd.DAO.ClientDAO;
import br.ufrn.imd.DAO.TransactionDAO;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Client;
import br.ufrn.imd.model.Transaction;

public class Queries {
	
	private List<Book> listBooks;
	private List<Client> listClients;
	
	
	public Queries() {
		super();
		this.listBooks = new BookDAO().listBooks();
		this.listClients = new ClientDAO().ListClient();
	}
	
	
	public List<Book> getListBooks() {
		return listBooks;
	}
	public void setListBooks(List<Book> listBooks) {
		this.listBooks = listBooks;
	}
	public List<Client> getListClients() {
		return listClients;
	}
	public void setListClients(List<Client> listClients) {
		this.listClients = listClients;
	}
	
	public Client buscaClientNome(String nome) {
		for (Client client : listClients) {
			if (client.getName().equals(nome)) {
				System.out.println("========Cliente Encontrado==========");
				System.out.println("Nome:"+client.getName());
				System.out.println("CPF:"+client.getCpf());
				System.out.println("==================================");
				return client;
			}
		}
		return null;
	}
	
	public Client buscaClientCpf(long cpf) {
		for (Client client : listClients) {
			if (client.getCpf() == cpf) {
				System.out.println("========Cliente Encontrado==========");
				System.out.println("Nome:"+client.getName());
				System.out.println("CPF:"+client.getCpf());
				System.out.println("==================================");
				return client;
			}
		}
		
		return null;
		
	}
	
	public Book buscaBookNome(String nome) {
		for (Book book : listBooks) {
			if (book.getName().equals(nome)) {
				System.out.println("========Livro Encontrado==========");
				System.out.println("Nome:"+book.getName());
				System.out.println("Autor:"+book.getAuthor());
				System.out.println("Descrição:"+book.getDescription());
				System.out.println("ID:"+book.getId());
				System.out.println("Preço:"+book.getPrice());
				System.out.println("Tags::"+book.getTags());
				System.out.println("==================================");
				return book;
			}
		}
		return null;
	}
	
	public Book buscaBookAutor(String author) {
		
		for (Book book : listBooks) {
			if (book.getAuthor().equals(author)) {
				System.out.println("========Livro Encontrado==========");
				System.out.println("Nome:"+book.getName());
				System.out.println("Autor:"+book.getAuthor());
				System.out.println("Descrição:"+book.getDescription());
				System.out.println("ID:"+book.getId());
				System.out.println("Preço:"+book.getPrice());
				System.out.println("Tags::"+book.getTags());
				System.out.println("==================================");
				return book;
			}
		}
		
		return null;
		
	}
	
	public Book buscaBookId(int id) {
		
		for (Book book : listBooks) {
			if (book.getId() == id) {
				System.out.println("========Livro Encontrado==========");
				System.out.println("Nome:"+book.getName());
				System.out.println("Autor:"+book.getAuthor());
				System.out.println("Descrição:"+book.getDescription());
				System.out.println("ID:"+book.getId());
				System.out.println("Preço:"+book.getPrice());
				System.out.println("Tags::"+book.getTags());
				System.out.println("==================================");
				return book;
			}
		}
		
		return null;
		
	}
	
	public Book buscaBookDescricao(String description) {
		
		for (Book book : listBooks) {
			if(book.getDescription() != null) {
				if (book.getDescription().equals(description)) {
					System.out.println("========Livro Encontrado==========");
					System.out.println("Nome:"+book.getName());
					System.out.println("Autor:"+book.getAuthor());
					System.out.println("Descrição:"+book.getDescription());
					System.out.println("ID:"+book.getId());
					System.out.println("Preço:"+book.getPrice());
					System.out.println("Tags::"+book.getTags());
					System.out.println("==================================");
					return book;
				}
			}
		}
		
		return null;
		
	}
	
	public ArrayList<Transaction> getTransactionsByClient(int Client){
		TransactionDAO transactionDAO = new TransactionDAO();
		ArrayList<Transaction> transactions = transactionDAO.getTransactionsByClient(Client);
		/*for(Transaction t: transactions) {
			System.out.println("========Transa��o Encontrada==========");
			System.out.println("Id:"+t.getId());
			System.out.println("Cliente: "+t.getClient());
			for(Book b: t.getBooks()) {
				System.out.println("Livro: " + b);
			}
			System.out.println("==================================");
		}*/
		return transactions;
	}
}