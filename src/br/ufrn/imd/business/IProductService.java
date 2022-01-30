package br.ufrn.imd.business;

import java.util.List;

public interface IProductService<T> {

	void addProduct(T product);
	void removeProduct(T product);
	void updateProduct(T product);
	List<T> listProducts();
	T retrieveProductsById(int id);
	T retrieveProductsByBarcode(String barcode);
}
