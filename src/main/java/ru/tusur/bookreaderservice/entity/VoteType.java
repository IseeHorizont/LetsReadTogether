package ru.tusur.bookreaderservice.entity;

public enum VoteType {

    LIKE("LIKE"),
    DISLIKE("DISLIKE");


    private String name;

    VoteType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
