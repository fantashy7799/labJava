package Lab4DataBase;

import Lab4DataBase.DataBase;

public class Good{
	
	private String title;
	private double cost;
	private int prodid;
	
	public String getTitle() {
		return title;
	}

	public double getCost() {
		return cost;
	}

	public int getProdid() {
		return prodid;
	}
	
	  public Good(int prodid, String title, double cost) { this.prodid=prodid;
	  this.title=title; this.cost=cost; }
	 
}
