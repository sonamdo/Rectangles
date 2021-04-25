package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        class Rectangle {
            float[] rectangle = {};
            float[] vertex1 = new float[2];
            float[] vertex2 = new float[2];
            float[] vertex3 = new float[2];
            float[] vertex4 = new float[2];

            Line2D line1 = new Line2D.Float();
            Line2D line2 = new Line2D.Float();
            Line2D line3 = new Line2D.Float();
            Line2D line4 = new Line2D.Float();

            Line2D[] lines = new Line2D[4];

            Rectangle(float[] array) {
                rectangle = array;
                float[] vertex1 = {rectangle[0], rectangle[1]};
                float[] vertex2 = {rectangle[2], rectangle[3]};
                float[] vertex3 = {rectangle[4], rectangle[5]};
                float[] vertex4 = {rectangle[6], rectangle[7]};

                System.out.println(Float.toString(vertex1[0]));

                // using the Java 2D API we create 2 lines with the Line2D class.
                // change this to for loop, go through all intersections
                line1 = new Line2D.Float(vertex1[0], vertex1[1], vertex2[0], vertex2[1]);
                line2 = new Line2D.Float(vertex2[0], vertex2[1], vertex3[0], vertex3[1]);
                line3 = new Line2D.Float(vertex3[0], vertex3[1], vertex4[0], vertex4[1]);
                line4 = new Line2D.Float(vertex4[0], vertex4[1], vertex1[0], vertex1[1]);

                Line2D[] lines = {line1, line2, line3, line4};
                // Line2D line2 = new Line2D.Float(vertex3[0], vertex3[1], vertex4[0], vertex4[1]);

            }

            //check to see if either square is contained within the other by comparing the x and y axis of the top-left and bottom-right vertices.
            public boolean containmentCheck(Rectangle a, Rectangle b){
                boolean contained = false;

                if (((a.vertex1[0] > b.vertex1[0]) && (a.vertex1[0] > b.vertex1[0])) &&
                    ((a.vertex3[0] < b.vertex3[0]) && (a.vertex3[0] < b.vertex3[0])))
                    contained = true;

                if (((a.vertex1[0] > b.vertex1[0]) && (a.vertex1[0] > b.vertex1[0])) &&
                    ((a.vertex3[0] < b.vertex3[0]) && (a.vertex3[0] < b.vertex3[0])))
                    contained = true;
                System.out.println("Contained: " + contained);
                return contained;
            }

            public boolean intersectionCheck(Rectangle a, Rectangle b){

                // check all lines in rectangle a to see if they intersect with all lines in rectangle b
                for(int z = 0; z < 4; z = z+1) {
                    for (int e = 0; e < 4; e = e+1){
                        if(a.lines[z].intersectsLine(b.lines[e])){

//                            System.out.println("Intersection at " + result);
                        }
                    }
                }
                // check for intersections between the 2 lines
                // CAVEAT: also show true when lines connect, not intersect
                boolean result = line2.intersectsLine(line1); // COMPARE ALL LINES FROM RECT A TO RECT B!
                // once intersectsLine =true, check x and y values of vertexes for match.
                System.out.println("Intersection: " + result);
                return result;
            }

            public boolean adjacencyCheck(Rectangle a, Rectangle b){
                boolean adjacent = false;

                // First loop check for matching X axis. No need to check for y as all intersections will connect on both x and y
                for(int x = 0; x < 8; x = x+2) {
                    for(int y = 0; y < 8; y = y+2){
                        if (a.rectangle[x] == b.rectangle[y]) {
//                            System.out.println("match");

                            if(a.vertex1[0] == b.vertex1[0]){
                                System.out.println(b.vertex1[0]);
                            }

                            if (
                                a.line1.contains(a.rectangle[x]+1, b.rectangle[y]) ||
                                a.line1.contains(a.rectangle[x]-1, b.rectangle[y]) ||
                                a.line1.contains(a.rectangle[x+1]+1, b.rectangle[y]) ||
                                a.line1.contains(a.rectangle[x+1]-1, b.rectangle[y])
                            ) adjacent = true;
                            // proper, sub-line, partial
                        }
                    }

                }
                System.out.println("adjacent: " + adjacent);
                return adjacent;
            }

        }


        Rectangle rectangle1 = new Rectangle(new float[]{0, 0, 0, 4, 4, 4, 4, 0});
        Rectangle rectangle2 = new Rectangle(new float[]{0, 12, 13, 14, 15, 16, 17, 18});
        rectangle1.containmentCheck(rectangle1, rectangle2);
        rectangle1.adjacencyCheck(rectangle1, rectangle2);

    }

}
