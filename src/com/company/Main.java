package com.company;

import java.util.Arrays;

public class Main {

    /* This is my first java program.
     * This will print 'Hello World' as the output
     */

    public static void main(String[] args) {

        class Rectangle {
            int[] rectangle = {};
            int[] vertex1 = {};
            int[] vertex2 = {};
            int[] vertex3 = {};
            int[] vertex4 = {};


            Rectangle(int[] array) {
                rectangle = array;
                int[] vertex1 = {rectangle[0], rectangle[1]};
                int[] vertex2 = {rectangle[0], rectangle[1]};
                int[] vertex3 = {rectangle[0], rectangle[1]};
                int[] vertex4 = {rectangle[0], rectangle[1]};

                System.out.println(Arrays.toString(vertex2));

            }

        }

        Rectangle rectangle1 = new Rectangle(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        Rectangle rectangle2 = new Rectangle(new int[]{11, 12, 13, 14, 15, 16, 17, 18});

    }
}
