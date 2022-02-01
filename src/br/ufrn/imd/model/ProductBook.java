package br.ufrn.imd.model;

import br.ufrn.imd.exceptions.BusinessException;

//import java.util.HashSet;
public class ProductBook extends Product{

	public ProductBook(){
		super();
	}
	
	private String author;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ProductBook) {
			return this.getId() == ((ProductBook)obj).getId();
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return getName();//"id: "+id+" name: "+name + " barcode: "+barcode;//+" description: "+description+" tags: "+tags+" price: "+price+" author: "+author;
	}
	@Override
	public void validate() throws BusinessException {
		String exceptions ="";
		if(getName().length() >= 63) {
			exceptions += "Nome do livro muito longo \n";
		}
		else if(getName().length() <= 1) {
			exceptions += "Nome do livro muito curto \n";
		}
		if(getAuthor().length() >= 63) {
			exceptions += "Nome do(a) autor(a) muito longo \n";
		}
		else if(getAuthor().length() <= 1) {
			exceptions += "Nome do(a) autor(a) muito curto \n";
		}
		if(getPrice() < 0) {
			exceptions += "Preço do livro não pode ser negativo \n";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
	}
}
