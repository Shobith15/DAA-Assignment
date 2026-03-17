import java.util.*;

public class Solution {

    private static boolean canMatch(int client, List<Integer>[] adj, int[] match, boolean[] visited) {
        for (int house : adj[client]) {
            if (!visited[house]) {
                visited[house] = true;
                
                if (match[house] < 0 || canMatch(match[house], adj, match, visited)) {
                    match[house] = client;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        int m = sc.nextInt();

        long[][] clients = new long[n][2];
        for (int i = 0; i < n; i++) {
            clients[i][0] = sc.nextLong();
            clients[i][1] = sc.nextLong();
        }

        long[][] houses = new long[m][2];
        for (int j = 0; j < m; j++) {
            houses[j][0] = sc.nextLong();
            houses[j][1] = sc.nextLong();
        }

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                if (houses[j][0] > clients[i][0] && houses[j][1] <= clients[i][1]) {
                    adj[i].add(j);
                }
            }
        }

        int[] match = new int[m];
        Arrays.fill(match, -1);

        int result = 0;
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[m];
            if (canMatch(i, adj, match, visited)) {
                result++;
            }
        }

        System.out.println(result);
    }
}
