package br.ufrn.imd.data;

import java.sql.Connection;
import java.util.List;

import br.ufrn.imd.data.connection.ConnectionJDBC;

public abstract class ProductDAOJDBC<T> {

	protected Connection connection;
	
	public ProductDAOJDBC(){
		this.connection = ConnectionJDBC.getInstance().getCon();
	}
	abstract void addProduct(T product);
	abstract void removeProduct(T product);
	abstract void updateProduct(T product);
	abstract List<T> listProducts();
	abstract T retrieveProductsById(int id);
	abstract T retrieveProductsByBarcode(String barcode);
}
