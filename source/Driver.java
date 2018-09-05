package escala;

import java.io.*;
import java.sql.*;
import escala.database.*;

public class Driver {
    public static void main(String[] args) throws SQLException, IOException {
        Portal portal = new Portal();
        portal.addRegion("data/raw/regions/Oceania.txt");
        Region region = portal.getRegion("Oceania");
        if (region == null) {
            System.out.println("No region found");
        }
        else {
            System.out.print("Region: ");
            System.out.println(region.getName());
            System.out.println(region.getTaxRate());
            System.out.println(region.getEntryCost());
            System.out.println(region.getLogisticsCost());
            System.out.println(region.getMarketingCost());
            System.out.println(region.getEfficiencyCost());
            System.out.println(region);
        }
    }
}
