package Aplikace.Eshopy;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import Aplikace.Eshopy.CompareEdition.CompareEdition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class Rishada {
	
	private SimpleStringProperty name;
	private SimpleStringProperty edition;
	private SimpleStringProperty number;
	private SimpleStringProperty price;
	private boolean foil;
	private SimpleStringProperty condition;
	private SimpleStringProperty language; //nenašel jsem non English kartu v poolu
	private float tradePrice;
	private ObservableList<Rishada> karty = FXCollections.observableArrayList();

	public Rishada () {
	}

	public ObservableList<Rishada> getKarty() {
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

	public void setNumber(String availability) {
		this.number = new SimpleStringProperty(availability);
	}

	public void setPrice(String price) {
		this.price = new SimpleStringProperty(price);
	}
	
	public void setCondition (String condition) {
		this.condition = new SimpleStringProperty(condition);
	}
	
	public void setLanguage (String language) {
		this.language = new SimpleStringProperty(language);
	}
	
	public void setTradePrice (float tradePrice) {
		this.tradePrice = tradePrice;
	}

	public void setFoil(boolean foil) {
		this.foil = foil;
	}

	public void findCardRishada (String findCard) throws IOException {
		final String url = "http://rishada.cz/kusovky/vysledky-hledani?searchtype=basic&xxwhichpage=1&xxcardname=" + findCard
	  			  + "&xxedition=1000000&xxpagesize=50&search=Vyhledat";
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
		Elements body = doc.select("table.buytable"); //načtu vše, co je na stránce pod heslem <table> a atribut class="tabArt " a načtu do pole Elementů
		Rishada rishada = null;
		
		for (Element e : body.select("tr")) {
			rishada = new Rishada();
			rishada.setLanguage("ENG");
			Elements elements = e.select("td");  
			Elements elements2 = elements.select("a[title] img");  //elemnty pro hledání Foil karet
			String getFoil = elements2.attr("alt");
			
			if (getFoil.equalsIgnoreCase("Foil")) {
				rishada.setFoil(true);
			} else {
				rishada.setFoil(false);
			}
			if (elements.size() > 0) {
				rishada.setName(elements.get(0).text());
			}
			if (elements.size() > 1) {
				String getEdition = elements.get(1).text();
				getEdition = CompareEdition.compare(getEdition);
				rishada.setEdition(getEdition);
			}
			if (elements.size() > 5) {
				String condition = elements.get(5).text();
				//rishada.setCondition(elements.get(5).text());
				if (condition.contains("(")) {
					String substringCondition = condition.substring(condition.indexOf("(")+1, condition.length()-1);
					rishada.setCondition(substringCondition);
				} else {
					if (condition.contains("Light played")) {
						rishada.setCondition("LP");
					}
					if (condition.contains("Near Mint")) {
						rishada.setCondition("NM");
					}
				}
			}
			if (elements.size() > 7) {
				rishada.setPrice(elements.get(7).text().replace(" Kč", ""));
				if (!rishada.getPrice().contains("Cena")) { //první řádek nemá číslo ale String = cena; musím ho odfiltrovat
				rishada.setTradePrice(Float.parseFloat(rishada.getPrice())*0.8f);
				}
			}
			
			if (elements.size() > 8) {
				rishada.setNumber(elements.get(8).text());
			}
			karty.add(rishada);
			}

		if (karty.size() > 0) {
			karty.remove(0);
			}
		}
}
