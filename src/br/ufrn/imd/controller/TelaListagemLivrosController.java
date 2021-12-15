package br.ufrn.imd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class TelaListagemLivrosController {

	private Stage myStage;
	
    @FXML
    private Button btCancel;

    @FXML
    private Button btList;

    @FXML
    private TableColumn<?, ?> tableAutor;

    @FXML
    private TableColumn<?, ?> tableBarcode;

    @FXML
    private TableView<?> tableCartList;

    @FXML
    private TableColumn<?, ?> tableDesc;

    @FXML
    private TableColumn<?, ?> tableName;

    @FXML
    private TableColumn<?, ?> tableValue;

    @FXML
    void listBooks(ActionEvent event) {

    }
    
    @FXML
    void cancel(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}



}
