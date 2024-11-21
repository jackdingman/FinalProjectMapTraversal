import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {

            TraversalLogic game = new TraversalLogic(); // Initialize the game
            Scanner scanner = new Scanner(System.in);
            boolean running = true;
            String direction;

            singleLetterOutputter("Welcome to the Dungeon Crawler! \n");
            sleep(1500);
            singleLetterOutputter("A grand, ancient throne room. King Acacius, wise and regal, sits upon his majestic throne, his eyes gleaming with purpose. The air is heavy with anticipation as the adventurer enters.\n");
            singleLetterOutputter("KING ACACIUS: What is your name, adventurer? \n");
            sleep(1500);
            System.out.println("What is your name: ");
            String name = scanner.nextLine();
            singleLetterOutputter("The perils before you are unknown, ");
            singleLetterOutputter(name+". ");
            singleLetterOutputter("Be careful and watch your back. " +
                    "Please retrieve for me the Sword of Curonis, kept away for centuries on top of a mountain.\n" +
                    "It is guarded by Vorthak the Fury, an ice elemental who is kept chained to the sword as a punishment from the Gods. \n");

        //Output first story prompt
        //Output directional options - must be particular to node
        //User enters chosen direction - pushed current location on Deque
        //New story prompt
        //Output directions + ability to fight. To go directions other than backwards, you must fight.

            while(running){
                NodeElement current = game.getCurrent();
                singleLetterOutputter("You are currently at: " + current.locationName + "\n");
                current.locationDescription(); // print description of area

                System.out.println(current.getAvailableDirections()); //movement options, particular to the node

                System.out.print("Enter a direction to move (or 'exit' to quit): ");
                direction = scanner.nextLine().trim().toLowerCase();

                if (direction.equals("exit")) {
                    running = false;
                    System.out.println("Thanks for playing!");
                } else if (direction.equals("backward")) {
                    game.backward();
                } else if (direction.equals("left") || direction.equals("right") || direction.equals("forward")) {
                    game.move(direction);
                } else {
                    System.out.println("Invalid direction. Try again.");
                }
            }
        scanner.close();

    }
    public static void sleep(Integer time){
        try {
            // Pause execution for entered amount of milliseconds
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // Handle the exception if the thread is interrupted
            System.err.println("Sleep was interrupted!");
        }
    }

    public static void singleLetterOutputter(String entry){

        char[] toArray = entry.toCharArray();

        for (char c : toArray) {
            System.out.print(c);
            sleep(0); //optimal 30 for game experience
        }

        //System.out.println();
    }
}
/*game.move("left");       // Move to the left (to room1)
            game.move("forward");    // Move forward (to room2)
            game.backward();        // Backtrack (to room1)
            game.move("right");        // Backtrack again (to start)*/