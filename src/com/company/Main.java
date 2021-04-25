package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Rectangle rectangle1 = new Rectangle(new float[]{6, 3, 9, 3, 9, 5, 6, 5});
        Rectangle rectangle2 = new Rectangle(new float[]{8, 4, 11, 4, 11, 7, 8, 7});

//        Rectangle rectangle3 = new Rectangle(new float[]{12, 1, 15, 1, 15, 4, 12, 4});
//        Rectangle rectangle4 = new Rectangle(new float[]{13, 2, 14, 2, 14, 3, 13, 3});

        rectangle1.intersectionCheck(rectangle1, rectangle2);
//      rectangle1.containmentCheck(rectangle3, rectangle4);
//       rectangle1.adjacencyCheck(rectangle1, rectangle2);
    }

        public static class Rectangle {
            float[] rectangleCoordinates = {};
            float[][] vertices = new float[4][];

            Line2D[] lines = new Line2D[4]; // array of arrays?

            Rectangle(float[] array) {
                rectangleCoordinates = array;
                for(int z = 0; z < 8; z = z+2) {

                }
                vertices[0] = new float[]{rectangleCoordinates[0], rectangleCoordinates[1]};
                vertices[1] = new float[]{rectangleCoordinates[2], rectangleCoordinates[3]};
                vertices[2] = new float[]{rectangleCoordinates[4], rectangleCoordinates[5]};
                vertices[3] = new float[]{rectangleCoordinates[6], rectangleCoordinates[7]};

                // using the Java 2D API we create 2 lines with the Line2D class.
                // change this to for loop, go through all intersections
                lines[0] = new Line2D.Float(vertices[0][0], vertices[0][1], vertices[1][0], vertices[1][1]);
                lines[1] = new Line2D.Float(vertices[1][0], vertices[1][1], vertices[2][0], vertices[2][1]);
                lines[2] = new Line2D.Float(vertices[2][0], vertices[2][1], vertices[3][0], vertices[3][1]);
                lines[3] = new Line2D.Float(vertices[3][0], vertices[3][1], vertices[0][0], vertices[0][1]);

            }

            //check to see if either square is contained within the other by comparing the x and y axis of the top-left and bottom-right vertices.
            public boolean containmentCheck(Rectangle a, Rectangle b){
                boolean contained = false;

                if ((a.vertices[0][0]<b.vertices[0][0]) && (a.vertices[0][1]<b.vertices[0][1]) &&
                (a.vertices[2][0]>b.vertices[2][0]) && (a.vertices[2][1]>b.vertices[2][1])){
                    contained = true;
                }

                System.out.println("Contained: " + contained);
                return contained;
            }

            public boolean intersectionCheck(Rectangle a, Rectangle b){
                ArrayList<float[]> intersections = new ArrayList<>() ;
                float y = 0;
                float x = 0;
                boolean intersectionFound = false;

                // check all lines(z) in rectangle a to see if they intersect with all lines(e) in rectangle b
                for(int z = 0; z < 4; z = z+1) {
                    for (int e = 0; e < 4; e = e+1){
                        if(a.lines[z].intersectsLine(b.lines[e])) {

                            //at 3 we have to loop back to the first vertex
                            if (z == 3) {
                                if (a.vertices[z][0] == a.vertices[0][0]) {
                                    x = (a.vertices[z][0]);
                                    intersectionFound = true;
                                }
                                ; // checks x axis for match
                                if (a.vertices[z][1] == a.vertices[0][1]) {
                                    y = (a.vertices[z][1]);
                                    intersectionFound = true;
                                }
                                ; // checks y axis for match
                            } else {
                                if (a.vertices[z][0] == a.vertices[z + 1][0]) {
                                    x = (a.vertices[z][0]);
                                    intersectionFound = true;
                                }
                                ; // checks x axis for match
                                if (a.vertices[z][1] == a.vertices[z + 1][1]) {
                                    y = (a.vertices[z][1]);
                                    intersectionFound = true;
                                }
                                ; // checks y axis for match
                            }

//                             check line 2 vertices
                            if (e == 3) {
                                if (b.vertices[e][0] == b.vertices[0][0]) {
                                    x = (b.vertices[e][0]);
                                    intersectionFound = true;
                                }
                                ; // checks x axis for match
                                if (b.vertices[e][1] == b.vertices[0][1]) {
                                    y = (b.vertices[e][1]);
                                    intersectionFound = true;
                                }
                                ; // checks y axis for match
                            } else {
                                if (b.vertices[e][0] == b.vertices[e + 1][0]) {
                                    x = (b.vertices[e][0]);
                                    intersectionFound = true;
                                }
                                ; // checks x axis for match
                                if (b.vertices[e][1] == b.vertices[e + 1][1]) {
                                    y = (b.vertices[e][1]);
                                    intersectionFound = true;
                                }
                                ; // checks y axis for match
                            }

                            //add intersection points to array
                            if (intersectionFound) {
                                float[] coordinates = {x,y};
                                intersections.add(coordinates);
                                intersectionFound = false;
                            }
                        }
                    }
                }

                // proper, sub-line, partial, non-adjacent
                // proper : 2 vertices will line up with intersections, but not further
                // sub-line : will not match vertices, and wont extend beyond vertices
                // partial : one side will extend beyond vertice
                // non-adjacent: easy, intersections.size() < 1

                for(var i = 0; i<intersections.size(); i = i+1){
                    System.out.println("hi");
                }

                return true;
            }

        }

    }


