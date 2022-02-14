package br.ufrn.imd.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;


public class TelaPagamentoDinheiroController extends TelaPagamentoController{

    @FXML
    private Button btCancel;

    @FXML
    private Button btFinish;

    @FXML
    private Text tValue;

    @FXML
    private Text tValueReceived;

    @FXML
    private Text tchange;

    @FXML
    private Text tchange2;
    
    @FXML 
    private TextField etValue;
    
    @FXML
    private Button btChange;
   
    @FXML
    public void changeChange() {
    	double change = Double.parseDouble(etValue.getText())-Double.parseDouble(tValue2.getText());
    	tchange2.setText(String.valueOf(change));
    }
    
    @FXML
    protected Text tValue2;
    
	private double value;
    
	public double getValue() {
		return value;
	}
	
	
	public void setValue(double value) {
		tValue2.setText(String.valueOf(value));
	}

    @FXML
    void finish(ActionEvent event) {
    	
    	
    	conclusion = true;
		Alert alert = new Alert(AlertType.CONFIRMATION, "Compra efetuada com sucesso!", ButtonType.OK);
    	alert.showAndWait();
    	myStage.close();
    }

    

	@Override
	protected boolean isConclusion() {
		return conclusion;
	}

}
