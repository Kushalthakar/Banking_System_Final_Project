//import CurrentPackage.*;
//import  SavingsPackage.*;
import java.util.*;
import java.text.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.*;

public class demobank
{

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 for Current account");
        System.out.println("Press 2 for Savings Account");
        System.out.println("Enter choice:");
        int c = sc.nextInt();
        switch(c)
        {
            case 1:
                current.democurrent();
            case 2:
                savings.demosavings();
        }
    }
}
