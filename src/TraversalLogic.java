import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class TraversalLogic{
    NodeElement current;
    Deque<NodeElement> path = new ArrayDeque<>();
    Map<String, NodeElement> dungeonMap = new HashMap<>();

    public TraversalLogic(){

        NodeElement beginning = new NodeElement("Fork in the Road");
        NodeElement forestPath = new NodeElement("Forest Path");
        NodeElement field = new NodeElement("Field");
        NodeElement cliff = new NodeElement("Cliff");
        NodeElement interiorCave = new NodeElement("Interior Cave");
        NodeElement caveEntrance = new NodeElement("Cave Entrance");
        NodeElement drawbridge = new NodeElement("Drawbridge");
        NodeElement mountain = new NodeElement("Mountain");
        NodeElement cavern = new NodeElement("Cavern");
        NodeElement hole = new NodeElement("hole");
        NodeElement dirtPath = new NodeElement("Dirt Path");

        dungeonMap.put("Forest Path", forestPath);
        dungeonMap.put("Field", field);
        dungeonMap.put("Cliff", cliff);
        dungeonMap.put("Cave Entrance", caveEntrance);
        dungeonMap.put("Drawbridge", drawbridge);
        dungeonMap.put("Wizard's Lair", mountain);

        beginning.left = hole; //from fork to woods
        beginning.forward = caveEntrance; // from fork to castle
        beginning.right = forestPath;

        //movement from hole
        //hole.backward = beginning;

        //movement from cave entrance
        caveEntrance.left = drawbridge;
        caveEntrance.forward = interiorCave;
        caveEntrance.right = cliff;
        //caveEntrance.backward = beginning;

        //movement from forest path
        forestPath.left = cliff;
        forestPath.right = field;

        //movement from Drawbridge
        drawbridge.left = mountain;
        drawbridge.right = cavern;
        //drawbridge.backward = caveEntrance;

        //movement from Interior Cave
        interiorCave.left = drawbridge;
        interiorCave.right = cliff;
        //interiorCave.backward; = caveEntrance;

        //movement from cliff
        cliff.left = caveEntrance;
        cliff.right = interiorCave;

        //movement from field
        field.forward = dirtPath;

        current = beginning;
        path.push(current);

    }

    public void move(String direction) { // Method to move to a new node in the specified direction

        NodeElement nextNode;

        if (direction.equals("backward")) { //BACKWARD IS NOT BEING CALLED WITHIN MOVE METHOD
            // Handle backward movement
            if (current.backward != null) { //can't be null, or player would be exiting the map
                nextNode = current.backward;
            } else {
                System.out.println("No way to move backward from here.");
                return;
            }
        } else {
            // Handle directions other than backwards
            nextNode = switch (direction) {
                case "left" -> current.left;
                case "right" -> current.right;
                case "forward" -> current.forward;
                default -> null; // If the direction is invalid, nextNode is null
            };
        }

        // Check if the chosen direction is valid
        if (nextNode != null) {
            nextNode.backward = current; // Dynamically set the backward reference for the next node
            path.push(current);          // Push current node onto the Deque before moving
            current = nextNode;          // Update the current node to the new location
            current.locationVisited = true;  // Mark the new location as visited
            System.out.println("Moved to: " + current.locationName); // Print the new location
        } else {
            System.out.println("No path in that direction."); // Print if there's no path
        }
    }

    // Method to move back to the previous node
    public void backward() {
        // Check for previous node to go back to (Deque size > 1)
        if (path.size() > 1) {
            current = path.peek(); // Peek at the top of the Deque for the previous node
            System.out.println("Backtracked to: " + current.locationName); // Print the backtracked location
            path.pop(); //remove node for new current
        } else {
            System.out.println("You're at the starting point and can't go back further."); //print if you cannot go back anymore

        }
    }

    NodeElement getCurrent() { //retrieves current node
        return current;
    }

}