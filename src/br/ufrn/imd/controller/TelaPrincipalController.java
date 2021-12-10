package br.ufrn.imd.controller;

import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.business.BookService;
import br.ufrn.imd.business.ClientService;
import br.ufrn.imd.business.IBookService;
import br.ufrn.imd.business.IClientService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class TelaPrincipalController implements Initializable{
	
	private Client client;
	private Book book;
	private double valorTotal;
	TelaAvisoController warning = new TelaAvisoController();
	
	ArrayList<Book> listBooks = new ArrayList<Book>();
	ObservableList<Book> observableBookList = FXCollections.observableArrayList();
	
	@FXML
    private Button btAddCleintToCart;
	
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
    private Label lbCbFuncionarios;

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
    private MenuItem mnItemCadProduto;

    @FXML
    private MenuItem mnItemCadProduto1;

    @FXML
    private MenuItem mnItemCadProduto2;

    @FXML
    private MenuItem mnItemListarProduto;

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
    void ConfirmarVenda(ActionEvent event) {

    }
    
    @FXML
    void CancelarVenda(ActionEvent event) {

    }

    @FXML
    void abrirTelaProduto(ActionEvent event) {

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
    		warning.openWarning(e.getMessage());
		}
    	catch (DataException e) {
    		warning.openWarning(e.getMessage());
		}
    	
    	tfClientName.setText(client.getName());
    }

    @FXML
    void searchBook(ActionEvent event) throws IOException {
    	IBookService bookService = new BookService();
    	
    	
    	try {
    		book = bookService.retrieveBookByBarcode(tfBarCode.getText());
		}
    	catch (BusinessException e) {
    		warning.openWarning(e.getMessage());
		}
    	catch (DataException e) {
    		warning.openWarning(e.getMessage());
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

    
}
