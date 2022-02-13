package br.ufrn.imd.controller.add;

import br.ufrn.imd.business.ClientService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TelaCadastroClienteController {
	
	private Stage myStage;
	private Client myClient;
	
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
    	myClient = new Client();
    	myClient.setCpf(tfCpf.getText());
    	myClient.setName(tfNome.getText());
    	
    	try {
			new ClientService().addClient(myClient);
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
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Cliente adicionado com sucesso", ButtonType.OK);
    	alert.showAndWait();
    	myStage.close();
    }

    @FXML
    void cancelClient(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

}
