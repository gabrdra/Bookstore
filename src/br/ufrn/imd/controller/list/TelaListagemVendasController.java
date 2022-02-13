package br.ufrn.imd.controller.list;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrn.imd.business.TransactionService;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Transaction;
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

public class TelaListagemVendasController implements Initializable {
	
	private Stage myStage;

    @FXML
    private Button btCancel;

    @FXML
    private Button btList;

    @FXML
    private TableColumn<Transaction, ArrayList<Integer>> tableBooks;

    @FXML
    private TableColumn<Transaction, Integer> tableClient;

    @FXML
    private TableColumn<Transaction, Integer> tableId;

    @FXML
    private TableView<Transaction> tableSales;

    @FXML
    private TableColumn<Transaction, Double> tableValue;
    
	ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	
	ObservableList<Transaction> observableTransactionList = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableId.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("id"));
		tableClient.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("client"));
		tableValue.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("value"));
		tableBooks.setCellValueFactory(new PropertyValueFactory<Transaction, ArrayList<Integer>>("productsId"));
		
	}

    @FXML
    void cancel(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

    @FXML
    void listSales(ActionEvent event) {
    	try {
    		transactionList =  (ArrayList<Transaction>) new TransactionService().listTransactions();
		} catch (DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	
    	observableTransactionList= FXCollections.observableArrayList();
    	observableTransactionList.addAll(transactionList);
    	
    	tableSales.setItems(observableTransactionList);

    }



}
