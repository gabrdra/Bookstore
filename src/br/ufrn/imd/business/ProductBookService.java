package br.ufrn.imd.business;

import java.util.List;

import br.ufrn.imd.data.ProductBookDAOJDBC;
import br.ufrn.imd.data.ProductDAOJDBC;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Product;
import br.ufrn.imd.model.ProductBook;
import br.ufrn.imd.model.Tag;
import br.ufrn.imd.model.Transaction;

public class ProductBookService implements IProductService<ProductBook> {

	ProductDAOJDBC<ProductBook> productDAO;
	
	public ProductBookService(){
		productDAO = new ProductBookDAOJDBC();
	}
	
	@Override
	public void addProduct(ProductBook product) throws BusinessException, DataException {
		if((retrieveProductByBarcode(product.getBarcode())).getBarcode() != null) {
			throw new BusinessException("Código de barras já foi cadastrado em outro produto \n");
		}
		product.validate();
		productDAO.addProduct(product);
		
	}

	@Override
	public void removeProduct(ProductBook product) throws DataException, BusinessException {
		String exceptions = "";
		List<Transaction> transactions = new TransactionService().listTransactions();
		for(Transaction transaction: transactions) {
			boolean found = false;
			for(ProductBook bookOnTransaction: transaction.getBooks()) {
				if(bookOnTransaction.getId() == product.getId()) {
					exceptions += "O produto não pode ser removido pois existe dentro de ao menos uma transação \n";
					found = true;
					break;
				}
			}
			if(found) {
				break;
			}
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		productDAO.removeProduct(product);
		
	}

	@Override
	public void updateProduct(ProductBook product) throws DataException, BusinessException {
		String exceptions = "";
		ProductBook bookDb = retrieveProductByBarcode(product.getBarcode());
		if(product.getId() != bookDb.getId() && bookDb.getBarcode()!= null) {
			System.out.println(retrieveProductById(product.getId()).getId());
			if(retrieveProductById(product.getId()).getId()==0) {
				exceptions += "Livro inexistente \n";
			}
			else {
				exceptions += "Código de barras já foi cadastrado em outro livro \n";
			}
		}
		product.validate();
		ITagService tagService = new TagService();
		for(Tag tag: product.getTags()) {
			if(tagService.retrieveTagById(tag.getId()).getName() == null) {
				exceptions += "Tag inexistente \n";
			}
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}
		productDAO.updateProduct(product);
		
	}

	@Override
	public List<ProductBook> listProducts() throws DataException {
		return productDAO.listProducts();
	}

	@Override
	public ProductBook retrieveProductById(int id) throws BusinessException, DataException {
		if(id < 1) {
			throw new BusinessException("id deve ser um número maior do que 0 \n");
		}
		return productDAO.retrieveProductById(id);
	}

	@Override
	public ProductBook retrieveProductByBarcode(String barcode) throws BusinessException, DataException {
		String exceptions = "";
		if(barcode == null) {
			throw new BusinessException("O código de barras não pode ser nulo \n");
		}
		if(!barcode.matches("[0-9]+")) {
			exceptions += "O código de barras deve conter somente números \n";
		}
		if(barcode.length()!= 13) {
			exceptions += "O código de barras deve conter exatamente 13 números \n";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}		
		return productDAO.retrieveProductByBarcode(barcode);
	}

}
