package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    //rectangles intersect
    Main.Rectangle rectangle1 = new Main.Rectangle(new float[]{6, 3, 9, 3, 9, 5, 6, 5});
    Main.Rectangle rectangle2 = new Main.Rectangle(new float[]{8, 4, 11, 4, 11, 7, 8, 7});

    //rectangle 3 contains rectangle 4
    Main.Rectangle rectangle3 = new Main.Rectangle(new float[]{12, 1, 15, 1, 15, 4, 12, 4});
    Main.Rectangle rectangle4 = new Main.Rectangle(new float[]{13, 2, 14, 2, 14, 3, 13, 3});

    //rectangles do not intersect
    Main.Rectangle rectangle5 = new Main.Rectangle(new float[]{13, 5, 16, 5, 16, 7, 13, 7});
    Main.Rectangle rectangle6 = new Main.Rectangle(new float[]{14, 8, 16, 8, 16, 9, 14, 9});

    //rectangles intersect
    Main.Rectangle rectangle7 = new Main.Rectangle(new float[]{-6, -3, -9, -3, -9, -5, -6, -5});
    Main.Rectangle rectangle8 = new Main.Rectangle(new float[]{-8, -4, -11, -4, -11, -7, -8, -7});

    //rectangle 9 contains rectangle 10
    Main.Rectangle rectangle9 = new Main.Rectangle(new float[]{-12, -1, -15, -1, -15, -4, -12, -4});
    Main.Rectangle rectangle10 = new Main.Rectangle(new float[]{-13, -2, -14, -2, -14, -3, -13, -3});

    //rectangles adjacent proper
    Main.Rectangle rectangle11 = new Main.Rectangle(new float[]{6, 3, 9, 3, 9, 5, 6, 5});
    Main.Rectangle rectangle12 = new Main.Rectangle(new float[]{8, 4, 11, 4, 11, 7, 8, 7});

    //rectangles adjacent sub-line
    Main.Rectangle rectangle13 = new Main.Rectangle(new float[]{16, 1, 17, 1, 17, 4, 16, 4});
    Main.Rectangle rectangle14 = new Main.Rectangle(new float[]{17, 2, 18, 2, 18, 3, 17, 3});

    //rectangles adjacent partial
    Main.Rectangle rectangle15 = new Main.Rectangle(new float[]{7, 8, 10, 8, 10, 9, 7, 9});
    Main.Rectangle rectangle16 = new Main.Rectangle(new float[]{8, 9, 12, 9, 12, 10, 8, 10});


    @Test
    void createRectangle(){

    }

    @Test
    void containmentCheckTest(){
        //check if containment check is working correctly
        assertFalse(rectangle1.containmentCheck(rectangle1, rectangle2));
        assertTrue(rectangle3.containmentCheck(rectangle3, rectangle4));
        assertFalse(rectangle5.containmentCheck(rectangle5, rectangle6));
        assertFalse(rectangle7.containmentCheck(rectangle7, rectangle8));
        assertFalse(rectangle9.containmentCheck(rectangle9, rectangle10));
    }

    @Test
    void adjacencyCheck(){

    }

}