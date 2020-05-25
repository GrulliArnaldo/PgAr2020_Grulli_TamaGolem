package worldorder;

import java.util.ArrayList;
import java.util.Map;

public class GolemFight {
	
	private static final String FIGHT_HISTORY = "%nFIGHT HISTORY: ";
	private static final String GOLEM_B_LIFE = "GolemB life : ";
	private static final String GOLEM_A_LIFE = "GolemA life : ";
	private static final String ELEMENTS_SAME = "All elements are the same!";
	private static final String PLAYER_WINS = "%nPlayer %d wins!";
	private static final String GOLEM_DMG = "Golem%c has inflicted %d damage to Golem%c!";
	private static final String PLAYER_CHOICE = "Player %d, make your %d° choice: ";
	
	/**
	 * metodo generale della battaglia multipla
	 * @param tamaGolemCount numero di golem per giocatore
	 * @param tamaGolemLife il valore della vita iniziale dei golem
	 * @param stonesCount il numero delle pietre per golem
	 * @param map la mappa degli elementi
	 * @param stonesStack la sacca iniziale di pietre disponibili da scegliere
	 * @return un oggetto di tipo Result con:
	 * 1. la sacca delle pietre aggiornata
	 * 2. il vincitore finale
	 */
	public static Result golemMultipleFights(int tamaGolemCount, int tamaGolemLife, int stonesCount, Map<Integer, Element> map, int[] stonesStack) {
		
		//inizializzo le variabili
		int playerOneGolems = tamaGolemCount;
		int playerTwoGolems = tamaGolemCount;
		int battleCount = 1;
		TamaGolem golemA = new TamaGolem(tamaGolemLife);
		TamaGolem golemB = new TamaGolem(tamaGolemLife);
		//stampo la videata iniziale della battaglia e incremento già il count per la prossima stampa
		Result.printRemainingGolems(battleCount, playerOneGolems, playerTwoGolems);
		battleCount++;
		//svolgo la prima battaglia e salvo il risultato
		Result result = GolemFight.golemSingleFight(golemA, golemB, stonesCount, map, stonesStack);
		
		do {
			//controllo chi è il vincitore 
			//imposto la vita rimanenete del golem del vincitore
			//decremento il numero dei golem del giocatore sconfitto
			//stampo di nuova la videata e incremento il counter
			//e svolgo un'altra battaglia
			if (result.getWinnerIndex() == 1) {
				playerTwoGolems--;
				if (playerTwoGolems>0) {
					Result.printRemainingGolems(battleCount, playerOneGolems, playerTwoGolems);
					battleCount++;
					golemA = new TamaGolem(result.getWinnerLifePoints());
					golemB = new TamaGolem(tamaGolemLife);
					result = GolemFight.golemSingleFight(golemA, golemB, stonesCount, map, result.getStonesStack());
				}
			}
			else if (result.getWinnerIndex() == 2) {
				playerOneGolems--;
				if (playerOneGolems>0) {
					Result.printRemainingGolems(battleCount, playerOneGolems, playerTwoGolems);
					battleCount++;
					golemB = new TamaGolem(result.getWinnerLifePoints());
					golemA = new TamaGolem(tamaGolemLife);
					result = GolemFight.golemSingleFight(golemA, golemB, stonesCount, map, result.getStonesStack());
				}
			}
		}while (playerOneGolems > 0 && playerTwoGolems > 0);
		
		//resituisco il risultato finale in base a chi ha ancora dei golem
		if (playerOneGolems > 0)
			return new Result(null, result.getStonesStack(), 1, 0);
		else
			return new Result(null, result.getStonesStack(), 2, 0);
			
	}
	
	/**
	 * metodo generale per far combatter 2 golem
	 * @param golemA primo golem del player 1
	 * @param golemB secondo golem del player 2
	 * @param stonesCount numero di pietre per golem
	 * @param map la mappa con già caricata la matrice
	 * @param stonesStack l'array con caricate le pietre disponibili per la scelta
	 * @return 
	 * 1. null nel caso in cui gli elementi sono uguali
	 * 2 un oggetto di tipo Result con caricate le informazioni del nuovo array di pietre, il vincitore, e 
	 * i punti vita del golem vincitore
	 */
	public static Result golemSingleFight(TamaGolem golemA, TamaGolem golemB, int stonesCount, Map<Integer, Element> map, int[] stonesStack) {
		
		//inizializzo le variabili
		Result result = new Result(null, stonesStack, 0, 0);
		ArrayList<TamaGolem> golemList = new ArrayList<TamaGolem>();
		golemList.add(golemA); //0
		golemList.add(golemB); //1
		
		//chiedo in input che pietre mettere ad ogni golem
		for(int i = 0;i<stonesCount;i++) {
			for(int j=0; j<2; j++) {
				System.out.println(String.format(PLAYER_CHOICE, j+1, i+1));
				result = Element.getElement(map, result.getStonesStack());
				golemList.get(j).getFightingElements().add(result.getElement());
			}
		}
		
		//controllo se negli elementi "target" del primo golem c'è l'elemento 'i' del secondo golem
		//controllo se negli elementi "target" del secondo golem c'è l'elemento 'i' del primo golem
		int i = 0;
		boolean lifePointsChange = false;
		System.out.println(String.format(FIGHT_HISTORY));
		do {
			for (int k = 0; k<2; k++) {
				for(int j = 0; j<golemList.get(k).getFightingElements().get(i).getMatchupList().size(); j++)
					if(golemList.get(k).getFightingElements().get(i).getMatchupList().get(j).getWinningElement().getElementName().equals(golemList.get(Result.getTheOverOne(k)).getFightingElements().get(i).getElementName())) {
						golemList.get(Result.getTheOverOne(k)).setLifePoints(golemList.get(Result.getTheOverOne(k)).getLifePoints() - golemList.get(k).getFightingElements().get(i).getMatchupList().get(j).getMatchupPower());
						lifePointsChange = true;
						System.out.println(String.format(GOLEM_DMG, Result.getRelativeLetter(k), golemList.get(k).getFightingElements().get(i).getMatchupList().get(j).getMatchupPower(), Result.getRelativeLetter(Result.getTheOverOne(k))));
						System.out.println(GOLEM_A_LIFE + golemList.get(0).getLifePoints());
						System.out.println(GOLEM_B_LIFE + golemList.get(1).getLifePoints());
					}
			}
			
			//tengo conto del fatto che gli elementi potrebbero essere uguali dopo un ciclo completo guardando se le vite dei golem sono state variate
			if (i == stonesCount-1) {
				if (lifePointsChange == false)
					break;
				else
					i = 0;
			}
			else
				i++;
			
		}while(golemList.get(0).getLifePoints()>0 && golemList.get(1).getLifePoints()>0);
		
		if (lifePointsChange) {
			int winner = Result.getWinner(golemList);
			System.out.println(String.format(PLAYER_WINS, winner+1));
			return new Result(null, result.getStonesStack(), winner+1, golemList.get(winner).getLifePoints());
		}
		else
			System.out.println(ELEMENTS_SAME);
		return null;
	}
}
