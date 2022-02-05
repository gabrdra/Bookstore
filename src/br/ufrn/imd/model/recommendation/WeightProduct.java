package br.ufrn.imd.model.recommendation;

import br.ufrn.imd.model.Product;

public class WeightProduct implements Comparable<WeightProduct>{
	private int weight;
	private Product product;
	public WeightProduct(int weight, Product product) {
		this.weight = weight;
		this.product = product;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public int compareTo(WeightProduct o) {
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
		return "Weight: "+ weight+" Name: "+product.getName();
	}
	
}
