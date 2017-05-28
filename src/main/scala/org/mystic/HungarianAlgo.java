package org.mystic;

import java.util.Arrays;

public class HungarianAlgo {

    public static final int INF = (Integer.MAX_VALUE / 2);

    public static int solve(int[][] matrix, int[] result) {
        int n = matrix.length;
        int[] u = new int[n];
        int[] v = new int[n];
        int[] markIndices = new int[n];
        Arrays.fill(markIndices, -1);
        for (int i = 0; i < n; i++) {
            int[] links = new int[n];
            Arrays.fill(links, -1);
            int[] mins = new int[n];
            Arrays.fill(mins, INF);
            boolean[] visited = new boolean[n];
            int markedI = i, markedJ = -1, j;
            do {
                j = -1;
                for (int j1 = 0; j1 < n; j1++)
                    if (!visited[j1]) {
                        if (matrix[markedI][j1] - u[markedI] - v[j1] < mins[j1]) {
                            mins[j1] = matrix[markedI][j1] - u[markedI] - v[j1];
                            links[j1] = markedJ;
                        }
                        if (j == -1 || mins[j1] < mins[j])
                            j = j1;
                    }
                int delta = mins[j];
                for (int j1 = 0; j1 < n; j1++)
                    if (visited[j1]) {
                        u[markIndices[j1]] += delta;
                        v[j1] -= delta;
                    } else {
                        mins[j1] -= delta;
                    }
                u[i] += delta;
                visited[j] = true;
                markedJ = j;
                markedI = markIndices[j];

            } while (markedI != -1);

            for (; links[j] != -1; j = links[j])
                markIndices[j] = markIndices[links[j]];
            markIndices[j] = i;
        }
        int cost = 0;
        for (int j = 0; j < n; j++) {
            cost += matrix[markIndices[j]][j];
            result[markIndices[j]] = j;
        }
        return cost;
    }

}
