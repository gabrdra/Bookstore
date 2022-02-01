package br.ufrn.imd.controller;

import br.ufrn.imd.business.BookService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TelaRemoverLivroController {
	
	private Stage myStage;
	private ProductBook book;

    @FXML
    private Button btBuscar;

    @FXML
    private Button btCancel;

    @FXML
    private Button btRemover;

    @FXML
    private Label lbAutor;

    @FXML
    private Label lbCodigo;

    @FXML
    private Label lbDescricao;

    @FXML
    private Label lbNome;

    @FXML
    private TextArea taDescricao;

    @FXML
    private TextField tfAutor;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNome;

    @FXML
    void buscarLivro(ActionEvent event) {
    	try {
			book = new BookService().retrieveBookByBarcode(tfCodigo.getText());
		} catch (BusinessException | DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	tfNome.setText(book.getName());
    	tfAutor.setText(book.getAuthor());
    	taDescricao.setText(book.getDescription());
    }

    @FXML
    void cancel(ActionEvent event) {
    	myStage.close();
    }
    
    public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

    @FXML
    void remBook(ActionEvent event) {
    	try {
			new BookService().removeBook(book);
		} catch (BusinessException | DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Removido com sucesso!", ButtonType.OK);
    	alert.showAndWait();
    	myStage.close();
    }

}
