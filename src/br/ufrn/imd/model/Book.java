package br.ufrn.imd.model;

//import java.util.HashSet;
import java.util.ArrayList;

public class Book {

	private int id;
	private String name;
	private String description;
	private ArrayList<Tag> tags;
	private double price;
	private String author;
	private String barcode;
	

	public Book() {
        super();
        this.tags = new ArrayList<Tag>();
	}
	
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
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public ArrayList<Integer> getTagsId() {
		ArrayList<Integer> tempArray = new ArrayList<Integer>();
		for (Tag tag : tags) {
			tempArray.add(tag.getId());
		}
		return tempArray;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Book) {
			return this.id == ((Book)obj).id;
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return name;//"id: "+id+" name: "+name + " barcode: "+barcode;//+" description: "+description+" tags: "+tags+" price: "+price+" author: "+author;
	}
}
