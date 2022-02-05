package br.ufrn.imd.data;

import java.sql.Connection;
import java.util.List;

import br.ufrn.imd.data.connection.ConnectionJDBC;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Product;

public abstract class ProductDAOJDBC {

	protected Connection connection;
	
	public ProductDAOJDBC(){
		this.connection = ConnectionJDBC.getInstance().getCon();
	}
	abstract public void addProduct(Product product)throws DataException;
	abstract public void removeProduct(Product product)throws DataException;
	abstract public void updateProduct(Product product)throws DataException;
	abstract public List<? extends Product> listProducts()throws DataException;
	abstract public Product retrieveProductById(int id)throws DataException;
	abstract public Product retrieveProductByBarcode(String barcode)throws DataException;
}
