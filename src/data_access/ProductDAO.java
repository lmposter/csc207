package data_access;

import entity.Product;
import entity.ProductFactory;
import use_case.create_product.CreatePdDAI;
import use_case.search.SearchDAI;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductDAO implements SearchDAI, CreatePdDAI {
    private final File csvFile;
    private final ProductFactory productFactory;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Product> products = new HashMap<>();

    public ProductDAO(String csvPath, ProductFactory productFactory){
        this.productFactory = productFactory;
        this.csvFile = new File(csvPath);
        headers.put("id", 0);
        headers.put("title", 1);
        headers.put("inventory", 2);
        headers.put("URL", 3);
        headers.put("price", 4);

        if (csvFile.length() == 0){
            save();
        } else {
            createProducts();
        }}

    private void createProducts(){
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                if (header.equals("id,title,inventory,URL,price")) {

                    String row;
                    while ((row = reader.readLine()) != null) {
                        String[] col = row.split(",");
                        String id = String.valueOf(col[headers.get("id")]);
                        String title = String.valueOf(col[headers.get("title")]);
                        String inventory = String.valueOf(col[headers.get("inventory")]);
                        String URL = String.valueOf(col[headers.get("URL")]);
                        String price = String.valueOf(col[headers.get("price")]);
                        Product product = productFactory.create(id, title, inventory, URL, price);
                        products.put(title, product);
                    }
                }else{
                    throw new IOException();
                }

            } catch (IOException e) {
                System.out.println("Can't read file");
                System.exit(1);
            }
    }


    private void save() {
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter(csvFile));
                writer.write(String.join(",", headers.keySet()));
                writer.newLine();

                for (Product pd : products.values()) {
                    String line = String.format("%s,%s,%s,%s,%s",
                            pd.getId(), pd.getTitle(), pd.getInventory(), pd.getURL(), pd.getPrice());
                    writer.write(line);
                    writer.newLine();
                }

                writer.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


            @Override
    public int numItemsFound(String content) {
        return 0;
    }

    @Override
    public ArrayList<Product> getItems(String content) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String header = reader.readLine();
            ArrayList<String> titleList = new ArrayList<>();

            if (header.equals("id,title,inventory,URL,price")) {
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String title = String.valueOf(col[headers.get("title")]);
                    titleList.add(title);
                }
            }else{}

        } catch (IOException e) {
            System.out.println("Can't read file");
            System.exit(1);
        }

        return null;
    }

    @Override
    public void save(Product product) {

    }
}