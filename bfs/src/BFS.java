import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class BFS {

    public static String go(String graph) {
        String[] part = graph.split(":");
        return go(Integer.parseInt(part[0]), Integer.parseInt(part[1]), part[2]);
    }

    public static String go(int nodeNumber, int startNode, String connections) {
        String[] e = connections.split(",");
        int[][] edges = new int[e.length][2];
        for (int i = 0; i < e.length; i++) {
            String[] ee = e[i].split(" ");
            edges[i][0] = Integer.parseInt(ee[0]);
            edges[i][1] = Integer.parseInt(ee[1]);
        }

        return go(nodeNumber, startNode, edges);
    }

    public static String distance(String graph) {
        String[] part = graph.split(":");
        return distance(Integer.parseInt(part[0]), Integer.parseInt(part[1]), part[2]);
    }

    public static String distance(int nodeNumber, int startNode, String connections) {
        String[] e = connections.split(",");
        int[][] edges = new int[e.length][2];
        for (int i = 0; i < e.length; i++) {
            String[] ee = e[i].split(" ");
            edges[i][0] = Integer.parseInt(ee[0]);
            edges[i][1] = Integer.parseInt(ee[1]);
        }

        return distance(nodeNumber, startNode, edges);
    }

    public static Map<Integer, SortedSet<Integer>> fillNeighbours(int nodeNumber, int[][] edges) {
        Map<Integer, SortedSet<Integer>> neighbours = new HashMap<>();
        for (int i = 1; i <= nodeNumber; i++) {
            neighbours.put(i, new TreeSet<Integer>());
        }
        for (int i = 0; i < edges.length; i++) {
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            neighbours.get(n1).add(n2);
            neighbours.get(n2).add(n1);
        }

        return neighbours;
    }

    public static String go(int nodeNumber, int startNode, int[][] edges) {
        Queue<Integer> q = new ConcurrentLinkedQueue<Integer>();
        List<Integer> visited = new ArrayList<Integer>();

        Map<Integer, SortedSet<Integer>> neighbours = fillNeighbours(nodeNumber, edges);

        q.add(startNode);

        while (q.size() > 0) {
            int node = q.remove();

            visited.add(node);

            neighbours.get(node).forEach(i -> { if (!visited.contains(i) && !q.contains(i)) q.add(i); });
        }

        return visited.stream().map(i -> i.toString()).collect(Collectors.joining(" "));
    }

    public static String distance(int nodeNumber, int startNode, int[][] edges) {
        Queue<Integer> q = new ConcurrentLinkedQueue<Integer>();
        List<Integer> visited = new ArrayList<Integer>();

        Map<Integer, SortedSet<Integer>> neighbours = fillNeighbours(nodeNumber, edges);

        Integer[] distances = new Integer[nodeNumber + 1];

        for (int i = 0; i < distances.length; i++) {
            distances[i] = -1;
        }

        distances[startNode] = 0;

        q.add(startNode);

        while (q.size() > 0) {
            int node = q.remove();

            visited.add(node);

            neighbours.get(node).
                    forEach(i -> {
                        if ((distances[i] == -1) || (distances[i] > distances[node] + 1)) distances[i] = distances[node] + 1;
                        if (!visited.contains(i) && !q.contains(i)) q.add(i);

                    });
        }

        return Arrays.stream(distances).map(i->i.toString()).collect(Collectors.joining(" ")).substring(2).trim();
    }

}