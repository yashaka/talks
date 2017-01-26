# Paradigm Shift (src files)

Sources are developed and published by [Automician](http://automician.com) team

## About
This project is a result of applying and comparing different programming paradigms for building web ui automation. 

It is part of the ["Paradigm Shift" course (Russian version)](https://seleniumcourses.com/products/paradigm-shift-selenide-java-in-russian)

The following applications are used as systems under tests:
- [TodoMVC](todomvc4tasj.herokuapp.com)
- [Grible](grible.org)

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
  - `>` means that "alternative code" is weaker/less-efficient than original
  - `>=` means that "alternative coe" is almost the same but a bit weaker/less-efficient than original
  - `~` means that "alternative code" is "more or less" the same, and it's more a "choice of taste"... but for somebody it may be "weaker" :)
  
- then follows the information (pros and cons) about alternative code
