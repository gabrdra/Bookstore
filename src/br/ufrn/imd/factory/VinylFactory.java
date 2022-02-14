package br.ufrn.imd.factory;

import br.ufrn.imd.controller.TelaPagamentoCartaoController;
import br.ufrn.imd.controller.TelaPagamentoDinheiroController;
import br.ufrn.imd.controller.add.TelaCadastroVinilController;
import br.ufrn.imd.controller.list.TelaListagemVinisController;
import br.ufrn.imd.controller.recommendation.TelaRecomendacaoVinilController;
import br.ufrn.imd.controller.remove.TelaRemoverVinilController;
import br.ufrn.imd.controller.update.TelaAtualizacaoVinilController;
import br.ufrn.imd.data.ProductDAOJDBC;
import br.ufrn.imd.data.ProductVinylDAOJDBC;
import br.ufrn.imd.data.connection.ConnectionStrings;
import javafx.fxml.FXMLLoader;

public class VinylFactory implements AbstractFactory{

	@Override
	public ProductDAOJDBC createProductDAO() {
		return new ProductVinylDAOJDBC();
	}

	@Override
	public ConnectionStrings createConnectionStrings() {
		return new ConnectionStrings("jdbc:postgresql://localhost:5432/Vinylstore", "postgres", "root");
	}

	@Override
	public FXMLLoader addProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaCadastroVinilController.class.getResource("/br/ufrn/imd/view/TelaCadastroVinil.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader removeProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaRemoverVinilController.class.getResource("/br/ufrn/imd/view/TelaRemoverVinil.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader listProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaListagemVinisController.class.getResource("/br/ufrn/imd/view/TelaListagemVinis.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader updateProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaAtualizacaoVinilController.class.getResource("/br/ufrn/imd/view/TelaAtualizacaoVinil.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader recomProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaRecomendacaoVinilController.class.getResource("/br/ufrn/imd/view/TelaRecomendacaoVinil.fxml"));
	    return fxmlLoader;
	}
	
	@Override
	public FXMLLoader paymentFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaPagamentoDinheiroController.class.getResource("/br/ufrn/imd/view/TelaPagamentoDinheiro.fxml"));
		return fxmlLoader;
	}

}
