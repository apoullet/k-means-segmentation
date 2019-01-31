package me.antoine.seg.calc;

import java.awt.image.BufferedImage;

public class FVectors {
    private Vector[] vectors;

    public FVectors(BufferedImage image) {
        this.vectors = new Vector[image.getWidth() * image.getHeight()];

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                int r = ((pixel >> 16) & 0xff);
                int g = ((pixel >> 8) & 0xff);
                int b = (pixel & 0xff);
                Vector vector = new Vector(r, g, b, x, y);
                this.vectors[x + y * image.getWidth()] = vector;
            }
        }
    }

    public Vector[] getFVectors() {
        return this.vectors;
    }
}

class Vector {
    private int[] elements;

    Vector(int r, int g, int b, int x, int y) {
        this.elements = new int[]{r, g, b, x, y};
    }

    public double distance(Vector other) {
        double sum = 0;

        for (int i = 0; i < 5; i++) {
            sum += Math.pow(this.elements[i] - other.elements[i], 2);
        }

        return Math.sqrt(sum);
    }

    public int[] location() {
        return new int[]{ this.elements[3], this.elements[4] };
    }

    public Vector add(Vector other) {
        return new Vector(this.elements[0] + other.elements[0],
                this.elements[1] + other.elements[1],
                this.elements[2] + other.elements[2],
                this.elements[3] + other.elements[3],
                this.elements[4] + other.elements[4]
                );
    }

    public Vector divide(int divisor) {
        return new Vector(this.elements[0] / divisor,
                this.elements[1] / divisor,
                this.elements[2] / divisor,
                this.elements[3] / divisor,
                this.elements[4] / divisor
                );
    }
}
