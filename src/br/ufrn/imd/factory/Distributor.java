package br.ufrn.imd.factory;

import br.ufrn.imd.data.ProductDAOJDBC;
import br.ufrn.imd.data.connection.ConnectionStrings;
import br.ufrn.imd.instanceController.InstanceController;
import javafx.fxml.FXMLLoader;

public class Distributor implements AbstractFactory{
	
	private static Distributor instance;
	
	AbstractFactory factory;
	
	public static Distributor getInstance() {
		if(instance == null) instance = new Distributor();
		return instance;
	}
	
	private Distributor() {
		switch(InstanceController.currentInstanceType) {
	    case BOOK:
	      factory = new BookFactory();
	      break;
	    case GAME:
	      factory = new GameFactory();
	      break;
	      
	    case VINYL:
	      factory = new VinylFactory();
	      break;
	    
	    default:
	      factory = null;
	    }
	}
	
	@Override
	public ProductDAOJDBC createProductDAO() {
		return factory.createProductDAO();
	}

	@Override
	public ConnectionStrings createConnectionStrings() {
		return factory.createConnectionStrings();
	}

	@Override
	public FXMLLoader addProductFXMLLoader() {
		return factory.addProductFXMLLoader();
	}

	@Override
	public FXMLLoader removeProductFXMLLoader() {
		return factory.removeProductFXMLLoader();
	}

	@Override
	public FXMLLoader listProductFXMLLoader() {
		return factory.listProductFXMLLoader();
	}

	@Override
	public FXMLLoader updateProductFXMLLoader() {
		return factory.updateProductFXMLLoader();
	}

	@Override
	public FXMLLoader recomProductFXMLLoader() {
		return factory.recomProductFXMLLoader();
	}

	
	
}
