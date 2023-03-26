package util;

import java.util.Scanner;

public abstract class Option {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    protected final void menu(){
        System.out.println(ANSI_GREEN+"==============="+ "Student Management System" + "==============="+ANSI_RESET);

        System.out.println(ANSI_GREEN+"1"+ANSI_RESET+ ". Insert Student to Application");
        System.out.println(ANSI_GREEN+"2"+ANSI_RESET+ ". Edit Student Information");
        System.out.println(ANSI_GREEN+"3"+ANSI_RESET+ ". Search Student Information");
        System.out.println(ANSI_GREEN+"4"+ANSI_RESET+ ". Delete Student Information");
        System.out.println(ANSI_GREEN+"5"+ANSI_RESET+ ". Show Student Information");
        System.out.println(ANSI_GREEN+"6"+ANSI_RESET+ ". Exit.");
    }

    protected final void searchMenu(){
        System.out.println(ANSI_GREEN+"1"+ANSI_RESET+". Search by Id");
        System.out.println(ANSI_GREEN+"2"+ANSI_RESET+". Search by Name");
        System.out.println(ANSI_GREEN+"3"+ANSI_RESET+". Search by Gender");
        System.out.println(ANSI_GREEN+"4"+ANSI_RESET+". Search by Class Name");
        System.out.println(ANSI_GREEN+"5"+ANSI_RESET+". Exit");
    }

    public static void alertMessage(String message){
        //System.err.println("Error input");
        System.out.println(ANSI_RED+"ERROR!!! "+ANSI_RESET+ANSI_GREEN+ message+ANSI_RESET +ANSI_RED+" Try again....."+ANSI_RESET);
    }
    public final void successMessage(String action){
        System.out.println(ANSI_GREEN+"SUCCESS: You have been "+ action +" successfully."+ANSI_RESET);
    }
    public void enterContinue(){
        Scanner input = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        input.nextLine();
    }
    public abstract void stop();
}
