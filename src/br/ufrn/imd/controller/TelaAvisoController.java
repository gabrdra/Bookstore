package br.ufrn.imd.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaAvisoController {
	
	private Stage confirmacaoStage;
	
    @FXML
    private Label lbConfirmText;

    @FXML
    private Button btConfirmOk;
    
    void openWarning(String texto) throws IOException {
    	
    	Alert alert = new Alert(AlertType.WARNING, "Delete ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    	alert.showAndWait();
    	
    	FXMLLoader loader2 = new FXMLLoader();
    	loader2.setLocation(TelaAvisoController.class.getResource("../view/TelaAviso.fxml"));
    	AnchorPane page = (AnchorPane) loader2.load();
    	
    	// Criando um novo Stage
    	Stage ConfirmacaoStage = new Stage();
    	ConfirmacaoStage.setTitle("Aviso");
    	ConfirmacaoStage.setResizable(false);
    	Scene scene = new Scene(page);
    	ConfirmacaoStage.setScene(scene);
    	
    	// Setando o Controle 
    	TelaAvisoController controller2 = loader2.getController();
    	controller2.setConfirmacaoStage(ConfirmacaoStage);
    	controller2.inserirTexto(texto);
    	ConfirmacaoStage.showAndWait();
    }
    
    public void inserirTexto(String texto) {
    	lbConfirmText.setText(texto);
    }
    
	@FXML
    void confirmarOk(ActionEvent event) {
		confirmacaoStage.close();
    }
    
	public void setConfirmacaoStage(Stage confirmacaoStage) {
		this.confirmacaoStage = confirmacaoStage;
	}

}
