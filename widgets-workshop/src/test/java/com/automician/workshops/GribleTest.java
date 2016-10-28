package com.automician.workshops;

import com.automician.workshops.pages.DataStorages;
import com.automician.workshops.pages.Home;
import com.automician.workshops.pages.Product;
import com.automician.workshops.pages.TestTables;
import com.automician.workshops.widgets.*;
import com.codeborne.selenide.Configuration;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static java.util.Arrays.asList;

public class GribleTest {
    {
        Configuration.fastSetValue = true;
        Configuration.baseUrl = "http://0.0.0.0:8123";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void createsTestTableBasedOnDataStorageForNewProduct() {
        Home home = new Home();
        home.open();

        String productName = "Product " + System.currentTimeMillis();

        home.addProduct(productName, productName);

        Product product =
                home.openProduct(productName);

        DataStorages dataStorages =
                product.openDataStorages();

        dataStorages.addCategory("Users");
        dataStorages.addDataStorage("Users", "Blobs");
        dataStorages.addDataStorage("Users", "Credentials");

        Table storage =
                dataStorages.table();  // or: dataStorages.storage();

        storage.column(0).setName("user");
        storage.addColumnAfter(0, "password");

        /* + more "obvious" and friendly for "newcomer"
         * - needs additional DataStorages#table method in context of implementation
         *   - and then also additional TestTable#table method
         *     with the same "kind of" duplicated implementation

         * ~
        Table table = new Table();

        table.column(0).setName("user");
        table.addColumnAfter(0, "password");
         * + KISS
         * + table as widget can be reused on other relevant pages
         * - "newcomer" need to learn more "widgets" to start using such approach
         */

        storage.row(0).fill("vasya", "pupkin1234");
        storage.shouldHaveRows(
                asList("vasya", "pupkin1234")
        );

        storage.addRowAfter(0).fill("masha", "4321ahsam");
        storage.shouldHaveRows(
                asList("vasya", "pupkin1234"),
                asList("masha", "4321ahsam")
        );

        dataStorages.save();

        /* + more "obvious" and friendly for "newcomer"
         * - needs additional DataStorages#save method in context of implementation

         * ~
        new ManageButtons().save();
         * + KISS
         * - "newcomer" need to learn more "widgets" to start using such approach
         */

        product.open();
        TestTables testTables =
                product.openTestTables();

        testTables.addCategory("Users");
        testTables.addTestTable("Users", "LoginValidation");

        Table table =
                testTables.table();  // or: dataStorages.storage();

        ContextMenu menu;

        menu = table.column(0).menu();
        menu.open();
        /* + more readable
         * ~
        menu = table.column(0).menu().open();
         * + more laconic
         * - less readable because too long
         */
        menu.inputFor("Column name").setValue("user");
        menu.inputFor("Data storage").click();
        menu.selectFor("Data storage").selectOption("Credentials");
        menu.select("Save");

        table.row(0).cell(0).fill("1");
        table.row(0).cell(0).hover();
        table.toolTip().shouldHaveKeyRowCells("", "user", "password");
        table.toolTip().shouldHaveValueRowCells("1", "vasya", "pupkin1234");

        /* ~
        new TableToolTip().shouldHaveKeyRowCells("", "user", "password");
        new TableToolTip().shouldHaveValueRowCells("1", "vasya", "pupkin1234");
         */

        menu = table.row(0).cell(0).menu();
        menu.open();
        menu.select("Insert column on the right");
        table.column(1).setName("login valid?");

        table.row(0).cell(1).fill("true");

        table.shouldHaveRows(
                asList("1", "true")
        );

        menu = table.row(0).cell(0).menu();
        menu.open();
        menu.select("Insert row below");
        table.row(1).fill("2", "false");

        table.shouldHaveRows(
                asList("1", "true"),
                asList("2", "false")
        );

        table.row(1).cell(0).hover();
        table.toolTip().shouldHaveKeyRowCells("", "user", "password");
        table.toolTip().shouldHaveValueRowCells("2", "masha", "4321ahsam");

        testTables.save();

        /* ~
        new ManageButtons().save();
         */

        refresh();

        table.shouldHaveRows(
                asList("1", "true"),
                asList("2", "false")
        );
    }
}
