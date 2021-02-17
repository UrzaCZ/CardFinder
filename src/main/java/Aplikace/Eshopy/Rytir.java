
package Aplikace.Eshopy;

import java.io.IOException;
import java.util.Iterator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Rytir {
    private SimpleStringProperty name;
    private SimpleStringProperty edition;
    private SimpleStringProperty number;
    private SimpleStringProperty price;
    private boolean foil;
    private SimpleStringProperty condition;
    private SimpleStringProperty language;
    private float tradePrice;
    private ObservableList<Rytir> karty = FXCollections.observableArrayList();

    public ObservableList<Rytir> getKarty() {
        return karty;
    }

    public Rytir() {
    }

    public String getName() {
        return this.name.get();
    }

    public String getEdition() {
        return this.edition.get();
    }

    public String getNumber() {
        return this.number.get();
    }

    public String getPrice() {
        return this.price.get();
    }

    public boolean isFoil() {
        return this.foil;
    }

    public String getCondition() {
        return this.condition.get();
    }

    public String getLanguage() {
        return this.language.get();
    }

    public float getTradePrice() {
        return this.tradePrice;
    }

    public int getResultNumber() {
        return this.karty.size();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setEdition(String edition) {
        this.edition = new SimpleStringProperty(edition);
    }

    public void setNumber(String availability) {
        this.number = new SimpleStringProperty(availability);
    }

    public void setPrice(String price) {
        this.price = new SimpleStringProperty(price);
    }

    public void setCondition(String condition) {
        this.condition = new SimpleStringProperty(condition);
    }

    public void setLanguage(String language) {
        this.language = new SimpleStringProperty(language);
    }

    public void setTradePrice(float tradePrice) {
        this.tradePrice = tradePrice;
    }

    public void setFoil(boolean foil) {
        this.foil = foil;
    }

    public void findCardRytir(String findCard) throws IOException {
        String url = "http://cernyrytir.cz/index.php3?akce=3&limit=0&jmenokarty=" + findCard + "&edice_magic=libovolna&poczob=30&foil=A&triditpodle=ceny&hledej_pouze_magic=1&submit=Vyhledej";
        Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
        Elements body = doc.select("table.kusovkytext[cellpadding=3]");
        String regex = "\\D";
        int counter = 0;
        Rytir rytir = null;

        for(Iterator var8 = body.select("tr").iterator(); var8.hasNext(); ++counter) {
            Element e = (Element)var8.next();
            String subName;
            String[] language;
            if (counter % 3 == 0) {
                rytir = new Rytir();
                rytir.setCondition("NM");
                rytir.setLanguage("ENG");
                String getName = e.text();
                if (getName.contains("foil")) {
                    language = getName.split(" - ");
                    rytir.setName(language[0]);
                    if (language[1].contains("lightly played")) {
                        rytir.setCondition("LP");
                    }

                    rytir.setFoil(true);
                } else {
                    rytir.setName(getName);
                    rytir.setFoil(false);
                }

                if (getName.contains("(")) {
                    subName = getName.substring(0, getName.indexOf("(") - 1);
                    getName = subName;
                } else if (getName.contains("Land")) {
                    subName = getName.substring(0, getName.indexOf("L") - 1);
                    getName = subName;
                }

                if (getName.contains("italian") || getName.contains("japanese") || getName.contains("chinese") || getName.contains("non-english")) {
                    language = getName.split(" - ");
                    rytir.setName(language[0]);
                    if (language[1].equalsIgnoreCase("italian")) {
                        rytir.setLanguage("ITL");
                    } else if (language[1].equalsIgnoreCase("japanese")) {
                        rytir.setLanguage("JPN");
                    } else if (language[1].equalsIgnoreCase("chinese")) {
                        rytir.setLanguage("CHN");
                    } else if (language[1].equalsIgnoreCase("non-english")) {
                        rytir.setLanguage("NON-ENG");
                    }
                }

                if (getName.contains("lightly played") || getName.contains("played")) {
                    language = getName.split(" - ");
                    rytir.setName(language[0]);
                    if (language[1].equalsIgnoreCase("lightly played")) {
                        rytir.setCondition("LP");
                    } else if (language[1].equalsIgnoreCase("played")) {
                        rytir.setCondition("PL");
                    }
                }
            } else {
                Elements elements;
                if ((counter + 2) % 3 == 0) {
                    elements = e.select("td");
                    if (elements.size() > 0) {
                        subName = ((Element)elements.get(0)).text();
                        rytir.setEdition(subName);
                    }
                } else if ((counter + 1) % 3 == 0) {
                    elements = e.select("td");
                    if (elements.size() > 1) {
                        language = ((Element)elements.get(1)).text().split(regex);
                        rytir.setNumber(language[0]);
                    }

                    if (elements.size() > 2) {
                        language = ((Element)elements.get(2)).text().split(regex);
                        rytir.setPrice(language[0]);
                        rytir.setTradePrice(Float.parseFloat(language[0]) * 0.8F);
                    }

                    this.karty.add(rytir);
                }
            }
        }

    }
}
