module cardFinder.cardFinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.swing;

    opens cardFinder.cardFinder to javafx.fxml;
    opens Aplikace.Eshopy to javafx.fxml;
    exports cardFinder.cardFinder;
    exports Aplikace.Eshopy;
}