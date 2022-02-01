package br.ufrn.imd.data;

import java.sql.Connection;
import java.util.List;

import br.ufrn.imd.data.connection.ConnectionJDBC;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Product;

public abstract class ProductDAOJDBC<T extends Product> {

	protected Connection connection;
	
	public ProductDAOJDBC(){
		this.connection = ConnectionJDBC.getInstance().getCon();
	}
	abstract public void addProduct(T product)throws DataException;
	abstract public void removeProduct(T product)throws DataException;
	abstract public void updateProduct(T product)throws DataException;
	abstract public List<T> listProducts()throws DataException;
	abstract public T retrieveProductById(int id)throws DataException;
	abstract public T retrieveProductByBarcode(String barcode)throws DataException;
}
