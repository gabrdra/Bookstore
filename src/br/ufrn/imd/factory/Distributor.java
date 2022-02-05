package br.ufrn.imd.factory;

import br.ufrn.imd.data.ProductDAOJDBC;
import br.ufrn.imd.instanceController.InstanceController;

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
	      
	      break;
	      
	    case VINYL:
	      
	      break;
	    
	    default:
	      factory = null;
	    }
	}
	
	@Override
	public ProductDAOJDBC createProductDAO() {
		return factory.createProductDAO();
	}

	
	
}
