package br.ufrn.imd.controller.list;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrn.imd.business.ProductService;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductGame;
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

public class TelaListagemJogosController extends TelaListagemProdutosController implements Initializable{

	private Stage myStage;
	
    @FXML
    private Button btCancel;

    @FXML
    private Button btList;

    @FXML
    private TableColumn<ProductGame, String> tablePublisher;

    @FXML
    private TableColumn<ProductGame, String> tablePlatform;
    
    @FXML
    private TableColumn<ProductGame, String> tableBarcode;

    @FXML
    private TableView<ProductGame> tableGamesList;

    @FXML
    private TableColumn<ProductGame, String> tableDesc;

    @FXML
    private TableColumn<ProductGame, String> tableName;

    @FXML
    private TableColumn<ProductGame, Double> tableValue;
    
	ArrayList<ProductGame> gameList = new ArrayList<ProductGame>();
	
	ObservableList<ProductGame> observableGameList = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableBarcode.setCellValueFactory(new PropertyValueFactory<ProductGame, String>("barcode"));
		tableName.setCellValueFactory(new PropertyValueFactory<ProductGame, String>("name"));
		tablePublisher.setCellValueFactory(new PropertyValueFactory<ProductGame, String>("publisher"));
		tablePlatform.setCellValueFactory(new PropertyValueFactory<ProductGame, String>("platform"));
		tableValue.setCellValueFactory(new PropertyValueFactory<ProductGame, Double>("price"));
		tableDesc.setCellValueFactory(new PropertyValueFactory<ProductGame, String>("description"));
		
	}

	@FXML
    void listGames(ActionEvent event) {
    	try {
    		//The following line should map the ArrayList<Product> to an ArrayList<ProductGame>. Fingers crossed.
    		gameList = (ArrayList<ProductGame>) new ProductService().listProducts();
		} catch (DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	
    	observableGameList= FXCollections.observableArrayList();
    	observableGameList.addAll(gameList);
    	
    	tableGamesList.setItems(observableGameList);

    }
    
    @FXML
    void cancel(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}





}
