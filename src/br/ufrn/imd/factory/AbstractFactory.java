package br.ufrn.imd.factory;

import br.ufrn.imd.data.ProductDAOJDBC;
import br.ufrn.imd.data.connection.ConnectionStrings;
import javafx.fxml.FXMLLoader;

public interface AbstractFactory {

	ProductDAOJDBC createProductDAO();
	ConnectionStrings createConnectionStrings();
	//When created add the method for creating the base GUI.
	FXMLLoader addProductFXMLLoader();
	FXMLLoader removeProductFXMLLoader();
	FXMLLoader listProductFXMLLoader();
	FXMLLoader updateProductFXMLLoader();
	FXMLLoader recomProductFXMLLoader();
	
	//Recommendation won't need one because it's already based on the Window being called.
}
