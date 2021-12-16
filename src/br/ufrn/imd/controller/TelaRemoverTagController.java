package br.ufrn.imd.controller;


import br.ufrn.imd.business.TagService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Tag;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TelaRemoverTagController {

	
	private Stage myStage;
	private Tag tag;
	
    @FXML
    private Button btBuscar;

    @FXML
    private Button btCancel;

    @FXML
    private Button btRemover;

    @FXML
    private Label lbId;

    @FXML
    private Label lbNome;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNome;

    @FXML
    void buscarTag(ActionEvent event) {
    	try {
			tag = new TagService().retrieveTagById(Integer.parseInt(tfId.getText()));
		} catch (BusinessException | DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	tfNome.setText(tag.getName());
    }

    @FXML
    void cancel(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}


    @FXML
    void remTag(ActionEvent event) {
    	try {
			new TagService().removeTag(tag);
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
