using NUnit.Framework;
using System;
using OpenQA.Selenium.Firefox;
using static NSelene.Selene;
using NSelene;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using System.Collections.Generic;

namespace WaitsAndPageFactory
{
    namespace Straightforward
    {

        namespace selenium
        {
            [TestFixture]
            public class BaseTest
            {
                public IWebDriver driver;

                [OneTimeSetUp]
                public void DriverSetUp()
                {
                    driver = new FirefoxDriver();
                }

                [OneTimeTearDown]
                public void DriverTearDown()
                {
                    driver.Quit();
                }
            }

            namespace QAFestDemo 
            {
                //todo: tbd
            }

            namespace Step01_SimpleE2E_FAILED
            {
                [TestFixture]
                public class GoogleTest : BaseTest
                {

                    [Test]
                    public void Search()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");

                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);
                        StringAssert.Contains("Selenide: concise UI tests in Java",
                                              driver.FindElement(By.CssSelector(".srg>.g:nth-of-type(1)"))
                                              .Text
                                             );

                        driver.FindElements(By.CssSelector(".srg>.g"))[0]
                              .FindElement(By.CssSelector("h3>a")).Click();
                        Assert.AreEqual("http://selenide.org/", driver.Url);
                    }
                }
            }

            namespace Step02_Option1_FAILED_PARTIALLY_FIXED_WithImplicitWaits_As_Simplest_Solution
            {
                [TestFixture]
                public class GoogleTest : BaseTest
                {
                    [OneTimeSetUp]
                    public void Init()
                    {
                        driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(4));
                    }

                    [Test]
                    public void Search()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");

                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);
                        StringAssert.Contains("Selenide: concise UI tests in Java",
                                              driver.FindElement(By.CssSelector(".srg>.g:nth-of-type(1)"))
                                              .Text
                                             );

                        driver.FindElements(By.CssSelector(".srg>.g"))[0]
                              .FindElement(By.CssSelector("h3>a")).Click();
                        Assert.AreEqual("http://selenide.org/", driver.Url);
                    }
                }
            }

            namespace Step03_Option1_FIXED_WithAdditionalExplicitWait
            {
                [TestFixture]
                public class GoogleTest : BaseTest
                {
                    [OneTimeSetUp]
                    public void Init()
                    {
                        driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(4));
                    }

                    [Test]
                    public void Search()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");

                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);
                        StringAssert.Contains("Selenide: concise UI tests in Java",
                                              driver.FindElement(By.CssSelector(".srg>.g:nth-of-type(1)"))
                                              .Text
                                             );

                        driver.FindElements(By.CssSelector(".srg>.g"))[0]
                              .FindElement(By.CssSelector("h3>a")).Click();
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2)).Until(
                            ExpectedConditions.UrlToBe("http://selenide.org/"));
                    }
                }

                /*
                 * WARNING: Do not mix implicit and explicit waits. Doing so can cause unpredictable wait times. 
                 * For example setting an implicit wait of 10 seconds and an explicit wait of 15 seconds, could 
                 * cause a timeout to occur after 20 seconds.
                 * 
                 * (c) http://docs.seleniumhq.org/docs/04_webdriver_advanced.jsp#explicit-and-implicit-waits
                 */
            }

            namespace Step04_Option2_FIXED_WithExplicitWaits_WaitingFor_Locator_AsPowerfulSolution
            {
                [TestFixture]
                public class GoogleTest : BaseTest
                {

                    [Test]
                    public void Search()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");

                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);
                        //StringAssert.Contains("Selenide: concise UI tests in Java",
                        //                      driver.FindElement(By.CssSelector(".srg>.g:nth-of-type(1)"))
                        //                      .Text
                        //                     );
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                ExpectedConditions.TextToBePresentInElementLocated(
                                    By.CssSelector(".srg>.g:nth-of-type(1)"), 
                                    "Selenide: concise UI tests in Java"
                                )
                            );

                        driver.FindElements(By.CssSelector(".srg>.g"))[0]
                              .FindElement(By.CssSelector("h3>a")).Click();
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2)).Until(
                            ExpectedConditions.UrlToBe("http://selenide.org/"));
                    }
                }
            }

            namespace Step05_WithExplicitWaitingFor_Element_AsMoreNaturalSolution_FAILED
            {
                [TestFixture]
                public class GoogleTest : BaseTest
                {

                    [Test]
                    public void Search()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");

                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);
                        /*
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                ExpectedConditions.TextToBePresentInElementLocated(
                                    By.CssSelector(".srg>.g:nth-of-type(1)"), 
                                    "Selenide: concise UI tests in Java"
                                )
                            );
                        */
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                ExpectedConditions.TextToBePresentInElement(
                                    driver.FindElement(By.CssSelector(".srg>.g:nth-of-type(1)")), 
                                    "Selenide: concise UI tests in Java"
                                )
                            );

                        driver.FindElements(By.CssSelector(".srg>.g"))[0]
                              .FindElement(By.CssSelector("h3>a")).Click();
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2)).Until(
                            ExpectedConditions.UrlToBe("http://selenide.org/"));
                    }
                }
            }

            namespace Step06_WithExplicitWaitingFor_Element_FIXED_ViaPageObjectAndPageFactory
            {
                using OpenQA.Selenium.Support.PageObjects;

                [TestFixture]
                public class GoogleTest : BaseTest
                {

                    [Test]
                    public void Search()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");

                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);
                        /*
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                ExpectedConditions.TextToBePresentInElement(
                                    driver.FindElement(By.CssSelector(".srg>.g:nth-of-type(1)")), 
                                    "Selenide: concise UI tests in Java"
                                )
                            );
                        */
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                ExpectedConditions.TextToBePresentInElement(
                                    new GooglePage(driver).FirstResult, 
                                    "Selenide: concise UI tests in Java"
                                )
                            );

                        driver.FindElements(By.CssSelector(".srg>.g"))[0]
                              .FindElement(By.CssSelector("h3>a")).Click();
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2)).Until(
                            ExpectedConditions.UrlToBe("http://selenide.org/"));
                    }
                }

                public class GooglePage
                {
                    [FindsBy(How = How.CssSelector, Using = ".srg>.g:nth-of-type(1)")]
                    public IWebElement FirstResult;

                    public GooglePage(IWebDriver driver)
                    {
                        PageFactory.InitElements(driver, this);
                    }
                }
            }

            namespace Step07_DeeperSearch_ForNaturalElementsManagement_FAILED
            {
                using OpenQA.Selenium.Support.PageObjects;

                [TestFixture]
                public class GoogleTest : BaseTest
                {

                    [Test]
                    public void Search()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");

                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);
                        /*
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                ExpectedConditions.TextToBePresentInElement(
                                    new GooglePage(driver).FirstResult, 
                                    "Selenide: concise UI tests in Java"
                                )
                            );
                        */
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                ExpectedConditions.TextToBePresentInElement(
                                    new GooglePage(driver).Results[0], 
                                    "Selenide: concise UI tests in Java"
                                )
                            );

                        driver.FindElements(By.CssSelector(".srg>.g"))[0]
                              .FindElement(By.CssSelector("h3>a")).Click();
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2)).Until(
                            ExpectedConditions.UrlToBe("http://selenide.org/"));
                    }
                }

                public class GooglePage
                {
                    [FindsBy(How = How.CssSelector, Using = ".srg>.g")]
                    public IList<IWebElement> Results;

                    public GooglePage(IWebDriver driver)
                    {
                        PageFactory.InitElements(driver, this);
                    }
                }
            }

            namespace Step08_ShowingWeeknessOfImplicitWaits_FAILED
            {
                using OpenQA.Selenium.Support.PageObjects;

                [TestFixture]
                public class GoogleTest : BaseTest
                {
                    [OneTimeSetUp]
                    public void Init()
                    {
                        driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(4));
                    }

                    [Test]
                    public void Search()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");

                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);
                        /*
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                ExpectedConditions.TextToBePresentInElement(
                                    new GooglePage(driver).Results[0], 
                                    "Selenide: concise UI tests in Java"
                                )
                            );
                        */
                        StringAssert.Contains("Selenide: concise UI tests in Java",
                                              new GooglePage(driver).Results[0]
                                              .Text
                                             );

                        driver.FindElements(By.CssSelector(".srg>.g"))[0]
                              .FindElement(By.CssSelector("h3>a")).Click();
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2)).Until(
                            ExpectedConditions.UrlToBe("http://selenide.org/"));
                    }
                }

                public class GooglePage
                {
                    [FindsBy(How = How.CssSelector, Using = ".srg>.g")]
                    public IList<IWebElement> Results;

                    public GooglePage(IWebDriver driver)
                    {
                        PageFactory.InitElements(driver, this);
                    }
                }
            }

            namespace Step09_DeeperSearch_FIXED_ByExplicitWaits_WithCustomConditions
            {
                using OpenQA.Selenium.Support.PageObjects;

                [TestFixture]
                public class GoogleTest : BaseTest
                {

                    [Test]
                    public void Search()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");

                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);
                        /*
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                ExpectedConditions.TextToBePresentInElement(
                                    new GooglePage(driver).Results[0], 
                                    "Selenide: concise UI tests in Java"
                                )
                            );
                        */
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                CustomConditions.ListNthElementHasText(
                                    new GooglePage(driver).Results,
                                    0,
                                    "Selenide: concise UI tests in Java"
                                )
                            );

                        driver.FindElements(By.CssSelector(".srg>.g"))[0]
                              .FindElement(By.CssSelector("h3>a")).Click();
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2)).Until(
                            ExpectedConditions.UrlToBe("http://selenide.org/"));
                    }
                }

                public class GooglePage
                {
                    [FindsBy(How = How.CssSelector, Using = ".srg>.g")]
                    public IList<IWebElement> Results;

                    public GooglePage(IWebDriver driver)
                    {
                        PageFactory.InitElements(driver, this);
                    }
                }

                public static class CustomConditions
                {
                    public static Func<IWebDriver, bool> ListNthElementHasText (IList<IWebElement> elements, int index, string expectedText)
                    {
                        return delegate (IWebDriver driver) {
                            bool result;
                            try {
                                IWebElement element = elements[index];
                                result = element.Text.Contains(expectedText);
                            }
                            catch (Exception) {
                                result = false;
                            }
                            return result;
                        };
                    }
                }
            }

            namespace Step10_AtomicTests_ForHighQualityCoverage_FAILED
            {
                using OpenQA.Selenium.Support.PageObjects;

                [TestFixture]
                public class GoogleTest : BaseTest
                {

                    [Test]
                    public void Search()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");

                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                CustomConditions.ListNthElementHasText(
                                    new GooglePage(driver).Results,
                                    0,
                                    "Selenide: concise UI tests in Java"
                                )
                            );
                    }

                    [Test]
                    public void FollowFirstLink()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");
                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);

                        driver.FindElements(By.CssSelector(".srg>.g"))[0]
                              .FindElement(By.CssSelector("h3>a")).Click();
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2)).Until(
                            ExpectedConditions.UrlToBe("http://selenide.org/"));
                    }
                }

                public class GooglePage
                {
                    [FindsBy(How = How.CssSelector, Using = ".srg>.g")]
                    public IList<IWebElement> Results;

                    public GooglePage(IWebDriver driver)
                    {
                        PageFactory.InitElements(driver, this);
                    }
                }

                public static class CustomConditions
                {
                    public static Func<IWebDriver, bool> ListNthElementHasText (IList<IWebElement> elements, int index, string expectedText)
                    {
                        return delegate (IWebDriver driver) {
                            bool result;
                            try {
                                IWebElement element = elements[index];
                                result = element.Text.Contains(expectedText);
                            }
                            catch (Exception) {
                                result = false;
                            }
                            return result;
                        };
                    }
                }
            }

            namespace Step11_AtomicTests_FIXED_WithAdditionalWaitsAndConditions
            {
                using OpenQA.Selenium.Support.PageObjects;

                [TestFixture]
                public class GoogleTest : BaseTest
                {

                    [Test]
                    public void Search()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");

                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                CustomConditions.ListNthElementHasText(
                                    new GooglePage(driver).Results,
                                    0,
                                    "Selenide: concise UI tests in Java"
                                )
                            );
                    }

                    [Test]
                    public void FollowFirstLink()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");
                        driver.FindElement(By.Name("q")).SendKeys("Selenide" + Keys.Enter);

                        new WebDriverWait(driver, TimeSpan.FromSeconds(2))
                            .Until(
                                CustomConditions.minimumSizeOf(
                                    new GooglePage(driver).Results,
                                    1
                                )
                            );
                        driver.FindElements(By.CssSelector(".srg>.g"))[0]
                              .FindElement(By.CssSelector("h3>a")).Click();
                        new WebDriverWait(driver, TimeSpan.FromSeconds(2)).Until(
                            ExpectedConditions.UrlToBe("http://selenide.org/"));
                    }
                }

                public class GooglePage
                {
                    [FindsBy(How = How.CssSelector, Using = ".srg>.g")]
                    public IList<IWebElement> Results;

                    public GooglePage(IWebDriver driver)
                    {
                        PageFactory.InitElements(driver, this);
                    }
                }

                public static class CustomConditions
                {
                    public static Func<IWebDriver, bool> ListNthElementHasText (IList<IWebElement> elements, int index, string expectedText)
                    {
                        return delegate (IWebDriver driver) {
                            bool result;
                            try {
                                IWebElement element = elements[index];
                                result = element.Text.Contains(expectedText);
                            }
                            catch (Exception) {
                                result = false;
                            }
                            return result;
                        };
                    }

                    public static Func<IWebDriver, bool> minimumSizeOf (IList<IWebElement> elements, int expectedSize)
                    {
                        return delegate (IWebDriver driver) {
                            return elements.Count >= expectedSize;
                        };
                    }
                }
            }
        
            namespace Step12_ABitMoreDRY_WithWaitsAllOverThePlace_ForSolidImplementation
            {
                using OpenQA.Selenium.Support.PageObjects;
                using static CustomConditions;
                using static ExpectedConditions;

                [TestFixture]
                public class GoogleTest : BaseTest
                {
                    GooglePage google;
                    WebDriverWait wait;

                    [OneTimeSetUp]
                    public void Init()
                    {
                        google = new GooglePage(driver);
                        wait = new WebDriverWait(driver, TimeSpan.FromSeconds(2));
                    }

                    [Test]
                    public void Search()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");

                        wait.Until(Visible(google.search)).SendKeys("Selenide" + Keys.Enter);
                        wait.Until(ListNthElementHasText(google.Results, 0, "Selenide: concise UI tests in Java"));
                    }

                    [Test]
                    public void FollowFirstLink()
                    {
                        driver.Navigate().GoToUrl("http://google.com/ncr");
                        wait.Until(Visible(google.search)).SendKeys("Selenide" + Keys.Enter);

                        /* now the following is not needed:
                        wait.Until(minimumSizeOf(google.Results, 1));
                         * but, once removed we also will get even less detailed error message on fail...  
                         */
                        wait.Until(ListNthElementInnerElementLocatedIsVisible(
                            google.Results, 0, By.CssSelector("h3>a"))).Click();

                        wait.Until(UrlToBe("http://selenide.org/"));
                    }
                }

                public class GooglePage
                {
                    [FindsBy(How = How.Name, Using = "q")]
                    public IWebElement search;

                    [FindsBy(How = How.CssSelector, Using = ".srg>.g")]
                    public IList<IWebElement> Results;

                    public GooglePage(IWebDriver driver)
                    {
                        PageFactory.InitElements(driver, this);
                    }
                }

                public static class CustomConditions
                {
                    public static Func<IWebDriver, bool> ListNthElementHasText (IList<IWebElement> elements, int index, string expectedText)
                    {
                        return delegate (IWebDriver driver) {
                            bool result;
                            try {
                                IWebElement element = elements[index];
                                result = element.Text.Contains(expectedText);
                            }
                            catch (Exception) {
                                result = false;
                            }
                            return result;
                        };
                    }

                    public static Func<IWebDriver, bool> minimumSizeOf (IList<IWebElement> elements, int expectedSize)
                    {
                        return delegate (IWebDriver driver) {
                            return elements.Count >= expectedSize;
                        };
                    }

                    public static Func<IWebDriver, IWebElement> Visible (IWebElement element)
                    {
                        return delegate (IWebDriver driver) {
                            return element.Displayed ? element : null;
                        };
                    }

                    public static Func<IWebDriver, IWebElement> ListNthElementInnerElementLocatedIsVisible (IList<IWebElement> elements, int index, By innerLocator)
                    {
                        return delegate (IWebDriver driver) {
                            try {
                                IWebElement element = elements[index].FindElement(innerLocator);
                                return element.Displayed ? element : null;
                            } catch (Exception) {
                                return null;
                            }
                        };
                    }
                }
            }
        }

        namespace nselene_as_alternative
        {
            using static ExpectedConditions;

            [TestFixture]
            public class BaseTest
            {
                protected WebDriverWait wait;

                [OneTimeSetUp]
                public void DriverSetUp()
                {
                    SetWebDriver(new FirefoxDriver());
                    wait = new WebDriverWait(GetWebDriver(), TimeSpan.FromSeconds(2));
                }

                [OneTimeTearDown]
                public void DriverTearDown()
                {
                    GetWebDriver().Quit();
                }
            }

            namespace Step1E2E
            {

                [TestFixture]
                public class GoogleTest : BaseTest
                {
                    [Test]
                    public void Search()
                    {
                        Open("http://google.com/ncr");

                        S(By.Name("q")).SetValue("Selenide").PressEnter();
                        SS(".srg>.g")[0].Should(Have.Text("Selenide: concise UI tests in Java"));

                        SS(".srg>.g")[0].Find("h3>a").Click();
                        wait.Until(UrlToBe("http://selenide.org/"));
                    }
                }
            }

            namespace Step2Atomic
            {

                [TestFixture]
                public class GoogleTest : BaseTest
                {
                    [Test]
                    public void Search()
                    {
                        Open("http://google.com/ncr");

                        S(By.Name("q")).SetValue("Selenide").PressEnter();
                        SS(".srg>.g")[0].Should(Have.Text("Selenide: concise UI tests in Java"));
                    }

                    [Test]
                    public void FollowLink()
                    {
                        Open("http://google.com/ncr");

                        S(By.Name("q")).SetValue("Selenide").PressEnter();

                        SS(".srg>.g")[0].Find("h3>a").Click();
                        wait.Until(UrlToBe("http://selenide.org/"));
                    }
                }
            }
        }
    }

    namespace WithPages_BONUS
    {
        using static ExpectedConditions;

        namespace Selenium_PageObjects
        {
            using OpenQA.Selenium.Support.PageObjects;
            using static CustomConditions;

            [TestFixture]
            public class BaseTest
            {
                public IWebDriver driver;
                public WebDriverWait wait;

                [OneTimeSetUp]
                public void DriverSetUp()
                {
                    driver = new FirefoxDriver();
                    wait = new WebDriverWait(driver, TimeSpan.FromSeconds(2));
                }

                [OneTimeTearDown]
                public void DriverTearDown()
                {
                    driver.Quit();
                }
            }

            [TestFixture]
            public class GoogleTest : BaseTest
            {
                GooglePage googlePage;

                [OneTimeSetUp]
                public void Init()
                {
                    googlePage = new GooglePage(driver);
                }

                [Test]
                public void Search()
                {
                    googlePage.Open();

                    googlePage.Search("Selenide");
                    googlePage.AssertNthResultHasText(0, "Selenide: concise UI tests in Java");
                }

                [Test]
                public void FollowFirstLink()
                {
                    googlePage.Open();
                    googlePage.Search("Selenide");

                    googlePage.FollowLink(0);
                    wait.Until(UrlToBe("http://selenide.org/"));
                }
            }

            public class GooglePage
            {

                [FindsBy(How = How.Name, Using = "q")]
                public IWebElement search;

                [FindsBy(How = How.CssSelector, Using = ".srg>.g")]
                IList<IWebElement> Results;
                /*
                 * >=
                public IList<IWebElement> Results;
                 */

                IWebDriver driver;
                WebDriverWait wait;

                public GooglePage(IWebDriver driver)
                {
                    this.driver = driver;
                    wait = new WebDriverWait(driver, TimeSpan.FromSeconds(4));
                    PageFactory.InitElements(driver, this);
                }

                public void Open()
                {
                    driver.Navigate().GoToUrl("http://google.com/ncr");
                }

                public void Search(string text)
                {
                    wait.Until(Visible(this.search)).Clear();
                    this.search.SendKeys(text + Keys.Enter);
                    /*
                     * though we need this.search only in one place - this method
                     * we still had to create redundant FindsBy field... 
                     */
                }

                public void FollowLink(int index)
                {
                    wait.Until(ListNthElementInnerElementLocatedIsVisible(this.Results, 0, By.CssSelector("h3>a"))).Click();
                }

                public void AssertNthResultHasText(int index, string text)
                {
                    wait.Until(ListNthElementHasText(
                        this.Results,
                        index,
                        text)
                    );
                }
            }

            public static class CustomConditions
            {
                public static Func<IWebDriver, bool> ListNthElementHasText (IList<IWebElement> elements, int index, string expectedText)
                {
                    return delegate (IWebDriver driver) {
                        bool result;
                        try {
                            IWebElement element = elements[index];
                            result = element.Text.Contains(expectedText);
                        }
                        catch (Exception) {
                            result = false;
                        }
                        return result;
                    };
                }

                public static Func<IWebDriver, bool> minimumSizeOf (IList<IWebElement> elements, int expectedSize)
                {
                    return delegate (IWebDriver driver) {
                        return elements.Count >= expectedSize;
                    };
                }

                public static Func<IWebDriver, IWebElement> Visible (IWebElement element)
                {
                    return delegate (IWebDriver driver) {
                        return element.Displayed ? element : null;
                    };
                }

                public static Func<IWebDriver, IWebElement> ListNthElementInnerElementLocatedIsVisible (IList<IWebElement> elements, int index, By innerLocator)
                {
                    return delegate (IWebDriver driver) {
                        try {
                            IWebElement element = elements[index].FindElement(innerLocator);
                            return element.Displayed ? element : null;
                        } catch (Exception) {
                            return null;
                        }
                    };
                }
            }

        }

        namespace NSelene_PageObjects
        {

            [TestFixture]
            public class BaseTest
            {
                public SeleneDriver driver;
                public WebDriverWait wait;

                [OneTimeSetUp]
                public void DriverSetUp()
                {
                    driver = new SeleneDriver(new FirefoxDriver());
                    wait = new WebDriverWait(driver, TimeSpan.FromSeconds(2));
                }

                [OneTimeTearDown]
                public void DriverTearDown()
                {
                    driver.Quite();
                }
            }

            [TestFixture]
            public class GoogleTest : BaseTest
            {
                GooglePage googlePage;

                [OneTimeSetUp]
                public void Init()
                {
                    googlePage = new GooglePage(driver);
                }

                [Test]
                public void Search()
                {
                    googlePage.Open();

                    googlePage.Search("selenide");
                    googlePage.AssertNthResultHasText(0, "Selenide: concise UI tests in Java");
                }

                [Test]
                public void FollowLink()
                {
                    googlePage.Open();
                    googlePage.Search("selenide");

                    googlePage.FollowLink(0);
                    wait.Until(UrlToBe("http://selenide.org/"));
                }
            }

            public class GooglePage
            {
                SeleneDriver driver;
                private SeleneCollection Results { get { return driver.FindAll(".srg>.g"); } }


                public GooglePage(SeleneDriver driver)
                {
                    this.driver = driver;
                }

                public void Open()
                {
                    driver.GoToUrl("http://google.com/ncr");
                }

                public void Search(string text)
                {
                    driver.Find(By.Name("q")).SetValue(text).PressEnter();
                }

                public void FollowLink(int index)
                {
                    this.Results[index].Find("h3>a").Click();
                }

                public void AssertNthResultHasText(int index, string text)
                {
                    this.Results[index].Should(Have.Text(text));
                }
            }
        }

        namespace NSelene_PageModules
        {

            [TestFixture]
            public class BaseTest
            {
                public WebDriverWait wait;

                [OneTimeSetUp]
                public void DriverSetUp()
                {
                    SetWebDriver(new FirefoxDriver());
                    wait = new WebDriverWait(GetWebDriver(), TimeSpan.FromSeconds(2));
                }

                [OneTimeTearDown]
                public void DriverTearDown()
                {
                    GetWebDriver().Quit();
                }
            }

            [TestFixture]
            public class GoogleTest : BaseTest
            {
                [Test]
                public void Search()
                {
                    Google.Open();

                    Google.Search("selenide");
                    Google.Results[0].Should(Have.Text("Selenide: concise UI tests in Java"));
                }

                [Test]
                public void FollowLink()
                {
                    Google.Open();
                    Google.Search("selenide");

                    Google.FollowLink(0);
                    wait.Until(UrlToBe("http://selenide.org/"));
                }
            }

            public static class Google
            {
                public static SeleneCollection Results = SS(".srg>.g");

                public static void Open()
                {
                    Selene.Open("http://google.com/ncr");
                }

                public static void Search(string text)
                {
                    S(By.Name("q")).SetValue(text).PressEnter();
                }

                public static void FollowLink(int index)
                {
                    Results[index].Find("h3>a").Click();
                }
            }
        }
    }
}

