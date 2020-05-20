package worldorder;

import java.util.HashMap;

import java.util.Map;

public class ElementGraph 
{
	
	private Map<Integer, Element> Elements = new HashMap<Integer, Element>();
	{
		
	}

	/**
	 * metodo di utilizzo generale che aiuta a rendere casuale certe scelte
	 * @return restituisce true o false in base a ció che dá la funzione random
	 */
	public static boolean coinToss()
	{
		if(Math.random() >= 0.5)return true;
		else return false;
	}
	/**
	 * metodo di calcolo casuale di quale elemento ha il vantaggio nello scontro
	 * @param i due elementi da prendere in considerazione
	 * @return restituisce l'elemento vincitore
	 */
	public static Element whoWins(Element element1, Element element2) 
	{
		Element winner = element1;
		if(coinToss())return winner;
		else winner = element2;
		return winner;
	}
	
	public static int winnerPower(Element winner) 
	{
		int winnerPower = 0;
		winnerPower = (int)Math.random()*(5-1)+ 1;
		return winnerPower;
	}
}