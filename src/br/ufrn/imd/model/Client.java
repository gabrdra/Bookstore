package br.ufrn.imd.model;

import java.util.HashSet;

public class Client {

	private int cpf;
	private String name;
	private HashSet<Transaction> transactions;

	
	
	public Client() {
		super();
		
		this.transactions = new HashSet<Transaction>();
	}
	
	
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashSet<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(HashSet<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	
}
