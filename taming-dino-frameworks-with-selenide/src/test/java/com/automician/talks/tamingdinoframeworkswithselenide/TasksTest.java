package com.automician.talks.tamingdinoframeworkswithselenide;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.*;


/*
* This code shows how to integrate Selenide into Selenium tests via SelenideDriver (which can be found at https://gist.github.com/yashaka/dc7607239518bd37298ef5eb5b08da9b)
*/
public class TasksTest {

    WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void tasksLifeCycle() {
        Tasks tasks = new Tasks(driver);

        driver.get("https://todomvc4tasj.herokuapp.com/");

        tasks.add("a", "b", "c");
        tasks.shouldHaveSize(3);

        tasks.toggleAll();
        tasks.clearCompleted();
        tasks.shouldBeEmpty();
    }
    public class Tasks {

        private final WebDriver driver;
        private final SelenideDriver I;

        @FindBy(css = "#todo-list>li")
        List<WebElement> list;

        @FindBy(css = "#new-todo")
        WebElement newTodo;

        public Tasks(WebDriver driver){
            this.driver = driver; //keeping old Selenium WebDriver API just in case :)
            this.I = new SelenideDriver(driver); // integrating Selenide API via this.I instance of SelenideDriver
            PageFactory.initElements(this.I, this); //turning on Selenide's implicit waits for WebElement
        }

        /*
         * "Legacy" Selenium Webdriver implementation
         * where elements are created
         * via PageFactory by SelenideDriver
         */

        public void shouldHaveSize(int size) {
            Assert.assertEquals(list.size(), size);
        }

        public void add(String... texts){
            for (String text : texts) {
                // tuning unstable parts via wrapping PageFactory elements to Selenide's API tools
                I.find(newTodo).shouldBe(enabled).sendKeys(text + Keys.ENTER);
            }
        }

        /*
         * "New" Direct Selenide implementation via SelenideDriver
         */

        public void toggleAll() {
            I.find("#toggle-all").click();
        }

        public void clearCompleted() {
            I.find("#clear-completed").click();
            I.find("#clear-completed").shouldBe(hidden);
        }

        public void shouldBeEmpty() {
        /*
         * still works with PageFactory elements like this.list
         */
            I.findAll(list).filterBy(visible).shouldBe(empty);
        }

    }
}
