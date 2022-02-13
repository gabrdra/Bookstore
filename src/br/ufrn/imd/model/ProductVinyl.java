package br.ufrn.imd.model;

import br.ufrn.imd.exceptions.BusinessException;

public class ProductVinyl extends Product{

	public ProductVinyl(){
		super();
	}
	
	private String band;
	private Integer year;
	
	
	public String getBand() {
		return band;
	}




	public void setBand(String band) {
		this.band = band;
	}




	public Integer getYear() {
		return year;
	}




	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ProductVinyl) {
			return this.getId() == ((ProductVinyl)obj).getId();
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
		if(getBand().length() >= 63) {
			exceptions += "Nome da banda muito longo \n";
		}
		else if(getBand().length() <= 1) {
			exceptions += "Nome da banda muito curto \n";
		}
		if(getPrice() < 0) {
			exceptions += "Preço do vinil não pode ser negativo \n";
		}
		if(getYear() >= 2022 || getYear() <= 1940) {
			exceptions += "Ano invalido, precisa ser entre 1940-2022 \n";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		
	}

}
