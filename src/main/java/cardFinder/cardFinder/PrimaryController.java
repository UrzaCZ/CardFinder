
package cardFinder.cardFinder;

import Aplikace.Eshopy.Images;
import Aplikace.Eshopy.Najada;
import Aplikace.Eshopy.Rishada;
import Aplikace.Eshopy.Rytir;
import Aplikace.Eshopy.Tolarie;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;

public class PrimaryController implements Initializable {
    public Button fxButtonSearch;
    @FXML
    private TextField findCard;
    @FXML
    private Button fxButton;
    @FXML
    private Label labelVysledkyCR;
    @FXML
    private ImageView cardImage;
    @FXML
    private Label labelVysledkyNajada;
    @FXML
    private Label labelVysledkyRishada;
    @FXML
    private Label labelVysledkyTolarie;
    @FXML
    private TableView<Rytir> cardCRlist;
    @FXML
    private TableColumn<Rytir, String> rytirName;
    @FXML
    private TableColumn<Rytir, String> rytirLang;
    @FXML
    private TableColumn<Rytir, String> rytirFoil;
    @FXML
    private TableColumn<Rytir, String> rytirCondition;
    @FXML
    private TableColumn<Rytir, String> rytirEdition;
    @FXML
    private TableColumn<Rytir, String> rytirNumber;
    @FXML
    private TableColumn<Rytir, String> rytirPrice;
    @FXML
    private TableColumn<Rytir, String> rytirTradePrice;
    @FXML
    private TableView<Najada> cardNajadaList;
    @FXML
    private TableColumn<Najada, String> najadaName;
    @FXML
    private TableColumn<Najada, String> najadaLang;
    @FXML
    private TableColumn<Najada, String> najadaFoil;
    @FXML
    private TableColumn<Najada, String> najadaCondition;
    @FXML
    private TableColumn<Najada, String> najadaEdition;
    @FXML
    private TableColumn<Najada, String> najadaNumber;
    @FXML
    private TableColumn<Najada, String> najadaPrice;
    @FXML
    private TableColumn<Najada, String> najadaTradePrice;
    @FXML
    private TableView<Rishada> cardRishadaList;
    @FXML
    private TableColumn<Rishada, String> rishadaName;
    @FXML
    private TableColumn<Rishada, String> rishadaLang;
    @FXML
    private TableColumn<Rishada, String> rishadaFoil;
    @FXML
    private TableColumn<Rishada, String> rishadaCondition;
    @FXML
    private TableColumn<Rishada, String> rishadaEdition;
    @FXML
    private TableColumn<Rishada, String> rishadaNumber;
    @FXML
    private TableColumn<Rishada, String> rishadaPrice;
    @FXML
    private TableColumn<Rishada, String> rishadaTradePrice;
    @FXML
    private TableView<Tolarie> cardTolarieList;
    @FXML
    private TableColumn<Tolarie, String> tolarieName;
    @FXML
    private TableColumn<Tolarie, String> tolarieLang;
    @FXML
    private TableColumn<Tolarie, String> tolarieFoil;
    @FXML
    private TableColumn<Tolarie, String> tolarieCondition;
    @FXML
    private TableColumn<Tolarie, String> tolarieEdition;
    @FXML
    private TableColumn<Tolarie, String> tolarieNumber;
    @FXML
    private TableColumn<Tolarie, String> tolariePrice;
    @FXML
    private TableColumn<Tolarie, String> tolarieTradePrice;
    private String card;
    Tolarie tolarie = new Tolarie();
    Rytir rytir = new Rytir();
    Najada najada = new Najada();
    Rishada rishada = new Rishada();
    Images image = new Images();
    private String selectedCardName;
    private String selctedCardEdition;
    private File file = new File("src/main/resources/cardFinder/cardFinder/Magic_card_back.jpg");

    public PrimaryController() {
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.najadaName.setCellValueFactory(new PropertyValueFactory("name"));
        this.najadaLang.setCellValueFactory(new PropertyValueFactory("language"));
        this.najadaFoil.setCellValueFactory(new PropertyValueFactory("foil"));
        this.najadaCondition.setCellValueFactory(new PropertyValueFactory("condition"));
        this.najadaEdition.setCellValueFactory(new PropertyValueFactory("edition"));
        this.najadaNumber.setCellValueFactory(new PropertyValueFactory("number"));
        this.najadaPrice.setCellValueFactory(new PropertyValueFactory("price"));
        this.najadaTradePrice.setCellValueFactory(new PropertyValueFactory("tradePrice"));
        this.rytirName.setCellValueFactory(new PropertyValueFactory("name"));
        this.rytirLang.setCellValueFactory(new PropertyValueFactory("language"));
        this.rytirFoil.setCellValueFactory(new PropertyValueFactory("foil"));
        this.rytirCondition.setCellValueFactory(new PropertyValueFactory("condition"));
        this.rytirEdition.setCellValueFactory(new PropertyValueFactory("edition"));
        this.rytirNumber.setCellValueFactory(new PropertyValueFactory("number"));
        this.rytirPrice.setCellValueFactory(new PropertyValueFactory("price"));
        this.rytirTradePrice.setCellValueFactory(new PropertyValueFactory("tradePrice"));
        this.rishadaName.setCellValueFactory(new PropertyValueFactory("name"));
        this.rishadaLang.setCellValueFactory(new PropertyValueFactory("language"));
        this.rishadaFoil.setCellValueFactory(new PropertyValueFactory("foil"));
        this.rishadaCondition.setCellValueFactory(new PropertyValueFactory("condition"));
        this.rishadaEdition.setCellValueFactory(new PropertyValueFactory("edition"));
        this.rishadaNumber.setCellValueFactory(new PropertyValueFactory("number"));
        this.rishadaPrice.setCellValueFactory(new PropertyValueFactory("price"));
        this.rishadaTradePrice.setCellValueFactory(new PropertyValueFactory("tradePrice"));
        this.tolarieName.setCellValueFactory(new PropertyValueFactory("name"));
        this.tolarieLang.setCellValueFactory(new PropertyValueFactory("language"));
        this.tolarieFoil.setCellValueFactory(new PropertyValueFactory("foil"));
        this.tolarieCondition.setCellValueFactory(new PropertyValueFactory("condition"));
        this.tolarieEdition.setCellValueFactory(new PropertyValueFactory("edition"));
        this.tolarieNumber.setCellValueFactory(new PropertyValueFactory("number"));
        this.tolariePrice.setCellValueFactory(new PropertyValueFactory("price"));
        this.tolarieTradePrice.setCellValueFactory(new PropertyValueFactory("tradePrice"));
        this.cardCRlist.setItems(this.rytir.getKarty());
        this.cardNajadaList.setItems(this.najada.getKarty());
        this.cardRishadaList.setItems(this.rishada.getKarty());
        this.cardTolarieList.setItems(this.tolarie.getKarty());

        try {
            this.image.getEditionMap();
        } catch (IOException var4) {
            AlertWindow.display("Nepodařilo se načíst seznam edic");
        }

        this.cardImage.setImage(this.image());
    }

    @FXML
    public void buttonActionSearch(ActionEvent event) throws IOException {
        this.cardCRlist.getItems().clear();
        this.cardNajadaList.getItems().clear();
        this.cardRishadaList.getItems().clear();
        this.cardTolarieList.getItems().clear();
        this.card = this.findCard.getText();
        this.connection(this.card);
        if (this.rytir.getResultNumber() == 0 && this.najada.getResultNumber() == 0 && this.rishada.getResultNumber() == 0 && this.tolarie.getResultNumber() == 0) {
            this.cardImage.setImage(this.image());
            AlertWindow.display("Nic jsem nenašel!");
        }

        this.labelVysledkyCR.setText(String.valueOf(this.rytir.getResultNumber()));
        this.labelVysledkyNajada.setText(String.valueOf(this.najada.getResultNumber()));
        this.labelVysledkyRishada.setText(String.valueOf(this.rishada.getResultNumber()));
        this.labelVysledkyTolarie.setText(String.valueOf(this.tolarie.getResultNumber()));
    }

    private void connection(String cards) throws IOException {
        this.rytir.findCardRytir(cards);
        this.najada.findCardNajada(cards);
        this.rishada.findCardRishada(cards);
        this.tolarie.findCardTolarie(cards);
    }

    private Image image() {
        BufferedImage bf = null;
        URL url = null;
        String webURL = "";
        if (this.rytir.getResultNumber() == 0 && this.najada.getResultNumber() == 0 && this.rishada.getResultNumber() == 0 && this.tolarie.getResultNumber() == 0) {
            try {
                bf = ImageIO.read(this.file);
            } catch (IOException var6) {
                AlertWindow.display("Nenačetl jsem obrázek");
            }
        } else {
            try {
                webURL = this.image.parseURLImage(this.selectedCardName, this.selctedCardEdition);
                if (webURL != null) {
                    url = new URL(webURL);
                    bf = ImageIO.read(url);
                } else {
                    bf = ImageIO.read(this.file);
                }
            } catch (IOException var5) {
                AlertWindow.display("Nenačetl jsem obrázek");
            }
        }

        Image image = SwingFXUtils.toFXImage(bf, (WritableImage)null);
        return image;
    }

    @FXML
    private void getCRSelected(MouseEvent event) {
        if (this.rytir.getResultNumber() > 0) {
            ObservableList<Rytir> rytirSelected = this.cardCRlist.getSelectionModel().getSelectedItems();
            this.selectedCardName = ((Rytir)rytirSelected.get(0)).getName();
            this.selctedCardEdition = ((Rytir)rytirSelected.get(0)).getEdition();
            this.cardImage.setImage(this.image());
        }

    }

    @FXML
    private void getNajadaSelected(MouseEvent event) {
        if (this.najada.getResultNumber() > 0) {
            ObservableList<Najada> najadaSelected = this.cardNajadaList.getSelectionModel().getSelectedItems();
            this.selectedCardName = ((Najada)najadaSelected.get(0)).getName();
            this.selctedCardEdition = ((Najada)najadaSelected.get(0)).getEdition();
            this.cardImage.setImage(this.image());
        }

    }

    @FXML
    private void getRishadaSelected(MouseEvent event) {
        if (this.rishada.getResultNumber() > 0) {
            ObservableList<Rishada> rishadaSelected = this.cardRishadaList.getSelectionModel().getSelectedItems();
            this.selectedCardName = ((Rishada)rishadaSelected.get(0)).getName();
            this.selctedCardEdition = ((Rishada)rishadaSelected.get(0)).getEdition();
            this.cardImage.setImage(this.image());
        }

    }

    @FXML
    private void getTolarieSelected(MouseEvent event) {
        if (this.tolarie.getResultNumber() > 0) {
            ObservableList<Tolarie> tolarieSelected = this.cardTolarieList.getSelectionModel().getSelectedItems();
            this.selectedCardName = ((Tolarie)tolarieSelected.get(0)).getName();
            this.selctedCardEdition = ((Tolarie)tolarieSelected.get(0)).getEdition();
            this.cardImage.setImage(this.image());
        }

    }
}
