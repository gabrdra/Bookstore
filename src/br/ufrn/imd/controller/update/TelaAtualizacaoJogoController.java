package br.ufrn.imd.controller.update;

import java.util.ArrayList;

import br.ufrn.imd.business.ITagService;
import br.ufrn.imd.business.ProductService;
import br.ufrn.imd.business.TagService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.exceptions.GUIException;
import br.ufrn.imd.model.ProductGame;
import br.ufrn.imd.model.Tag;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaAtualizacaoJogoController {
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
	private Label lbPublisher;
	@FXML
	private Label lbDescription;
	@FXML
	private TextField tfTags;
	@FXML
	private TextField tfPublisher;
	@FXML
	private TextField tfDescription;
	@FXML
	private Button btUpdate;
	
	@FXML
	private Label lbPlatform;
	
	@FXML
	private TextField tfPlatform;

	// Event Listener on Button[#btSearchId].onAction
	@FXML
	public void onSearchId(ActionEvent event) {
		try {
			populateInterface((ProductGame)new ProductService().retrieveProductById(Integer.parseInt(tfId.getText())));
		} catch (NumberFormatException | DataException | BusinessException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	private void populateInterface(ProductGame game) {
		try {
			if(game.getId() == 0) {
				throw new GUIException("Jogo inexistente \n");
			}
			tfId.setText(String.valueOf(game.getId()));
			tfBarcode.setText(game.getBarcode());
			tfName.setText(game.getName());
			tfPrice.setText(String.valueOf(game.getPrice()));
			tfPublisher.setText(game.getPublisher());
			tfPlatform.setText(game.getPlatform());
			tfDescription.setText(game.getDescription());
			String tagsString = "[";
			ArrayList<Integer> tagsId = game.getTagsId();
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
			populateInterface((ProductGame)new ProductService().retrieveProductByBarcode(tfBarcode.getText()));
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
			ProductGame game = new ProductGame();
			game.setId(Integer.parseInt(tfId.getText()));
			game.setName(tfName.getText());
			game.setBarcode(tfBarcode.getText());
			game.setPrice(Double.parseDouble(tfPrice.getText()));
			game.setPublisher(tfPublisher.getText());
			game.setPlatform(tfPlatform.getText());
			game.setDescription(tfDescription.getText());
			ArrayList<Tag> tags  = new ArrayList<Tag>();
			ITagService tagService = new TagService();
			for (String t : tfTags.getText().split("\\D+")) { // the \\D+ is a regular expression that leaves only the numbers behind
				if (t.isEmpty()) {
			        continue;
			    }
			    tags.add(tagService.retrieveTagById(Integer.parseInt(t)));
			}
			game.setTags(tags);
			new ProductService().updateProduct(game);
			}
			catch(NumberFormatException | BusinessException | DataException e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
	        	alert.showAndWait();
	        	return;
			}
		Alert alert = new Alert(AlertType.INFORMATION, "Atualizado com sucesso", ButtonType.OK);
		alert.showAndWait();
	}
}
