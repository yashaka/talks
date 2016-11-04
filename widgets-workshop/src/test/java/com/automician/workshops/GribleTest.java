package com.automician.workshops;

import com.automician.workshops.configs.BaseTest;
import com.automician.workshops.pages.DataStorages;
import com.automician.workshops.pages.Home;
import com.automician.workshops.pages.Product;
import com.automician.workshops.pages.TestTables;
import com.automician.workshops.widgets.*;
import com.automician.workshops.widgets.paradigmshift.Form;
import com.automician.workshops.widgets.paradigmshift.InputValue;
import com.automician.workshops.widgets.paradigmshift.SelectValue;
import com.codeborne.selenide.Configuration;
import org.junit.Test;

import static com.automician.worshops.core.Gherkin.*;
import static com.codeborne.selenide.Selenide.*;
import static java.util.Arrays.asList;

public class GribleTest extends BaseTest{

    /* + hides common non-test-logic technical details in a parent base class
     * >
public class GribleTest {
    {
        Configuration.fastSetValue = true;
        Configuration.baseUrl = "http://0.0.0.0:8123";
    }
...
}
     *
     */

    @Test
    public void createsTestTableBasedOnDataStorageForNewProduct() {
        GIVEN("At Home page");
        Home home = new Home();
        home.open();

        /* * emulates "BDD's Gherkin"
         * + Keeps all details in Allure Report
         *   [12:42:49.959] GIVEN [At Home page] (2ms)
         *   [12:42:49.970] Open (4s 869ms)
         * - kind of "Spike"
         *   * would be great to enhance Allure Reports
         *     to report at least classes of objects
         *   + though not a bigger spike than "BDD as a tool for reports" ;)

         * ~
         app.home().open();
         * * or: `_.home().open()`
         *   underscore `_` can be chosen just as more laconic alternative
         * + Keeps all details in Allure Report
         *    [12:42:49.959] Home (2s)
         *    [12:42:49.970] Open (4s 869ms)
         *    - though these details are not so structured
         *      as in "emulated Gherkin version"
         *      where Gherkin GIVEN/WHEN/THEN serve as titles
         *      (because also of non-ideal implementation in Allure
         *      of reporting for "chainable method calls)
         * - needs additional implementation
         *   which may be pretty bulky
         *   and complicated
               (if there will be performance issues
                (the probability is rather low though)
                you may need to implement a "singleton" behavior
                for such methods like home()
                which actually just did `return new Home()`...)
         * + though maybe even more handy in usage for newcomers
         *   because creates a "one entry point" to all "widgets and pages" model
         * - still not all-powerful
         *   in case you have a long chain:
         *     app.dataStorages().table().row(0).cell(0).fill("vasya")
         *     app.dataStorages().table().row(0).cell(1).fill("qwerty")
         *   you may want to break it down to
         *     Table storage = app.dataStorages().table();
         *
         *     storage.row(0).cell(0).fill("vasya");
         *     storage.row(0).cell(1).fill("qwerty");
         *   and now all "better reporting capabilities" are broken again :(

         * >
        Home home = new Home();
        home.open();
         *  - lacks details in Allure Report:
         *    [12:42:49.970] Open (4s 869ms)
         */

        String productName = "Product " + System.currentTimeMillis();

        WHEN("New product created");
        /* >
        WHEN("New product created: " + productName);
         */

        home.addProduct(productName);

        AND("Its Data Storages opened");
        Product product =
                home.openProduct(productName);

        DataStorages dataStorages =
                product.openDataStorages();

        THEN("Create two Data Storages under new category created");
        dataStorages.addCategory("Users");
        dataStorages.addDataStorage("Users", "Blobs");
        dataStorages.addDataStorage("Users", "Credentials");

        EXPECT("Storage table with one column 'editme' and one empty row");
        Table storage =
                dataStorages.table();  // or: dataStorages.storage();

        storage.shouldHaveColumnHeaders("editme");
        storage.shouldHaveRows(
                asList("")
        );

        THEN("edit current column name and add one more column");

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

        THEN("Fill existing table row with data");
        storage.row(0).fill("vasya", "pupkin1234");
        storage.shouldHaveRows(
                asList("vasya", "pupkin1234")
        );

        AND("Add one more row with data");
        storage.addRowAfter(0).fill("masha", "4321ahsam");
        storage.shouldHaveRows(
                asList("vasya", "pupkin1234"),
                asList("masha", "4321ahsam")
        );

        THEN("Save changes");
        dataStorages.save();

        /* + more "obvious" and friendly for "newcomer"
         * - needs additional DataStorages#save method in context of implementation

         * ~
        new ManageButtons().save();
         * + KISS
         * - "newcomer" need to learn more "widgets" to start using such approach
         */

        THEN("Open product test tables");
        product.open();
        TestTables testTables =
                product.openTestTables();

        AND("Add new test table under new category");
        testTables.addCategory("Users");
        testTables.addTestTable("Users", "LoginValidation");

        EXPECT("Test table with one column 'editme' and one empty row");
        Table table =
                testTables.table();  // or: dataStorages.storage();

        storage.shouldHaveColumnHeaders("editme");
        storage.shouldHaveRows(
                asList("")
        );

        THEN("Configure first column to be connected to data storage prepared above");

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

        /* ~
        new Form(menu.element()).fill(
                new InputValue("Column name", "user"),
                new SelectValue("Data storage", "Credentials")
        );
         */

        /* <
        menu.form().fill(
                ValueFor.input("Column name", "user"),
                ValueFor.select("DataStorage", "Credentials")
        );
        */

        menu.select("Save");

        THEN("Connect first cell of this column to first row from data storage");
        table.row(0).cell(0).fill("1");
        table.row(0).cell(0).hover();

        EXPECT("Connection works by showing connected data in a cell tooltip (on hover)");
        table.toolTip().shouldHaveKeyRowCells("", "user", "password");
        table.toolTip().shouldHaveValueRowCells("1", "vasya", "pupkin1234");

        /* ~
        new TableToolTip().shouldHaveKeyRowCells("", "user", "password");
        new TableToolTip().shouldHaveValueRowCells("1", "vasya", "pupkin1234");
         */

        THEN("Adds one more column for data and fill its cell");
        table.addColumnAfter(0, "login valid?");

        table.row(0).cell(1).fill("true");

        table.shouldHaveRows(
                asList("1", "true")
        );

        THEN("Adds one more row with both connected data storage data and new data");
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

        THEN("Save changes");
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
