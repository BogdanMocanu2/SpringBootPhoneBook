package com.bogdan.demo.enums;

public enum Commands {

    list("list"),
    show("show"),
    find("find"),
    add("add"),
    number("number"),
    mail("editEmail"),
    name("editName"),
    surname("surname"),
    delete("delete"),
    help("help"),
    exit("exit"),
    edit("edit"),
    yes("yes"),
    no("noe"),
    cancel("cancel"),
    category("category");

    private final String value;

    Commands(final String value) {
        this.value = value;
    }

    public String getCommand() {
        return this.value;
    }

}
