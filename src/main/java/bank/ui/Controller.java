package bank.ui;

import bank.model.Klient;
import bank.service.Bank;
import bank.service.Context;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.io.File;
import java.text.Format;
import java.util.List;
import java.util.Optional;

public class Controller {
    Bank bank;

    @FXML
    TextArea oknoTekstowe;

    @FXML
    Menu toolsMenu;

    @FXML
    Canvas mycanvas;

    @FXML
    Stage stage;

    @FXML
    ComboBox<Klient> komboKlientow;

    public Controller() {
        this.bank = Context.getBank();
    }

    public void sayIt() {
        oknoTekstowe.setText(bank.getKlienci().toString());
    }

    public void zmienNaDuze() {
        String tekst = oknoTekstowe.getText();
        tekst = tekst.toUpperCase();
        oknoTekstowe.setText(tekst);
    }

    public void disableToolsMenu() {
        boolean isDisabled = toolsMenu.isDisable();
        if (isDisabled) {
            toolsMenu.setDisable(false);
        } else {
            toolsMenu.setDisable(true);
        }
    }

    public void drawOnCanvas() {
        //Użycie canvasu:
        GraphicsContext gc = mycanvas.getGraphicsContext2D();
        int size = 30;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                gc.fillRoundRect(5 + i * 50, 5 + j * 50, size, size, 10, 10);
            }
        }
        //see eg. http://docs.oracle.com/javafx/2/canvas/jfxpub-canvas.htm
        gc.setFill(Color.color(0.1, 0.7, 0.5, 0.5));
        gc.fillArc(10, 110, 300, 300, 45, 240, ArcType.OPEN);
        //todo: draw image
    }

    public void alertujUsera() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog!");
        alert.setHeaderText("Some header");
        //todo: wyświetla narazie tylko jedną linijkę
        alert.setContentText("Lorem ipsum dolor sit amet enim. Etiam ullamcorper. ");
        alert.showAndWait();
        //more at: http://code.makery.ch/blog/javafx-dialogs-official/
    }

    public void myOpenFile() {
        File f = new FileChooser().showOpenDialog(stage);
        System.out.println(f.getAbsolutePath());
    }

    public void dodajUsera() {
        Dialog<String> dialog = new Dialog<>();
        //title...
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        //GridPane   ma mieć (3, 2)
        GridPane grid = new GridPane();
        //Dostawić elementy

        dialog.getDialogPane().setContent(grid);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println(result.get());
        }
    }

    public void loginDialog() {
        //Tworzenie obiektu dialogowego:
        // parametrem jest wynik który dostaniemy z dialogu (tutaj parę dwóch napisów)
        // (gdybyśmy chcieli pojedynczą np. liczbę, to byłoby Dialog<Integer>)
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Log in");
        dialog.setHeaderText("Logowanie do systemu");
        //Customizacja buttonów okna
        ButtonType naszeLogowanie = new ButtonType("Zaloguj", ButtonBar.ButtonData.APPLY);
//        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.PREVIOUS, ButtonType.YES.NEXT, ButtonType.FINISH, ButtonType.CANCEL);
        dialog.getDialogPane().getButtonTypes().addAll(naszeLogowanie, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField userField = new TextField();
        userField.setPromptText("User");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Hasło");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(userField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        /*
         * Wykonanie operacji przez wątek UI (JavaFX application thread)
         * lepsze niż samo userField.requestFocus();
         *
         * See also: https://stackoverflow.com/questions/13784333/platform-runlater-and-task-in-javafx
         */
        Platform.runLater(userField::requestFocus);

        //Te pola mają wiele "properties", które są "ObservableValue"; można zaczepiać listenery
//        userField.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.equals("Abra Kadabra")) {
//                System.out.println("Powitał Kadabra!");
//            }
//        });

//        userField.borderProperty().addListener((observable, oldValue, newValue) -> {
//            Border xxx = oldValue;
//        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == naszeLogowanie) {
                return new Pair<>(userField.getText(), passField.getText());
            } else if (dialogButton==ButtonType.CANCEL) {
                System.out.println("Cancel button clicked");
            }
            return null;
        });

        //Ten kod pokazuje dialog na ekranie, i zmusza usera do reakcji
        Optional<Pair<String, String>> result = dialog.showAndWait();

        if (result.isPresent()) {
            Pair<String, String> ppp = result.get();
            System.out.println("Username:" + ppp.getKey());
            System.out.println("Password:" + ppp.getValue());
        }

        //todo: "new game dialog" or "num keyboard dialog"
        //todo: akcje pod klawiszami

    }

    public void refresh() {
        List<Klient> klients = bank.getKlienci();
        komboKlientow.setConverter(new StringConverter<Klient>() {
            @Override
            public String toString(Klient k) {
                String res = String.format("%-20s (Id:%-3d)", k.getNazwisko(), k.getId());
                return res;
            }

            @Override
            public Klient fromString(String string) {
                return null;
            }
        });
        komboKlientow.getItems().clear();
        komboKlientow.getItems().addAll(klients);
        komboKlientow.setValue(klients.get(0));
    }

    public void addClientDialog() {
        Dialog<String> dialog = new Dialog<>();
        //title...
        dialog.setTitle("Add client");
        dialog.setHeaderText("Add new client to the bank");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField userField = new TextField();
        userField.setPromptText("Name");
        TextField peselField = new TextField();
        peselField.setPromptText("Pesel");
        TextField adresField = new TextField();
        adresField.setPromptText("Address");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(userField, 1, 0);
        grid.add(new Label("Pesel:"), 0, 1);
        grid.add(peselField, 1, 1);
        grid.add(new Label("Address:"), 0, 2);
        grid.add(adresField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return userField.getText();
            } else if (dialogButton==ButtonType.CANCEL) {
                System.out.println("Cancel button clicked");
            }
            return null;
        });


        dialog.getDialogPane().setContent(grid);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println(result.get());
            bank.addClient(result.get());
        }
    }
}
