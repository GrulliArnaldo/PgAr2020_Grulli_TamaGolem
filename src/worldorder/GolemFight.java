package worldorder;

import java.util.Map;

public class GolemFight {
	
	private static final String PLAYER_1_CHOICE = "Player 1, make your %d° choice: ";
	private static final String PLAYER_2_CHOICE = "Player 2, make your %d° choice: ";
	
	
	public static int golemMultipleFights(int tamaGolemCount, int tamaGolemLife, int stonesCount, Map<Integer, Element> map) {
		int playerOneGolems = tamaGolemCount;
		int playerTwoGolems = tamaGolemCount;
		int[] result = new int[2];
		TamaGolem golemA = new TamaGolem(tamaGolemLife);
		TamaGolem golemB = new TamaGolem(tamaGolemLife);
		
		do {
			result = GolemFight.golemSingleFight(golemA, golemB, stonesCount, map);
			if (result[0] == 1) {
				playerTwoGolems--;
				golemA = new TamaGolem(result[1]);
				golemB = new TamaGolem(tamaGolemLife);
			}
			else if (result[0] == 2) {
				playerOneGolems--;
				golemB = new TamaGolem(result[1]);
				golemA = new TamaGolem(tamaGolemLife);
			}

		}while (playerOneGolems > 0 && playerTwoGolems > 0);
		
		if (playerOneGolems > 0)
			return 1;
		else
			return 2;
			
	}
	
	/**
	 * metodo per la battaglia singola di 2 golem "non inizializzati"
	 * @param golemA 
	 * @param golemB
	 * @param stonesCount
	 * @param map
	 * @return restituisce 1 se vince il player 1, 2 se vince il player 2, null se gli elementi sono uguali
	 */
	public static int[] golemSingleFight(TamaGolem golemA, TamaGolem golemB, int stonesCount, Map<Integer, Element> map) {
		int[] result = new int[2];
		
		for(int i = 0;i<stonesCount;i++) {
			System.out.println(String.format(PLAYER_1_CHOICE, i+1));
			golemA.getFightingElements().add(Element.getElement(map));
			System.out.println(String.format(PLAYER_2_CHOICE, i+1));
			golemB.getFightingElements().add(Element.getElement(map));

		}
		
		int i = 0;
		boolean lifePointsChange = false;
		do {
			for(int j = 0; j<golemA.getFightingElements().get(i).getMatchupList().size(); j++)
				if(golemA.getFightingElements().get(i).getMatchupList().get(j).getWinningElement().getElementName().equals(golemB.getFightingElements().get(i).getElementName())) {
					golemB.setLifePoints(golemB.getLifePoints() - golemA.getFightingElements().get(i).getMatchupList().get(j).getMatchupPower());
					lifePointsChange = true;
					System.out.println(String.format("GolemA has inflicted %d to GolemB!", golemA.getFightingElements().get(i).getMatchupList().get(j).getMatchupPower()));
					System.out.println("Golem A life : " + golemA.getLifePoints());
					System.out.println("Golem B life : " + golemB.getLifePoints());
				}
			
			for(int j = 0; j<golemB.getFightingElements().get(i).getMatchupList().size();j++)
				if(golemB.getFightingElements().get(i).getMatchupList().get(j).getWinningElement().getElementName().equals(golemA.getFightingElements().get(i).getElementName())) {
					golemA.setLifePoints(golemA.getLifePoints() - golemB.getFightingElements().get(i).getMatchupList().get(j).getMatchupPower());
					lifePointsChange = true;
					System.out.println(String.format("GolemB has inflicted %d to GolemA!", golemB.getFightingElements().get(i).getMatchupList().get(j).getMatchupPower()));
					System.out.println("Golem A life : " + golemA.getLifePoints());
					System.out.println("Golem B life : " + golemB.getLifePoints());
				}
			
			if (i == stonesCount-1) {
				if (lifePointsChange == false)
					break;
				else
					i = 0;
			}
			else
				i++;
		}while(golemA.getLifePoints()>0 && golemB.getLifePoints()>0);
		
		if (lifePointsChange) {
			if (golemA.getLifePoints() > 0) {
				System.out.println("Player 1 wins!");
				result[0] = 1;
				result[1] = golemA.getLifePoints();
				return result;
			}
			else {
				System.out.println("Player 2 wins!");
				result[0] = 2;
				result[1] = golemB.getLifePoints();
				return result;
			}
		}
		else
			System.out.println("All elements are the same!");
		return null;
	}
}
