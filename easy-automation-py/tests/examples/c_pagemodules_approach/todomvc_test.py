from selene import config

from tests.examples.c_pagemodules_approach.pages import tasks


def test_filter_tasks():
    config.browser_name = 'chrome'
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

