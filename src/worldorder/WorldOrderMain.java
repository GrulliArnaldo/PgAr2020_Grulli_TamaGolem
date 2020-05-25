package worldorder;

import java.util.Arrays;
import java.util.Map;

import it.unibs.fp.mylib.InputDati;

public class WorldOrderMain {
	
	private static final String ELEMENTS_POWERS = "%d° Power: >%d< Vs--> %s";
	private static final String HOW_MANY_ELEMENTS = "With how many elements would you like to play: ";
	private static final String GAME_CLOSED = "Game successfully closed!";
	private static final String FINAL_MESSAGE = "Player %d is the final winner!";
	private static final String EQUILIBRIUM_REVEALEAD = "Equilibrium revealead: ";
	public static final String ASTERISCHI = "**************************************************************";
	private static final String PLAY_AGAIN = "Would you like to play again?";
	private static final String WELCOME = "WELCOME TO TAMAGOLEM, THE GAME!!!";
	private static final String ELEMENTS_PRINT_FORMAT = "%d ---> %s";


	public static void main(String[] args) {
		
		System.out.println(WELCOME);
		boolean anotherGame;

		do {
			//inizializzo le variabili 
			int elementNumber = InputDati.leggiIntero(HOW_MANY_ELEMENTS, 4, Element.ELEMENT_NAMES.length);
			int stonesCount = (int)Math.ceil((elementNumber + 1) / 3 + 1);
			int tamaGolemCount = (int)Math.ceil((elementNumber - 2) * (elementNumber - 1) / (2 * stonesCount));
			int[] stonesStack = new int[elementNumber];
			Arrays.fill(stonesStack, (int)Math.ceil(3 * stonesCount * tamaGolemCount / elementNumber));
			
			//creo la matrice e la mappa di conseguenza
			int[][] matrix = Equilibrium.getEquilibriumMatrix(elementNumber);
			Map<Integer, Element> map = Equilibrium.getRandomElementMap(matrix);
			int tamaGolemLife = Equilibrium.getMatrixMax(matrix);
			
			//stampo le relative chiavi corrispondenti ai elementi
			for(int i = 0; i<map.size(); i++)
				System.out.println(String.format(ELEMENTS_PRINT_FORMAT, i, map.get(i).getElementName()));
			
			// stampa finale
			Result result = GolemFight.golemMultipleFights(tamaGolemCount, tamaGolemLife, stonesCount, map, stonesStack);
			System.out.println(ASTERISCHI);
			System.out.println(String.format(FINAL_MESSAGE, result.getWinnerIndex()));
			System.out.println(ASTERISCHI);
			System.out.println(EQUILIBRIUM_REVEALEAD);
			for(int i = 0; i<map.size();i++) {
				System.out.println("###Element name: " + map.get(i).getElementName());
				for (int j=0;j<map.get(i).getMatchupList().size();j++)
					System.out.println(String.format(ELEMENTS_POWERS,(j+1),  map.get(i).getMatchupList().get(j).getMatchupPower(), map.get(i).getMatchupList().get(j).getWinningElement().getElementName()));
			}
			System.out.println(ASTERISCHI);
			
			//richiesta altra partita
			anotherGame = InputDati.yesOrNo(PLAY_AGAIN);
		}while (anotherGame);
		
		//stampa uscita dal ciclo
		System.out.println(GAME_CLOSED);
	}
}
