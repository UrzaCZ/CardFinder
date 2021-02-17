package Aplikace.Eshopy.CompareEdition;

public class CompareEdition {

	public static String compare(String edition) {
		if (edition.equalsIgnoreCase("Fifth Edition")) {
			return "5th Edition";
		}
		if (edition.equalsIgnoreCase("Fourth Edition")) {
			return "4th Edition";
		}
		if (edition.equalsIgnoreCase("Judgement")) {
			return "Judgment";
		}
		if (edition.equalsIgnoreCase("Classic Sixth Edition")) {
			return "6th Edition";
		}
		if (edition.equalsIgnoreCase("Seventh Edition")) {
			return "7th Edition";
		}
		if (edition.equalsIgnoreCase("Eighth Edition")) {
			return "8th Edition";
		}
		if (edition.equalsIgnoreCase("Ravnica: City of Guilds")) {
			return "Ravnica";
		}
		if (edition.equalsIgnoreCase("Double Masters: Extras")) {
			return "Double Masters Extras";
		}
		if (edition.equalsIgnoreCase("Magic 2014 Core Set")) {
			return "Magic 2014";
		}
		if (edition.equalsIgnoreCase("Magic 2015 Core Set")) {
			return "Magic 2015";
		}
		if (edition.equalsIgnoreCase("Planechase 2012 Edition")) {
			return "Planechase 2012";
		}
		if (edition.equalsIgnoreCase("Ikoria Commander")) {
			return "Commander 2020";
		}
		if (edition.equalsIgnoreCase("Ikoria: Lair of Behemoths")) {
			return "Ikoria";
		}
		if (edition.contains("Revised")) {
			return "3rd Edition";
		}
		if (edition.contains("Timeshifted")) {
			return "Timeshifted	";
		}
		if (edition.contains("PDS:")) {
			return edition.replace("PDS:", "PD:");
		}
		if (edition.contains("- Extras")) {
			return edition.replace("- ", "");
		}
		if (edition.contains("Saga")) {
			return "Urza´s Saga";
		}
		if (edition.equalsIgnoreCase("Modern Masters 2015 Edition")) {
			return "Modern Masters 2015";
		}
		if (edition.equalsIgnoreCase("Zendikar Rising Expeditions")) {
			return "ZNR Expeditions";
		}
		if (edition.equalsIgnoreCase("Zendikar Rising Commander")) {
			return "Commander ZNR";
		}
		if (edition.equalsIgnoreCase("Modern Masters 2017 Edition")) {
			return "Modern Masters 2017";
		}
		if (edition.contains("Duel Decks:")) {
			return edition.replace("Duel Decks", "DD");
		}
		if (edition.contains("Duel Decks Anthology,")) {
			return edition.substring(0, edition.indexOf(","));
		}
		if (edition.equalsIgnoreCase("Commander 2013 Edition")) {
			return "Commander 2013";
		} else {
			
		return edition;
		}
	}
}
