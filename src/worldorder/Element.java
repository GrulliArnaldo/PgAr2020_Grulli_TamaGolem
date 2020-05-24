package worldorder;
import java.util.ArrayList;
import java.util.Map;

import it.unibs.fp.mylib.InputDati;


public class Element {
	
	private static final String ELEMENTS_PRINT_FORMAT = "%d ---> %s";
	private static final String STONE_CHOICE = "Insert your choice: ";
	public static final String[] ELEMENT_NAMES = {
			"NORMAL",
			"FIRE",
			"WATER",
			"GRASS",
			"ELECTRIC",
			"ICE",
			"FIGHTING",
			"POISON",
			"GROUND",
			"FLYING",
			"PSYCHIC",
			"BUG",
			"ROCK",
			"GHOST",
			"DARK",
			"DRAGON",
			"STEEL",
			"FAIRY"};
	
	private String elementName;
	private ArrayList<Matchup> matchupList = new ArrayList<Matchup>();
	
	public Element(String elementName){
		this.elementName = elementName;
	}
	
	/**
	 * metodo che stampa la lista degli elementi di una mappa
	 * chiede il valore della chiave cercata 
	 * @param map la mappa del equilibrio
	 * @return l'elemento corrispondente
	 */
	public static Element getElement(Map<Integer, Element> map) {
		int scelta;
		
		for(int i = 0; i<map.size(); i++)
			System.out.println(String.format(ELEMENTS_PRINT_FORMAT, i, map.get(i).getElementName()));
		scelta = InputDati.leggiIntero(STONE_CHOICE, 0, map.size());

		return map.get(scelta);
	}


	public String getElementName(){
		return elementName;
	}


	public ArrayList<Matchup> getMatchupList() {
		return matchupList;
	}


	public void setMatchupList(ArrayList<Matchup> matchupList) {
		this.matchupList = matchupList;
	}


	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
}
