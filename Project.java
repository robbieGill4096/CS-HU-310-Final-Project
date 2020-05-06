import java.sql.*;
/*
((insert description here))
 */
public class Project {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:52116/CS310?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC", "msandbox",
					"Hello667");
			// Do something with the Connection
			System.out.println("Database [test db] connection succeeded!");
			System.out.println();
			ResultSet resultSet = null;
			conn.setAutoCommit(false);//transaction block starts
			//Parse
			if (args[0].equals("/?")) {
				if(args.length != 1) {
					System.out.println("invalid usage, Usage:  java Project /?");
					System.exit(1);
				} else {
					System.out.println("java Project CreateItem <itemCode> <itemDescription> <price>");
					System.out.println("java Project CreatePurchase <itemCode> <PurchaseQuantity>");
					System.out.println("java Project CreateShipment <itemCode> <ShipmentQuantity> <shipmentDate>");
					System.out.println("java Project GetItems <itemCode>");
					System.out.println("java Project GetShipments <itemCode>");
					System.out.println("java Project GetPurchases <itemCode>");
					System.out.println("java Project ItemsAvailable <itemCode>");
					System.out.println("java Project UpdateItem <itemCode> <price>");
					System.out.println("java Project DeleteItem <itemCode>");
					System.out.println("java Project DeleteShipment <itemCode>");
					System.out.println("java Project DeletePurchase <itemCode>");
				}
			}
			else if (args[0].equals("CreateItem")) {
				if(args.length < 3) {
					System.out.println("invalid usage, Usage: java Project CreateItem <itemCode> <itemDescription> <price>");
					System.exit(1);
				} else {
					if (args.length == 4) {
						cstmt = conn.prepareCall("{call CreateItem(?, ?, ?)}" );
						cstmt.setString(1,args[1]);
						cstmt.setString(2,args[2]);
						cstmt.setDouble(3, Double.parseDouble(args[3]));
						cstmt.executeUpdate();
					} else {
						cstmt = conn.prepareCall("{call CreateItem(?, ?)}" );
						cstmt.setString(1,args[1]);
						cstmt.setString(2,args[2]);
						cstmt.executeUpdate();
					}
				}
			}
			else if (args[0].equals("CreatePurchase")) {
				if(args.length != 3) {
					System.out.println("invalid usage, Usage: java Project CreatePurchase <itemCode> <PurchaseQuantity>");
					System.exit(1);
				} else {
					cstmt = conn.prepareCall("{call CreatePurchase(?, ?)}" );
					cstmt.setString(1,args[1]);
					cstmt.setDouble(2,Double.parseDouble(args[2]));
					cstmt.executeUpdate();
				}
			}
			else if (args[0].equals("CreateShipment")) {
				if(args.length != 4) {
					System.out.println("invalid usage, Usage: java Project CreateShipment <itemCode> <ShipmentQuantity> <shipmentDate>");
					System.exit(1);
				} else {
					cstmt = conn.prepareCall("{call CreateShipment(?, ?, ?)}" );
					cstmt.setString(1,args[1]);
					cstmt.setDouble(2,Double.parseDouble(args[2]));
					cstmt.setDate(3, java.sql.Date.valueOf(args[3]));
					cstmt.executeUpdate();
				}
			}
			else if (args[0].equals("GetItems")) {
				if(args.length != 2) {
					System.out.println("invalid usage, Usage: java Project GetItems <itemCode>");
					System.exit(1);
				} else {
					cstmt = conn.prepareCall("{call GetItems(?)}" );
					cstmt.setString(1, args[1]);
					resultSet = cstmt.executeQuery();
				}
			}
			else if (args[0].equals("GetShipments")) {
				if(args.length != 2) {
					System.out.println("invalid usage, Usage: java Project GetShipments <itemCode>");
					System.exit(1);
				} else {
					cstmt = conn.prepareCall("{call GetShipments(?)}" );
					cstmt.setString(1, args[1]);
					resultSet = cstmt.executeQuery();
				}
			}
			else if (args[0].equals("GetPurchases")) {
				if(args.length != 2) {
					System.out.println("invalid usage, Usage: java Project GetPurchases <itemCode>");
					System.exit(1);
				} else {
					cstmt = conn.prepareCall("{call GetAllShipments(?)}" );
					cstmt.setString(1, args[1]);
					resultSet = cstmt.executeQuery();
				}
			}
			else if (args[0].equals("ItemsAvailable")) {
				if(args.length != 2) {
					System.out.println("invalid usage, Usage: java Project ItemsAvailable <itemCode>");
					System.exit(1);
				} else {
					cstmt = conn.prepareCall("{call ItemsAvailable(?)}");
					cstmt.setString(1, args[1]);
					resultSet = cstmt.executeQuery();
				}
			}
			else if (args[0].equals("UpdateItem")) {
				if(args.length != 3) {
					System.out.println("invalid usage, Usage: java Project UpdateItem <itemCode> <price>");
					System.exit(1);
				} else {
					cstmt = conn.prepareCall("{call UpdateItem(?,?)}" );
					cstmt.setString(1, args[1]);
					cstmt.setDouble(2, Double.parseDouble(args[2]));
					cstmt.executeUpdate();
				}
			}
			else if (args[0].equals("DeleteItem")) {
				if(args.length != 2) {
					System.out.println("invalid usage, Usage: java Project DeleteItem <itemCode>");
					System.exit(1);
				} else {
					cstmt = conn.prepareCall("{call DeleteItem(?)}" );
					cstmt.setString(1, args[1]);
					cstmt.executeUpdate();
				}
			}
			else if (args[0].equals("DeleteShipment")) {
				if(args.length != 2) {
					System.out.println("invalid usage, Usage: java Project DeleteShipment <itemCode>");
					System.exit(1);
				} else {
					cstmt = conn.prepareCall("{call DeleteShipment(?)}" );
					cstmt.setString(1, args[1]);
					cstmt.executeUpdate();
				}
			}
			else if (args[0].equals("DeletePurchase")) {
				if(args.length != 2) {
					System.out.println("invalid usage, Usage: java Project DeletePurchase <itemCode>");
					System.exit(1);
				} else {
					cstmt = conn.prepareCall("{call DeletePurchase(?)}" );
					cstmt.setString(1, args[1]);
					cstmt.executeUpdate();
				}
			} else {
				System.out.println("No arguments used, input: java Project /? for all usage commands");
			}

			if (resultSet != null) {
				ResultSetMetaData rsmd = resultSet.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				while (resultSet.next()) {
					for (int i = 1; i <= columnsNumber; i++) {
						if (i > 1) System.out.print(",  ");
						String columnValue = resultSet.getString(i);
						System.out.print(columnValue + " " + rsmd.getColumnName(i));
					}
					System.out.println(" ");
				}
			}


		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (cstmt != null) {
				cstmt.close();
			}
			conn.setAutoCommit(true); // restore dafault mode
			conn.close();
		}
	}
}
