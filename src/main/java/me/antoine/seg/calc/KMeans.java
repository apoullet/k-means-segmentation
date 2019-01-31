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

                    this.index.put(current, lookup.get(minVector));
                }
            }

            for (int c = 0; c < centroids.length; c++) {
                Vector mean = new Vector(0, 0, 0, 0, 0);
                int count = 0;

                for (Vector vector : this.index.keySet()) {
                    if (this.index.get(vector) == c) {
                        mean = mean.add(vector);
                        count++;
                    }
                }

                centroids[c] = mean.divide(count);
            }
        }
    }
}

