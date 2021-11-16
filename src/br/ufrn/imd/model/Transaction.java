package br.ufrn.imd.model;

import java.util.HashSet;

public class Transaction {
	private int id;
	private double value;
	private HashSet<Book> books;
	
	
	public Transaction() {
		super();
		this.books = new HashSet<Book>();
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
	public HashSet<Book> getBooks() {
		return books;
	}
	public void setBooks(HashSet<Book> books) {
		this.books = books;
	}
	
	
}
