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

import br.ufrn.imd.business.recommendation.RecommendationGame;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class TelaRecomendacaoJogoController extends TelaRecomendacaoProdutoController implements Initializable {
	@FXML
	private TextField fxNumber;
	@FXML
	private TextField fxId;
	
	@FXML
	private Label lbPlatform;
	
	@FXML
	private TextField fxPlatform;
	@FXML
	private Button btSearch;

	
    @FXML
    private TableColumn<ProductGame, String> tableBarcode;

    @FXML
    private TableColumn<ProductGame, String> tableDesc;

    @FXML
    private TableColumn<ProductGame, String> tableName;
    
    @FXML
    private TableColumn<ProductGame, String> tablePlatform;

    @FXML
    private TableView<ProductGame> tableRecomList;

    @FXML
    private TableColumn<ProductGame, Double> tableValue;
    
	List<ProductGame> listGames = new ArrayList<ProductGame>();
	
	ObservableList<ProductGame> observableGameList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    tableBarcode.setCellValueFactory(new PropertyValueFactory<ProductGame, String>("barcode"));
	    tableName.setCellValueFactory(new PropertyValueFactory<ProductGame, String>("name"));
		tableValue.setCellValueFactory(new PropertyValueFactory<ProductGame, Double>("price"));
		tablePlatform.setCellValueFactory(new PropertyValueFactory<ProductGame, String>("platform"));
		tableDesc.setCellValueFactory(new PropertyValueFactory<ProductGame, String>("description"));
		
	}

	// Event Listener on Button[#btSearch].onAction
	@FXML
	public void onSearchButtonPressed(ActionEvent event) {
		RecommendationGame recommendation;
		try {
			recommendation = new RecommendationGame();
			listGames = recommendation.retrieveRecommendationsForClient(Integer.parseInt(fxId.getText()), Integer.parseInt(fxNumber.getText()), null);
		} catch (NumberFormatException | DataException | BusinessException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
		
		
		observableGameList = FXCollections.observableArrayList();
		observableGameList.addAll(listGames);
		
		tableRecomList.setItems(observableGameList);
		
	}

}
