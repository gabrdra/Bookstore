package br.ufrn.imd.factory;

import br.ufrn.imd.controller.TelaPagamentoPixController;
import br.ufrn.imd.controller.add.TelaCadastroLivroController;
import br.ufrn.imd.controller.list.TelaListagemLivrosController;
import br.ufrn.imd.controller.recommendation.TelaRecomendacaoLivroController;
import br.ufrn.imd.controller.remove.TelaRemoverLivroController;
import br.ufrn.imd.controller.update.TelaAtualizacaoLivroController;
import br.ufrn.imd.data.ProductBookDAOJDBC;
import br.ufrn.imd.data.ProductDAOJDBC;
import br.ufrn.imd.data.connection.ConnectionStrings;
import javafx.fxml.FXMLLoader;

public class BookFactory implements AbstractFactory {

	@Override
	public ProductDAOJDBC createProductDAO() {
		return new ProductBookDAOJDBC();
	}

	@Override
	public ConnectionStrings createConnectionStrings() {
		return new ConnectionStrings("jdbc:postgresql://localhost:5432/Bookstore", "postgres", "root");
	}

	@Override
	public FXMLLoader addProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaCadastroLivroController.class.getResource("/br/ufrn/imd/view/TelaCadastroLivro.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader removeProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaRemoverLivroController.class.getResource("/br/ufrn/imd/view/TelaRemoverLivro.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader listProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaListagemLivrosController.class.getResource("/br/ufrn/imd/view/TelaListagemLivros.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader updateProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaAtualizacaoLivroController.class.getResource("/br/ufrn/imd/view/TelaAtualizacaoLivro.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader recomProductFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaRecomendacaoLivroController.class.getResource("/br/ufrn/imd/view/TelaRecomendacaoLivro.fxml"));
	    return fxmlLoader;
	}

	@Override
	public FXMLLoader paymentFXMLLoader() {
		FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(TelaPagamentoPixController.class.getResource("/br/ufrn/imd/view/TelaPagamentoPix.fxml"));
		return fxmlLoader;
	}

}
