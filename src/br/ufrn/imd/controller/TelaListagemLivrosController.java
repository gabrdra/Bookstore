package br.ufrn.imd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import br.ufrn.imd.business.ProductService;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductBook;
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
    private TableColumn<ProductBook, String> tableAuthor;

    @FXML
    private TableColumn<ProductBook, String> tableBarcode;

    @FXML
    private TableView<ProductBook> tableBooksList;

    @FXML
    private TableColumn<ProductBook, String> tableDesc;

    @FXML
    private TableColumn<ProductBook, String> tableName;

    @FXML
    private TableColumn<ProductBook, Double> tableValue;
    
	ArrayList<ProductBook> bookList = new ArrayList<ProductBook>();
	
	ObservableList<ProductBook> observablebookList = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableBarcode.setCellValueFactory(new PropertyValueFactory<ProductBook, String>("barcode"));
		tableName.setCellValueFactory(new PropertyValueFactory<ProductBook, String>("name"));
		tableAuthor.setCellValueFactory(new PropertyValueFactory<ProductBook, String>("author"));
		tableValue.setCellValueFactory(new PropertyValueFactory<ProductBook, Double>("price"));
		tableDesc.setCellValueFactory(new PropertyValueFactory<ProductBook, String>("description"));
		
	}

	@FXML
    void listBooks(ActionEvent event) {
    	try {
    		//The following line should map the ArrayList<Product> to an ArrayList<ProductBook>. Fingers crossed.
    		bookList = (ArrayList<ProductBook>) new ProductService().listProducts();
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
