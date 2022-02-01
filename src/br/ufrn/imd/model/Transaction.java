package br.ufrn.imd.model;

import java.util.ArrayList;

public class Transaction {
	private int id;
	private double value;
	private int client;
	private ArrayList<Integer> productsId;
	
	
	public Transaction() {
		super();
		this.productsId = new ArrayList<Integer>();
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
	public ArrayList<Integer> getProductsId() {
		return productsId;
	}
	public void setProductsId(ArrayList<Integer> products) {
		this.productsId = products;
	}
	
	/*public ArrayList<Integer> getBooksId() {
		ArrayList<Integer> tempArray = new ArrayList<Integer>();
		for (ProductBook book : books) {
			tempArray.add(book.getId());
		}
		return tempArray;
	}*/
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id: "+id+ " client: " + client + " value: "+value+ " products: "+ productsId;
	}
	
}
