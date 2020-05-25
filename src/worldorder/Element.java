package worldorder;
import java.util.ArrayList;
import java.util.Map;

import it.unibs.fp.mylib.InputDati;


public class Element {
	
	private static final String STONES_FINISHED = String.format(">>>All stones of the chosen element are finished!%nChoose another element: ");
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
	 * @param stonesStack la raccolta di pietre da aggiornare
	 * @return un oggetto di tipo result con:
	 * 1. l'element scelto
	 * 2. la raccolta di pietre aggiornata
	 * 3. 0
	 * 4. 0
	 */
	public static Result getElement(Map<Integer, Element> map, int[] stonesStack) {
		int scelta;
		
		do {
			scelta = InputDati.leggiIntero(STONE_CHOICE, 0, map.size()-1);
			if (stonesStack[scelta] < 0)
				System.out.println(STONES_FINISHED);
		}while(stonesStack[scelta] < 0);
		
		stonesStack[scelta]--;
		return new Result(map.get(scelta), stonesStack, 0, 0);
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
