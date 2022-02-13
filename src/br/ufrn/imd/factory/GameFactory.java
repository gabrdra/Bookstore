package br.ufrn.imd.factory;

import br.ufrn.imd.controller.add.TelaCadastroJogoController;
import br.ufrn.imd.controller.list.TelaListagemJogosController;
import br.ufrn.imd.controller.recommendation.TelaRecomendacaoJogoController;
import br.ufrn.imd.controller.remove.TelaRemoverJogoController;
import br.ufrn.imd.controller.update.TelaAtualizacaoJogoController;
import br.ufrn.imd.data.ProductDAOJDBC;
import br.ufrn.imd.data.ProductGameDAOJDBC;
import br.ufrn.imd.data.connection.ConnectionStrings;
import javafx.fxml.FXMLLoader;

public class GameFactory implements AbstractFactory{

	@Override
	public ProductDAOJDBC createProductDAO() {
		return new ProductGameDAOJDBC();
	}

	@Override
	public ConnectionStrings createConnectionStrings() {
		return new ConnectionStrings("jdbc:postgresql://localhost:5432/Gamestore", "postgres", "root");
	}

	@Override
	public FXMLLoader addProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaCadastroJogoController.class.getResource("/br/ufrn/imd/view/TelaCadastroJogo.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader removeProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaRemoverJogoController.class.getResource("/br/ufrn/imd/view/TelaRemoverJogo.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader listProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaListagemJogosController.class.getResource("/br/ufrn/imd/view/TelaListagemJogos.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader updateProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaAtualizacaoJogoController.class.getResource("/br/ufrn/imd/view/TelaAtualizacaoJogo.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader recomProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaRecomendacaoJogoController.class.getResource("/br/ufrn/imd/view/TelaRecomendacaoJogo.fxml"));
	    return fxmlLoader;
	}

}
