import java.util.*;

public class SimpleSocialNetwork {

    // Friendship graph
    private Map<String, List<String>> friendshipNetwork;

    public SimpleSocialNetwork() {
        friendshipNetwork = new HashMap<>();
    }

    // Add user
    public void addUser(String user) {
        if (!friendshipNetwork.containsKey(user)) {
            friendshipNetwork.put(user, new ArrayList<>());
            System.out.println("User '" + user + "' has been added.");
        } else {
            System.out.println("User '" + user + "' already exists.");
        }
    }

    // Add friendship
    public void addFriendship(String user1, String user2) {
        if (!friendshipNetwork.containsKey(user1) || !friendshipNetwork.containsKey(user2)) {
            System.out.println("One of the users does not exist. Please add both users.");
            return;
        }

        friendshipNetwork.get(user1).add(user2);
        friendshipNetwork.get(user2).add(user1);
        System.out.println("Friendship between '" + user1 + "' and '" + user2 + "' has been added.");
    }

    // Display all friends of user
    public void displayFriends(String user) {
        if (!friendshipNetwork.containsKey(user)) {
            System.out.println("User '" + user + "' does not exist.");
            return;
        }

        List<String> friends = friendshipNetwork.get(user);
        if (friends.isEmpty()) {
            System.out.println("User '" + user + "' has no friends.");
        } else {
            System.out.println("Friends of '" + user + "': " + friends);
        }
    }

    // Check if two users are connected with depth-first search
    public boolean areConnected(String user1, String user2) {
        if (!friendshipNetwork.containsKey(user1) || !friendshipNetwork.containsKey(user2)) {
            System.out.println("One of the users does not exist.");
            return false;
        }

        Set<String> visited = new HashSet<>();
        return depthFirstSearch(user1, user2, visited);
    }

    // Depth-First Search
    private boolean depthFirstSearch(String currentUser, String targetUser, Set<String> visited) {
        if (currentUser.equals(targetUser)) {
            return true;
        }

        visited.add(currentUser);

        for (String friend : friendshipNetwork.get(currentUser)) {
            if (!visited.contains(friend)) {
                if (depthFirstSearch(friend, targetUser, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}
