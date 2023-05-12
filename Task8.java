import java.util.*;

public class RoutingTableBuilder {

    public static void main(String[] args) {
        // input
        List<List<String>> connectivity = new ArrayList<>();
        connectivity.add(Arrays.asList("A", "B"));
        connectivity.add(Arrays.asList("A", "C"));
        connectivity.add(Arrays.asList("A", "D"));
       
        // routing table building
        Map<String, Map<String, String>> routingTables = buildRoutingTables(connectivity);
       
  
        for (String node : routingTables.keySet()) {
            System.out.println("Routing table for node " + node + ":");
            Map<String, String> routingTable = routingTables.get(node);
            for (String destination : routingTable.keySet()) {
                String nextHop = routingTable.get(destination);
                System.out.println("  To " + destination + ", send to " + nextHop);
            }
        }
    }
   
    public static Map<String, Map<String, String>> buildRoutingTables(List<List<String>> connectivity) {
        // Build connections between nodes
        Map<String, Map<String, String>> routingTables = new HashMap<>();
        Set<String> nodes = getNodes(connectivity);
        for (String node : nodes) {
            Map<String, String> routingTable = new HashMap<>();
            for (String destination : nodes) {
                if (!destination.equals(node)) {
                    routingTable.put(destination, null);
                }
            }
            routingTables.put(node, routingTable);
        }
       
        // figuring out shortest path and computing distance for each node to another
        for (String node : nodes) {
            Set<String> visited = new HashSet<>();
            visited.add(node);
            Queue<String> queue = new LinkedList<>();
            queue.offer(node);
            int distance = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String curr = queue.poll();
                    Map<String, String> routingTable = routingTables.get(node);
                    routingTable.put(curr, curr.equals(node) ? null : curr);
                    for (List<String> edge : connectivity) {
                        if (edge.get(0).equals(curr) && !visited.contains(edge.get(1))) {
                            visited.add(edge.get(1));
                            queue.offer(edge.get(1));
                        }
                    }
                }
                distance++;
            }
        }
       
        return routingTables;
    }
   
    private static Set<String> getNodes(List<List<String>> connectivity) {
        Set<String> nodes = new HashSet<>();
        for (List<String> edge : connectivity) {
            nodes.add(edge.get(0));
            nodes.add(edge.get(1));
        }
        return nodes;
    }
   
}
