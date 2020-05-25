package worldorder;

import java.util.ArrayList;

public class Result {
	public static final String BATTLE_COUNT = "Battle %d, fight!";

	private Element element;
	private int[] stonesStack;
	private int winnerIndex;
	private int winnerLifePoints;
	
	public Result(Element element, int[] stonesStack, int winnerIndex, int winnerLifePoints) {
		this.element = element;
		this.stonesStack = stonesStack;
		this.winnerIndex = winnerIndex;
		this.winnerLifePoints = winnerLifePoints;
	}
	
	/**
	 * restituisce l'altro tra 0 e 1
	 * @param n deve essere 0 oppure 1
	 * @return 
	 * 1. 0 se si inserisce 1
	 * 2. 1 se si inserisce 0
	 * 3. -1 se si inserisce qualcos'altro
	 */
	public static int getTheOverOne(int n) {
		if (n==1)
			return 0;
		if (n==0)
			return 1;
		return -1;
	}
	
	/**
	 * resituisce il char relativo a 0 oppure 1
	 * @param n intero tra 0 e 1 
	 * @return
	 * 1. 'A' se si inserisce 0
	 * 2. 'B' se si inserisce 1
	 * 3. 'Z' negli altri casi
	 */
	public static char getRelativeLetter(int n) {
		if (n==1)
			return 'B';
		if (n==0)
			return 'A';
		return 'Z';
	}
	
	/**
	 * guarda quale golem ha ancora dei punti vita
	 * @param golemList la lista con 2 golem
	 * @return
	 * 1. 0 se il primo golem ha ancora dei punti vita
	 * 2. 1 negli altri casi
	 */
	public static int getWinner(ArrayList<TamaGolem> golemList) {
		if (golemList.get(0).getLifePoints() > 0)
			return 0;
		else
			return 1;
	}
	
	/**
	 * metodo di stampa delle info delle battaglie
	 * @param battleCount il numero della battaglia
	 * @param playerOneGolems il numero dei golem del player 1
	 * @param playerTwoGolems il numero dei golem del player 2
	 */
	public static void printRemainingGolems(int battleCount, int playerOneGolems, int playerTwoGolems) {
		System.out.println(WorldOrderMain.ASTERISCHI);
		System.out.println(String.format(BATTLE_COUNT, battleCount));
		System.out.println("Player 1 remaining golems: " + playerOneGolems);
		System.out.println("Player 2 remaining golems: " + playerTwoGolems);
		System.out.println(WorldOrderMain.ASTERISCHI);
	}

	//GETTERS AND SETTERS
	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public int[] getStonesStack() {
		return stonesStack;
	}

	public void setStonesStack(int[] stonesStack) {
		this.stonesStack = stonesStack;
	}

	public int getWinnerIndex() {
		return winnerIndex;
	}

	public void setWinnerIndex(int winnerIndex) {
		this.winnerIndex = winnerIndex;
	}

	public int getWinnerLifePoints() {
		return winnerLifePoints;
	}

	public void setWinnerLifePoints(int winnerLifePoints) {
		this.winnerLifePoints = winnerLifePoints;
	}
}
