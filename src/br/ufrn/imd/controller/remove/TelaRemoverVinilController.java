package br.ufrn.imd.controller.remove;

import br.ufrn.imd.business.ProductService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductVinyl;
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

public class TelaRemoverVinilController extends TelaRemoverProdutoController{
	private Stage myStage;
	private ProductVinyl vinyl;

    @FXML
    private Button btBuscar;

    @FXML
    private Button btCancel;

    @FXML
    private Button btRemover;

    @FXML
    private Label lbBand;

    @FXML
    private Label lbCodigo;

    @FXML
    private Label lbDescricao;

    @FXML
    private Label lbNome;

    @FXML
    private TextArea taDescricao;

    @FXML
    private TextField tfBand;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNome;

    @FXML
    void buscarVinil(ActionEvent event) {
    	try {
    		vinyl = (ProductVinyl) new ProductService().retrieveProductByBarcode(tfCodigo.getText());
		} catch (BusinessException | DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	tfNome.setText(vinyl.getName());
    	tfBand.setText(vinyl.getBand());
    	taDescricao.setText(vinyl.getDescription());
    }

    @FXML
    void cancel(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

    @FXML
    void remVinyl(ActionEvent event) {
    	try {
			new ProductService().removeProduct(vinyl);
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
