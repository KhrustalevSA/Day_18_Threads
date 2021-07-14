package com.company;

public class Runner extends Thread {
    String name;
    Runner other;
    int number;
    int count = 0;
    String teamName;

    Runner(String name,Runner other,int number, Team team){
        this.name = name;
        this.number = number;
        this.other = other;
        this.teamName = team.name;
    }
    Runner(String name,int number, Team team){
        this.name = name;
        this.number = number;
        this.other = null;
        this.teamName = team.name;
    }
    public void run(){
        try {
            if(other != null) {
                System.out.println(name + " ждёт " + other.name);
                other.join();
            }
            System.out.println(name + " начинает бежать ");
            while (count < 50) {
                ++count;
                System.out.println(name + " бежит " + count);
                Thread.yield();
                if(number == 4 && count == 50){
                    sleep(1000);
                    System.out.println("Победитель команда: " + teamName );
                    System.exit(0);
                }
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
