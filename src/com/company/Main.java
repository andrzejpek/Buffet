package com.company;

import java.util.*;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + ", Price: " + price);
        return sb.toString();
    }
}

class Meat extends Product {
    private int weight;

    public Meat(String name, double price, int weight) {
        super(name, price);
        this.weight = weight;
    }

    @Override
    public String toString() {
        return super.toString() + ", [weight= " + weight + "gram]";
    }
}

class Addition extends Product {
    private boolean whetherPremium;
    private int supplement = 2;

    public Addition(String name, double price) {
        super(name, price);
    }

    public Addition(String name, double price, boolean whetherPremium) {
        super(name, price);
        this.whetherPremium = whetherPremium;
    }

    @Override
    public String toString() {
        return super.toString() + ", Premium: " + (whetherPremium ? "Yes, supplement is=" + supplement + "zł " : "no ");
    }
}

class Drink extends Addition {
    private int volume;

    public Drink(String name, double price, int volume) {
        super(name, price);
        this.volume = volume;
    }

    @Override
    public String toString() {
        return super.toString() + "volume= " + volume;
    }
}

class Menu {
    List<Product> produkts = new LinkedList<>();

    public void newProductMenu(Product product) {
        produkts.add(product);
    }


    public void showMenu() {
        int i = 1;
        for (Product p : produkts) {
            System.out.println("[" + i + "] " + p.toString());
            i++;
        }
    }

    public Product getProdukt(int idx) {
        return produkts.get(idx);
    }
}

class Basket {
    List<Product> productInBasket = new LinkedList<>();
    public double account;

    public void addProduct(Product product) {
        productInBasket.add(product);
    }

    public void dellProduct(int idx) {
        List<Product> newProduct = new LinkedList<>();
        int i = 0;
        if (idx > productInBasket.size()) {
            System.out.println("Entered incorrectly, enter the correct one");
        } else {
            for (Product product : productInBasket) {
                if (idx != i) {
                    newProduct.add(product);
                }
                i++;
            }
            productInBasket = newProduct;
        }
    }

    public void showBasket() {
        int i = 0;
        int j = 1;
        for (Product p : productInBasket) {
            System.out.println("[" + j + "] " + p.toString());
            account += productInBasket.get(i).getPrice();
            i++;
            j++;
        }
        System.out.println(account);
        account = 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.newProductMenu(new Meat("Pork", 14, 100));
        menu.newProductMenu(new Meat("Beef", 16, 100));
        menu.newProductMenu(new Addition("Potatoes", 6, false));
        menu.newProductMenu(new Addition("Chips", 7, true));
        menu.newProductMenu(new Addition("Salad Coleslaw", 5, false));
        menu.newProductMenu(new Addition("Tomatoes", 7, false));
        menu.newProductMenu(new Addition("Salad", 8, false));
        menu.newProductMenu(new Drink("Cola", 8, 500));
        menu.newProductMenu(new Drink("Tea", 7, 400));
        menu.newProductMenu(new Drink("Coffee", 8, 500));
        menu.showMenu();
        String command;
        Scanner scanner = new Scanner(System.in);
        Basket basket = new Basket();
        do {
            command = scanner.nextLine();
            if (!command.equalsIgnoreCase("end")) {
                try {
                    int idx = Integer.parseInt(command);
                    basket.addProduct(menu.getProdukt(idx - 1));
                } catch (Exception e) {
                    System.out.println("Entered incorrectly, enter the correct one");
                }
            }
        } while (!command.equalsIgnoreCase("end"));
        basket.showBasket();
        do {
            System.out.println("What to delete?");
            command = scanner.nextLine();
            if (!command.equalsIgnoreCase("end")) {
                try {
                    int idx = Integer.parseInt(command);
                    basket.dellProduct(idx - 1);
                } catch (Exception e) {
                    System.out.println("Entered incorrectly, enter the correct one");
                }
            }
            basket.showBasket();
        } while (!command.equalsIgnoreCase("end"));
        scanner.close();
    }
}