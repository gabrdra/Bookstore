package br.ufrn.imd.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class TelaPagamentoCartaoController extends TelaPagamentoController{

    @FXML
    private Button btCancel;

    @FXML
    private Button btFinish;

    @FXML
    private TextField etCvv;

    @FXML
    private TextField etDate;

    @FXML
    private TextField etName;

    @FXML
    private TextField etNumber;

    @FXML
    private Label lbCvv;

    @FXML
    private Label lbDate;

    @FXML
    private Label lbName;

    @FXML
    private Label lbNumber;

    @FXML
    private Text tValue;
    
    @FXML
    protected Text tValue2;

    
	private double value;
    
	public double getValue() {
		return value;
	}
	
	@Override
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
