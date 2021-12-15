package br.ufrn.imd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrn.imd.business.BookService;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Book;
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

public class TelaListagemLivrosController implements Initializable{

	private Stage myStage;
	
    @FXML
    private Button btCancel;

    @FXML
    private Button btList;

    @FXML
    private TableColumn<Book, String> tableAuthor;

    @FXML
    private TableColumn<Book, String> tableBarcode;

    @FXML
    private TableView<Book> tableBooksList;

    @FXML
    private TableColumn<Book, String> tableDesc;

    @FXML
    private TableColumn<Book, String> tableName;

    @FXML
    private TableColumn<Book, Double> tableValue;
    
	ArrayList<Book> bookList = new ArrayList<Book>();
	
	ObservableList<Book> observablebookList = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableBarcode.setCellValueFactory(new PropertyValueFactory<Book, String>("barcode"));
		tableName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
		tableAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		tableValue.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
		tableDesc.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
		
	}

    @FXML
    void listBooks(ActionEvent event) {
    	try {
    		bookList =  new BookService().listBooks();
		} catch (DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	
    	observablebookList= FXCollections.observableArrayList();
    	observablebookList.addAll(bookList);
    	
    	tableBooksList.setItems(observablebookList);

    }
    
    @FXML
    void cancel(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}





}
