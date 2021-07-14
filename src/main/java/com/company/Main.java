package com.company;

public class Main {

    public static void main(String[] args) {
        String[] teamNames = {"Динамо", "ЦСКА"};
        String[][] runnerNames = {{"Петр", "Василий", "Иван", "Глафира"}, {"Юрий", "Оксана", "Александр", "Анна"}};
        System.out.println("Победила команда ");
        Relay relay = new Relay(teamNames, runnerNames);
        relay.run();
    }

}
