package com.oreillyauto.example;

public class Example {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Example [name=" + name + "]";
    }
    
}
