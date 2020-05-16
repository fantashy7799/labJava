package Lab4DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Lab4DataBase.*;
import javax.sound.sampled.LineListener;
import javax.swing.JOptionPane;

public class DataBase {
	

	static public DataBase instance() {
		while (dataBase == null) {
			Scanner scanner = new Scanner(System.in);
			String username = "root";
			String password = "0983071404";
			try {
				dataBase = new DataBase(username, password);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dataBase;
	}
	
	private static DataBase dataBase = null;
	private static String name = "manager";
	private static int prodid = 1;
	private Connection connection;

	private DataBase(String username, String password) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/databasemaneger?useSSL=false";
		this.connection = DriverManager.getConnection(url, username, password);
		createProductsTable(10);
	}
	
	public ArrayList<Good> getData() {
		String showQuery = "SELECT * FROM " + name;
		var arrayResult=new ArrayList<Good>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(showQuery);
			arrayResult=show(resultSet);
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return arrayResult;
	}

	private void createProductsTable(int N) {
		String dropQuery = "SHOW TABLES LIKE '" + name + "'";

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(dropQuery);

			if (resultSet != null) {
				if (resultSet.next()) {
					clearTable();
				} else {
					String createQuery = "CREATE TABLE " + name + "(" + "id INT NOT NULL AUTO_INCREMENT, "
							+ "prodid INT NOT NULL, " + "title VARCHAR(90) NOT NULL UNIQUE  , "
							+ "cost DOUBLE NOT NULL," + "PRIMARY KEY (id)" + ");";
					executeUpdate(createQuery);
				}
			}
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		String title = "product";

		Random random = new Random();

		for (int i = 1; i <= N; ++i) {
			int titleNumber = random.nextInt(1001);
			double cost = random.nextInt(10001);
			addProduct(title + titleNumber, cost);
		}

	}

	private boolean executeUpdate(String query) {
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
		return true;
	}

	private void clearTable() {
		String clearQuery = "TRUNCATE TABLE " + name;
		executeUpdate(clearQuery);
	}

	public boolean addProduct(String title, double cost) {
		String addQuery = "INSERT INTO " + name + " (prodid, title, cost) VALUES (" + prodid + ", '" + title + "', "
				+ cost + ')';
		var t=executeUpdate(addQuery);

		++prodid;
		return t;
	}

	public boolean deleteProduct(String title) {
		var sizeOld=getData().size();
		String deleteQuery = "DELETE FROM " + name + " WHERE title = '" + title + "'";
		executeUpdate(deleteQuery);
		var sizeNew=getData().size();
		return sizeNew!=sizeOld;
	}

	private ArrayList<Good> show(ResultSet resultSet) throws SQLException {
		var arrayResult=new ArrayList<Good>();
		if (resultSet != null) {
			while (resultSet.next()) {
				int prodid = resultSet.getInt("prodid");
				String title = resultSet.getString("title");
				double cost = resultSet.getDouble("cost");
				arrayResult.add(new Good(prodid, title, cost));
			}
			resultSet.close();
			return arrayResult;
		} else {
			System.out.println("There are no records");
		}
		return null;
	}
	
	public Good getPriceByTitle(String title) {
		String priceQuery = "SELECT * FROM " + name + " WHERE title ='" + title + "'";

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(priceQuery);
			if (resultSet != null) {
				try {
					resultSet.next();
					return new Good(resultSet.getInt("prodid"), title, resultSet.getDouble("cost"));
					//System.out.println("Product with title " + title + " has price " + resultSet.getInt("cost"));
				} catch (SQLException e) {
					//System.out.println(e.getMessage());
				}
			} else {//System.out.println("There is no product with such title");
			}
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		JOptionPane.showMessageDialog(null, title+" not found");
		return null;
	}

	public void changePrice(String title, double newPrice) {
		if(DataBase.instance().getPriceByTitle(title)==null) return;
		String changeQuery = "UPDATE " + name + " SET cost = " + newPrice + " WHERE title = '" + title + "'";
		executeUpdate(changeQuery);
	}

	public ArrayList<Good> showProductsInPriceRange(double leftBorder, double rightBorder) {
		ArrayList<Good> result=new ArrayList<Good>();
		if (leftBorder > rightBorder) {
			double temp = leftBorder;
			leftBorder = rightBorder;
			rightBorder = temp;
		}
		String showQuery = "SELECT * FROM " + name + " WHERE cost BETWEEN " + leftBorder + " AND " + rightBorder;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(showQuery);
			result= show(resultSet);
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
