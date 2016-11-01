# Widgets Workshop (src files)

Sources are developed and published by [Automician](http://automician.com) team

## About
This project is a result of building automation from scratch for [Grible](grible.org) in the "more or less" true OOP style:) 

Grible is rather useless web app, but with pretty complicated UI (many complex elements/blocks repeated through different pages, excel-style talbes, etc.), implemented under the hood in rather ugly way. This makes it very good example in context of automation;)

"The true OOP" style means that test model (pages, complex blocks of elements) are implemented using "more or less" OOP approach, where every "block of html elements" that reflect some real "entity on UI" is represented in the code as an object (aka widget or ElementObject). 

This approach is the most powerful in languages like Java (since it does not support other paradigms, like Functional Programming in a good enough way). And though this power is actually not needed on the majority of projects:) - there may be good reasons to use it:

- if you or your Lead are "fa-OOP-natics" :)
- if you want to impress your management with "state of the art OOP like automation"
- if for some reason you have to use OOP on your project
  - you have very very complicated UI and ugly inside, e.g. given block of elements that looks similar but have different implementation on different pages, - you have to implement some bulk operations on them (you need OOP Abstraction and Polymorphism here)
  - you have to use two browsers in one test
    - e.g. if you are testing social network you may want to open two browsers with different users logged in, and implement some scenario including steps from different users. So you definitely need a stateful object, remembering its driver/browser.
  - you have a lot of pages which have the same structure but are "parametrized" with some information which is needed in the majority of page steps. This is an exact example of "object state" and case of "OOP encapsulation".
    - usually Pages are not complicated enough in this context, and actually you need only one object of class, with no any parameters to constructor (other than driver, which can be managed automatically). So when all the time you create only one object of a page class, maybe you don't need "stateful objects" and classes, and a classic module will be completely enough, isn't it? ;)
  - etc.
- if you want to involve "newcomers with lack of development experience" into writing tests
  - but keeping them out of the "test framework boat"
    - implementation of test model is much more complicated with OOP approach than Procedural/Modular approach. But in usage this "OOP" Test Model is more handy and easy. Actually this project should show this (look for test scenario code below)
  - if you want newcomers to commit to test framework implementation, i.e. write PageObjects - then you may want to consider Procedural/Modular approach (see [diaspora tests](https://github.com/juliaviluhina/diasporatests) for example)

## Notes on sources  

Feel free to surf the sources of this project to get familiar with the implementation. There you will find comments of this style:

```java
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
```

The meaning is the following:

- comments block starts with information about preceding code
  - `+` indicates the strong point about it (pros)
  - `-` indicates the weak point about it (cons)
  - `*` may indicate "just some details"

- then follows the "alternative code" with one of the following preceding marks:
  - `>` means that "alternative code" is weaker than original
  - `>=` means that "alternative coe" is almost the same but a bit weaker than original
  - `~` means that "alternative code" is "more or less" the same, and it's more a "choice of taste"... but for somebody it may be "weaker" :)
  
- then follows the information (pros and cons) about alternative code

## The actual scenario implemented:

```java
    @Test
    public void createsTestTableBasedOnDataStorageForNewProduct() {
        GIVEN("At Home page");
        Home home = new Home();
        home.open();

        String productName = "Product " + System.currentTimeMillis();

        WHEN("New product created");

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

        Table storage =
                dataStorages.table();  // or: dataStorages.storage();

        EXPECT("Storage table with one column 'editme' and one empty row");

        storage.shouldHaveColumnHeaders("editme");
        storage.shouldHaveRows(
                asList("")
        );

        THEN("edit current column name and add one more column");

        storage.column(0).setName("user");
        storage.addColumnAfter(0, "password");

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
        menu.inputFor("Column name").setValue("user");
        menu.inputFor("Data storage").click();
        menu.selectFor("Data storage").selectOption("Credentials");
        menu.select("Save");

        THEN("Connect first cell of this column to first row from data storage");
        table.row(0).cell(0).fill("1");
        table.row(0).cell(0).hover();

        EXPECT("Connection works by showing connected data in a cell tooltip (on hover)");
        table.toolTip().shouldHaveKeyRowCells("", "user", "password");
        table.toolTip().shouldHaveValueRowCells("1", "vasya", "pupkin1234");

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
```

It's very long, which may not be very good, but actually it reflects the main business flow of the grible. It's pretty good to have at least 1 (and most probably not more) such long End to End scenario implemented for "covering integration aspects of testing". Though it may be also a good idea to break this long scenario into two or three separate ones, but better to take such decision on real project taking into account it specifics.

The Allure Report for this scenario will look like this:

```
Testcase: createsTestTableBasedOnDataStorageForNewProduct

Creates test table based on data storage for new product Severity: NORMAL

[12:42:49.959] GIVEN [At Home page] (2ms)
[12:42:49.970] Open (4s 869ms)
[12:42:54.841] WHEN [New product created] (0)
+
[12:42:54.843] Add product [Product 1477734174841] (1s 356ms) 8 sub-steps
[12:42:56.201] AND [Its Data Storages opened] (0)
+
[12:42:56.203] Open product [Product 1477734174841] (1s 345ms) 3 sub-steps
+
[12:42:57.549] Open data storages (889ms) 2 sub-steps
[12:42:58.439] THEN [Create two Data Storages under new category created] (0)
+
[12:42:58.439] Add category [Users] (702ms) 5 sub-steps
+
[12:42:59.142] Add data storage [Users, Blobs] (608ms) 11 sub-steps
+
[12:42:59.751] Add data storage [Users, Credentials] (759ms) 11 sub-steps
[12:43:00.511] EXPECT [Storage table with one column 'editme' and one empty row] (0)
+
[12:43:00.523] Table (11ms)
[12:43:00.524] Should have column headers [ [editme]] (352ms) 2 sub-steps
+
[12:43:00.876] Should have rows [[ []]] (605ms) 5 sub-steps
[12:43:01.481] THEN [edit current column name and add one more column] (0)
+
[12:43:01.482] Column [0] (3ms) 3 sub-steps
+
[12:43:01.485] Set name [user] (383ms) 9 sub-steps
+
[12:43:01.869] Add column after [0, password] (885ms) 22 sub-steps
[12:43:02.754] THEN [Fill existing table row with data] (0)
+
[12:43:02.755] Row [0] (1ms) 2 sub-steps
+
[12:43:02.756] Fill [ [vasya, pupkin1234]] (1s 227ms) 12 sub-steps
+
[12:43:03.984] Should have rows [[ [vasya, pupkin1234]]] (629ms) 5 sub-steps
[12:43:04.613] AND [Add one more row with data] (0)
+
[12:43:04.614] Add row after [0] (379ms) 11 sub-steps
+
[12:43:04.993] Fill [ [masha, 4321ahsam]] (1s 110ms) 12 sub-steps
+
[12:43:06.103] Should have rows [[[vasya, pupkin 1234], [masha, 4321ahsam]]] (933ms) 8 sub-steps
[12:43:07.037] THEN [Save changes] (0)
+
[12:43:07.038] Save (97ms) 1 sub-step
[12:43:07.136] THEN [Open product test tables] (0)
[12:43:07.136] Open (157ms)
+
[12:43:07.294] Open test tables (362ms) 2 sub-steps
[12:43:07.657] AND [Add new test table under new category] (0)
+
[12:43:07.657] Add category [Users] (546ms) 5 sub-steps
+
[12:43:08.204] Add test table [Users, LoginValidation] (545ms) 6 sub-steps
[12:43:08.749] EXPECT [Test table with one column 'editme' and one empty row] (0)
+
[12:43:08.750] Table (0)
[12:43:08.750] Should have column headers [ [editme]] (468ms) 2 sub-steps
+
[12:43:09.218] Should have rows [[ []]] (612ms) 5 sub-steps
[12:43:09.830] THEN [Configure first column to be connected to data storage prepared above] (0)
+
[12:43:09.831] Column [0] (1ms) 3 sub-steps
[12:43:09.832] Menu (0)
[12:43:09.832] Open (127ms)
[12:43:09.960] Input for [Column name] (0)
+
[12:43:09.960] Set value [user] (153ms) 2 sub-steps
[12:43:10.113] Input for [Data storage] (0)
+
[12:43:10.114] Click (96ms) 1 sub-step
[12:43:10.210] Select for [Data storage] (1ms)
+
[12:43:10.211] Select option [Credentials] (120ms) 1 sub-step
[12:43:10.332] Select [Save] (112ms)
[12:43:10.445] THEN [Connect first cell of this column to first row from data storage] (0)
+
[12:43:10.445] Row [0] (1ms) 2 sub-steps
+
[12:43:10.446] Cell [0] (54ms) 1 sub-step
[12:43:10.501] Fill [1] (586ms)
+
[12:43:11.089] Row [0] (0) 2 sub-steps
+
[12:43:11.090] Cell [0] (22ms) 1 sub-step
[12:43:11.112] Hover (63ms)
[12:43:11.176] EXPECT [Connection works by showing connected data in a cell tooltip (on hover)] (0)
[12:43:11.176] Tool tip (0)
[12:43:11.177] Should have key row cells [ [, user, password]] (312ms)
[12:43:11.490] Tool tip (0)
[12:43:11.490] Should have value row cells [ [1, vasya, pupkin1234]] (309ms)
[12:43:11.800] THEN [Adds one more column for data and fill its cell] (0)
+
[12:43:11.800] Add column after [0, login valid?] (685ms) 22 sub-steps
+
[12:43:12.485] Row [0] (1ms) 2 sub-steps
+
[12:43:12.486] Cell [1] (29ms) 1 sub-step
[12:43:12.515] Fill [true] (411ms)
+
[12:43:12.926] Should have rows [[ [1, true]]] (586ms) 5 sub-steps
[12:43:13.512] THEN [Adds one more row with both connected data storage data and new data] (0)
+
[12:43:13.512] Row [0] (1ms) 2 sub-steps
+
[12:43:13.513] Cell [0] (20ms) 1 sub-step
[12:43:13.533] Menu (0)
[12:43:13.534] Open (156ms)
[12:43:13.691] Select [Insert row below] (106ms)
+
[12:43:13.798] Row [1] (0) 2 sub-steps
+
[12:43:13.799] Fill [ [2, false]] (1s 46ms) 12 sub-steps
+
[12:43:14.846] Should have rows [[[1, true], [2, false]]] (1s 35ms) 8 sub-steps
+
[12:43:15.882] Row [1] (1ms) 2 sub-steps
+
[12:43:15.883] Cell [0] (19ms) 1 sub-step
[12:43:15.902] Hover (114ms)
[12:43:16.017] Tool tip (0)
[12:43:16.017] Should have key row cells [ [, user, password]] (296ms)
[12:43:16.313] Tool tip (0)
[12:43:16.313] Should have value row cells [ [2, masha, 4321ahsam]] (292ms)
[12:43:16.606] THEN [Save changes] (0)
+
[12:43:16.606] Save (96ms) 1 sub-step
+
[12:43:16.954] Should have rows [[[1, true], [2, false]]] (970ms) 8 sub-steps
The test has passed! 
```