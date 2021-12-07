package br.ufrn.imd.application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// caminho da Tela Principal
			Parent root = FXMLLoader.load(getClass().getResource("../view/TelaPrincipal.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("BookStore");
			primaryStage.setResizable(true);
			primaryStage.setMinHeight(600);
			primaryStage.setMinWidth(1000);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
