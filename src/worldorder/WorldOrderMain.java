package worldorder;

import java.util.Arrays;
import java.util.Map;

import it.unibs.fp.mylib.InputDati;

public class WorldOrderMain {
	
	private static final String GAME_CLOSED = "Game successfully closed!";
	private static final String FINAL_MESSAGE = "Player %d wins is the final winner!";
	private static final String EQUILIBRIUM_REVEALEAD = "Equilibrium revealead: ";
	private static final String ASTERISCHI = "**************************************************************";
	private static final String PLAY_AGAIN = "Would you like to play again?";
	private static final String WELCOME = "WELCOME TO THE TAMAGOLEM, THE GAME!!!";


	public static void main(String[] args) {
		
		System.out.println(WELCOME);
		boolean anotherGame;
		
		do {
		int elementNumber = InputDati.leggiIntero("How many elements?", 4, Element.ELEMENT_NAMES.length);
		int[][] matrix = Equilibrium.getEquilibriumMatrix(elementNumber);
		Map<Integer, Element> map = Equilibrium.getRandomElementMap(matrix);
		
		int stonesCount = (int)Math.ceil((elementNumber + 1) / 3 + 1);
		int tamaGolemCount = (int)Math.ceil((elementNumber - 2) * (elementNumber - 1) / (2 * stonesCount));
		int[] stonesStack = new int[elementNumber];
		Arrays.fill(stonesStack, (int)Math.ceil(2 * stonesCount * tamaGolemCount / elementNumber));
		int tamaGolemLife = Equilibrium.getMatrixMax(matrix);
		
		int result = GolemFight.golemMultipleFights(tamaGolemCount, tamaGolemLife, stonesCount, map);
		
		System.out.println(String.format(FINAL_MESSAGE, result));
		
		System.out.println(ASTERISCHI);
		System.out.println(EQUILIBRIUM_REVEALEAD);
		for(int i = 0; i<map.size();i++) {
			System.out.println("###Element name: " + map.get(i).getElementName());
			for (int j=0;j<map.get(i).getMatchupList().size();j++)
				System.out.println(String.format("%d° Power: >%d< Vs--> %s",(j+1),  map.get(i).getMatchupList().get(j).getMatchupPower(), map.get(i).getMatchupList().get(j).getWinningElement().getElementName()));
		}
		System.out.println(ASTERISCHI);
		
		anotherGame = InputDati.yesOrNo(PLAY_AGAIN);
		}while (anotherGame);
		
		
		System.out.println(GAME_CLOSED);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
