package br.ufrn.imd.controller.list;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrn.imd.business.ProductService;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductVinyl;
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

public class TelaListagemVinisController extends TelaListagemProdutosController implements Initializable{

	private Stage myStage;
	
    @FXML
    private Button btCancel;

    @FXML
    private Button btList;

    @FXML
    private TableColumn<ProductVinyl, String> tableBand;

    @FXML
    private TableColumn<ProductVinyl, String> tableYear;
    
    @FXML
    private TableColumn<ProductVinyl, String> tableBarcode;

    @FXML
    private TableView<ProductVinyl> tableVinylsList;

    @FXML
    private TableColumn<ProductVinyl, String> tableDesc;

    @FXML
    private TableColumn<ProductVinyl, String> tableName;

    @FXML
    private TableColumn<ProductVinyl, Double> tableValue;
    
	ArrayList<ProductVinyl> vinylList = new ArrayList<ProductVinyl>();
	
	ObservableList<ProductVinyl> observableVinylList = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableBarcode.setCellValueFactory(new PropertyValueFactory<ProductVinyl, String>("barcode"));
		tableName.setCellValueFactory(new PropertyValueFactory<ProductVinyl, String>("name"));
		tableBand.setCellValueFactory(new PropertyValueFactory<ProductVinyl, String>("band"));
		tableYear.setCellValueFactory(new PropertyValueFactory<ProductVinyl, String>("year"));
		tableValue.setCellValueFactory(new PropertyValueFactory<ProductVinyl, Double>("price"));
		tableDesc.setCellValueFactory(new PropertyValueFactory<ProductVinyl, String>("description"));
		
	}

	@FXML
    void listVinyls(ActionEvent event) {
    	try {
    		//The following line should map the ArrayList<Product> to an ArrayList<ProductVinyl>. Fingers crossed.
    		vinylList = (ArrayList<ProductVinyl>) new ProductService().listProducts();
		} catch (DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	
    	observableVinylList= FXCollections.observableArrayList();
    	observableVinylList.addAll(vinylList);
    	
    	tableVinylsList.setItems(observableVinylList);

    }
    
    @FXML
    void cancel(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}





}
