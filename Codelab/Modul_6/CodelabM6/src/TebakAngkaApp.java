import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;

public class TebakAngkaApp extends Application {
    private int target;
    private int count;
    private TextField input;
    private Label feedbackLabel;
    private Label countLabel;
    private Button guessButton;

    @Override
    public void start(Stage primaryStage) {
        generateNewTarget();

        Label title = new Label("🔢 Tebak Angka 1-100\uD83E\uDD14");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        feedbackLabel = new Label();
        feedbackLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");

        input = new TextField();
        input.setPromptText("Masukkan tebakanmu");
        input.setMaxWidth(150);

        guessButton = new Button("Coba Tebak!");
        guessButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        guessButton.setOnAction(e -> checkGuess());

        HBox inputAndButton = new HBox(10, input, guessButton);
        inputAndButton.setAlignment(Pos.CENTER);

        countLabel = new Label("Jumlah percobaan: 0");

        VBox layout = new VBox(15, title, feedbackLabel, inputAndButton, countLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E3F2FD;");

        Scene scene = new Scene(layout, 400, 250);
        primaryStage.setTitle("Tebak Angka");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(input.getText());
            count++;
            countLabel.setText("Jumlah percobaan: " + count);

            if (guess < target) {
                feedbackLabel.setText("⬇️ Terlalu kecil");
            } else if (guess > target) {
                feedbackLabel.setText("⬆️ Terlalu besar");
            } else {
                feedbackLabel.setText("✅ Tebakan benar!");
                guessButton.setText("Main Lagi");
                guessButton.setOnAction(e -> resetGame());
            }

            input.clear();

        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Masukkan angka yang valid!");
        }
    }

    private void resetGame() {
        generateNewTarget();
        count = 0;
        countLabel.setText("Jumlah percobaan: 0");
        feedbackLabel.setText("");
        guessButton.setText("Coba Tebak!");
        guessButton.setOnAction(e -> checkGuess());
    }

    private void generateNewTarget() {
        target = new Random().nextInt(100) + 1;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
