import java.io.*;
import java.util.*;
import javax.swing.*;

public class Project4Test
{	public static ArrayList<Airports> airportDetails = new ArrayList<Airports>();
	public static ArrayList<Flights> flightDetails = new ArrayList<Flights>();
	public static File file1 = new File("Airports.txt");
	public static File file2 = new File("Flights.txt");
	public static void main(String [] args) throws IOException
	{
		int choice ;
		String menuOption = "" ;
		loadFiles();
		writeAirportFile();
		writeFlightFile();
		
		while ((menuOption != null) && (!(menuOption.equals("0"))))
		{
			menuOption = getMenuOption () ;
			if (menuOption != null)
			{
				choice = Integer.parseInt(menuOption) ;
				if (choice != 0)
				{
					switch(choice)
					{
						//case 1: addAirport() ; break ;
						//case 2: editAirportName() ; break ;
						//case 3: deleteAirport() ; break ;
						//case 4: editTravel() ; break ;
						//case 5: deleteFlight() ; break ;
						case 6: searchFlightByAirport() ; break ;
						//case 7: searchFlightByDepartureDate() ; break ;
						case 8: addFlight() ; writeFlightFile() ; break ;
					}
				}
			}
		}
	}
	
	public static String getMenuOption()
	{
		String menuOptions = 	"1. Add a new airport." +
								"\n2. Edit and existing airport." +
								"\n3. Delete an airport and associated flights." +
								"\n4. Edit travel days and dates of a flight." +
								"\n5. Delete a flight." +
								"\n6. Search for flights by departure airport and arrival airport." +
								"\n7. Search for flights by departure airport, arrival airport, and departure date." +
								"\n8. Add a new flight." +
								"\n0. Exit." ;
								
		String menuMessage = "Choose number of the option you wish to execute." ;
		String 	errorMessage = "Invalid menu selection. \n\nValid options are 0-8 inclusive." ;
				errorMessage += "\nSelect OK to retry" ;
		String errorHeader = "Error in user input" ;
		boolean validInput = false; ;
		String selection = "", menuChoicePattern = "[0-8]{1}" ;
		
		while (!(validInput))
		{
			selection = JOptionPane.showInputDialog(null, menuOptions, menuMessage, 3) ;
			
			if (selection == null || selection.matches(menuChoicePattern))
				validInput = true ;
			else
				JOptionPane.showMessageDialog(null, errorMessage, errorHeader, 2) ;
		}
		return selection ;
	}
	public static void searchFlightByAirport()
	{
		int index = 0, amountFound = 0;
		String errorMessage1 = "Departure airport and arrival airport cannot be the same.";
		String errorMessage2 = "One or both fields were empty.";
		String airport1 = "";
		String airport2 = "";
		String success = "";
		String failure = "";
		String result = "";
		boolean found1 = false;
		boolean found2 = false;
		
		JPanel panel = new JPanel();
		JTextField departureAirport = new JTextField();
		JTextField arrivalAirport = new JTextField();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel("Search for the flights departing airport name:"));
		panel.add(departureAirport);
		panel.add(new JLabel("And arriving at airport name:"));
		panel.add(arrivalAirport);
		
		JOptionPane.showMessageDialog(null,panel,"Search for flight by airports", JOptionPane.PLAIN_MESSAGE);
		//Validation
		
		for(int i = 0; (i < airportDetails.size() &&(found1 != true || found2 != true)); i++)
		{
			if(departureAirport.getText().equalsIgnoreCase(airportDetails.get(i).getAirportName()))
			{
				airport1 = airportDetails.get(i).getIATA();
				found1 = true;
			}
			if(arrivalAirport.getText().equalsIgnoreCase(airportDetails.get(i).getAirportName()))
			{
				airport2 = airportDetails.get(i).getIATA();
				found2 = true;
			}
		}
		
		if ((departureAirport.getText().length() == 0) || (arrivalAirport.getText().length() == 0))
		{
			JOptionPane.showMessageDialog(null,errorMessage2,"Input Error",JOptionPane.ERROR_MESSAGE);
		}
		else if (airport1.equalsIgnoreCase(airport2) && airport1.length() != 0)
		{
			JOptionPane.showMessageDialog(null,errorMessage1,"Input Error",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			while((airport1.length() > 0) && (airport2.length() > 0) && (index < flightDetails.size()))
			{
				if((flightDetails.get(index).getDepartureIATA()).equalsIgnoreCase(airport1) && (flightDetails.get(index).getArrivalIATA()).equalsIgnoreCase(airport2))
				{
					result += flightDetails.get(index).getAll();
					result += "\n";
					amountFound++;
				}
				index++;
			}
			if(amountFound == 0)
			{
				failure = ("No flights are departing " + departureAirport.getText() + " and arriving at " + arrivalAirport.getText() + ".");
				JOptionPane.showMessageDialog(null,failure,"No Flights Found", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{	
				success = (amountFound + " flight(s) found departing " + departureAirport.getText() + " and arriving at " + arrivalAirport.getText() + ":\n");
				JOptionPane.showMessageDialog(null,(success + result),"Flights Found", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	
	
	public static void addFlight()
	{
		boolean found = false;
		String success = " successfully added!";
		String failure = "New flight could not be added. Flight existed previously.";
		
		JPanel panel = new JPanel();
		
		JTextField flightNumberField = new JTextField();
		JTextField departureIATIField = new JTextField();
		JTextField arrivalIATIField = new JTextField();
		JTextField departureTimeField = new JTextField();
		JTextField arrivalTimeField = new JTextField();
		JTextField operatingDaysField = new JTextField();
		JTextField startDatesField = new JTextField();
		JTextField endDatesField = new JTextField();
		
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel("Please enter the details for the new flight."));
		panel.add(new JLabel("Flight Number:   e.g. EI1234 or AA9876"));
		panel.add(flightNumberField);
		panel.add(new JLabel("Departure Airport:   e.g. DUB or SJC "));
		panel.add(departureIATIField);
		panel.add(new JLabel("Arrival Airport:   e.g. SFO or SNN"));
		panel.add(arrivalIATIField);
		panel.add(new JLabel("Departure Time:   e.g. 0000 or 1545"));
		panel.add(departureTimeField);
		panel.add(new JLabel("Arrival Time:   e.g. 0000 or 1545"));
		panel.add(arrivalTimeField);
		panel.add(new JLabel("Operating Days:   e.g. MTWTFSS or -T-TF-S"));
		panel.add(operatingDaysField);
		panel.add(new JLabel("Start Dates:   e.g. DD/MM/YYYY or 31/12/2016"));
		panel.add(startDatesField);
		panel.add(new JLabel("End Dates:   e.g. DD/MM/YYYY or 11/12/2017"));
		panel.add(endDatesField);
		
		JOptionPane.showMessageDialog(null, panel, "Add New Flight", JOptionPane.PLAIN_MESSAGE);
		//Call for validation
		
		Flights tempFlight = new Flights(flightNumberField.getText(),departureIATIField.getText(),arrivalIATIField.getText(),departureTimeField.getText(),arrivalTimeField.getText(),operatingDaysField.getText(),startDatesField.getText(),endDatesField.getText());
		for(int i = 0; i < flightDetails.size() && !found; i++)
		{
			if(flightDetails.get(i).getFlightNumber().compareToIgnoreCase(tempFlight.getFlightNumber()) == 0)
			{
				found = true;
				JOptionPane.showMessageDialog(null,failure,"Add Fight Failed", JOptionPane.ERROR_MESSAGE);
			}
			else if(flightDetails.get(i).getFlightNumber().compareToIgnoreCase(tempFlight.getFlightNumber()) > 0)
			{
				flightDetails.add(i,tempFlight);
				found = true;
				JOptionPane.showMessageDialog(null,"Flight " + flightNumberField.getText() + success,"Flight Added", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	
	public static void loadFiles() throws IOException
	{
		int airportParts = 2, flightParts = 8;
		Scanner in;
		Airports tempAirport;
		Flights tempFlight;
		String[] fileElements;
		
		//if Airports.txt exists, it loads the values into the airportDetails
		if(file1.exists())
		{
			in = new Scanner(file1);
			while(in.hasNext())
			{
				fileElements = in.nextLine().split(",");
				tempAirport = new Airports(fileElements[0], fileElements[1]);
				airportDetails.add(tempAirport);
			}
			in.close();
			
		}
		
		/*
		for(int i = 0; i <airportDetails.size(); i++)
			System.out.println(airportDetails.get(i).getAll();
		*/
		
		//if Flights.txt exists, it loads the values into flightDetails
		if(file2.exists())
		{
			in = new Scanner(file2);
			while(in.hasNext())
			{
				fileElements = in.nextLine().split(",");
				tempFlight = new Flights(fileElements[0], fileElements[1], fileElements[2], fileElements[3], fileElements[4], fileElements[5], fileElements[6], fileElements[7]);
				flightDetails.add(tempFlight);
			}
			in.close();
		}
		/*
		for(int i = 0; i < flightDetails.size(); i++)
			System.out.println(flightDetails.get(i).getAll();
		*/
	}
	
	
	public static void writeAirportFile() throws IOException
	{
		String result;
		PrintWriter out = new PrintWriter(file1);
		
		for (int i = 0; i < airportDetails.size(); i++)
			out.println(airportDetails.get(i).getAllComma());
		out.close();
	}
	
	public static void writeFlightFile() throws IOException
	{
		String result;
		PrintWriter out = new PrintWriter(file2);
		
		for (int i = 0; i < flightDetails.size(); i++)
			out.println(flightDetails.get(i).getAllComma());
		out.close();
	}
}