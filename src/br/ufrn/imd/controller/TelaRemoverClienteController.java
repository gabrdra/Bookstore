package br.ufrn.imd.controller;

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

public class TelaRemoverClienteController {
	
	private Client client;
	
	private Stage myStage;

    @FXML
    private Button btBuscar;

    @FXML
    private Button btCancel;

    @FXML
    private Button btRemover;

    @FXML
    private Label lbCpf;

    @FXML
    private Label lbNome;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfNome;

    @FXML
    void buscarCliente(ActionEvent event) {
    	try {
			client = new ClientService().retrieveClientByCpf(tfCpf.getText());
		} catch (BusinessException | DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	tfNome.setText(client.getName());
    }

    @FXML
    void cancel(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

    @FXML
    void remClient(ActionEvent event) {
    	try {
			new ClientService().removeClient(client);
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
