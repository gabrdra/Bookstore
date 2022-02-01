package br.ufrn.imd.model;

import java.util.ArrayList;

public class Transaction {
	private int id;
	private double value;
	private int client;
	private ArrayList<ProductBook> books;
	
	
	public Transaction() {
		super();
		this.books = new ArrayList<ProductBook>();
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
	public ArrayList<ProductBook> getBooks() {
		return books;
	}
	public void setBooks(ArrayList<ProductBook> books) {
		this.books = books;
	}
	
	public ArrayList<Integer> getBooksId() {
		ArrayList<Integer> tempArray = new ArrayList<Integer>();
		for (ProductBook book : books) {
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
