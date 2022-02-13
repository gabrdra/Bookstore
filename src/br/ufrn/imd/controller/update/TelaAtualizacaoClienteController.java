package br.ufrn.imd.controller.update;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import br.ufrn.imd.business.ClientService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.exceptions.GUIException;
import br.ufrn.imd.model.Client;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class TelaAtualizacaoClienteController {
	@FXML
	private Label lbId;
	@FXML
	private TextField tfName;
	@FXML
	private Label lbName;
	@FXML
	private TextField tfId;
	@FXML
	private Button btSearchId;
	@FXML
	private Button btSearchCpf;
	@FXML
	private Label lbCpf;
	@FXML
	private TextField tfCpf;
	@FXML
	private Button btUpdate;

	// Event Listener on Button[#btSearchId].onAction
	@FXML
	public void onSearchId(ActionEvent event) {
		try {
			//System.out.println(tfName.getText());
			populateInterface(new ClientService().retrieveClientById(Integer.parseInt(tfId.getText())));
		} catch (NumberFormatException | DataException | BusinessException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	// Event Listener on Button[#btSearchCpf].onAction
	@FXML
	public void onSearchCpf(ActionEvent event) {
		try {
			System.out.println(tfName.getText());
			populateInterface(new ClientService().retrieveClientByCpf(tfCpf.getText()));
		} catch (DataException | BusinessException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	private void populateInterface(Client client) {
		try {
		if(client.getId() == 0) {
			throw new GUIException("Cliente inexistente \n");
		}
		tfId.setText(String.valueOf(client.getId()));
		tfName.setText(client.getName());
		tfCpf.setText(client.getCpf());}
		catch(GUIException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	// Event Listener on Button[#btUpdate].onAction
	@FXML
	public void updateClient(ActionEvent event) {
		try {
		Client client = new Client();
		client.setId(Integer.parseInt(tfId.getText()));
		client.setName(tfName.getText());
		client.setCpf(tfCpf.getText());
		new ClientService().updateClient(client);
		}catch (NumberFormatException | DataException | BusinessException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
		
	}
}
