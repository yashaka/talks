import os

from selene import config
from selene.browsers import Browser
from selene.tools import execute_script

from tests.examples.e_configurable_tests.pages import tasks


def setup_module(m):
    config.browser_name = os.getenv('BROWSER_NAME', Browser.CHROME)


def teardown_function(f):
    execute_script('localStorage.clear()')


def test_filter_tasks():
    tasks.visit()

    tasks.add('a', 'b', 'c')
    tasks.should_be('a', 'b', 'c')

    tasks.toogle('b')
    tasks.filter_active()
    tasks.should_be('a', 'c')

    tasks.filter_completed()
    tasks.should_be('b')

    tasks.filter_all()
    tasks.should_be('a', 'b', 'c')


def test_clear_completed():
    config.browser_name = 'chrome'
    tasks.visit()

    tasks.add('a', 'b', 'c')
    tasks.toogle('b')
    tasks.clear_completed()

    tasks.should_be('a', 'c')
