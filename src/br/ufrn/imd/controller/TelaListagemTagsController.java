package br.ufrn.imd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrn.imd.business.TagService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Tag;
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

public class TelaListagemTagsController implements Initializable {

	private Stage myStage;
	
	
    @FXML
    private Button btCancel;

    @FXML
    private Button btList;

    @FXML
    private TableColumn<Tag, String> tableId;

    @FXML
    private TableColumn<Tag, String>tableName;

    @FXML
    private TableView<Tag> tableTagsList;
    
	ArrayList<Tag> tagList = new ArrayList<Tag>();
	
	ObservableList<Tag> observableTagList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableId.setCellValueFactory(new PropertyValueFactory<Tag, String>("id"));
		tableName.setCellValueFactory(new PropertyValueFactory<Tag, String>("name"));
		
	}
    
    @FXML
    void cancel(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

    @FXML
    void listTags(ActionEvent event) {
    	try {
        	tagList = (ArrayList<Tag>) new TagService().listTags();
		} catch (DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	
    	observableTagList = FXCollections.observableArrayList();
    	observableTagList.addAll(tagList);
    	
    	tableTagsList.setItems(observableTagList);

    }



}
