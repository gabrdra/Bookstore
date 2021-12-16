package br.ufrn.imd.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrn.imd.business.recommendation.Recommendation;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class TelaRecomendacaoController implements Initializable {
	@FXML
	private TextField fxNumber;
	@FXML
	private TextField fxId;
	@FXML
	private Button btSearch;
	
    @FXML
    private TableColumn<Book, String> tableBarcode;

    @FXML
    private TableColumn<Book, String> tableDesc;

    @FXML
    private TableColumn<Book, String> tableName;

    @FXML
    private TableView<Book> tableRecomList;

    @FXML
    private TableColumn<Book, Double> tableValue;
    
	ArrayList<Book> listBooks = new ArrayList<Book>();
	
	ObservableList<Book> observableBookList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    tableBarcode.setCellValueFactory(new PropertyValueFactory<Book, String>("barcode"));
	    tableName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
		tableValue.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
		tableDesc.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
		
	}

	// Event Listener on Button[#btSearch].onAction
	@FXML
	public void onSearchButtonPressed(ActionEvent event) {
		Recommendation recommendation = new Recommendation();
		try {
			listBooks = recommendation.getRecommendationsForClient(Integer.parseInt(fxId.getText()), Integer.parseInt(fxNumber.getText()));
		} catch (NumberFormatException | DataException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
		
		
		observableBookList = FXCollections.observableArrayList();
		observableBookList.addAll(listBooks);
		
		tableRecomList.setItems(observableBookList);
		
	}

}
