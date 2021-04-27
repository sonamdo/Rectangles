# Rectangles

Java application that takes two rectangles and checks their relation to each other in three ways:
1. **Intersection**: Whether or not the rectangangles intersect and at what X and Y points
2. **Containment**: Does either rectangle fully contain the other
3. **Adjacency**: Do the rectangles share a side and is it proper, sub-line, or partial.

Upon running the application you will be prompted for the X and Y vertice points for the first rectangle. Input will be 8 numbers in string format with a single space between each digit. Afterwards you will be asked to input the same values for the second rectangle.

**Input format**: X1 Y1 X2 Y2 X3 Y3 X4 Y4, submit then repeat the second rectangle. 

**Example**: 2 1 4 1 4 3 2 3 [Press Enter] 2 3 4 3 4 5 2 5 [Enter Again]

Once the values have been received, the app will output one of the below: 
- If the rectangles are adjacent and the type
- Whether or not they are contained
- Do they intersect, and at what points
