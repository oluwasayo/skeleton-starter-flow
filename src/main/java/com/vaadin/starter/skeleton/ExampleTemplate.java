package com.vaadin.starter.skeleton;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.starter.skeleton.ExampleTemplate.ExampleModel;

import java.util.List;

/**
 * Simple template example.
 */
@Tag("example-template")
@HtmlImport("src/example-template.html")
public class ExampleTemplate extends PolymerTemplate<ExampleModel> {

    /**
     * Template model which defines the single "value" property.
     */
    public interface ExampleModel extends TemplateModel {
        void setPersons(List<Person> persons);

        void setTitle(String title);
    }

    public ExampleTemplate() {
        getModel().setTitle("Default Title");
        getModel().setPersons(MainView.randomPersons());
    }

    public void setPersons(List<Person> persons) {
        getModel().setPersons(persons);
    }

    public void setTitle(String title) {
        getModel().setTitle(title);
    }

    public static final class Person {
        private String name;
        private int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
