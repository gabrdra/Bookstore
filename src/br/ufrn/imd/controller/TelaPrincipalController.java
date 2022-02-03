package br.ufrn.imd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import br.ufrn.imd.business.ClientService;
import br.ufrn.imd.business.IClientService;
import br.ufrn.imd.business.ProductService;
import br.ufrn.imd.business.TransactionService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.model.ProductBook;
import br.ufrn.imd.model.Client;
import br.ufrn.imd.model.Product;
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
	private ProductBook book;
	private double valorTotal;
	private Transaction transaction;
	
	ArrayList<ProductBook> listBooks = new ArrayList<ProductBook>();
	
	ObservableList<ProductBook> observableBookList = FXCollections.observableArrayList();
	
	@FXML
    private Button btAddCleintToCart;
	
	@FXML
    private Button btRemCleintToCart;
	
	@FXML
    private TextField tfCpf;
	
	

    @FXML
    private MenuItem mnRecommendation;

    @FXML
    private MenuItem mnRemBook;

    @FXML
    private MenuItem mnRemClient;

    @FXML
    private MenuItem mnRemTag;

    @FXML
    private MenuItem mnUpdateBook;

    @FXML
    private MenuItem mnUpdateClient;

    @FXML
    private MenuItem mnUpdateTag;
    
    @FXML
    private MenuItem mnUpdateSales;

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
    private TableColumn<ProductBook, String> tableBarcode;

    @FXML
    private TableView<ProductBook> tableCartList;

    @FXML
    private TableColumn<ProductBook, String> tableDesc;

    @FXML
    private TableColumn<ProductBook, String> tableName;

    @FXML
    private TableColumn<ProductBook, Double> tableValue;
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
	    tableBarcode.setCellValueFactory(new PropertyValueFactory<ProductBook, String>("barcode"));
	    tableName.setCellValueFactory(new PropertyValueFactory<ProductBook, String>("name"));
		tableValue.setCellValueFactory(new PropertyValueFactory<ProductBook, Double>("price"));
		tableDesc.setCellValueFactory(new PropertyValueFactory<ProductBook, String>("description"));
	}

    @FXML
    void ConfirmarVenda(ActionEvent event) throws DataException, BusinessException {
    	transaction = new Transaction();
    	ArrayList<Integer> productsId = new ArrayList<Integer>();
    	for(Product product:listBooks) {
    		productsId.add(product.getId());
    	}
    	
    	transaction.setProductsId(productsId);
    	if(client != null) {
        	transaction.setClient(client.getId());
    	}
    	
    	transaction.setValue(valorTotal);
    	
    	Alert confirmationAlert = new Alert(AlertType.CONFIRMATION, "Valor: "+ transaction.getValue()+"\n Chave pix: 1234567891012", ButtonType.YES, ButtonType.CANCEL);
    	Optional<ButtonType> result = confirmationAlert.showAndWait();
    	if(result.isPresent() && result.get() == ButtonType.CANCEL) {
    		CancelarVenda(null);
    		return;
    	}
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
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Venda realizada com sucesso!", ButtonType.OK);
    	alert.showAndWait();
    	resetObservableList();
    	tableReset();
    	remCleintToCart(event);
    	valorTotal = 0;
    	lbTotalValue.setText("R$0,00");
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
    void openListClients(ActionEvent event) {
    	 try {
			FXMLLoader fxmlLoader = new FXMLLoader();
		    fxmlLoader.setLocation(TelaListagemClientesController.class.getResource("/br/ufrn/imd/view/TelaListagemClientes.fxml"));
		    AnchorPane page = (AnchorPane) fxmlLoader.load();
		    
		    Stage stageClient = new Stage();
		    stageClient.setTitle("Lista Clientes");
		    Scene scene = new Scene(page);
		    stageClient.setResizable(false);
		    stageClient.setScene(scene);
		    
		    TelaListagemClientesController controller = fxmlLoader.getController();
	    	controller.setMyStage(stageClient);
	    	stageClient.showAndWait();
	        
  	 }catch (IOException e) {
			e.printStackTrace();
		}
   }
    
    @FXML
    void openListTags(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = new FXMLLoader();
			    fxmlLoader.setLocation(TelaListagemTagsController.class.getResource("/br/ufrn/imd/view/TelaListagemTags.fxml"));
			    AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stageTag = new Stage();
			    stageTag.setTitle("Lista Clientes");
			    Scene scene = new Scene(page);
			    stageTag.setResizable(false);
			    stageTag.setScene(scene);
			    
			    TelaListagemTagsController controller = fxmlLoader.getController();
		    	controller.setMyStage(stageTag);
		    	stageTag.showAndWait();
		        
	 	 }catch (IOException e) {
				e.printStackTrace();
			}
	  }
    
    @FXML
    void openListSales(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = new FXMLLoader();
			    fxmlLoader.setLocation(TelaListagemVendasController.class.getResource("/br/ufrn/imd/view/TelaListagemVendas.fxml"));
			    AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stage = new Stage();
			    stage.setTitle("Lista Vendas");
			    Scene scene = new Scene(page);
			    stage.setResizable(false);
			    stage.setScene(scene);
			    
			    TelaListagemVendasController controller = fxmlLoader.getController();
		    	controller.setMyStage(stage);
		    	stage.showAndWait();
		        
	 	 }catch (IOException e) {
				e.printStackTrace();
			}
	  }
    
    @FXML
    void fecharMainApp(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void openListBooks(ActionEvent event) {
     	 try {
			FXMLLoader fxmlLoader = new FXMLLoader();
		    fxmlLoader.setLocation(TelaListagemLivrosController.class.getResource("/br/ufrn/imd/view/TelaListagemLivros.fxml"));
		    AnchorPane page = (AnchorPane) fxmlLoader.load();
		    
		    Stage stageBook = new Stage();
		    stageBook.setTitle("Lista Livros");
		    Scene scene = new Scene(page);
		    stageBook.setResizable(false);
		    stageBook.setScene(scene);
		    
		    TelaListagemLivrosController controller = fxmlLoader.getController();
	    	controller.setMyStage(stageBook);
	    	stageBook.showAndWait();
	        
   	 }catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void listarInfo(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION, "Autores: Eduardo e João", ButtonType.OK);
    	alert.showAndWait();
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
    	ProductService bookService = new ProductService();
    	
    	
    	try {
    		book = (ProductBook) bookService.retrieveProductByBarcode(tfBarCode.getText());
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
    	for (ProductBook book : listBooks) {
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
    	listBooks = new ArrayList<ProductBook>();
    	observableBookList = FXCollections.observableArrayList();
		tableCartList.setItems(observableBookList);
    }
    
    @FXML
    void openRecomScreen(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = new FXMLLoader();
			    fxmlLoader.setLocation(TelaRecomendacaoController.class.getResource("/br/ufrn/imd/view/TelaRecomendacao.fxml"));
			    AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stage = new Stage();
			    stage.setTitle("Gerar Recomedanção");
			    Scene scene = new Scene(page);
			    stage.setResizable(false);
			    stage.setScene(scene);
			    
//			    TelaRecomendacaoController controller = fxmlLoader.getController();
//		    	controller.setMyStage(stage);
		    	stage.showAndWait();
		        
	 	 }catch (IOException e) {
				e.printStackTrace();
			}
	  }

    @FXML
    void openRemBookScreen(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = new FXMLLoader();
			    fxmlLoader.setLocation(TelaRemoverLivroController.class.getResource("/br/ufrn/imd/view/TelaRemoverLivro.fxml"));
			    AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stage = new Stage();
			    stage.setTitle("Remover Livro");
			    Scene scene = new Scene(page);
			    stage.setResizable(false);
			    stage.setScene(scene);
			    
			    TelaRemoverLivroController controller = fxmlLoader.getController();
		    	controller.setMyStage(stage);
		    	stage.showAndWait();
		        
	 	 }catch (IOException e) {
				e.printStackTrace();
			}
	  }

    @FXML
    void openRemClientScreen(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = new FXMLLoader();
			    fxmlLoader.setLocation(TelaRemoverClienteController.class.getResource("/br/ufrn/imd/view/TelaRemoverCliente.fxml"));
			    AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stage = new Stage();
			    stage.setTitle("Remover Cliente");
			    Scene scene = new Scene(page);
			    stage.setResizable(false);
			    stage.setScene(scene);
			    
			    TelaRemoverClienteController controller = fxmlLoader.getController();
		    	controller.setMyStage(stage);
		    	stage.showAndWait();
		        
	 	 }catch (IOException e) {
				e.printStackTrace();
			}
	  }

    @FXML
    void openRemTagScreen(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = new FXMLLoader();
			    fxmlLoader.setLocation(TelaRemoverTagController.class.getResource("/br/ufrn/imd/view/TelaRemoverTag.fxml"));
			    AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stage = new Stage();
			    stage.setTitle("Remover Tag");
			    Scene scene = new Scene(page);
			    stage.setResizable(false);
			    stage.setScene(scene);
			    
			    TelaRemoverTagController controller = fxmlLoader.getController();
		    	controller.setMyStage(stage);
		    	stage.showAndWait();
		        
	 	 }catch (IOException e) {
				e.printStackTrace();
			}
	  }

    @FXML
    void openUpdateBookScreen(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = new FXMLLoader();
			    fxmlLoader.setLocation(TelaAtualizacaoLivroController.class.getResource("/br/ufrn/imd/view/TelaAtualizacaoLivro.fxml"));
			    AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stageBook = new Stage();
			    stageBook.setTitle("Atualizar Livro");
			    Scene scene = new Scene(page);
			    stageBook.setResizable(false);
			    stageBook.setScene(scene);
			    
//			    TelaAtualizacaoLivroController controller = fxmlLoader.getController();
//		    	controller.setMyStage(stageBook);
		    	stageBook.showAndWait();
		        
	   	 }catch (IOException e) {
				e.printStackTrace();
			}
    }

    @FXML
    void openUpdateClientScreen(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = new FXMLLoader();
			    fxmlLoader.setLocation(TelaAtualizacaoClienteController.class.getResource("/br/ufrn/imd/view/TelaAtualizacaoCliente.fxml"));
			    AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stage = new Stage();
			    stage.setTitle("Atualizar Cliente");
			    Scene scene = new Scene(page);
			    stage.setResizable(false);
			    stage.setScene(scene);
			    
//			    TelaAtualizacaoClienteController controller = fxmlLoader.getController();
//		    	controller.setMyStage(stage);
		    	stage.showAndWait();
		        
	 	 }catch (IOException e) {
				e.printStackTrace();
			}
	  }

    @FXML
    void openUpdateTagScreen(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = new FXMLLoader();
			    fxmlLoader.setLocation(TelaAtualizacaoTagController.class.getResource("/br/ufrn/imd/view/TelaAtualizacaoTag.fxml"));
			    AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stage = new Stage();
			    stage.setTitle("Atualizar Tag");
			    Scene scene = new Scene(page);
			    stage.setResizable(false);
			    stage.setScene(scene);
			    
//			    TelaAtualizacaoTagController controller = fxmlLoader.getController();
//		    	controller.setMyStage(stage);
		    	stage.showAndWait();
		        
	 	 }catch (IOException e) {
				e.printStackTrace();
			}
	  }

    
    @FXML
    void openUpdateSalesScreen(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = new FXMLLoader();
			    fxmlLoader.setLocation(TelaAtualizacaoTransacaoController.class.getResource("/br/ufrn/imd/view/TelaAtualizacaoTransacao.fxml"));
			    AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stage = new Stage();
			    stage.setTitle("Atualizar Venda");
			    Scene scene = new Scene(page);
			    stage.setResizable(false);
			    stage.setScene(scene);
			    
//			    TelaAtualizacaoClienteController controller = fxmlLoader.getController();
//		    	controller.setMyStage(stage);
		    	stage.showAndWait();
		        
	 	 }catch (IOException e) {
				e.printStackTrace();
			}
	  }

    
}
