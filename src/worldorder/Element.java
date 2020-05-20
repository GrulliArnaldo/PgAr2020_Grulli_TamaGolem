package worldorder;
import java.util.ArrayList;


public class Element 
{
	private String elementName;
	private ArrayList<Matchup> matchupList = new ArrayList<Matchup>();
	
	public Element(String elementName)
	{
		this.elementName = elementName;
	}


	public String getElementName()
	{
		return elementName;
	}
}
