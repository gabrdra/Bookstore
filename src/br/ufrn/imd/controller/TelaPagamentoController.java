package br.ufrn.imd.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import javafx.stage.Stage;

public abstract class TelaPagamentoController{
	
	

	protected Stage myStage;
    
	protected  boolean conclusion;
	

	public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}
	
    @FXML
    void cancel(ActionEvent event) {
    	conclusion = false;
		Alert alert = new Alert(AlertType.WARNING, "Pagamento Cancelado", ButtonType.OK);
    	alert.showAndWait();
    	myStage.close();
    }
    
   
	

	protected abstract boolean isConclusion();

	protected abstract void setValue(double value);



    
	
    

}
