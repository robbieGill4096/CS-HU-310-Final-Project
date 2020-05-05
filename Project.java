import java.io.*;
import java.sql.*;
import java.util.*;
/*
((insert description here))
 */
public class Project {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:52116/test?verifyServerCertificate=false&useSSL=true", "msandbox",
					"Hello667");
			// Do something with the Connection
			System.out.println("Database [test db] connection succeeded!");
			System.out.println();
			ResultSet resultSet;
			conn.setAutoCommit(false);//transaction block starts
			stmt = conn.createStatement();
			//Parse
			if (args[0].equals("/?")) {
				if(args.length != 1) {
					System.out.println("invalid usage, Usage:  java Project /?");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			}
			else if (args[0].equals("CreateItem")) {
				if(args.length != 4) {
					System.out.println("invalid usage, Usage: java Project CreateItem <itemCode> <itemDescription> <price>");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			}
			else if (args[0].equals("CreatePurchase")) {
				if(args.length != 3) {
					System.out.println("invalid usage, Usage: java Project CreatePurchase <itemCode> <PurchaseQuantity>");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			}
			else if (args[0].equals("CreateShipment")) {
				if(args.length != 4) {
					System.out.println("java Project CreateShipment <itemCode> <ShipmentQuantity> <shipmentDate>");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			}
			else if (args[0].equals("GetItems")) {
				if(args.length != 2) {
					System.out.println("java Project GetItems <itemCode>");
					System.exit(1);
				} else {
					if(args[1].equals("%")) {
						
					} else {
						
					}
				}
			}
			else if (args[0].equals("GetShipments")) {
				if(args.length != 2) {
					System.out.println("java Project GetShipments <itemCode>");
					System.exit(1);
				} else {
					//Insert SQL code here
				}
			}
			else if (args[0].equals("GetPurchases")) {
				if(args.length != 2) {
					System.out.println("java Project GetPurchases <itemCode>");
					System.exit(1);
				} else {
					if (args[1].equals("%")) {
						resultSet = stmt.executeQuery("SELECT * FROM Purchase ORDER BY ItemID ASC;");
					} else {
						resultSet = stmt.executeQuery("SELECT * FROM Purchase where ItemID = "+args[1]+";");
					}
					System.out.println(resultSet);
				}
			}
			else if (args[0].equals("ItemsAvailable")) {
				if(args.length != 2) {
					System.out.println("java Project ItemsAvailable <itemCode>");
					System.exit(1);
				} else {
					if (args[1].equals("%")) {
						stmt.executeQuery("Select i.ItemCode, i.ItemDescription, SUM(s.Quantity - p.Quantity) as AvailableItems"
								+ " From Item i, p Purchase, s Shipment ORDER BY i.ItemCode;");
					} else {
						//Still working on this
						stmt.executeQuery("Select i.ItemCode, i.ItemDescription, SUM(s.Quantity - p.Quantity) as AvailableItems"
							+ " From Item i, p Purchase, s Shipment where ItemCode = "+args[1]+";");
					}
				}
			}
			else if (args[0].equals("UpdateItem")) {
				if(args.length != 3) {
					System.out.println("java Project UpdateItem <itemCode> <price>");
					System.exit(1);
				} else {
					stmt.executeUpdate( "Update Item set Price = "+args[2]+" where ItemCode = "+args[1]+";");
				}
			}
			else if (args[0].equals("DeleteItem")) {
				if(args.length != 2) {
					System.out.println("java Project DeleteItem <itemCode>");
					System.exit(1);
				} else {
					stmt.executeUpdate("Delete From Item where ItemCode = "+args[1]+";");
				}
			}
			else if (args[0].equals("DeleteShipment")) {
				if(args.length != 2) {
					System.out.println("java Project DeleteShipment <itemCode>");
					System.exit(1);
				} else {
					stmt.executeUpdate("set @itemID = (Select ID from Item where itemCode = "+args[1]+"); "
							+ "Delete From Shipment where ItemID = @itemID limit 1;");
				}
			}
			else if (args[0].equals("DeletePurchase")) {
				if(args.length != 2) {
					System.out.println("java Project DeletePurchase <itemCode>");
					System.exit(1);
				} else {
					stmt.executeUpdate("set @itemID = (Select ID from Item where itemCode = "+args[1]+");"
							+ "Delete From Purchase where ItemID = @itemID limit 1;");
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
		if (stmt != null) {
			stmt.close();
		}
		
		conn.setAutoCommit(true); // restore dafault mode
		conn.close();
	}
}
