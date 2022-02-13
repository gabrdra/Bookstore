package br.ufrn.imd.model;

import java.util.ArrayList;

import br.ufrn.imd.exceptions.BusinessException;

public abstract class Product {
	private int id;
	private String name;
	private String description;
	private ArrayList<Tag> tags;
	private double price;
	private String barcode;
	
	public Product() {
		this.tags = new ArrayList<Tag>();
	}
	
	public abstract void validate() throws BusinessException;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}

	public double getPrice() {
		return price;
	}

	public ArrayList<Integer> getTagsId() {
		ArrayList<Integer> tempArray = new ArrayList<Integer>();
		for (Tag tag : tags) {
			tempArray.add(tag.getId());
		}
		return tempArray;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Product) {
			return this.getId() == ((Product)obj).getId();
		}
		return super.equals(obj);
	}
	
}
