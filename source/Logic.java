
// Commented out for Local Testing From Main
package escala;


/*
*	0	12	Australia (Oceania)
*	1	14	Western Europe
*	2	8	Eastrn Europe (All of Rusia)
*	3	17	SouthEast Asia(China, India Philipins)
*	4	5	Midddle East
*	5	7	North Africa
*	6	10	South Africa
*	7	13	North America (Canada, US)
*	8	8	Latin/Central America(Mexico, Central Carribean)
*	9	6	South Amrica
*/


public class Logic
{

	private int marketShare;
	private boolean [] active;
	private final double [] distribution = { .12, .14, .08, .17, .05, .07, .10, .13, .08, .06 };
	int [] regMarketShare;
	private int cash;
	private int logistics;
	private int marketing;
	private int product;


	public static void main(String[] args)
	{
		Logic test = new Logic(0,0,0,0);

		System.out.println(test.toStringStats());

		test.updateStats(1000,53,35,23);

		for(int i = 0; i < 5 ; i++)
		{
			test.setActive(i);
		}

		System.out.println(test.toStringStats());

		for(int i = 0; i < 10; i++)
		{

			for(int j = 0; j < 10; j++)
				System.out.print(test.regMarketShare[j] + "  ");

			System.out.println("");
			test.timedUpdate();

			System.out.println(test.toStringTimed());
		}
	}


	public Logic(int cash, int product, int marketing, int logistics)
	{
		this.cash = cash;
		this.product = product;
		this.marketing = marketing;
		this.logistics = logistics;

		active = new boolean [10];
		regMarketShare = new int [10];
	}

	//Checks to see if the player can afford the purchase, if so purchases the goods
	public boolean isValidPurchase(int cash, int product, int marketing, int logistics)
	{
		if((this.cash - cash) < 0)
			return false;

		if((this.product - product) < 0)
			return false;

		if((this.marketing - marketing) < 0)
			return false;
		
		if((this.logistics - logistics) < 0)
			return false;

		updateStats(cash, product, marketing, logistics);

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

	public String toStringStats()
	{
		String string = String.format("%d, %d, %d, %d", cash, product, marketing, logistics);
		return string;
	}

	void setActive(int region)
	{
		this.active[region] =  true;
	}

	void timedUpdate()
	{
		int minDiff =  Math.min(logistics, Math.min(marketing, product)) + 20;
		int log = Math.min(minDiff,this.logistics);
		int mark = Math.min(minDiff,this.marketing);
		int prod = Math.min(minDiff,this.product);
		int total = 0;
		int flag = 0;

		for(int i = 0; i < active.length; i++)
		{
			if(active[i])
			{
				regMarketShare[i] += .45 * ((.33 * log) + (.33 * mark) + (.33 * prod));

				if(regMarketShare[i] > 1000)
					regMarketShare[i] = 1000;
			}

			total += regMarketShare[i] * distribution[i];
		}

		this.marketShare = total/10;
		this.cash += (this.marketShare) * (log + mark + prod);
	}

	public String toStringTimed()
	{
		String string = String.format("%d, %d", this.cash, this.marketShare);
		return string;
	}



}