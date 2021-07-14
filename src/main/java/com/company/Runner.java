package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Runner extends Thread {
    String name;
    Runner other;
    int number;
    int count = 0;
    String teamName;
    ArrayList<String> winner;

    Runner(String name,Runner other,int number, Team team,ArrayList<String> winner){
        this.name = name;
        this.number = number;
        this.other = other;
        this.teamName = team.name;
        this.winner = team.winner;
        this.winner = winner;
    }
    Runner(String name,int number, Team team){
        this.name = name;
        this.number = number;
        this.other = null;
        this.teamName = team.name;
        this.winner = null;
    }
    public void run(){
        try {
            if(other != null) {
                System.out.println(name + " ждёт " + other.name);
                other.join();
            }
            System.out.println(name + " принимает эстафету от " + other.name + " и начинает бежать ");
            while (count < 50) {
                ++count;
                System.out.println(name + " бежит " + count + " метров");
                Thread.yield();
                if(number == 4 && count == 50){
                    winner.add(teamName);
                }
                if (count == 50 && number != 4){
                    System.out.println("Передача эстафеты");
                }
            }
            sleep( (long) (Math.random() * 10) );

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
