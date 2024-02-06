package com.mtdev00.Sistema_Cadastro.View;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PanelMain extends Application {
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PanelMain.fxml"));
			Parent parent = loader.load();
			Scene mainScene = new Scene(parent);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("WorkingWe");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
