package worldorder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Equilibrium {
	
	private static final String[] ELEMENT_NAMES = {
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
	
	/**
	 * dato un numero di nodi(nel nostro caso elementi)
	 * crea una matrice triangolare alta, con i valori sulla diagonale e nel triangolo basso azzerati
	 * la matrice viene compilata in modo tale che sottraendole la sua trasposta, il risultato sia una matrice antisimmetrica
	 * avendo la matrice antisimmetrica, si può costruire l'equilibrio degli archi unidirezionali 
	 * il metodo controlla anche che non ci siano valori uguali a 0 a parte che sulla diagonale principale
	 * e sulla parte triangolare basse
	 * @param elementNumber il numero di nodi(elementi) con cui creare la matrice 
	 * @return restituisce una matrice di tipo int
	 */
	public static int[][] getHighMatrix(int elementNumber){
		
		//dichiaro tutte le variabili e riempio tutta la matrice con 0
		int columnSum = 0;
		int remainingNumbers = 0;
		int switchValues = 0;
		Random random = new Random();
		int[][] equilibrium = new int[elementNumber][elementNumber];
		for (int i=0;i<elementNumber;i++)
			Arrays.fill(equilibrium[i], 0);
		
		//inizializzo la prima riga
		equilibrium[0][1] = random.nextInt(elementNumber - (int)(elementNumber/2)+1)+(int)(elementNumber/2);
		remainingNumbers = equilibrium.length-2;
		int[] array = Arrays.copyOf(Equilibrium.breakUpInteger(-equilibrium[0][1], remainingNumbers, elementNumber), remainingNumbers);
		for(int i=2, k = 0; i<equilibrium.length;i++)
			equilibrium[0][i] = array[k++];
		
		//calcolo tutti i valori seguenti, tranne l'ultimo
		for(int i=1;i<equilibrium.length-2; i++) {
			columnSum = Equilibrium.getSumOfAMatrixColumn(equilibrium, i);
			remainingNumbers = equilibrium.length-i-1;
			array = Arrays.copyOf(Equilibrium.breakUpInteger(columnSum, remainingNumbers, elementNumber), remainingNumbers);
			for(int j = (i+1), k=0; j<equilibrium.length; j++) {
					equilibrium[i][j] = array[k++];
			}		
		}
		//calcolo l'ultimo valore, nel caso fosse uno 0, scambio i 2 valori della riga superiore
		do {
			switchValues = equilibrium[equilibrium.length-3][equilibrium.length-1];
			equilibrium[equilibrium.length-3][equilibrium.length-1] = equilibrium[equilibrium.length-3][equilibrium.length-2];
			equilibrium[equilibrium.length-3][equilibrium.length-2] = switchValues;
			equilibrium[equilibrium.length-2][equilibrium.length-1] = -Equilibrium.getSumOfAMatrixColumn(equilibrium, equilibrium.length-1);
		}while(equilibrium[equilibrium.length-2][equilibrium.length-1] == 0);
			
		return equilibrium;
	}
	
	/**
	 * prende i valori da una matrice e crea una mappa di elementi, e all'interno dei rispettivi elementi, 
	 * gli archi con le rispettive direzioni e pesi 
	 * @param matrix la matrice antisimmetrica di riferimento
	 * @return una mappa
	 */
	public static Map<Integer, Element> getRandomElementMap(int[][] matrix){
		Map<Integer, Element> map = new HashMap<Integer, Element>();
		
		for(int i=0; i<matrix.length;i++) {
			map.put(i, new Element(ELEMENT_NAMES[i]));
			for(int j=0; j<matrix.length;j++)
				if (matrix[i][j]>0)
					map.get(i).getMatchupList().add(new Matchup(new Element(ELEMENT_NAMES[j]), matrix[i][j]));
		}
		
		return map;
	}
	
	/**
	 * crea una matrice antisimmetrica data una matrice quadrata e la sua trasposta
	 * @param matrixOriginal matrice quadrata iniziale
	 * @param matrixTrasposed la trasposta della sua matrice
	 * @return la matrice antisimmetrica
	 */
	public static int[][] antisymmetricMatrix(int[][] matrixOriginal, int[][] matrixTrasposed){
		int lenght = matrixOriginal.length;
		int[][] matrixAntisymetric = new int[lenght][lenght];
		
		for(int i = 0; i<lenght; i++)
			for(int j = 0; j<lenght; j++)
				matrixAntisymetric[i][j] = matrixOriginal[i][j] - matrixTrasposed[i][j];
		
		return matrixAntisymetric;
	}
	/**
	 * calcola la trasposta di una matrice
	 * @param highMatrix la matrice da calcolare di tipo int
	 * @return la matrice trasposta
	 */
	public static int[][] getLowMatrix(int[][] highMatrix){
		int lenght = highMatrix.length;
		int[][] lowMatrix = new int[lenght][lenght];
		
		for (int i = 0; i<lenght; i++)
			for(int j = 0; j<lenght; j++)
				lowMatrix[i][j] = highMatrix[j][i];
		return lowMatrix;
	}
	
	/**
	 * metodo che crea un insieme di valori che sommati tra di loro danno il numero richiesto in input
	 * @param number il numero da scomporre
	 * @param partsCount il quantitativo di valori nei quali scomporre il numero richiesto
	 * @param il numero massimo dei valori
	 * @return un array con all'interno dei valori che sommati danno il numero richiesto
	 */
	public static int[] breakUpInteger(int number, int partsCount, int elementNumber) {
		int[] breakParts = new int[partsCount];
		int sum;
		Random random = new Random();
		
		do {
			sum = 0;
			for(int i=0; i<breakParts.length-1; i++) {
				do {
					breakParts[i] = random.nextInt((int)(Math.abs(elementNumber)/2))-random.nextInt((int)(Math.abs(elementNumber/2)));
				}while(breakParts[i] == 0);
				sum += breakParts[i];
			}
			breakParts[partsCount-1] = -(sum-number); 
		}while (sum == number);
		return breakParts;
	}
	
	/**
	 * stampa a video una matrice 
	 * @param matrix la matrice da stampare (con valori interi)
	 */
	public static void printMatrix(int[][] matrix) {
		for (int i=0; i<matrix.length;i++) {
			for(int j=0;j<matrix.length;j++)
				System.out.print(String.format("%2d | ", matrix[i][j]));
			System.out.println();
		}
	}
	
	/**
	 * calcola la somma di una determinata colonna
	 * @param matrix la matrice di riferimento(di tipo int[][])
	 * @param columnIndex l'indice della colonna (da 0 a length)
	 * @return un valore integer della somma
	 */
	public static int getSumOfAMatrixColumn(int[][] matrix, int columnIndex) {
		int sum = 0;
		for (int i=0; i<matrix.length; i++)
			sum += matrix[i][columnIndex];
		return sum;
	}
}
