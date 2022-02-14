package br.ufrn.imd.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class TelaPagamentoPixController extends TelaPagamentoController{

    @FXML
    private Button btCancel;

    @FXML
    private Button btFinish;

    @FXML
    private ImageView ivCode;

    @FXML
    private Text tPix;

    @FXML
    private Text tValue;
    
    @FXML
    private Text tValue2;
  

	

    @FXML
    void finish(ActionEvent event) {
    	
    	conclusion = true;
		Alert alert = new Alert(AlertType.CONFIRMATION, "Compra efetuada com sucesso!", ButtonType.OK);
    	alert.showAndWait();
    	myStage.close();
    }
    
	@Override
	public void setValue(double value) {
		tValue2.setText(String.valueOf(value));
	}

    
    
	@Override
	protected boolean isConclusion() {
		return conclusion;
	}





}
