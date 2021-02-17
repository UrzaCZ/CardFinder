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

public class Tolarie {
	
	private SimpleStringProperty name;
	private SimpleStringProperty edition;
	private SimpleStringProperty number;
	private SimpleStringProperty price;
	private boolean foil;
	private SimpleStringProperty condition;
	private SimpleStringProperty language;
	private float tradePrice;
	private ObservableList<Tolarie> karty = FXCollections.observableArrayList(); //Arraylist pro uchování karet = Objektů z tolárky co jsme našli


	public Tolarie () {
	}

	public ObservableList<Tolarie> getKarty() {
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
	
	private Tolarie(String name, String edition, String number, String price, boolean foil, String condition, String language, float tradePrice) {
		this.name = new SimpleStringProperty(name);
		this.edition = new SimpleStringProperty(edition);
		this.number = new SimpleStringProperty(number);
		this.price = new SimpleStringProperty(price);
		this.tradePrice = tradePrice;
		this.foil = foil;
		this.condition = new SimpleStringProperty(condition);
		this.language = new SimpleStringProperty(language);
		
	}
	
	public void findCardTolarie(String findCard) throws IOException {
		if (findCard.contains("´")) {
			String replaced = findCard.replace("´", "'");
			findCard =  replaced;
		}
		final String url = "https://tolarie.cz/koupit_karty/?name=" + findCard;
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
		Elements body = doc.select("table.kusovky"); //načtu vše, co je na stránce pod heslem <table> a atribut class="kusovky" a načtu do pole Elementů
		String regex = "\\D"; //pro pozdější úpravu vytěžených dat - Tolárka má k číslům i text
		boolean setFoil;
		String setCondition = null; 
        for (Element e : body.select("tbody")) { //projetí všech elementů co začnají na <tbody>
    		String setLanguage = "ENG";
        	String setName = e.select("td.td_name span.product_name").text();//pod <td class="td_name"> najdu atribut <a href> ve kterém načtu text v příkazu title=
        	if (setName.equals("")) { //u některých karet nemá obrázek. Pokud nenajdu název z obrázku, beru ho z textu 
        		setName = e.select("td.td_name a.product_name").text();
        	}
        	if (setName.contains("(")) {  //pokud je ve jméně závorka, zjistíme jestli tam je jinej jazyk, a odstraníme ji = příznaky parsujeme jinde
        		if (setName.contains("non-english")) {
        			setLanguage = "NON-ENG";
        		}
        		setName = setName.substring(0, (setName.indexOf("("))-1);
        	}
        	Elements elements = e.select("td.td_priznak a"); //musím načíst elementy na příznaku <td class="td_priznak"> <a 
        	String content = elements.attr("data-tooltip"); //přečtu co je napsáno v <a data-tooltip=
    		
        	if (content.equalsIgnoreCase("Foil")) { //pokud je tam napsáno foil, přehodím Foil na true, jinak false
        		setFoil = true;
        	} else {							
        		setFoil = false;					
        	}
        	if (content.equalsIgnoreCase("Played")) { //pokud je příznak že karta je hraná, tak bude mít condition
        		setCondition = "PL";			
        	} else if (content.equalsIgnoreCase("Slightly played")) {
        		setCondition = "SP";				
        	} else {
        		setCondition = "NM";		
        	}
        	String setEdition = e.select("td.td_edice").text(); //pod <td class="td_edice"> načtu text edice
        	setEdition = CompareEdition.compare(setEdition);
        	String setAvailability = e.select("td.td_name div.availability").text(); //pod <td class="td_name"> najdu atribut <div class="availability"> a načtu počet
        	String setPrice = e.select("td.td_price").text(); //pod <td class="td_edice"> načtu text ceny
        	if (setPrice.equals("")) {
        		setPrice = "0";
        	} else if (setPrice.contains("Kč")) {
        		setPrice = setPrice.replace(" Kč", "");
        	}
        	float setTradePrice = ((Float.parseFloat(setPrice)*0.8f));
        	String[] setNumber = setAvailability.split(regex); // upravím výsledek "skladem (počet) kusů" pouze na počet 
        	String[] setValue = setPrice.split(regex); //upravím výsledek "(číslo) korun" pouze na číslo
        	Tolarie tol = new Tolarie (setName, setEdition, setNumber[setNumber.length-1], setValue[setValue.length-1], 
        			setFoil, setCondition, setLanguage, setTradePrice); //nalezenou kartu načtu jako objekt
        	karty.add(tol); //přidáme do listu, abysme to mohli vypsat
        }
	}
}
