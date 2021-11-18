package br.ufrn.imd.controller;

import java.util.List;

import br.ufrn.imd.DAO.BookDAO;
import br.ufrn.imd.DAO.ClientDAO;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Client;

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
				return client;
			}
		}
		return null;
	}
	
	public Client buscaClientCpf(String cpf) {
		
		
		return null;
		
	}
	
	public Book buscaBookNome(String nome) {
		for (Book book : listBooks) {
			System.out.println(book.getName());
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
	
	public Book buscaBookAutor(String autor) {
		
		
		return null;
		
	}
	
	public Book buscaBookId(String id) {
		
		
		return null;
		
	}
	
	public Book buscaBookDescricao(String descricao) {
		
		
		return null;
		
	}
	
	
}
