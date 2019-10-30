/*
 *  Triangle.java
 * 
 *  This class will get input of 3 sides of a triangle, validate the input, and output the area and perimeter of the triangle.
 *  
 */

import java.util.Scanner;

public class Triangle
{
    
    public static void main(String []args)
    {
        // Initialize scanner object
        Scanner scan = new Scanner(System.in);
        
        System.out.println("This program calculates the area and perimeter of a given triangle.");
        System.out.println("Please provide the three lengths of a triangles sides.");
        
        // User input of triangle sides
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        
        // Check if any input is < 1
        if( a < 1 || b < 1 || c < 1){
         System.out.println("Input error, triangle sides should be greater than zero.");   
        }else {
            // Triangle sides validation rule
            // Check if the sum of any 2 sides is bigger than the 3rd side.
            boolean isTriangleValid = (a + b > c) && (a + c > b) && (b + c > a);
            
            if(isTriangleValid){
             // Compute triangle perimeter
             int perimeter = a + b + c;
             
             // Compute triangle area with Heron formula
             double s = perimeter / 2;
             double area = Math.sqrt(s * (s-a) * (s-b) * (s-c));
             
             // Print result to user
             System.out.println("The perimeter of the triangle is " + perimeter + ".");
             System.out.println("The area of the triangle is " + area + ".");
            } else {
                // The triangle is not valid, print error to user
                System.out.println("The given sides - a: " + a + ", b: " + b + ", c: " + c + ", are not valid.");
            }
        }
    }

}
