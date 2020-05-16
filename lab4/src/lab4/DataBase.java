package lab4;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import lab4.*;
import javax.sound.sampled.LineListener;

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

	private void executeUpdate(String query) {
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void clearTable() {
		String clearQuery = "TRUNCATE TABLE " + name;
		executeUpdate(clearQuery);
	}

	public void addProduct(String title, double cost) {
		String addQuery = "INSERT INTO " + name + " (prodid, title, cost) VALUES (" + prodid + ", '" + title + "', "
				+ cost + ')';
		executeUpdate(addQuery);

		++prodid;
	}

	public void deleteProduct(String title) {
		String deleteQuery = "DELETE FROM " + name + " WHERE title = '" + title + "'";
		executeUpdate(deleteQuery);
	}

	private void show(ResultSet resultSet) throws SQLException {
		if (resultSet != null) {
			System.out.println("prodid title cost");
			while (resultSet.next()) {
				int prodid = resultSet.getInt("prodid");
				String title = resultSet.getString("title");
				double cost = resultSet.getDouble("cost");
				System.out.println(prodid + " " + title + " " + cost);
			}
			resultSet.close();
		} else {
			System.out.println("There are no records");
		}

	}

	public void showProducts() {
		String showQuery = "SELECT * FROM " + name;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(showQuery);
			show(resultSet);
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void getPriceByTitle(String title) {
		String priceQuery = "SELECT cost FROM " + name + " WHERE title ='" + title + "'";

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(priceQuery);
			if (resultSet != null) {
				try {
					resultSet.next();
					System.out.println("Product with title " + title + " has price " + resultSet.getInt("cost"));
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("There is no product with such title");
			}
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void changePrice(String title, double newPrice) {
		String changeQuery = "UPDATE " + name + " SET cost = " + newPrice + " WHERE title = '" + title + "'";
		executeUpdate(changeQuery);
	}

	public void showProductsInPriceRange(double leftBorder, double rightBorder) {
		if (leftBorder > rightBorder) {
			double temp = leftBorder;
			leftBorder = rightBorder;
			rightBorder = temp;
		}
		String showQuery = "SELECT * FROM " + name + " WHERE cost BETWEEN " + leftBorder + " AND " + rightBorder;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(showQuery);
			show(resultSet);
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
