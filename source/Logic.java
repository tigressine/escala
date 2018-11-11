// Part of Escala.
// Written by Jonathan Ponader.

package escala;

import escala.*;
import java.util.*;
import javax.swing.*;
import escala.structures.*;

public class Logic
{
	private Game game;
	private double marketShare;
	private int cash = 400;
	private int logistics = 0;
	private int marketing = 0;
	private int product = 0;
	private int bought = 1;
	private Calendar cal;
	private static ArrayList<Region> regions;
	private boolean free = true;

	public Logic(Game game)
	{
		cal = Calendar.getInstance();
		cal.set(1000,1,1);
		this.game = game;
		regions = game.getAllRegions();
	}

    	// Sets Key Stats for Game
	public void setLogic(int cash, int product, int marketing, int logistics)
	{
		this.cash = cash;
		this.product = product;
		this.marketing = marketing;
		this.logistics = logistics;
	}

	public boolean purchaseRegion(Region r){
		return isValidPurchase(r.getEntryCost(), r.getEfficiencyCost(), r.getMarketingCost(), r.getLogisticsCost());
	}

	//Checks to see if the player can afford the purchase, if so purchases the goods
	public boolean isValidPurchase(int cash, int product, int marketing, int logistics)
	{	
		// Allows for First region to be purcahsed for free
		if (!free)
		{
			if((this.cash - cash) < 0)
				return false;

			updateStats(cash * -1, product, marketing, logistics);
			bought++;
		}
		else
		{
			free = false;
			updateStats(0, product, marketing, logistics);
		}
		return true;
	}

	//updates the stats after purchase or improvements
	public void updateStats(int cash, int product, int marketing, int logistics)
	{
		this.cash += cash;
		this.product += product;
		this.marketing += marketing;
		this.logistics += logistics;
	}

	// Creates string for use on the MAP
	public String cashToString()
	{
		String string = String.format("$ %06d", cash);
		return string;
	}
	
	// Creates string for use on the MAP
	public String shareToString()
	{
		String string = String.format("%02.2f %%", marketShare);
		return string;
	}

	//Returns Date for Map Label
    	public String getDate()
    	{
		String string = String.format("%02d/%02d/%02d", 
			cal.get(Calendar.MONTH), 
			cal.get(Calendar.DAY_OF_MONTH), 
			(cal.get(Calendar.YEAR)%100));
		return string;
   	 }
	
	public int getLog(){
		return this.logistics;
	}

	public int getProd(){
		return this.product;
	}

	public int getMark(){
		return this.marketing;
	}

	public double getShare(){
		return marketShare;
	}

	// Called once per period, to update the game, applying the game
	public void timedUpdate()
	{
		int log = this.logistics;
		int mark = this.marketing;
		int prod = this.product;
		double total = 0;

		for (Region region : regions) 
		{
            if (region.isPurchased()) 
			{
				if(region.getMarketShare() > 100){
					region.setMarketShare(100);
				}
				else if(region.getMarketShare() >= 75){
					region.incrementMarketShare(.0025 * ((.25 * log) + (.50 * mark) + (.25 * prod)));
				}
				else if(region.getMarketShare() >= 50){
					region.incrementMarketShare(.0040 * ((.50 * log) + (.25 * mark) + (.25 * prod)));
				}
				else{
					region.incrementMarketShare(.0055 * ((.25 * log) + (.50 * mark) + (.25 * prod)));
				}
			}
            		total += region.getMarketShare()  * region.getWorldShare();
        	}

		this.cal.add(Calendar.DAY_OF_MONTH, 2);
		this.marketShare = total;
		this.cash += (int)(total*10)/(Math.min(Math.pow(2,bought), (6 * bought)));
		winState();
	}

	//Ends Game in Win or Loose Condition
	private void winState(){
		if(this.marketShare >= 100){
			JOptionPane.showMessageDialog(null, "You Win, Thank you For Playing!");
			System.exit(0);
		}

		else if((cal.get(Calendar.YEAR)%100) == 5){
			JOptionPane.showMessageDialog(null, "You Loose, Better Luck Next Time");
			System.exit(0);
		}
		else
			return;
	}
}
