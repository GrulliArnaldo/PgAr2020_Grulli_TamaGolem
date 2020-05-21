package worldorder;

import java.util.Arrays;
import java.util.Map;

public class WorldOrderMain {
	
	public static void main(String[] args) {
		int num = 2;
		int parts = 4;
		int elementNumber = 7;
		int sumRow = 0;
		int[][] matrix = Equilibrium.getHighMatrix(elementNumber);
		int[][] matrixTrasposed = Equilibrium.getLowMatrix(matrix);
		int[][] matrixAntisymmetric = Equilibrium.antisymmetricMatrix(matrix, matrixTrasposed);
		
		
//		int[] breakParts = Arrays.copyOf(Equilibrium.breakUpInteger(-num, parts, elementNumber), parts);
//		for (int i : breakParts)
//			System.out.println(i);
		
		Equilibrium.printMatrix(matrix);
		System.out.println();
		Equilibrium.printMatrix(matrixTrasposed);
		System.out.println();
		Equilibrium.printMatrix(matrixAntisymmetric);
		System.out.println();
		
		for (int i = 0; i<elementNumber;i++) {
			System.out.println("Column " + (i+1) + " : " + Equilibrium.getSumOfAMatrixColumn(matrixAntisymmetric, i));
			for(int j = 0; j<elementNumber;j++)
				sumRow += matrixAntisymmetric[i][j];
			System.out.println("Row " + (i+1) + " : " + sumRow);
		}
		System.out.println();
		Map<Integer, Element> map = Equilibrium.getRandomElementMap(matrixAntisymmetric);
		for(int i = 0; i<map.size();i++) {
			System.out.println("###Element name: " + map.get(i).getElementName());
			for (int j=0;j<map.get(i).getMatchupList().size();j++)
				System.out.println(String.format("%d° Power: >%d< Vs--> %s",(j+1),  map.get(i).getMatchupList().get(j).getMatchupPower(), map.get(i).getMatchupList().get(j).getWinningElement().getElementName()));
		}
		
		
			
		
	}
	
	
	
}
