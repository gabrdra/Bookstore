package br.ufrn.imd.model;

import java.util.HashSet;

public class Client {

	private long cpf;
	private String name;
	private HashSet<Transaction> transactions;

	
	
	public Client() {
		super();
		
		this.transactions = new HashSet<Transaction>();
	}
	
	
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long l) {
		this.cpf = l;
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
