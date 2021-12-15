package br.ufrn.imd.controller;

import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.business.BookService;
import br.ufrn.imd.business.ClientService;
import br.ufrn.imd.business.IBookService;
import br.ufrn.imd.business.IClientService;
import br.ufrn.imd.business.TransactionService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Client;
import br.ufrn.imd.model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaPrincipalController implements Initializable{
	
	private Client client;
	private Book book;
	private double valorTotal;
	private Transaction transaction;
	
	ArrayList<Book> listBooks = new ArrayList<Book>();
	
	ObservableList<Book> observableBookList = FXCollections.observableArrayList();
	
	@FXML
    private Button btAddCleintToCart;
	
	@FXML
    private Button btRemCleintToCart;
	
	@FXML
    private TextField tfCpf;
	

    @FXML
    private TextField tfClientName;


    @FXML
    private Button btAddBookToCart;

    @FXML
    private Button btSearchBook;

    @FXML
    private Button btConfirmarVenda;

    @FXML
    private Button btCancelarVenda;
    
    @FXML
    private Label lbClientCpf;

    @FXML
    private GridPane lbCodPro;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbQnt;

    @FXML
    private Label lbVT;

    @FXML
    private Label lbValor;

    @FXML
    private Label lbTotalValue;

    @FXML
    private MenuItem mnAddBook;

    @FXML
    private MenuItem mnAddClient;

    @FXML
    private MenuItem mnAddTag;

    @FXML
    private MenuItem mnListBooks;

    @FXML
    private MenuItem mnListClients;
    
    @FXML
    private MenuItem mnListTags;
    
    @FXML
    private MenuItem mnListSales;
    
    @FXML
    private MenuItem mnItemSair;

    @FXML
    private MenuItem mnItemSobre;

    @FXML
    private TableColumn<Book, String> tableBarcode;

    @FXML
    private TableView<Book> tableCartList;

    @FXML
    private TableColumn<Book, String> tableDesc;

    @FXML
    private TableColumn<Book, String> tableName;

    @FXML
    private TableColumn<Book, Double> tableValue;
    @FXML
    private TextField tfBarCode;

    @FXML
    private TextField tfBookName;

    @FXML
    private TextField tfValue;
 
    @FXML
    private Label lbAutor;
    
    @FXML
    private TextField tfBookAuthor;
    
    
	@java.lang.Override
	public void initialize(java.net.URL arg0, java.util.ResourceBundle arg1) {
	    tableBarcode.setCellValueFactory(new PropertyValueFactory<Book, String>("barcode"));
	    tableName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
		tableValue.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
		tableDesc.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
	}

    @FXML
    void ConfirmarVenda(ActionEvent event) throws DataException, BusinessException {
    	transaction = new Transaction();
    	
    	transaction.setBooks(listBooks);
    	if(client != null) {
        	transaction.setClient(client.getId());
    	}

    	transaction.setValue(valorTotal);
    	try {
    		new TransactionService().addTransaction(transaction);
    		
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
    }
    
    @FXML
    void CancelarVenda(ActionEvent event) {
    	resetObservableList();
    	tableReset();
    	remCleintToCart(event);
    	valorTotal = 0;
    	lbTotalValue.setText("R$0,00");
    }

    @FXML
    void openAddBookScreen(ActionEvent event) throws IOException {
    	 try {
			FXMLLoader fxmlLoader = new FXMLLoader();
		    fxmlLoader.setLocation(TelaCadastroLivroController.class.getResource("/br/ufrn/imd/view/TelaCadastroLivro.fxml"));
		    AnchorPane page = (AnchorPane) fxmlLoader.load();
		    
		    Stage stageBook = new Stage();
		    stageBook.setTitle("Cadastrar Livro");
		    Scene scene = new Scene(page);
		    stageBook.setResizable(false);
		    stageBook.setScene(scene);
		    
	        TelaCadastroLivroController controller = fxmlLoader.getController();
	    	controller.setMyStage(stageBook);
	    	stageBook.showAndWait();
	    	System.out.println("TESTE");
	        
    	 }catch (IOException e) {
			e.printStackTrace();
		}
    	 
    }

    @FXML
    void openAddClientScreen(ActionEvent event) {
   	 try {
			FXMLLoader fxmlLoader = new FXMLLoader();
		    fxmlLoader.setLocation(TelaCadastroClienteController.class.getResource("/br/ufrn/imd/view/TelaCadastroCliente.fxml"));
		    AnchorPane page = (AnchorPane) fxmlLoader.load();
		    
		    Stage stageClient = new Stage();
		    stageClient.setTitle("Cadastrar Cliente");
		    Scene scene = new Scene(page);
		    stageClient.setResizable(false);
		    stageClient.setScene(scene);
		    
		    TelaCadastroClienteController controller = fxmlLoader.getController();
	    	controller.setMyStage(stageClient);
	    	stageClient.showAndWait();
	        
   	 }catch (IOException e) {
			e.printStackTrace();
		}
    }
    @FXML
    void openAddTagScreen(ActionEvent event) {
      	 try {
 			FXMLLoader fxmlLoader = new FXMLLoader();
 		    fxmlLoader.setLocation(TelaCadastroTagController.class.getResource("/br/ufrn/imd/view/TelaCadastroTag.fxml"));
 		    AnchorPane page = (AnchorPane) fxmlLoader.load();
 		    
 		    Stage stageClient = new Stage();
 		    stageClient.setTitle("Cadastrar Tag");
 		    Scene scene = new Scene(page);
 		    stageClient.setResizable(false);
 		    stageClient.setScene(scene);
 		    
 		   TelaCadastroTagController controller = fxmlLoader.getController();
 	    	controller.setMyStage(stageClient);
 	    	stageClient.showAndWait();
 	        
    	 }catch (IOException e) {
 			e.printStackTrace();
 		}
    }
    
    @FXML
    void fecharMainApp(ActionEvent event) {

    }

    @FXML
    void listarCadastroProdutos(ActionEvent event) {

    }

    @FXML
    void listarInfo(ActionEvent event) {

    }
    
    @FXML
    void addCleintToCart(ActionEvent event) throws DataException, BusinessException, IOException {
    	IClientService clientService = new ClientService();
    	
    	try {
    	client = clientService.retrieveClientByCpf(tfCpf.getText());
    	}
    	catch (BusinessException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	catch (DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	
    	tfClientName.setText(client.getName());
    }
    
    @FXML
    void remCleintToCart(ActionEvent event) {
    	client = new Client();
    	tfClientName.setText(null);
    	tfCpf.setText(null);
    	
    }

    @FXML
    void searchBook(ActionEvent event) throws IOException {
    	IBookService bookService = new BookService();
    	
    	
    	try {
    		book = bookService.retrieveBookByBarcode(tfBarCode.getText());
		}
    	catch (BusinessException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	catch (DataException e) {
        	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
    	
		
		tfValue.setText(Double.toString(book.getPrice()));
		tfBookName.setText(book.getName());
		tfBookAuthor.setText(book.getAuthor());
		
    }
    
    @FXML
    void addBookToCart(ActionEvent event) throws IOException {
    	searchBook(event);
    	if(book!=null) {
    		listBooks.add(book);
    		observableBookList.removeAll(listBooks);
    		observableBookList.addAll(listBooks);
    		tableCartList.setItems(observableBookList);
    	}
    	
    	book = null;
    	totalValueRefresh();
    	tableReset();
    }
    
    void totalValueRefresh() {
    	valorTotal=0;
    	for (Book book : listBooks) {
			valorTotal+=book.getPrice();
		}
    	lbTotalValue.setText("R$" + String.format("%.2f", valorTotal));
    }

    void tableReset() {

    	tfBarCode.setText(null);
		tfValue.setText(null);
		tfBookName.setText(null);
		tfBookAuthor.setText(null);
    }
    
    void resetObservableList() {
    	listBooks = new ArrayList<Book>();
    	observableBookList = FXCollections.observableArrayList();
		tableCartList.setItems(observableBookList);
    }
    
    

    
}
