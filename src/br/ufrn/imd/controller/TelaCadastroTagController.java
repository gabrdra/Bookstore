package br.ufrn.imd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroTagController {

	private Stage myStage;
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

    }

    @FXML
    void cancelTag(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

}
