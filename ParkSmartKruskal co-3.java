import java.util.*;

class UnionFindNaive {
    int[] parent;

    UnionFindNaive(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // No path compression
    int find(int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    // No union by rank
    boolean union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        if (rx == ry)
            return false;

        parent[rx] = ry;
        return true;
    }
}

public class ParkSmartKruskal {

    static List<String> vertices =
            Arrays.asList(
                    "C", // 0 Parking Zone C
                    "D", // 1 Parking Zone D
                    "A", // 2 Parking Zone A
                    "B", // 3 Parking Zone B
                    "P", // 4 Central Parking Hub
                    "F", // 5 Parking Zone F
                    "E"  // 6 Parking Zone E
            );

    static List<int[]> kruskalNaive(int n, int[][] edges) {

        Arrays.sort(edges, Comparator.comparingInt(e -> e[0]));

        UnionFindNaive uf = new UnionFindNaive(n);

        List<int[]> mst = new ArrayList<>();

        for (int[] e : edges) {

            int weight = e[0];
            int u = e[1];
            int v = e[2];

            if (uf.union(u, v)) {
                mst.add(e);
            }
        }

        return mst;
    }

    public static void main(String[] args) {

        int n = 7;

        // {weight, u, v}
        int[][] edges = {

                {4, 0, 1},   // C-D
                {5, 2, 3},   // A-B
                {6, 3, 0},   // B-C
                {7, 4, 1},   // P-D
                {9, 5, 6},   // F-E
                {9, 4, 6},   // P-E

                // Extra edges
                {10, 0, 2},  // C-A
                {11, 1, 3},  // D-B
                {12, 2, 4},  // A-P
                {13, 3, 5},  // B-F
                {14, 1, 6},  // D-E
                {15, 0, 5}   // C-F
        };

        List<int[]> mst = kruskalNaive(n, edges);

        int totalCost = 0;

        System.out.println("====================================");
        System.out.println("ParkSmart MST using Kruskal");
        System.out.println("====================================");

        System.out.println("\nResulting MST Edges:");

        for (int[] e : mst) {

            String u = vertices.get(e[1]);
            String v = vertices.get(e[2]);

            System.out.println(
                    u + " - " + v + " : ₹" + e[0] + " cr"
            );

            totalCost += e[0];
        }

        System.out.println(
                "\nTotal MST Cost = ₹" + totalCost + " crore"
        );

        System.out.println("\nNode Mapping:");
        System.out.println("P = Central Parking Hub");
        System.out.println("A = Parking Zone A");
        System.out.println("B = Parking Zone B");
        System.out.println("C = Parking Zone C");
        System.out.println("D = Parking Zone D");
        System.out.println("E = Parking Zone E");
        System.out.println("F = Parking Zone F");
    }
}