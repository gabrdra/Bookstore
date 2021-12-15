package br.ufrn.imd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroClienteController {
	
	private Stage myStage;
	
    @FXML
    private Button btAdicionar;

    @FXML
    private Button btCancel;

    @FXML
    private Label lbCpf;

    @FXML
    private Label lbNome;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfNome;

    @FXML
    void addClient(ActionEvent event) {

    }

    @FXML
    void cancelClient(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

}
