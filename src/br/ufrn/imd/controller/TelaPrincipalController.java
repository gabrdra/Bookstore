package br.ufrn.imd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TelaPrincipalController {

    @FXML
    private Button btAdicionarProduto;

    @FXML
    private Button btBuscar;

    @FXML
    private Button btConfirmarVenda;

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
    private Label lbValorTotal;

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
    private Spinner<?> spQnt;

    @FXML
    private TableColumn<?, ?> tableCodigo;

    @FXML
    private TableColumn<?, ?> tableDesc;

    @FXML
    private TableView<?> tableListaCompras;

    @FXML
    private TableColumn<?, ?> tableNome;

    @FXML
    private TableColumn<?, ?> tableQnt;

    @FXML
    private TableColumn<?, ?> tableTotal;

    @FXML
    private TableColumn<?, ?> tableValor;

    @FXML
    private TextField tfCodPro;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfValor;

    @FXML
    void AdicionarProdutoALista(ActionEvent event) {

    }

    @FXML
    void ConfirmarVenda(ActionEvent event) {

    }

    @FXML
    void abrirTelaProduto(ActionEvent event) {

    }

    @FXML
    void buscarProdutoLista(ActionEvent event) {

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

}
