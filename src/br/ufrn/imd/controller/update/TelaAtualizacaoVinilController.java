package br.ufrn.imd.controller.update;

import java.util.ArrayList;

import br.ufrn.imd.business.ITagService;
import br.ufrn.imd.business.ProductService;
import br.ufrn.imd.business.TagService;
import br.ufrn.imd.exceptions.BusinessException;
import br.ufrn.imd.exceptions.DataException;
import br.ufrn.imd.exceptions.GUIException;
import br.ufrn.imd.model.ProductVinyl;
import br.ufrn.imd.model.Tag;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaAtualizacaoVinilController {
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
	private Label lbBad;
	@FXML
	private Label lbDescription;
	@FXML
	private TextField tfTags;
	@FXML
	private TextField tfBand;
	@FXML
	private TextField tfDescription;
	@FXML
	private Button btUpdate;
	
	@FXML
	private Label lbYear;
	
	@FXML
	private TextField tfYear;

	// Event Listener on Button[#btSearchId].onAction
	@FXML
	public void onSearchId(ActionEvent event) {
		try {
			populateInterface((ProductVinyl)new ProductService().retrieveProductById(Integer.parseInt(tfId.getText())));
		} catch (NumberFormatException | DataException | BusinessException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
	private void populateInterface(ProductVinyl vinyl) {
		try {
			if(vinyl.getId() == 0) {
				throw new GUIException("Jogo inexistente \n");
			}
			tfId.setText(String.valueOf(vinyl.getId()));
			tfBarcode.setText(vinyl.getBarcode());
			tfName.setText(vinyl.getName());
			tfPrice.setText(String.valueOf(vinyl.getPrice()));
			tfBand.setText(vinyl.getBand());
			tfYear.setText(vinyl.getYear().toString());
			tfDescription.setText(vinyl.getDescription());
			String tagsString = "[";
			ArrayList<Integer> tagsId = vinyl.getTagsId();
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
			populateInterface((ProductVinyl)new ProductService().retrieveProductByBarcode(tfBarcode.getText()));
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
			ProductVinyl vinyl = new ProductVinyl();
			vinyl.setId(Integer.parseInt(tfId.getText()));
			vinyl.setName(tfName.getText());
			vinyl.setBarcode(tfBarcode.getText());
			vinyl.setPrice(Double.parseDouble(tfPrice.getText()));
			vinyl.setBand(tfBand.getText());
			vinyl.setYear(Integer.parseInt(tfYear.getText()));
			vinyl.setDescription(tfDescription.getText());
			ArrayList<Tag> tags  = new ArrayList<Tag>();
			ITagService tagService = new TagService();
			for (String t : tfTags.getText().split("\\D+")) { // the \\D+ is a regular expression that leaves only the numbers behind
				if (t.isEmpty()) {
			        continue;
			    }
			    tags.add(tagService.retrieveTagById(Integer.parseInt(t)));
			}
			vinyl.setTags(tags);
			new ProductService().updateProduct(vinyl);
			}
			catch(NumberFormatException | BusinessException | DataException e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
	        	alert.showAndWait();
	        	return;
			}
		Alert alert = new Alert(AlertType.ERROR, "Atualizado com sucesso", ButtonType.OK);
		alert.showAndWait();
		return;
	}
}
