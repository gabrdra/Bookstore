package br.ufrn.imd.controller.recommendation;

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
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.business.recommendation.RecommendationBook;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class TelaRecomendacaoLivroController extends TelaRecomendacaoProdutoController implements Initializable {
	@FXML
	private TextField fxNumber;
	@FXML
	private TextField fxId;
	@FXML
	private Button btSearch;
	
    @FXML
    private TableColumn<ProductBook, String> tableBarcode;

    @FXML
    private TableColumn<ProductBook, String> tableDesc;

    @FXML
    private TableColumn<ProductBook, String> tableName;

    @FXML
    private TableView<ProductBook> tableRecomList;

    @FXML
    private TableColumn<ProductBook, Double> tableValue;
    
	List<ProductBook> listBooks = new ArrayList<ProductBook>();
	
	ObservableList<ProductBook> observableBookList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    tableBarcode.setCellValueFactory(new PropertyValueFactory<ProductBook, String>("barcode"));
	    tableName.setCellValueFactory(new PropertyValueFactory<ProductBook, String>("name"));
		tableValue.setCellValueFactory(new PropertyValueFactory<ProductBook, Double>("price"));
		tableDesc.setCellValueFactory(new PropertyValueFactory<ProductBook, String>("description"));
		
	}

	// Event Listener on Button[#btSearch].onAction
	@FXML
	public void onSearchButtonPressed(ActionEvent event) {
		RecommendationBook recommendation;
		try {
			recommendation = new RecommendationBook();
			listBooks = recommendation.retrieveRecommendationsForClient(Integer.parseInt(fxId.getText()), Integer.parseInt(fxNumber.getText()), null);
		} catch (NumberFormatException | DataException | BusinessException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
		
		
		observableBookList = FXCollections.observableArrayList();
		observableBookList.addAll(listBooks);
		
		tableRecomList.setItems(observableBookList);
		
	}

}
