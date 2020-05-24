package worldorder;

import java.util.ArrayList;

public class TamaGolem {

	private int lifePoints;
	private ArrayList<Element> fightingElements = new ArrayList<Element>();
	
	
	public TamaGolem(int lifePoints) {
		this.lifePoints = lifePoints;
	}
	
	public int getLifePoints() {
		return lifePoints;
	}
	
	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}
	
	public ArrayList<Element> getFightingElements() {
		return fightingElements;
	}
	
	public void setFightingElements(ArrayList<Element> fightingElements) {
		this.fightingElements = fightingElements;
	}
	
	
}
