package com.company;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{

    public static void main(String... args) {

        //Ask for user input through command line
        Scanner userInput = new Scanner(System.in);

        System.out.println("To create a rectangle, we need the X and Y axes of it's 4 vertices. Please enter these 8 values in order with spaces inbetween each number. Format: X1 Y1 X2 Y2 X3 Y3 X4 Y4");
        String firstRectangle = userInput.nextLine();

        System.out.println("Now do the same for the second rectangles values. Remember: X1 Y1 X2 Y2 X3 Y3 X4 Y4");
        String secondRectangle = userInput.nextLine();

        String[] firstRectangleArray = firstRectangle.split("\\s+");
        String[] secondRectangleArray = secondRectangle.split("\\s+");

        float[] rectangleOne = new float[8];
        float[] rectangleTwo = new float[8];

        // converts rectangle dimensions from string to array
        for(int z = 0; z < 8; z = z+1) {
            rectangleOne[z] = Float.parseFloat(firstRectangleArray[z]);
            rectangleTwo[z] = Float.parseFloat(secondRectangleArray[z]);
        }

        // creates 2 rectangle classes based on input
        Rectangle rectangle1 = new Rectangle(rectangleOne);
        Rectangle rectangle2 = new Rectangle(rectangleTwo);

        rectangle1.intersectionCheck(rectangle1, rectangle2);
        rectangle1.containmentCheck(rectangle1, rectangle2);

    }

        public static class Rectangle {
            float[] rectangleCoordinates;
            float[][] vertices = new float[4][];

            Line2D[] lines = new Line2D[4];

            Rectangle(float[] array) {
                rectangleCoordinates = array;
                vertices[0] = new float[]{rectangleCoordinates[0], rectangleCoordinates[1]};
                vertices[1] = new float[]{rectangleCoordinates[2], rectangleCoordinates[3]};
                vertices[2] = new float[]{rectangleCoordinates[4], rectangleCoordinates[5]};
                vertices[3] = new float[]{rectangleCoordinates[6], rectangleCoordinates[7]};

                // using the Java 2D API we create lines for each side of the rectangle with Line2D class.
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

                System.out.println("Rectangle contains other rectangle: " + contained);
                return contained;
            }

            public String intersectionCheck(Rectangle a, Rectangle b){
                String result = "Test";
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

                // More then 2 intersection points means the rectangles are adjacent
                if (intersections.size()>2){

                    boolean adjacencyOnX = false;
                    float adjacencyAxis = 0;

                    // compare x axis of first and last intersection points. If true then rectangles are adjacent on x axis. If false then it must be y axis
                    if (intersections.get(0)[0] == intersections.get(intersections.size()-1)[0]){
                        adjacencyOnX = true;
                        adjacencyAxis = intersections.get(0)[0];
                    } else {adjacencyAxis = intersections.get(0)[1];}

                    float[] rectangleValuesA = new float[2];
                    float[] rectangleValuesB = new float[2];
                    int countA = 0;
                    int countB = 0;

                    // check rectangles vertices along adjacency axis and get their values. This will give us two line segments we can compare for type of overlapping
                    if(adjacencyOnX == true){
                        for (int e = 0; e < 4; e = e+1){
                            if(a.vertices[e][0] == adjacencyAxis){
                                rectangleValuesA[countA] = a.vertices[e][1];
                                countA++;
                            }
                            if(b.vertices[e][0] == adjacencyAxis){
                                rectangleValuesB[countB] = b.vertices[e][1];
                                countB++;
                            }
                        }
                    }

                    if(adjacencyOnX == false){
                        for (int e = 0; e < 4; e = e+1){
                            if(a.vertices[e][1] == adjacencyAxis){
                                rectangleValuesA[countA] = a.vertices[e][0];
                                countA++;
                            }
                            if(b.vertices[e][1] == adjacencyAxis){
                                rectangleValuesB[countB] = b.vertices[e][0];
                                countB++;
                            }
                        }
                    }


                    // set the min and max values of line segments for comparison
                    float minA, maxA, minB, maxB;
                    if(rectangleValuesA[0]<rectangleValuesA[1]){
                        minA = rectangleValuesA[0];
                        maxA = rectangleValuesA[1];
                    } else {
                        minA = rectangleValuesA[1];
                        maxA = rectangleValuesA[0];
                    }

                    if(rectangleValuesB[0]<rectangleValuesB[1]){
                        minB = rectangleValuesB[0];
                        maxB = rectangleValuesB[1];
                    } else {
                        minB = rectangleValuesB[1];
                        maxB = rectangleValuesB[0];
                    }

                    // series of if statements detect what type of adjacency the rectangles have
                    if(((minA < minB) && (maxA > maxB)) ||
                        ((minA > minB) && (maxA < maxB))
                    ){
                        result = "Adjacent rectangles. Type: Sub-line";
                    }

                    if((minA == minB) && (maxA == maxB)){
                        result = "Adjacent rectangles. Type: Proper";
                    }

                    if(((minA < minB) && (maxA > minB) && (maxA > minB)) ||
                        ((minA > minB) && (maxA < minB) && (maxA < minB)))
                    {
                        result = "Adjacent rectangles. Type: Partial";
                    }

                } else if (intersections.size() > 0) {
                    {
                        result = "Intersections found at:";
                        for (var i = 0; i < intersections.size(); i = i + 1) {
                            result += ("\n" + intersections.get(i)[0] + ", " + intersections.get(i)[1]);
                        }
                    }
                } else {
                    result = "No intersections found";
                }

                System.out.println(result);
                return result;
            }

        }

    }


