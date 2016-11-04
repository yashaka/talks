This package (paradigmshift) contains additional implementation of "Form" widget. 

It was implemented during paradigm-shift webinar to show usage of OOP Abstraction and Polymorphism.


You will find it's usage in commented block 
of alternative version to the current solution:

```java
        menu.inputFor("Column name").setValue("user");
        menu.inputFor("Data storage").click();
        menu.selectFor("Data storage").selectOption("Credentials");

        /* ~
        new Form(menu.element()).fill(
                new InputValue("Column name", "user"),
                new SelectValue("Data storage", "Credentials")
        );
         */
```

Current solution is Ok enough. 


The alternative implementation is much more complicated, but wins on larger scale, when you will have too many widgets with "forms". Each time you need a method in new widget for editing some form input - with alternative implementation you will not have to create it. Because it will be already covered in Form#fill ;)