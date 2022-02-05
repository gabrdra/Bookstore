package br.ufrn.imd.business;

import java.util.List;

import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Product;

public interface IProductService {

	public void addProduct(Product product) throws BusinessException, DataException;
	public void removeProduct(Product product) throws DataException, BusinessException;
	public void updateProduct(Product product)throws DataException, BusinessException;
	public List<? extends Product> listProducts()throws DataException;
	public Product retrieveProductById(int id)throws BusinessException, DataException;
	public Product retrieveProductByBarcode(String barcode)throws BusinessException, DataException;
}
