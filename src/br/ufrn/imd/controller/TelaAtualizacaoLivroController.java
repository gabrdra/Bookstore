package br.ufrn.imd.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;

import br.ufrn.imd.business.BookService;
import br.ufrn.imd.business.ITagService;
import br.ufrn.imd.business.TagService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.exceptions.GUIException;
import br.ufrn.imd.model.Book;
import br.ufrn.imd.model.Tag;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class TelaAtualizacaoLivroController {
	@FXML
	private Label lbId;
	@FXML
	private TextField tfId;
	@FXML
	private Button btSearchId;
	@FXML
	private Label lbBarcode;
	@FXML
	private TextField tfBarcode;
	@FXML
	private Button btSearchBarcode;
	@FXML
	private Label lbName;
	@FXML
	private TextField tfName;
	@FXML
	private Label lbPrice;
	@FXML
	private TextField tfPrice;
	@FXML
	private Label lbTags;
	@FXML
	private Label lbAuthor;
	@FXML
	private Label lbDescription;
	@FXML
	private TextField tfTags;
	@FXML
	private TextField tfAuthor;
	@FXML
	private TextField tfDescription;
	@FXML
	private Button btUpdate;

	// Event Listener on Button[#btSearchId].onAction
	@FXML
	public void onSearchId(ActionEvent event) {
		try {
			populateInterface(new BookService().retrieveBookById(Integer.parseInt(tfId.getText())));
		} catch (NumberFormatException | DataException | BusinessException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	private void populateInterface(Book book) {
		try {
			if(book.getId() == 0) {
				throw new GUIException("Livro inexistente \n");
			}
			tfId.setText(String.valueOf(book.getId()));
			tfBarcode.setText(book.getBarcode());
			tfName.setText(book.getName());
			tfPrice.setText(String.valueOf(book.getPrice()));
			tfAuthor.setText(book.getAuthor());
			tfDescription.setText(book.getDescription());
			String tagsString = "[";
			ArrayList<Integer> tagsId = book.getTagsId();
			for(int i = 0; i < tagsId.size(); i++) {
				if(i != tagsId.size()-1) {
					tagsString += String.valueOf(tagsId.get(i))+", ";
				}
				else {
					tagsString += String.valueOf(tagsId.get(i));
				}
			}
			tagsString += "]";
			tfTags.setText(tagsString);
		}
		catch(GUIException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	// Event Listener on Button[#btSearchBarcode].onAction
	@FXML
	public void onSearchBarcode(ActionEvent event) {
		try {
			populateInterface(new BookService().retrieveBookByBarcode(tfBarcode.getText()));
		} catch (NumberFormatException | DataException | BusinessException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	// Event Listener on Button[#btUpdate].onAction
	@FXML
	public void updateClient(ActionEvent event) {
		try {
			Book book = new Book();
			book.setId(Integer.parseInt(tfId.getText()));
			book.setName(tfName.getText());
			book.setBarcode(tfBarcode.getText());
			book.setPrice(Double.parseDouble(tfPrice.getText()));
			book.setAuthor(tfAuthor.getText());
			book.setDescription(tfDescription.getText());
			ArrayList<Tag> tags  = new ArrayList<Tag>();
			ITagService tagService = new TagService();
			for (String t : tfTags.getText().split("\\D+")) { // the \\D+ is a regular expression that leaves only the numbers behind
				if (t.isEmpty()) {
			        continue;
			    }
			    tags.add(tagService.retrieveTagById(Integer.parseInt(t)));
			}
			book.setTags(tags);
			new BookService().updateBook(book);
			}
			catch(NumberFormatException | BusinessException | DataException e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
	        	alert.showAndWait();
	        	return;
			}
	}
}
