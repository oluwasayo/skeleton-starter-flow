package com.vaadin.starter.skeleton;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.ExampleTemplate.Person;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * The main view contains a button and a template element.
 */
@HtmlImport("styles/shared-styles.html")
@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        ExampleTemplate template = new ExampleTemplate();

        Button button = new Button("Click me", event -> {
            template.setPersons(randomPersons());
            template.setTitle("Title" + randomNumber());
        });

        add(button, template);
        setClassName("main-layout");
    }

    public static List<Person> randomPersons() {
        return Stream
                .generate(() -> new Person("Person" + randomNumber(), randomNumber()))
                .limit(10)
                .collect(toList());
    }

    private static int randomNumber() {
        return (int) (100 * Math.random());
    }
}
