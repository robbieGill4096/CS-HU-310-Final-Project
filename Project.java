import java.io.*;
import java.sql.*;
import java.util.*;
/*
((insert description here))
 */
public class Project {
	private Connection conn = null;
	
	public static void main(String[] args) {
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:52116/test?verifyServerCertificate=false&useSSL=true", "msandbox",
					"Hello667");
			// Do something with the Connection
			System.out.println("Database [test db] connection succeeded!");
			System.out.println();
			
			con.setAutoCommit(false);//transaction block starts
		
			//Parse
			if (args[0].equals("/?") {
				if(args.length != 1) {
					System.out.println("invalid usage, Usage:  java Project /?");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			}
			else if (args[0].equals("CreateItem") {
				if(args.length != 4) {
					System.out.println("invalid usage, Usage: java Project CreateItem <itemCode> <itemDescription> <price>");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			}
			else if (args[0].equals("CreatePurchase") {
				if(args.length != 3) {
					System.out.println("invalid usage, Usage: java Project CreatePurchase <itemCode> <PurchaseQuantity>");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			}
			else if (args[0].equals("CreateShipment") {
				if(args.length != 4) {
					System.out.println("java Project CreateShipment <itemCode> <ShipmentQuantity> <shipmentDate>");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			}
			else if (args[0].equals("GetItems") {
				if(args.length != 2) {
					System.out.println("java Project GetItems <itemCode>");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			}
			else if (args[0].equals("GetShipments") {
				if(args.length != 2) {
					System.out.println("java Project GetShipments <itemCode>");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			}
			else if (args[0].equals("GetPurchases") {
				if(args.length != 2) {
					System.out.println("java Project GetPurchases <itemCode>");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			}
			else if (args[0].equals("ItemsAvailable") {
				if(args.length != 2) {
					System.out.println("java Project ItemsAvailable <itemCode>");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			} else {
				System.out.println("No arguments used, input: java Project /? for all usage commands");
			}
		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		}
//		if(stmt!=null)
//			stmt.close();
//		
//		if(stmt2!=null)
//			stmt2.close();
//		
		con.setAutoCommit(true); // restore dafault mode
		con.close();
	}
}
