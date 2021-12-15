package br.ufrn.imd.model;

import java.util.ArrayList;

public class Transaction {
	private int id;
	private double value;
	private int client;
	private ArrayList<Book> books;
	
	
	public Transaction() {
		super();
		this.books = new ArrayList<Book>();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public void setClient(int client) {
		this.client = client;
	}
	public int getClient() {
		return this.client;
	}
	public ArrayList<Book> getBooks() {
		return books;
	}
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	public ArrayList<Integer> getBooksId() {
		ArrayList<Integer> tempArray = new ArrayList<Integer>();
		for (Book book : books) {
			tempArray.add(book.getId());
		}
		return tempArray;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id: "+id+ " client: " + client + " value: "+value+ " books: "+ books;
	}
	
}
