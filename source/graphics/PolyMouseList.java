package escala.graphics;

import java.awt.Polygon;
import java.net.URL;
import java.awt.Point;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Jonathan Ponader
 */

public class PolyMouseList
{
	private static PolyMouseList instance = null;

	private static final int NUM_REGIONS = 10;
	static Polygon [] regions = new Polygon[NUM_REGIONS];
	final String[] regionNames = {"Asia", "EasternEurope", "LatinAmerica", "MiddleEast", 
            "NorthAfrica", "NorthAmerica", "Ocenia", "SouthAfrica", "SouthAmerica", "WesternEurope"};

	public PolyMouseList()
	{
		URL url;

		for(int i = 0; i < NUM_REGIONS; i++) 
		{
            url = getClass().getResource("/data/polygons/" + regionNames[i] + ".txt"); 

            try
            {
            	regions[i] = addPoly(new File(url.getPath()));
            }
            catch (IOException e)
            {
       			 e.printStackTrace();
       		}
        }
	}

	public int contains(Point p, double scale)
	{
		p.x = (int)((1/scale) * (double)p.x);
		p.y = (int)((1/scale) * (double)p.y);

		for(int i = 0; i < NUM_REGIONS; i++)
		{
			if(regions[i].contains(p))
				return i;
		}
		return Integer.MAX_VALUE;
	}

	private Polygon addPoly(File file) throws  IOException
	{
		Polygon poly = new Polygon();
		Scanner in = new Scanner(file);
		
		while(in.hasNext())
			poly.addPoint(in.nextInt(),in.nextInt());

		return poly;
	}

	public static PolyMouseList getInstance() {
        if(instance == null){
            instance = new PolyMouseList();
        }
        return instance;
    }
}
