package br.ufrn.imd.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import br.ufrn.imd.business.TagService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.exceptions.GUIException;
import br.ufrn.imd.model.Tag;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class TelaAtualizacaoTagController {
	@FXML
	private Label lbId;
	@FXML
	private TextField tfId;
	@FXML
	private Label lbName;
	@FXML
	private TextField tfName;
	@FXML
	private Button btSearchId;
	@FXML
	private Button btSearchName;
	@FXML
	private Button btUpdate;

	// Event Listener on Button[#btSearchId].onAction
	@FXML
	public void onSearchId(ActionEvent event) {
		try {
			populateInterface(new TagService().retrieveTagById(Integer.parseInt(tfId.getText())));
		} catch (NumberFormatException | DataException | BusinessException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	// Event Listener on Button[#btSearchName].onAction
	@FXML
	public void onSearchName(ActionEvent event) {
		try {
			System.out.println(tfName.getText());
			populateInterface(new TagService().retrieveTagByName(tfName.getText()));
		} catch (DataException | BusinessException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	private void populateInterface(Tag tag) {
		try {
			if(tag.getId() == 0) {
				throw new GUIException("Tag inexistente \n");
			}
			tfId.setText(String.valueOf(tag.getId()));
			tfName.setText(tag.getName());
		}
		
		catch(GUIException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	// Event Listener on Button[#btUpdate].onAction
	@FXML
	public void updateTag(ActionEvent event) {
		try {
			Tag tag = new Tag();
			tag.setId(Integer.parseInt(tfId.getText()));
			tag.setName(tfName.getText());
			new TagService().updateTag(tag);
		} catch (NumberFormatException | DataException | BusinessException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
}
