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

	@Override
	public String toString() {
		return "id: "+id+" name: "+name+" description: "+description+" tags: "+tags+" price: "+price+" author: "+author;
	}
	
}
