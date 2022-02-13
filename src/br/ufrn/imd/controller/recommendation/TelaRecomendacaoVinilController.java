package br.ufrn.imd.controller.recommendation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.business.recommendation.RecommendationVinyl;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductVinyl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class TelaRecomendacaoVinilController extends TelaRecomendacaoProdutoController implements Initializable {
	@FXML
	private TextField fxNumber;
	@FXML
	private TextField fxId;
	
	@FXML
	private Label lbYears;
	
	@FXML
	private Label lbPlatform1;
	
	@FXML
	private TextField fxBegin;
	
	@FXML
	private TextField fxEnd;
	
	@FXML
	private Button btSearch;

	
    @FXML
    private TableColumn<ProductVinyl, String> tableBarcode;

    @FXML
    private TableColumn<ProductVinyl, String> tableDesc;

    @FXML
    private TableColumn<ProductVinyl, String> tableName;
    
    @FXML
    private TableColumn<ProductVinyl, String> tableYear;

    @FXML
    private TableView<ProductVinyl> tableRecomList;

    @FXML
    private TableColumn<ProductVinyl, Double> tableValue;
    
	List<ProductVinyl> listVinyls = new ArrayList<ProductVinyl>();
	
	ObservableList<ProductVinyl> observableVinylList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    tableBarcode.setCellValueFactory(new PropertyValueFactory<ProductVinyl, String>("barcode"));
	    tableName.setCellValueFactory(new PropertyValueFactory<ProductVinyl, String>("name"));
		tableValue.setCellValueFactory(new PropertyValueFactory<ProductVinyl, Double>("price"));
		tableYear.setCellValueFactory(new PropertyValueFactory<ProductVinyl, String>("year"));
		tableDesc.setCellValueFactory(new PropertyValueFactory<ProductVinyl, String>("description"));
		
	}

	// Event Listener on Button[#btSearch].onAction
	@FXML
	public void onSearchButtonPressed(ActionEvent event) {
		RecommendationVinyl recommendation;
		try {
			recommendation = new RecommendationVinyl();
			listVinyls = recommendation.retrieveRecommendationsForClient(Integer.parseInt(fxId.getText()), Integer.parseInt(fxNumber.getText()), null);
		} catch (NumberFormatException | DataException | BusinessException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
		
		
		observableVinylList = FXCollections.observableArrayList();
		observableVinylList.addAll(listVinyls);
		
		tableRecomList.setItems(observableVinylList);
		
	}

}
