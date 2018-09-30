// Part of Escala.
// Written by Jonathan Ponader.


//NO LONGER NEEDED

package escala.graphics;

import java.awt.Polygon;
import java.net.URL;
import java.net.URLDecoder;
import java.awt.Point;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFrame;

public class PolyMouseList
{
	private static PolyMouseList instance = null;

	private static final int NUM_REGIONS = 10;
	static Polygon [] regions = new Polygon[NUM_REGIONS];
	final String[] regionNames = {"Asia", "EasternEurope", "LatinAmerica", "MiddleEast", 
            "NorthAfrica", "NorthAmerica", "Oceania", "SouthAfrica", "SouthAmerica", "WesternEurope"};

	public PolyMouseList()
	{
		/*URL url;

		for(int i = 0; i < NUM_REGIONS; i++) 
		{
            url = getClass().getResource("/data/raw/polygons/" + regionNames[i] + ".txt"); 
            try
            {
                String path = URLDecoder.decode(url.getPath(), "UTF-8");
            	regions[i] = addPoly(new File(path));
            }
            catch (IOException e)
            {
       			 e.printStackTrace();
       		}
        }*/
	}
/*
	public int contains(Point p)
	{
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
   */
}
