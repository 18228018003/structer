package com.mengtu.map;

import java.util.Objects;

public class Person {
    private int age;
    private float height;
    private String name;

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age && Float.compare(person.height, height) == 0 && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, height, name);
    }
}
