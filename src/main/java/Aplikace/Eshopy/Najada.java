package Aplikace.Eshopy;

import java.io.IOException;
import java.util.Collection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import Aplikace.Eshopy.CompareEdition.CompareEdition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class Najada {
	
	private SimpleStringProperty name;
	private SimpleStringProperty edition;
	private SimpleStringProperty number;
	private SimpleStringProperty price;
	private boolean foil;
	private SimpleStringProperty condition;
	private SimpleStringProperty language;
	private float tradePrice;
	private ObservableList<Najada> karty = FXCollections.observableArrayList();


	public Najada () {
	}

	public ObservableList<Najada> getKarty() {
		return karty;
	}
	
	public String getName() {
		return name.get();
	}

	public String getEdition() {
		return edition.get();
	}

	public String getNumber() {
		return number.get();
	}

	public String getPrice() {
		return price.get();
	}

	public boolean isFoil() {
		return foil;
	}

	public String getCondition() {
		return condition.get();
	}

	public String getLanguage() {
		return language.get();
	}

	public float getTradePrice() {
		return tradePrice;
	}

	public int getResultNumber() {
		return karty.size();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public void setEdition(String edition) {
		this.edition = new SimpleStringProperty(edition);
	}

	public void setNumber(String number) {
		this.number = new SimpleStringProperty(number);
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

	public void findCardNajada (String findCard) throws IOException {
		final String url = "https://www.najada.cz/cz/kusovky-mtg/?Anchor=EShopSearchArticles&Search=" + findCard + "&Sender=Submit&MagicCardSet=-1#";
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
		Elements body = doc.select("table.tabArt "); //načtu vše, co je na stránce pod heslem <table> a atribut class="tabArt " a načtu do pole Elementů
		Najada najada = null;
		String textName = null; //pomocná třída pro parsování jména - Najada u některých karet nemá jméno
		
		for (Element e : body.select("tr")) {
			najada = new Najada ();
			Elements elements = e.select ("td");
			if (elements.size() > 0) { //iterujeme po sloupcích
				String text = elements.get(0).text(); //v prvním sloupci je jméno
				if (!text.equals("")) {    			  //pokud narazím na text, přiřadím mu jméno + načtu do pomocného Stringu
					textName = text;
					if (text.contains("Borderless")) {
						textName = text.substring(0, text.indexOf("("));
					}
					najada.setName(textName);
				} else {							  //pokud nenajdu jméno ale mezeru do jména přiřadím jméno z pomocného Stringu
					najada.setName(textName);
				}
			}
			if (elements.size() > 1) {
				String foiled = elements.get(1).text();
				najada.setFoil( !foiled.equalsIgnoreCase ("Ne" ));
			}
			if (elements.size() > 6) {
				String getEdition = elements.get(6).text();
				getEdition = CompareEdition.compare(getEdition);
				najada.setEdition(getEdition);
			}
			if (elements.size() > 7) {
				String getLanguage = elements.get(7).select("td img").attr("title");
				if(getLanguage.equalsIgnoreCase("Angličtina")) {
					najada.setLanguage("ENG");
				} else {
					najada.setLanguage(getLanguage);
				}
			}
			
			if (elements.size() > 8) {
				najada.setCondition(elements.get(8).text());
			}
			
			if (elements.size() > 9) {
				String[] text = elements.get(9).text().split(" Kč "); //částku rozdělím podle _Kč_ abych upravil na čísla
				String setPrice = text[0].replace(" ", ""); 			//načtu částku a mezi tisícema odstraním mezery
				najada.setPrice(setPrice);
				najada.setTradePrice(Float.parseFloat((setPrice))* 0.8f);
				if (text[1].contains("(")) { 				//počet kus ma Najáda v závorkách
					najada.setNumber(text[1].substring(1,(text[1].length()-1))); //vytěžím číslo mezi závorkama
				}
			}
			
			karty.add(najada);
		}
		if (karty.size() > 0) {
			karty.remove(0); //vymažu první záznam - tam se načtou samí NUll
		}
	}
}
