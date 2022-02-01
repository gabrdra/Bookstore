package br.ufrn.imd.model.recommendation;

import br.ufrn.imd.model.ProductBook;

public class WeightBook implements Comparable<WeightBook>{
	private int weight;
	private ProductBook book;
	public WeightBook(int weight, ProductBook book) {
		this.weight = weight;
		this.book = book;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public ProductBook getBook() {
		return book;
	}
	public void setBook(ProductBook book) {
		this.book = book;
	}
	@Override
	public int compareTo(WeightBook o) {
		if(this.weight>o.weight) {
			return 1;
		}
		else if(this.weight < o.weight) {
			return -1;
		}
		else {
			return 0;
		}
	}
	@Override
	public String toString() {
		return "Weight: "+ weight+" Name: "+book.getName();
	}
	
}
