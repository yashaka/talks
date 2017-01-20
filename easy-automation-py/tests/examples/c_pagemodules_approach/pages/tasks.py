from selene import tools
from selene.support import by
from selene.support.conditions import be
from selene.support.conditions import have
from selene.tools import wait_to, s, ss


_elements = ss('#todo-list li')


def visit():
    tools.visit('https://todomvc4tasj.herokuapp.com')
    wait_to(have.js_returned_true("return $._data($('#clear-completed').get(0), 'events').hasOwnProperty('click')"))


def add(*texts):
    for text in texts:
        s('#new-todo').set_value(text).press_enter()


def should_be(*texts):
    _elements.filtered_by(be.visible).should(have.exact_texts(*texts))


def toogle(text):
    _elements.element_by(have.exact_text(text)).element('.toggle').click()


def filter_active():
    s(by.link_text('Active')).click()


def filter_completed():
    s(by.link_text('Completed')).click()


def filter_all():
    s(by.link_text('All')).click()
