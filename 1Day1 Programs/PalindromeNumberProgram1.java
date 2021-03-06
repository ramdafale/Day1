package com.training.java;
/*********************************************************************************
 *  Program name :		  Check if integer is palindrome or not.
 *   Program Description : It check if given number is read same from left and right. 
 *   					  eg. 121, 131;
 * Programmer Name: Ram Dafale
 * DateOfRelease: 16-April-2018
 *********************************************************************************/

//Library to take input from user in console
import java.util.Scanner;


public class PalindromeNumber {

	
	 /************************************************************************************
	  *Method Name: main
	  *Description: It takes input from user. then it breaks the number by getting remainder 
	  *				then it adds that remainder to sum varibale. and then divide the number.
	  *				this process repeats till we get same number as user specified.
	  ************************************************************************************/	 
	public static void main(String[] args) {
		
		 int num, temp, sum = 0,x;
		 // sum varibale to 
		 // try block to monitor exception and to avoid the wrong input from user
		 try
		 {
		 // take input from user and store in a variable 
	        Scanner inputNumber = new Scanner(System.in);
	        System.out.print("Enter any number:");
	        num = inputNumber.nextInt();
	       // temporary variable to store user input 
	        temp = num;
	       // loop for checking the number greater than 0
	        while(num > 0)
	        {
	        	
	            x = num % 10; 
	            sum = sum * 10 + x;
	            num = num / 10;
	        }
	        // comparing given integer with calculated integer
	        // if sum and temp are equal then given number is palindrome
	        if(sum == temp)
	        {
	            System.out.println("Given number "+temp+" is Palindrome");
	        }
	        else
	        {
	            System.out.println("Given number "+temp+" is Not Palindrome");
	        }
		 }
		 // catch block to catch exception if any and show appropriate response to user
		 catch(Exception e)
		 {
			 System.out.print("Enter only Number");
			 
		 }
		

	}
}

/********************************************************************
output:
Enter any number:131
Given number 131 is Palindrome

**********************************************************************/


