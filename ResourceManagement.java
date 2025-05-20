package Priorityqueue;

import java.util.LinkedHashMap;
import java.util.PriorityQueue;

public class ResourceManagement {
	private double totalBudget;
	private double currentBudget;
	private PriorityQueue<Department> departments;
	private LinkedHashMap<String, Double> purchaseHistory;

	public ResourceManagement(double budget) {
		totalBudget = budget;
		currentBudget = budget;
		departments = new PriorityQueue<>();
		purchaseHistory = new LinkedHashMap<>();
	}

	public void distributeBudget() {
		PriorityQueue<Department> tempQueue = new PriorityQueue<>();
		while (currentBudget > 0 || !departments.isEmpty()) {
			Department dept = departments.poll(); // department with lowest spendings
			boolean boughtItem = false;
			
			while (!dept.getItemsDesired().isEmpty()) {
				String nextItem = dept.getItemsDesired().keySet().iterator().next();
				double price = dept.getItemsDesired().get(nextItem);

				if (price > currentBudget) {
					dept.getItemsRemoved().put(nextItem, price);
					dept.getItemsDesired().remove(nextItem);
				} else {
					dept.purchaseItem(nextItem, price);
					currentBudget -= price;
					dept.updateSpendings();
					purchaseHistory.put("Department of " + String.format("%-30s", dept.getName()) + " - " + nextItem, price);
					boughtItem = true;
					break;
				}
			}

			if (!boughtItem && dept.getItemsDesired().isEmpty()) {
				double scholarship = Math.min(1000, currentBudget);
				currentBudget -= scholarship;
				dept.setScholarships(dept.getScholarships() + scholarship);
				
				
				dept.updateSpendings();
			}
			
			if (dept.getItemsDesired().isEmpty() && currentBudget == 0) {
				tempQueue.add(dept);
			}
			else {
				departments.add(dept); // reinsert to queue
		
			}
		}
		departments = tempQueue;
	}

	public void printPurchaseHistory() {
		System.out.println("ITEMS PURCHASED");
		System.out.println("----------------------------");
		for (String entry : purchaseHistory.keySet()) {
			System.out.printf("%s\n$%.2f\n", entry, purchaseHistory.get(entry));
		}
		System.out.println();
	}

	public void printDepartmentReports() {
		for (Department dept : departments) {
			System.out.println(dept.getName());
			System.out.printf("Total Spent = $%.2f\n", dept.getSpendings());
			System.out.printf("Percent of Budget = %.2f%%\n", (dept.getSpendings() / totalBudget) * 100);
			System.out.println("----------------------------");
			System.out.println("ITEMS RECEIVED");
			for (String item : dept.getItemsReceived().keySet()) {
				System.out.printf("%-30s - %30s\n", item, "$" + dept.getItemsReceived().get(item));
			}
			System.out.println("ITEMS NOT RECEIVED");
			for (String item : dept.getItemsRemoved().keySet()) {
				System.out.printf("%-30s - %30s\n", item, "$" + dept.getItemsRemoved().get(item));
			}
			System.out.println();
		}
	}

	public double getTotalBudget() {
		return totalBudget;
	}

	public double getCurrentBudget() {
		return currentBudget;
	}

	public PriorityQueue<Department> getDepartments() {
		return departments;
	}

	public LinkedHashMap<String, Double> getPurchaseHistory() {
		return purchaseHistory;
	}
}

