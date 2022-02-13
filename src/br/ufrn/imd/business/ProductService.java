package br.ufrn.imd.business;

import java.util.List;

import br.ufrn.imd.data.ProductDAOJDBC;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.factory.Distributor;
import br.ufrn.imd.model.Product;
import br.ufrn.imd.model.Tag;
import br.ufrn.imd.model.Transaction;

public class ProductService implements IProductService {

	ProductDAOJDBC productDAO;
	
	public ProductService(){
		productDAO = Distributor.getInstance().createProductDAO();
	}
	
	@Override
	public void addProduct(Product product) throws BusinessException, DataException {
		if((retrieveProductByBarcode(product.getBarcode())).getBarcode() != null) {
			throw new BusinessException("C�digo de barras j� foi cadastrado em outro produto \n");
		}
		product.validate();
		productDAO.addProduct(product);
		
	}

	@Override
	public void removeProduct(Product product) throws DataException, BusinessException {
		String exceptions = "";
		
		List<Transaction> transactions = new TransactionService().listTransactions();
		for(Transaction transaction: transactions) {
			boolean found = false;
			for(Integer productOnTransaction: transaction.getProductsId()) {
				if(productOnTransaction == product.getId()) {
					exceptions += "O produto n�o pode ser removido pois existe dentro de ao menos uma transa��o \n";
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
	public void updateProduct(Product product) throws DataException, BusinessException {
		String exceptions = "";
		Product bookDb = retrieveProductByBarcode(product.getBarcode());
		if(product.getId() != bookDb.getId() && bookDb.getBarcode()!= null) {
			System.out.println(retrieveProductById(product.getId()).getId());
			if(retrieveProductById(product.getId()).getId()==0) {
				exceptions += "Livro inexistente \n";
			}
			else {
				exceptions += "C�digo de barras j� foi cadastrado em outro produto \n";
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
	public List<? extends Product> listProducts() throws DataException {
		return productDAO.listProducts();
	}

	@Override
	public Product retrieveProductById(int id) throws BusinessException, DataException {
		if(id < 1) {
			throw new BusinessException("id deve ser um n�mero maior do que 0 \n");
		}
		return productDAO.retrieveProductById(id);
	}

	@Override
	public Product retrieveProductByBarcode(String barcode) throws BusinessException, DataException {
		String exceptions = "";
		if(barcode == null) {
			throw new BusinessException("O c�digo de barras n�o pode ser nulo \n");
		}
		if(!barcode.matches("[0-9]+")) {
			exceptions += "O c�digo de barras deve conter somente n�meros \n";
		}
		if(barcode.length()!= 13) {
			exceptions += "O c�digo de barras deve conter exatamente 13 n�meros \n";
		}
		if(!exceptions.equals("")) {
			throw new BusinessException(exceptions);
		}		
		return productDAO.retrieveProductByBarcode(barcode);
	}

}
