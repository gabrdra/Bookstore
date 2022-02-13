package br.ufrn.imd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import br.ufrn.imd.business.ClientService;
import br.ufrn.imd.business.IClientService;
import br.ufrn.imd.business.ProductService;
import br.ufrn.imd.business.TransactionService;
import br.ufrn.imd.controller.add.TelaCadastroClienteController;
import br.ufrn.imd.controller.add.TelaCadastroProdutoController;
import br.ufrn.imd.controller.add.TelaCadastroTagController;
import br.ufrn.imd.controller.list.TelaListagemClientesController;
import br.ufrn.imd.controller.list.TelaListagemProdutosController;
import br.ufrn.imd.controller.list.TelaListagemTagsController;
import br.ufrn.imd.controller.list.TelaListagemVendasController;
import br.ufrn.imd.controller.remove.TelaRemoverClienteController;
import br.ufrn.imd.controller.remove.TelaRemoverProdutoController;
import br.ufrn.imd.controller.remove.TelaRemoverTagController;
import br.ufrn.imd.controller.update.TelaAtualizacaoClienteController;
import br.ufrn.imd.controller.update.TelaAtualizacaoTagController;
import br.ufrn.imd.controller.update.TelaAtualizacaoTransacaoController;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.factory.Distributor;
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
	private Product product;
	private double valorTotal;
	private Transaction transaction;
	
	ArrayList<Product> listProducts = new ArrayList<Product>();
	
	ObservableList<Product> observableProductList = FXCollections.observableArrayList();
	
	@FXML
    private Button btAddCleintToCart;
	
	@FXML
    private Button btRemCleintToCart;
	
	@FXML
    private TextField tfCpf;
	
	

    @FXML
    private MenuItem mnRecommendation;

    @FXML
    private MenuItem mnRemProduct;

    @FXML
    private MenuItem mnRemClient;

    @FXML
    private MenuItem mnRemTag;

    @FXML
    private MenuItem mnUpdateProduct;

    @FXML
    private MenuItem mnUpdateClient;

    @FXML
    private MenuItem mnUpdateTag;
    
    @FXML
    private MenuItem mnUpdateSales;

    @FXML
    private TextField tfClientName;
   
    @FXML
    private Button btAddProductToCart;

    @FXML
    private Button btSearchProduct;

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
    private MenuItem mnAddProduct;

    @FXML
    private MenuItem mnAddClient;

    @FXML
    private MenuItem mnAddTag;

    @FXML
    private MenuItem mnListProducts;

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
    private TableColumn<Product, String> tableBarcode;

    @FXML
    private TableView<Product> tableCartList;

    @FXML
    private TableColumn<Product, String> tableDesc;

    @FXML
    private TableColumn<Product, String> tableName;

    @FXML
    private TableColumn<Product, Double> tableValue;
    @FXML
    private TextField tfBarCode;

    @FXML
    private TextField tfProductName;

    @FXML
    private TextField tfValue;
 
    @FXML
    private Label lbDescricao;
    
    @FXML
    private TextField tfProductDescricao;
    
    
	@java.lang.Override
	public void initialize(java.net.URL arg0, java.util.ResourceBundle arg1) {
	    tableBarcode.setCellValueFactory(new PropertyValueFactory<Product, String>("barcode"));
	    tableName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		tableValue.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
		tableDesc.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
	}

    @FXML
    void ConfirmarVenda(ActionEvent event) throws DataException, BusinessException {
    	transaction = new Transaction();
    	ArrayList<Integer> productsId = new ArrayList<Integer>();
    	for(Product product:listProducts) {
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
    void openAddProductScreen(ActionEvent event) throws IOException {
    	 try {
			FXMLLoader fxmlLoader = Distributor.getInstance().addProductFXMLLoader();
			AnchorPane page = (AnchorPane) fxmlLoader.load();
		    
		    Stage stage = new Stage();
		    stage.setTitle("Cadastrar Produto");
		    Scene scene = new Scene(page);
		    stage.setResizable(false);
		    stage.setScene(scene);
		    
	        TelaCadastroProdutoController controller = fxmlLoader.getController();
	    	controller.setMyStage(stage);
	    	stage.showAndWait();
	        
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
    void openListProducts(ActionEvent event) {
     	 try {
			FXMLLoader fxmlLoader = Distributor.getInstance().listProductFXMLLoader();
			AnchorPane page = (AnchorPane) fxmlLoader.load();
		    
		    Stage stage = new Stage();
		    stage.setTitle("Lista Produtos");
		    Scene scene = new Scene(page);
		    stage.setResizable(false);
		    stage.setScene(scene);
		    
		    TelaListagemProdutosController controller = fxmlLoader.getController();
	    	controller.setMyStage(stage);
	    	stage.showAndWait();
	        
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
    void searchProduct(ActionEvent event) throws IOException {
    	ProductService productService = new ProductService();
    	
    	
    	try {
    		product =  productService.retrieveProductByBarcode(tfBarCode.getText());
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
    	
		
		tfValue.setText(Double.toString(product.getPrice()));
		tfProductName.setText(product.getName());
		tfProductDescricao.setText(product.getDescription());
		
    }
    
    @FXML
    void addProductToCart(ActionEvent event) throws IOException {
    	searchProduct(event);
    	if(product!=null) {
    		listProducts.add(product);
    		observableProductList.removeAll(listProducts);
    		observableProductList.addAll(listProducts);
    		tableCartList.setItems(observableProductList);
    	}
    	
    	product = null;
    	totalValueRefresh();
    	tableReset();
    }
    
    void totalValueRefresh() {
    	valorTotal=0;
    	for (Product product : listProducts) {
			valorTotal+=product.getPrice();
		}
    	lbTotalValue.setText("R$" + String.format("%.2f", valorTotal));
    }

    void tableReset() {

    	tfBarCode.setText(null);
		tfValue.setText(null);
		tfProductName.setText(null);
		tfProductDescricao.setText(null);
    }
    
    void resetObservableList() {
    	listProducts = new ArrayList<Product>();
    	observableProductList = FXCollections.observableArrayList();
		tableCartList.setItems(observableProductList);
    }
    
    @FXML
    void openRecomScreen(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = Distributor.getInstance().recomProductFXMLLoader();
				AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stage = new Stage();
			    stage.setTitle("Gerar Recomedanção");
			    Scene scene = new Scene(page);
			    stage.setResizable(false);
			    stage.setScene(scene);
			    
//			    TelaRecomendacaoLivroController controller = fxmlLoader.getController();
//		    	controller.setMyStage(stage);
		    	stage.showAndWait();
		        
	 	 }catch (IOException e) {
				e.printStackTrace();
			}
	  }

    @FXML
    void openRemProductScreen(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = Distributor.getInstance().removeProductFXMLLoader();
				AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stage = new Stage();
			    stage.setTitle("Remover Produto");
			    Scene scene = new Scene(page);
			    stage.setResizable(false);
			    stage.setScene(scene);
			    
			    TelaRemoverProdutoController controller = fxmlLoader.getController();
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
    void openUpdateProductScreen(ActionEvent event) {
	   	 try {
				FXMLLoader fxmlLoader = Distributor.getInstance().updateProductFXMLLoader();
				AnchorPane page = (AnchorPane) fxmlLoader.load();
			    
			    Stage stage = new Stage();
			    stage.setTitle("Atualizar Produto");
			    Scene scene = new Scene(page);
			    stage.setResizable(false);
			    stage.setScene(scene);
			    
//			    TelaAtualizacaoProdutoController controller = fxmlLoader.getController();
//		    	controller.setMyStage(stageProduct);
		    	stage.showAndWait();
		        
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
