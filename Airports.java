public class Airports
{
	private String IATA;
	private String airportName;
	
	Airports(String name, String code)
	{
		airportName = name;
		IATA = code;
	}
	
	public void setIATA(String code)
	{
		IATA = code;
	}
	
	public void setAirportName(String name)
	{
		airportName = name;
	}
	
	public String getIATA()
	{
		return IATA;
	}
	
	public String getAirportName()
	{
		return airportName;
	}
	
	public String getAll()
	{
		String result = (airportName + " " + IATA);
		return result;
	}
	
	public String getAllComma()
	{
		String result = (airportName + "," + IATA);
		return result;
	}
	
}