
/**
 * Congruent.java
 * 
 * This program will get as input from the user the vertices of 2 triangles,
 * and print to the screen a message if the triangles are congruent or not.
 * 
 * The length of the triangles sides will be calculated with the following formula:
 *  length = Square root of ( (x1-x2)^2 + (y1-y2)^2 )
 * and then used to check if sides are equal. 
 * 
 */

import java.util.Scanner;

public class Congruent
{
    public static void main(String[] args)
    {
        // Initialize Scanner object
        Scanner scan = new Scanner(System.in);
        
        ///////////// First triangle
        System.out.println("Please enter vertices of traingle A:");
        
        // First vertex
        System.out.println("First triangle, Vertex 1 coordinate X:");
        double x11 = scan.nextDouble();
        System.out.println("First triangle, Vertex 1 coordinate Y:");
        double y11 = scan.nextDouble();
        
        // Second vertex
        System.out.println("First triangle, Vertex 2 coordinate X:");
        double x12 = scan.nextDouble();
        System.out.println("First triangle, Vertex 2 coordinate Y:");
        double y12 = scan.nextDouble();
        
        // Third vertex
        System.out.println("First triangle, Vertex 3 coordinate X:");
        double x13 = scan.nextDouble();
        System.out.println("First triangle, Vertex 3 coordinate Y:");
        double y13 = scan.nextDouble();
        
        
        // First side
        double a1 = Math.sqrt(Math.pow((x11-x12),2) + Math.pow((y11-y12),2));
        
        // Second side
        double b1 = Math.sqrt(Math.pow((x11-x13),2) + Math.pow((y11-y13),2));
        
        // Third side
        double c1 = Math.sqrt(Math.pow((x12-x13),2) + Math.pow((y12-y13),2));
        
        
        ///////////// Second triangle
        System.out.println("\nPlease enter vertices of traingle A:");
        
        // First vertex
        System.out.println("Second triangle, Vertex 1 coordinate X:");
        double x21 = scan.nextDouble();
        System.out.println("Second triangle, Vertex 1 coordinate Y:");
        double y21 = scan.nextDouble();
        
        // Second vertex
        System.out.println("Second triangle, Vertex 2 coordinate X:");
        double x22 = scan.nextDouble();
        System.out.println("Second triangle, Vertex 2 coordinate Y:");
        double y22 = scan.nextDouble();
        
        // Third vertex
        System.out.println("Second triangle, Vertex 3 coordinate X:");
        double x23 = scan.nextDouble();
        System.out.println("Second triangle, Vertex 3 coordinate Y:");
        double y23 = scan.nextDouble();
        
        
        // First side
        double a2 = Math.sqrt(Math.pow((x21-x22),2) + Math.pow((y21-y22),2));
        
        // Second side
        double b2 = Math.sqrt(Math.pow((x21-x23),2) + Math.pow((y21-y23),2));
        
        // Third side
        double c2 = Math.sqrt(Math.pow((x22-x23),2) + Math.pow((y22-y23),2));
        
        
        // Print triangle information
        System.out.println("\nThe first triangle is (" + x11 + ", " + y11 + ") ("+ x12 + ", " + y12 + ") ("+ x13 + ", " + y13 + ").");
        System.out.println("Its lengths are " + a1 + ", " + b1 + ", " + c1 + ".");
        
        System.out.println("The second triangle is (" + x21 + ", " + y21 + ") ("+ x22 + ", " + y22 + ") ("+ x23 + ", " + y23 + ").");
        System.out.println("Its lengths are " + a2 + ", " + b2 + ", " + c2 + ".");
        
        
        // Check if the triangles are congurent by comparing lengths
        if(a1 == a2 && b1 == b2 && c1 == c2)
        {
         System.out.println("\nThe triangles are congruent.");   
        }
        else 
        {
            System.out.println("\nThe triangles are not congruent.");
        }
    }
}
