import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class NodeElement{
    String locationName;                        //location used as an identifier for the node
    boolean locationVisited;                    //boolean for tracking whether the location has been visited yet
    boolean bossDefeated;                       //boolean for tracking whether a boss has been defeated within a node.
    NodeElement left, right, forward, backward; // for connections within the map
    String description;

    public NodeElement(String locationName){
        this.locationName = locationName;
        this.locationVisited = false;
        this.bossDefeated = false;
        this.description = description;
    }
    public void locationDescription(){
    //for descriptions of location
    }
    public String getAvailableDirections(){
        StringBuilder directions = new StringBuilder("Available directions: ");
        if (left != null) directions.append("left, ");
        if (right != null) directions.append("right, ");
        if (forward != null) directions.append("forward, ");
        directions.append("backward, ");
        if (directions.toString().endsWith(", ")) {
            directions.setLength(directions.length() - 2); // Remove trailing comma and space
        }

        return directions.toString();
    }

}
