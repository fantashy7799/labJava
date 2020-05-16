//package Lab4DataBase;
//
//import java.util.*;
//
//public class Parser {
//    private Scanner scanner;
//    private HashMap<String, Runnable> map;
//
//    public Parser() {
//        this.scanner = new Scanner(System.in);
//        this.scanner.useLocale(Locale.US);
//
//        this.map = new HashMap<>();
//        this.map.put("/add", this::parseAdd);
//        this.map.put("/delete", this::parseDelete);
//        this.map.put("/show_all", this::parseShowAll);
//        this.map.put("/price", this::parseGetPrice);
//        this.map.put("/change_price", this::parseChangePrice);
//        this.map.put("/filter_by_price", this::parseFilterByPrice);
//    }
//
//    public void process() {
//        DataBase.instance();
//        System.out.println("Enter command to interact with data base:");
//        while(scanner.hasNext()) {
//            parseCommand();
//        }
//    }
//
//    private void parseAdd() {
//        String name;
//        double cost;
//        try {
//            name = parseName();
//            cost = parseCost();
//        }
//        catch (NoSuchElementException e) {
//            System.out.println(e.getMessage());
//            scanner.nextLine();
//            return;
//        }
//        scanner.nextLine();
//
//        DataBase.instance().addProduct(name, cost);
//    }
//
//    private void parseDelete() {
//        String name;
//        try {
//            name = parseName();
//        }
//        catch (NoSuchElementException e) {
//            System.out.println(e.getMessage());
//            scanner.nextLine();
//            return;
//        }
//        scanner.nextLine();
//
//        DataBase.instance().deleteProduct(name);
//    }
//
//    private void parseShowAll() {
//        if (scanner.nextLine().length() > 0) {
//            System.out.println("Command /show_all mustn't contains arguments");
//        }
//
//        DataBase.instance().showProducts();
//    }
//
//    private void parseGetPrice() {
//        String name;
//        try {
//            name = parseName();
//        }
//        catch (NoSuchElementException e) {
//            System.out.println(e.getMessage());
//            scanner.nextLine();
//            return;
//        }
//        scanner.nextLine();
//
//        DataBase.instance().getPriceByTitle(name);
//    }
//
//    private void parseChangePrice() {
//        String name;
//        double cost;
//        try {
//            name = parseName();
//            cost = parseCost();
//        }
//        catch (NoSuchElementException e) {
//            System.out.println(e.getMessage());
//            scanner.nextLine();
//            return;
//        }
//        scanner.nextLine();
//
//        DataBase.instance().changePrice(name, cost);
//    }
//
//    private void parseFilterByPrice() {
//        double leftBorder;
//        double rightBorder;
//        try {
//            leftBorder = parseCost();
//            rightBorder = parseCost();
//        }
//        catch (NoSuchElementException e) {
//            System.out.println(e.getMessage());
//            scanner.nextLine();
//            return;
//        }
//        scanner.nextLine();
//
//        DataBase.instance().showProductsInPriceRange(leftBorder, rightBorder);
//    }
//
//    private void parseCommand() {
//        String command = scanner.next();
//        if (map.containsKey(command)) {
//            map.get(command).run();
//        }
//        else {
//            System.out.println("There is no such commands. Try to type another: ");
//            scanner.nextLine();
//        }
//    }
//
//    private String parseName() throws NoSuchElementException {
//        String name;
//        if (scanner.hasNext()) {
//            name = scanner.next();
//        }
//        else {
//            throw new NoSuchElementException("You must pass name with this command");
//        }
//
//        return name;
//    }
//
//    private double parseCost() throws NoSuchElementException {
//        double cost;
//        if (scanner.hasNextDouble()) {
//            cost = scanner.nextDouble();
//        }
//        else {
//            throw new NoSuchElementException("You must pass cost with this command");
//        }
//
//        return cost;
//    }
//}
