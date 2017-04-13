public class Flights
{
	private String flightNumber;
	private String departureIATA;
	private String arrivalIATA;
	private String departureTime;
	private String arrivalTime;
	private String operatingDays;
	private String startDates;
	private String endDates;
	
	Flights(String fN, String dI, String aI, String dT, String aT, String oD, String sD, String eD)
	{
		flightNumber = fN;
		departureIATA = dI;
		arrivalIATA = aI;
		departureTime = dT;
		arrivalTime = aT;
		operatingDays = oD;
		startDates = sD;
		endDates = eD;
	}
	
	public void setFlightNumber(String fN)
	{
		flightNumber = fN;
	}
	
	public void setDepartureIATA(String dI)
	{
		departureIATA = dI;
	}
	
	public void setArrivalIATA(String aI)
	{
		arrivalIATA = aI;
	}
	
	public void setDepartureTime(String dT)
	{
		departureTime = dT;
	}
	
	public void setArrivalTime(String aT)
	{
		arrivalTime = aT;
	}
	
	public void setOperatingDays(String oD)
	{
		operatingDays = oD;
	}
	
	public void setStartDates(String sD)
	{
		startDates = sD;
	}
	
	public void setEndDates(String eD)
	{
		endDates = eD;
	}
	
	public String getFlightNumber()
	{
		return flightNumber;
	}
	
	public String getDepartureIATA()
	{
		return departureIATA;
	}
	
	public String getArrivalIATA()
	{
		return arrivalIATA;
	}
	
	public String getDepartureTime()
	{
		return departureTime;
	}

	public String getArrivalTime()
	{
		return arrivalTime;
	}
	
	public String getOperatingDays()
	{
		return operatingDays;
	}
	
	public String getStartDates()
	{
		return startDates;
	}
	
	public String getEndDates()
	{
		return endDates;
	}
	
	public String getAll()
	{
		String result = (flightNumber + " " + departureIATA + " " + arrivalIATA + " " + departureTime + " " + arrivalTime + " " + operatingDays + " " + startDates + " " + endDates);
		return result;
	}
	
	public String getAllComma()
	{
		String result = (flightNumber + "," + departureIATA + "," + arrivalIATA + "," + departureTime + "," + arrivalTime + "," + operatingDays + "," + startDates + "," + endDates);
		return result;
	}
	
}