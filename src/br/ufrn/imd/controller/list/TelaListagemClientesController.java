package br.ufrn.imd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrn.imd.business.ClientService;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TelaListagemClientesController implements Initializable{

	
	private Stage myStage;
	
    @FXML
    private Button btCancel;

    @FXML
    private Button btList;

    @FXML
    private TableView<Client> tableClientsList;

    @FXML
    private TableColumn<Client, String> tableCpf;

    @FXML
    private TableColumn<Client, String> tableName;
    
	ArrayList<Client> clientList = new ArrayList<Client>();
	
	ObservableList<Client> observableclientList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableCpf.setCellValueFactory(new PropertyValueFactory<Client, String>("cpf"));
		tableName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
		
	}

    @FXML
    void cancel(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}
    @FXML
    void listClients(ActionEvent event) throws DataException {
    	try {
        	clientList = (ArrayList<Client>) new ClientService().listClients();
		} catch (DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	
    	observableclientList = FXCollections.observableArrayList();
    	observableclientList.addAll(clientList);
    	tableClientsList.setItems(observableclientList);
    	
    }



}
