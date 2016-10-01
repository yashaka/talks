using System;
using NUnit.Framework;
using OpenQA.Selenium.Firefox;
using static NSelene.Selene;

namespace TamingDinoFrameworksCs
{
    [TestFixture]
    public class BaseTest
    {
        [OneTimeSetUp]
        public void DriverSetup()
        {
            SetWebDriver(new FirefoxDriver());
        }

        [OneTimeTearDown]
        public void DriverTearDown()
        {
            GetWebDriver().Quit();
        }
    }
}
