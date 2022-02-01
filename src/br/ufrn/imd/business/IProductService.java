package br.ufrn.imd.business;

import java.util.List;

import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Product;

public interface IProductService<T extends Product> {

	public void addProduct(T product) throws BusinessException, DataException;
	public void removeProduct(T product) throws DataException, BusinessException;
	public void updateProduct(T product)throws DataException, BusinessException;
	public List<T> listProducts()throws DataException;
	public T retrieveProductById(int id)throws BusinessException, DataException;
	public T retrieveProductByBarcode(String barcode)throws BusinessException, DataException;
}
