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

public class TelaCadastroTagController {

	private Stage myStage;
	private Tag myTag;
	
    @FXML
    private Button btAdicionar;

    @FXML
    private Button btCancel;

    @FXML
    private Label lbNome;

    @FXML
    private TextField tfNome;

    @FXML
    void addTag(ActionEvent event) {
    	myTag = new Tag();
    	myTag.setName(tfNome.getText());
    	
    	try {
        	new TagService().addTag(myTag);
    	}catch (BusinessException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	catch (DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Tag adicionada com sucesso", ButtonType.OK);
    	alert.showAndWait();
    	myStage.close();
    }

    @FXML
    void cancelTag(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

}
