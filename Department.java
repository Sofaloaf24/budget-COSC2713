package Priorityqueue;

import java.util.LinkedHashMap;

public class Department implements Comparable<Department> {
	private String name;
	private double spendings;
	private double scholarships;

	public double getScholarships() {
		return scholarships;
	}

	public void setScholarships(double scholarships) {
		this.scholarships = scholarships;
	}

	private LinkedHashMap<String, Double> itemsDesired;
	private LinkedHashMap<String, Double> itemsReceived;
	private LinkedHashMap<String, Double> itemsRemoved; // NEW

	public Department(String n, LinkedHashMap<String, Double> iD) {
		name = n;
		itemsDesired = iD;
		itemsReceived = new LinkedHashMap<>();
		itemsRemoved = new LinkedHashMap<>(); 
		scholarships = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public double getSpendings() {
		return spendings;
	}

	public void updateSpendings() {
		double temp = scholarships;
		for (String item : itemsReceived.keySet()) {
			temp += itemsReceived.get(item);
		}
		spendings = temp;
	}

	public void purchaseItem(String itemName, double itemPrice) {
		if (itemsDesired.containsKey(itemName)) {
			itemsReceived.put(itemName, itemPrice);
			itemsDesired.remove(itemName);
		} else {
			System.out.println("Item " + itemName + " is not in the items desired list.");
		}
	}

	public LinkedHashMap<String, Double> getItemsDesired() {
		return itemsDesired;
	}

	public void setItemsDesired(LinkedHashMap<String, Double> m) {
		itemsDesired = m;
	}

	public LinkedHashMap<String, Double> getItemsReceived() {
		return itemsReceived;
	}

	public LinkedHashMap<String, Double> getItemsRemoved() {
		return itemsRemoved;
	}

	@Override
	public int compareTo(Department o) {
		return Double.compare(this.spendings, o.getSpendings());
	}
}
