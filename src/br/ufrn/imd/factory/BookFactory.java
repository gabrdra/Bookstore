package br.ufrn.imd.factory;

import br.ufrn.imd.data.ProductBookDAOJDBC;
import br.ufrn.imd.data.ProductDAOJDBC;
import br.ufrn.imd.data.connection.ConnectionStrings;

public class BookFactory implements AbstractFactory {

	@Override
	public ProductDAOJDBC createProductDAO() {
		return new ProductBookDAOJDBC();
	}

	@Override
	public ConnectionStrings createConnectionStrings() {
		return new ConnectionStrings("jdbc:postgresql://localhost:5432/Bookstore", "postgres", "root");
	}

}
