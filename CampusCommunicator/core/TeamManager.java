package CampusCommunicator.core;

import CampusCommunicator.models.EmployeeStats;

import java.util.HashMap;
import java.util.ArrayList;

public class TeamManager {
    private HashMap<String, ArrayList<EmployeeStats>> teams;

    public TeamManager() {
        teams = new HashMap<>();

        // Sample team data
        ArrayList<EmployeeStats> teamA = new ArrayList<>();
        teamA.add(new EmployeeStats("Alice", 30, 28, 1000));
        teamA.add(new EmployeeStats("Bob", 30, 25, 1000));
        teamA.add(new EmployeeStats("Charlie", 30, 26, 1000));
        teamA.add(new EmployeeStats("David", 30, 29, 1000));
        teams.put("Team A", teamA);

        ArrayList<EmployeeStats> teamB = new ArrayList<>();
        teamB.add(new EmployeeStats("Eve", 30, 27, 1000));
        teamB.add(new EmployeeStats("Frank", 30, 24, 1000));
        teamB.add(new EmployeeStats("Grace", 30, 28, 1000));
        teamB.add(new EmployeeStats("Heidi", 30, 26, 1000));
        teams.put("Team B", teamB);

        ArrayList<EmployeeStats> teamC = new ArrayList<>();
        teamC.add(new EmployeeStats("Anamika", 30, 30, 1000));
        teamC.add(new EmployeeStats("Jasmine", 30, 29, 1000));
        teamC.add(new EmployeeStats("Kishan", 30, 27, 1000));
        teamC.add(new EmployeeStats("Lara", 30, 28, 1000));
        teams.put("Team C", teamC);
    }

    public void displayTeam(String teamName) {
        if (!teams.containsKey(teamName)) {
            System.out.println("⚠️ Team not found.");
            return;
        }
        System.out.println("\n📢 Team: " + teamName);
        for (EmployeeStats e : teams.get(teamName)) {
            System.out.println(e);
        }
    }

    public void listTeams() {
        System.out.println("\n🏢 Available Teams:");
        for (String name : teams.keySet()) {
            System.out.println("- " + name);
        }
    }
}
