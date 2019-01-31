package me.antoine.seg.calc;

import java.util.*;

public class KMeans {
    private HashMap<Vector, Integer> index;

    public KMeans(int width, int height, Vector[] vectors, int k) {
        index = new HashMap<>();

        this.solve(width, height, vectors, k);
    }

    private void solve(int width, int height, Vector[] vectors, int k) {
        Random random = new Random();
        Vector[] centroids = new Vector[k];
        Map<Vector, Integer> lookup = new HashMap<>();

        for (int i = 0; i < centroids.length; i++) {
            centroids[i] = new Vector(random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(width),
                    random.nextInt(height));

            lookup.put(centroids[i], i);
        }

        Map<Vector, Set<Vector>> layout = new HashMap<>();

        for (int i = 0; i < k; i++) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Vector current = vectors[x + y * width];

                    double minDistance = Double.MAX_VALUE;
                    Vector minVector = null;

                    for (Vector centroid : centroids) {
                        double distance = current.distance(centroid);

                        if (distance < minDistance) {
                            minDistance = distance;
                            minVector = centroid;
                        }
                    }

                    layout.getOrDefault(minVector, new HashSet<>()).add(current);
                }
            }

            for (int c = 0; c < centroids.length; c++) {
                Vector mean = new Vector(0, 0, 0, 0, 0);

                for (Vector vector : layout.get(centroids[c])) {
                    mean = mean.add(vector);
                }

                centroids[c] = mean.divide(layout.get(centroids[c]).size());
            }
        }

        for (int c = 0; c < centroids.length; c++) {
            for (Vector vector : layout.get(centroids[c])) {
                this.index.put(vector, lookup.get(centroids[c]));
            }
        }
    }
}

