package com.automician.workshops;

import com.automician.workshops.pages.DataStorages;
import com.automician.workshops.pages.Home;
import com.automician.workshops.pages.Product;
import com.automician.workshops.widgets.DataStorage;
import com.codeborne.selenide.Configuration;
import org.junit.Test;

import static java.util.Arrays.asList;

public class GribleTest {
    {
        Configuration.fastSetValue = true;
    }

    @Test
    public void createsTestTableBasedOnDataStorageForNewProduct() {
        Home home = new Home();
        home.open();

        String productName = "Product " + System.currentTimeMillis();
        home.addProduct(productName, productName);
        Product product = home.openProduct(productName);

        DataStorages dataStorages = product.openDataStorages();

        dataStorages.addCategory("Users");
        dataStorages.addDataStorage("Users", "Credentials");

        DataStorage storage = new DataStorage();
        storage.column(0).setName("user");
        storage.addColumnAfter(0, "password");
        storage.row(0).fill("vasya", "pupkin1234");
//        storage.shouldHaveRows(
//                asList("vasya", "pupkin1234")
//        );
//        storage.addRowAfter(0);
//        storage.row(1).fill("masha", "4321ahsam");
//        storage.shouldHaveRows(
//                asList("vasya", "pupkin1234"),
//                asList("masha", "4321ahsam")
//        );
//        storage.save();
//
//        product.open();
//        product.openTestTables();
    }
}
