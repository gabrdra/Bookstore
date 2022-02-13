package br.ufrn.imd.controller.add;

import java.util.ArrayList;

import br.ufrn.imd.business.ProductService;
import br.ufrn.imd.business.TagService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductGame;
import br.ufrn.imd.model.Tag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TelaCadastroJogoController extends TelaCadastroProdutoController implements Initializable{
	
	private ProductGame game;

	private Stage myStage;
    @FXML
    private Button btAdicionar;

    @FXML
    private Button btCancel;

    @FXML
    private Label lbPublisher;

    @FXML
    private Label lbCodigo;

    @FXML
    private Label lbDescricao;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbPreco;

    @FXML
    private Label lbTags;

    @FXML
    private TextArea taDescricao;

    @FXML
    private TextField tfPublisher;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfPreco;

    @FXML
    private TextField tfTags;
    
    @FXML
    private TextField tfPlatform;
    
    @FXML
    private Label lbPlatform;
    
    @FXML
    private TableColumn<Tag, Integer> tableId;

    @FXML
    private TableColumn<Tag, String> tableName;

    @FXML
    private TableView<Tag> tableTagList;

    @FXML
    private Button btAdd;
    
    @FXML
    private ComboBox<Tag> cbTags;
    
	ArrayList<Tag> listToAddTags = new ArrayList<Tag>();
	
	ObservableList<Tag> observableTagToAddList = FXCollections.observableArrayList();
	
	ArrayList<Tag> listTags = new ArrayList<Tag>();
	
	ObservableList<Tag> observableTagList = FXCollections.observableArrayList();
	
	
	
	@java.lang.Override
	public void initialize(java.net.URL arg0, java.util.ResourceBundle arg1) {
		tableId.setCellValueFactory(new PropertyValueFactory<Tag, Integer>("id"));
		tableName.setCellValueFactory(new PropertyValueFactory<Tag, String>("name"));
		
		try {
			listToAddTags= (ArrayList<Tag>) new TagService().listTags();
		}catch (DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
		observableTagToAddList.removeAll(listToAddTags);
		observableTagToAddList.addAll(listToAddTags);
		cbTags.setItems(observableTagToAddList);

	}

    @FXML
    void addGame(ActionEvent event) throws BusinessException, DataException {
    	game = new ProductGame();
    	game.setBarcode(tfCodigo.getText());
    	game.setName(tfNome.getText());
    	game.setPrice((tfPreco.getText() == "") ? 0 : Double.parseDouble(tfPreco.getText()));
    	game.setPublisher(tfPublisher.getText());
    	game.setPlatform(tfPlatform.getText());
    	game.setDescription(taDescricao.getText());
    	game.setTags(listTags);
    	try {
        	new ProductService().addProduct(game);
    	}catch (BusinessException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	catch (DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	catch(NumberFormatException e) {
    		Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
    	}
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Jogo adicionado com sucesso", ButtonType.OK);
    	alert.showAndWait();
    	myStage.close();
    }

    @FXML
    void addTagToGame(ActionEvent event) {
    	listTags.add(cbTags.getValue());
    	observableTagList.removeAll(listTags);
    	observableTagList.addAll(listTags);
		tableTagList.setItems(observableTagList);
    }
    
    @FXML
    void cancelGame(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

}
