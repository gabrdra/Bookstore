package br.ufrn.imd.model;

import br.ufrn.imd.exceptions.BusinessException;

public class ProductGame extends Product{
	
	private String publisher;
	private String platform;
	
	public ProductGame() {
		super();
		// TODO Auto-generated constructor sstub
	}
	

	public String getPublisher() {
		return publisher;
	}




	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}




	public String getPlatform() {
		return platform;
	}




	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Override
	public String toString() {
		return getName();//"id: "+id+" name: "+name + " barcode: "+barcode;//+" description: "+description+" tags: "+tags+" price: "+price+" author: "+author;
	}


	@Override
	public void validate() throws BusinessException {
		String exceptions ="";
		if(getName().length() >= 63) {
			exceptions += "Nome do Jogo muito longo \n";
		}
		else if(getName().length() <= 1) {
			exceptions += "Nome do Jogo muito curto \n";
		}
		if(getPublisher().length() >= 63) {
			exceptions += "Nome da editora muito longo \n";
		}
		else if(getPublisher().length() <= 1) {
			exceptions += "Nome da editora muito curto \n";
		}
		if(getPlatform().length() >= 63) {
			exceptions += "Nome da plataforma muito longo \n";
		}
		else if(getPlatform().length() <= 1) {
			exceptions += "Nome da plataforma muito curto \n";
		}
		if(getPrice() < 0) {
			exceptions += "Preço do Jogo não pode ser negativo \n";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		
	}

}
