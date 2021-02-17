package cardFinder.cardFinder;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertWindow {
	
	public static void display(String msg) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("CHYBA");
		window.setHeight(150);
		window.setWidth(250);

		Label label = new Label();
		label.setFont(Font.font("System", FontWeight.BOLD, 16));
		label.setText(msg);

		VBox layout = new VBox(10);
		Button ok = new Button("OK");
		ok.setMinWidth(80);
		ok.setMinHeight(30);
		ok.setOnAction(e -> window.close());
		layout.getChildren().addAll(label, ok);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
