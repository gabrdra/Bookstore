package br.ufrn.imd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroLivroController {

	private Stage myStage;
    @FXML
    private Button btAdicionar;

    @FXML
    private Button btCancel;

    @FXML
    private Label lbAutor;

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
    private TextField tfAutor;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfPreco;

    @FXML
    private TextField tfTags;

    @FXML
    void addBook(ActionEvent event) {
    	
    }

    @FXML
    void cancelBook(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

}
