package br.ufrn.imd.factory;

import br.ufrn.imd.data.ProductBookDAOJDBC;
import br.ufrn.imd.data.ProductDAOJDBC;

public class BookFactory implements AbstractFactory {

	@Override
	public ProductDAOJDBC createProductDAO() {
		return new ProductBookDAOJDBC();
	}

}
