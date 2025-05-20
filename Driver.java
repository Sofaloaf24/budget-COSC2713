package Priorityqueue;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Driver {
	public void readInItemsDesired(String fileName, ResourceManagement RM) {
		File file = new File(fileName);
		Scanner scnr;
		try {
			scnr = new Scanner(file);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("The file " + fileName + " was not found.");
			return;
		}

		String departmentName = scnr.nextLine();
		scnr.nextLine();
		LinkedHashMap<String, Double> items = new LinkedHashMap<>();

		while (scnr.hasNext()) {
			String itemName = scnr.next();
			scnr.nextLine();
			double itemPrice = Double.parseDouble(scnr.next());
			if (scnr.hasNext()) scnr.nextLine();
			items.put(itemName, itemPrice);
		}

		Department dept = new Department(departmentName, items);
		RM.getDepartments().add(dept);
	}

	public static void main(String[] args) {

		String fileNames[][] = {{
		      "./TestCase1/Department-ComputerScience.txt",
		      "./TestCase1/Department-Mathematics.txt",
		      "./TestCase1/Department-Chemistry.txt",
		      "./TestCase1/Department-PhysicsAndAstronomy.txt"},
		      {
		      "./TestCase1/Department-ComputerScience.txt",
		      "./TestCase1/Department-Mathematics.txt",
		      "./TestCase1/Department-Chemistry.txt",
		      "./TestCase1/Department-PhysicsAndAstronomy.txt"},
		      {
		      "./TestCase2/Department-DeptA.txt",
		      "./TestCase2/Department-DeptB.txt",
		      "./TestCase2/Department-DeptC.txt",
		      "./TestCase2/Department-DeptD.txt",
		      "./TestCase2/Department-DeptE.txt",
		      "./TestCase2/Department-DeptF.txt",
		      "./TestCase2/Department-DeptG.txt"}
		      };
		                                                

		    

		    System.out.print("This solution was completed by:\n Sofia Flores\n Jacob Leblanc\n Nico Salvatore Pizzano\n Gabriel B Perez\n");
		
		    double budgets[] = {41850.00, 0.01, 27961.10};
		    for( int i=0; i<3; i++ )
		      testFile( fileNames[i], budgets[i] );
		    
		    System.out.print("This solution was completed by:\n Sofia Flores\n Jacob Leblanc\n Nico Salvatore Pizzano\n Gabriel B Perez\n");
		    
		    
		    
	}
	
	public static void testFile( String fileNames[], double budget ) {  
	    String budgetString = String.format("$%.2f", budget );
	    System.out.println("----------------------------TESTING with budget = " + budgetString + "----------------------------");
	    System.out.println("");

	    ResourceManagement rm = new ResourceManagement(budget);
	    Driver driver = new Driver();
	    for(String file : fileNames) {
	    	driver.readInItemsDesired(file, rm);
	    }
	    rm.distributeBudget();
		rm.printPurchaseHistory();
		rm.printDepartmentReports();  
	  }
}

