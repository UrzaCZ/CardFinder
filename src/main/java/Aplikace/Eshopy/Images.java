package Aplikace.Eshopy;

import java.io.IOException;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Images {

	private HashMap<String, String> edition = new HashMap<String, String>();
	
	public Images () {
	}
	
	public void getEditionMap() throws IOException { //naplnění HashMapy jako seznamu edic - Klíč je dlouhý název edice - value je zkratka
		String url = "http://cernyrytir.cz/index.php3?akce=3";
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
		Element body = doc.selectFirst("table.kusovkytext select");
		for (Element e : body.select("option")) {
			if (!e.attr("value").equalsIgnoreCase("standard") && !e.attr("value").equalsIgnoreCase("pioneer") && 
				!e.attr("value").equalsIgnoreCase("modern") && !e.attr("value").equalsIgnoreCase("libovolna")
				&& !e.text().contains("--------------")) {	//vyfiltruju blbosti co tam rytíř má
				edition.put(e.text(), e.attr("value"));
			}
		}
	}
			
	public String parseURLImage(String selectedCardName, String selctedCardEdition) throws IOException {
		String searchedEdition = "";
		if (edition.size() == 0 ) {	//pokud se mi nepodařilo poprvé načíst mapu, zkusím to i tady
			getEditionMap();
		}
		if ((selctedCardEdition.equalsIgnoreCase("Masters sety")) || (selctedCardEdition.equalsIgnoreCase("Masters Series"))) {
			String rishadaName = "";
			if (selectedCardName.contains(" ")) {
				rishadaName = selectedCardName.replace(" ", "%20");
			}
			String urlImage = "http://rishada.cz/big/Masters%20sety/" + rishadaName + "%20(1).jpg";
			return urlImage;
		}
		if (selctedCardEdition.equalsIgnoreCase("Whitebordered Basic sets")) {
			String rishadaName = "";
			if (selectedCardName.contains(" ")) {
				rishadaName = selectedCardName.replace(" ", "%20");
			}
			String urlImage = "http://rishada.cz/big/Base39/" + rishadaName + "%20(1).jpg";
			return urlImage;
		}
		if ((selctedCardEdition.equalsIgnoreCase("Zendikar Expedition")) || selctedCardEdition.equalsIgnoreCase("Zendikar Expeditions")) {
			String rishadaName = "";
			if (selectedCardName.contains(" ")) {
				rishadaName = selectedCardName.replace(" ", "%20");
			}
			String urlImage = "http://rishada.cz/big/Zendikar%20Expedition/" + rishadaName + "%20(1).jpg";
			return urlImage;
		}
		if (selctedCardEdition.equalsIgnoreCase("Blackbordered Basic Sets")) {
			String rishadaName = "";
			if (selectedCardName.contains(" ")) {
				rishadaName = selectedCardName.replace(" ", "%20");
			}
			String urlImage = "http://rishada.cz/big/BBS/" + rishadaName + "%20(1).jpg";
			return urlImage;
		}
		if (selctedCardEdition.equalsIgnoreCase("Duel Decky")) {
			String rishadaName = "";
			if (selectedCardName.contains(" ")) {
				rishadaName = selectedCardName.replace(" ", "%20");
			}
			String urlImage = "http://rishada.cz/big/Duel%20Decky/" + rishadaName + "%20(1).jpg";
			return urlImage;
		}
		if (edition.containsKey(selctedCardEdition)) {  //porovnám jméno edice s klíčem v mapě, do stringu uložím zkratku edice- podle ní hledám obrázek
			searchedEdition = edition.get(selctedCardEdition);
		} else {
			return null;
		}
		String url = "http://cernyrytir.cz/index.php3?akce=3&limit=0&jmenokarty=" + selectedCardName + "&edice_magic=" + searchedEdition + "&poczob=30&"
	  			  + "foil=A&triditpodle=ceny&hledej_pouze_magic=1&submit=Vyhledej";
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
		Elements body = doc.select("table.kusovkytext[cellpadding=3]").select("a[href$=\".jpg\"]");
		String attr = body.attr("href");
		if (!attr.contentEquals("")) {
			String urlImage = "http://cernyrytir.cz" + attr;
			return urlImage;
		} else {		//Pokud se mi nepodařilo na ČR najít obrázek, vracím null
			return null;
		}

	}
}