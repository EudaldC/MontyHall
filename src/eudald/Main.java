package eudald;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        input.useDelimiter("\n");

        System.out.print("Change doors (yes/no)? ");
        boolean doorChange = input.next().trim().equalsIgnoreCase("yes");
        System.out.print("Enter number of cycles (default 10000): ");
        String text = input.next();
        int cycles = text.trim().matches("[0-9]{1,8}") ? Integer.parseInt(text.trim()) : 10000;
        int winCount = 0;
        for (int i = 0; i < cycles; i++) {
            int chosenDoor = randomDoor();
            int winnerDoor = randomDoor();
            // Open a door which isn't the winner or the chosen by the participant, then change the participant door to
            // one which isn't the previous chosen or the removed one
            if (doorChange) chosenDoor = conditionedDoor(chosenDoor, conditionedDoor(chosenDoor, winnerDoor));
            if (chosenDoor == winnerDoor) winCount++;
        }
        double winPct = winCount * 100.0d / cycles;
        System.out.println("Winner door chosen " + winPct + "% of the times after " + cycles + " cycles");
    }

    private static int randomDoor() {
        return 1 + random.nextInt(3);
    }

    private static int conditionedDoor(int a, int b) {
        int door;
        do door = randomDoor(); while (door == a || door == b);
        return door;
    }
}
