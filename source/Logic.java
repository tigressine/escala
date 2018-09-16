// Part of Escala.
// Written by Jonathan Ponader.

package escala;


/*
*	0	17	Asia(China, India Philippines)
*	1	8	Eastern Europe (All of Russia)
*	2	8	Latin/Central America(Mexico, Central Caribbean)
*	3	5	Middle East
*	4	7	North Africa
*	5	13	North America (Canada, US)
*	6	12	Oceania
*	7	10	South Africa
*	8	6	South America
*	9	14	Western Europe
*/


public class Logic
{
	private static final int NUM_REGIONS = 10;
	String[] regionNames = {"Asia", "EasternEurope", "LatinAmerica", "MiddleEast", 
            "NorthAfrica", "NorthAmerica", "Oceania", "SouthAfrica", "SouthAmerica", "WesternEurope"};

    private static Logic instance = null;
	private int marketShare = 0;
	private boolean [] active = new boolean [10];
	private final double [] distribution = { .17, .08, .08, .05, .07, .13, .12, .10, .06, .14 };
	int [] regMarketShare = new int [10];
	private int cash = 0;
	private int logistics = 0;
	private int marketing = 0;
	private int product = 0;

	public Logic()
	{

	}

	public static Logic getInstance() {
    	if(instance == null)
       	{
            instance = new Logic();
    	}
        return instance;
    }

	public void setLogic(int cash, int product, int marketing, int logistics)
	{
		this.cash = cash;
		this.product = product;
		this.marketing = marketing;
		this.logistics = logistics;
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

	public String cashToString()
	{
		String string = String.format("$ %05d", cash);
		return string;
	}

	public String shareToString()
	{
		String string = String.format("%02d %%", marketShare);
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

		for(int i = 0; i < NUM_REGIONS; i++)
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
}
