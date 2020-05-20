package worldorder;

public class Matchup 
{
	private Element winningElement;
	private int matchupPower;
	
	Matchup(Element winningElement, int matchupPower)
	{
		this.winningElement = winningElement;
		
		this.matchupPower = matchupPower;
	}

	public Element getWinningElement() {
		return winningElement;
	}

	public void setWinningElement(Element winningElement) {
		this.winningElement = winningElement;
	}
	
	public int getMatchupPower() 
	{
		return matchupPower;
	}
	public void setMatchupPower(int matchupPower) 
	{
		this.matchupPower = matchupPower;
	}
}
