package com.deterra.common;

/**
 * Created by Pavel Nikolaev on 27/09/2015.
 */
import java.util.*;


public class Utilities {

    public static String getHostName(){ //using static methods for testing purposes


        String hostNum;                            //initialise variables
        boolean result;

        System.out.println("enter Host IP");

        Scanner scan = new Scanner(System.in);             //regular expression to validate input
        hostNum = scan.nextLine();
        result = hostNum.matches("([0-9]{1,3})([.])([0-9]{1,3})([.])([0-9]{1,3})([.])([0-9]{1,3})");

        while (!result) {                                               //while loop that prompts user for input
            System.out.println("Please enter a valid IP address");                  // until it is in the correct format
            hostNum = scan.nextLine();
            result = hostNum.matches("([0-9]{1,3})([.])([0-9]{1,3})([.])([0-9]{1,3})([.])([0-9]{1,3})");
        }

        scan.close();

      return hostNum;
    }

    public static int getInteger(){ //using static methods for testing purposes

        String num;
        boolean result;

        Scanner scan = new Scanner(System.in);
        num = scan.nextLine();
        result=num.matches("([0-9]{1,8})");

        while (!result) {
            System.out.println("Please enter a valid integer");
            num = scan.nextLine();
            result=num.matches("([0-9]{1,8})");
        }
        int NUM = Integer.parseInt(num);
        return NUM;
    }


    public static void timeOutFunction(int time, Object object) throws InterruptedException{

        System.out.println("time starting"); //synchronizing object
        synchronized (object){
            object.wait(time);          //waits for x amount of time, where x is in milliseconds
        }
        System.out.println("time is up");

    }

}





