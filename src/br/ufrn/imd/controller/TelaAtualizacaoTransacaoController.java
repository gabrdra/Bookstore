package br.ufrn.imd.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;

import br.ufrn.imd.business.BookService;
import br.ufrn.imd.business.IBookService;
import br.ufrn.imd.business.TransactionService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.exceptions.GUIException;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Transaction;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class TelaAtualizacaoTransacaoController {
	@FXML
	private Label lbId;
	@FXML
	private TextField tfId;
	@FXML
	private Label lbIdClient;
	@FXML
	private TextField tfIdClient;
	@FXML
	private Button btSearchId;
	@FXML
	private Label lbPrice;
	@FXML
	private TextField tfPrice;
	@FXML
	private Label lbBooks;
	@FXML
	private TextField tfBooks;
	@FXML
	private Button btUpdate;

	// Event Listener on Button[#btSearchId].onAction
	@FXML
	public void onSearchId(ActionEvent event) {
		try {
			populateInterface(new TransactionService().retrieveTransactionById(Integer.parseInt(tfId.getText())));
		} catch (NumberFormatException | DataException | BusinessException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	private void populateInterface(Transaction transaction) {
		try {
			if(transaction.getId() == 0) {
				throw new GUIException("Transação inexistente \n");
			}
			tfId.setText(String.valueOf(transaction.getId()));
			tfIdClient.setText(String.valueOf(transaction.getClient()));
			tfPrice.setText(String.valueOf(transaction.getValue()));
			String booksString = "[";
			ArrayList<Integer> booksId = transaction.getBooksId();
			for(int i = 0; i < booksId.size(); i++) {
				if(i != booksId.size()-1) {
					booksString += String.valueOf(booksId.get(i))+", ";
				}
				else {
					booksString += String.valueOf(booksId.get(i));
				}
			}
			booksString += "]";
			tfBooks.setText(booksString);
		}
		catch(GUIException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	// Event Listener on Button[#btUpdate].onAction
	@FXML
	public void updateTransaction(ActionEvent event) {
		try {
		Transaction transaction = new Transaction();
		transaction.setId(Integer.parseInt(tfId.getText()));
		transaction.setClient(Integer.parseInt(tfIdClient.getText()));
		transaction.setValue(Double.parseDouble(tfPrice.getText()));
		ArrayList<Book> books  = new ArrayList<Book>();
		IBookService bookService = new BookService();
		for (String t : tfBooks.getText().split("\\D+")) { // the \\D+ is a regular expression that leaves only the numbers behind
			if (t.isEmpty()) {
		        continue;
		    }
		    books.add(bookService.retrieveBookById(Integer.parseInt(t)));
		}
		transaction.setBooks(books);
		new TransactionService().updateTransaction(transaction);
		}
		catch(NumberFormatException | BusinessException | DataException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
}
