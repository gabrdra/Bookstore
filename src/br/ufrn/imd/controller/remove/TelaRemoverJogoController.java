package br.ufrn.imd.controller.remove;

import br.ufrn.imd.business.ProductService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TelaRemoverJogoController extends TelaRemoverProdutoController{
	private Stage myStage;
	private ProductGame game;

    @FXML
    private Button btBuscar;

    @FXML
    private Button btCancel;

    @FXML
    private Button btRemover;

    @FXML
    private Label lbPublisher;

    @FXML
    private Label lbCodigo;

    @FXML
    private Label lbDescricao;

    @FXML
    private Label lbNome;

    @FXML
    private TextArea taDescricao;

    @FXML
    private TextField tfPublisher;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNome;

    @FXML
    void buscarJogo(ActionEvent event) {
    	try {
    		game = (ProductGame) new ProductService().retrieveProductByBarcode(tfCodigo.getText());
		} catch (BusinessException | DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	tfNome.setText(game.getName());
    	tfPublisher.setText(game.getPublisher());
    	taDescricao.setText(game.getDescription());
    }

    @FXML
    void cancel(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

    @FXML
    void remGame(ActionEvent event) {
    	try {
			new ProductService().removeProduct(game);
		} catch (BusinessException | DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Removido com sucesso!", ButtonType.OK);
    	alert.showAndWait();
    	myStage.close();
    }
}
