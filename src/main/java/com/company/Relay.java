package com.company;

import java.util.ArrayList;
import java.util.List;

public class Relay extends Thread {
    List<Team> teamsOnRelay = new ArrayList<>();
    int count = 0;
    int relayLength = 500;
    ArrayList<String> winner = new ArrayList<>();

    Relay(String[] teamNames,String[][] teamRunners){
        for (String str : teamNames){
            Team team = new Team();
            team.name = str;
            for (int i=0; i<4; i++){
                team.runners.add(teamRunners[count][i]);
            }
            teamsOnRelay.add(team);

            count++;
        }
    }

    public void run(){
        try {
            for(Team team : teamsOnRelay){
                Runner runner1 = new Runner(team.runners.get(0),1,team);
                Runner runner2 = new Runner(team.runners.get(1),runner1,2,team,winner);
                Runner runner3 = new Runner(team.runners.get(2),runner2,3,team,winner);
                Runner runner4 = new Runner(team.runners.get(3),runner3,4,team,winner);

                runner1.start();
                runner2.start();
                runner3.start();
                runner4.start();

            }
            sleep(250);
            System.out.println("Победила команда " + winner.get(0));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
