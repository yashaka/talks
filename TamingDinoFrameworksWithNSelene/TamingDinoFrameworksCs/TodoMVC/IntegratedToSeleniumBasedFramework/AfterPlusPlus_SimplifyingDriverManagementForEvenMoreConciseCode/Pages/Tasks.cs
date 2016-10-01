using System;
using OpenQA.Selenium;
using NSelene;
using static NSelene.Selene;
using NSelene.Conditions;
using NSelene.Support.Extensions;

namespace TamingDinoFrameworksCs.TodoMVC.IntegratedToSeleniumBasedFramework.AfterPlusPlus.Pages
{
    public class Tasks
    {

        /*
         * If you will never write a test where you will open two browsers at once,
         * Then it's completely enough to use semi-automatic driver management 
         * with static helpers - Selene.S & Selene.SS (aka Find & FindALl)
         *      to create and find elements. 
         * They will use drivers created via Selene.SetWebDriver per thread
         * And so will work for tests being run in parallel. 
         * Actually this is what is actually needed in Acceptance Web UI Automation.
         * And this is how it is implemented in original Selenide, which is used 
         * on many projects and everybody are happy with such implementation there:)
         * 
         * Once more:
         * 
         * "many browsers but one per each parallel test" will work
         * 
         * but "many browsers in one test" will not work with Selene.S & Selene.SS helpers. 
         *      You should use SeleneDriver#Find & SeleneDriver#FindAll instead
         *      As it was shown in After & AfterPlus* namespaces.
         */

        SeleneCollection list = SS("#todo-list>li");

        SeleneElement clearCompleted = S("#clear-completed");

        public void Visit()
        {
            Open("https://todomvc4tasj.herokuapp.com/");
        }

        public void FilterActive()
        {
            S(By.LinkText("Active")).Click();
        }

        public void FilterCompleted()
        {
            S(By.LinkText("Completed")).Click();
        }

        public void Add(params string[] taskTexts)
        {
            foreach (var text in taskTexts)
            {
                S("#new-todo").Should(Be.Enabled).SetValue(text).PressEnter();
            }
        }

        public void Toggle(string taskText)
        {
            list.FindBy(Have.ExactText(taskText)).Find(".toggle").Click();
        }

        public void ClearCompleted()
        {
            clearCompleted.Click();
            clearCompleted.ShouldNot(Be.Visible);
        }

        public void ShouldBe(params string[] names)
        {
            list.FilterBy(Be.Visible).Should(Have.Texts(names));
        }

        public void ShouldBeEmpty(params string[] names)
        {
            list.FilterBy(Be.Visible).Should(Be.Empty);
        }
    }

}
