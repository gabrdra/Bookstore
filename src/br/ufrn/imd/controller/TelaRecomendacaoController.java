package br.ufrn.imd.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import br.ufrn.imd.business.recommendation.Recommendation;
import br.ufrn.imd.exceptions.DataException;
import javafx.event.ActionEvent;

public class TelaRecomendacaoController {
	@FXML
	private TextField fxNumber;
	@FXML
	private TextField fxId;
	@FXML
	private Button btSearch;

	// Event Listener on Button[#btSearch].onAction
	@FXML
	public void onSearchButtonPressed(ActionEvent event) {
		Recommendation recommendation = new Recommendation();
		try {
			System.out.println(recommendation.getRecommendationsForClient(Integer.parseInt(fxId.getText()), Integer.parseInt(fxNumber.getText())));
		} catch (NumberFormatException | DataException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
        	alert.showAndWait();
        	return;
		}
	}
}
