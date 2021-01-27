package com.company;

import javafx.application.Application;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleMenu {
    private static final MyGymManager myGymManager = new MyGymManager();
    private static int count;
    private static String name;
    private static String membershipStartDate;
    private static boolean next;
    private static boolean loop;
    public static void main(String[] args) throws Exception {
        MyGymManager.loadJson();
        Scanner input = new Scanner(System.in); // Creating Scanner
        do {
            System.out.println("========= Welcome to Gym Management System ========\n");
            System.out.println("\t\t\tEnter '1' for Add New Member: ");
            System.out.println("\t\t\tEnter '2' for Delete Member Details : ");
            System.out.println("\t\t\tEnter '3' for Display Member Details: ");
            System.out.println("\t\t\tEnter '4' for Save the Member Details into text file: ");
            System.out.println("\t\t\tEnter '5' for sorting the name  ");
            System.out.println("\t\t\tEnter '6' for Open Table view");
            System.out.println("\t\t\tEnter '7' for Exit the System");
            int option = input.nextInt();

            switch (option) {
                case 1:
                    addMember();
                    break;
                case 2:
                    delMember();
                    break;
                case 3:
                    myGymManager.printMemberDetails();  // Print the Member Details in Console
                    break;
                case 4:
                    myGymManager.saveDetails(); // Save the Details into File
                    break;
                case 5:
                    myGymManager.sort();  // For Sort the name in Ascending Order
                    break;
                case 6:
                    Application.launch(MemberDetailsGui.class); // For gui
                    break;
                case 7:
                    System.exit(0);  // System Exit
                default:
                    System.out.println("\t\t\tInvalid Selection");
            }

           do {
                System.out.print("\t\t\twhat u need ?\n\t\t\tQ = Quit\n\t\t\tC = Continue\n\t\t\tEnter you are choice : ");
                String choice = input.next();
                if (choice.equals("Q") || choice.equals("q")) {
                    next = false;
                    loop = false;
                } else if (choice.equals("C") || choice.equals("c")) {
                    next = true;
                    loop = false;
                }else {
                    loop = true;
                    System.out.println("\t\t\tPlease enter valid input");
                }
            }while (loop);


        }while (next);
    }

    public static void addMember() {
        Scanner usrInput = new Scanner(System.in);  // Creating Scanner
        if (count < 100) {
            System.out.print("\t\t\tEnter the Membership No: ");
            String membershipNumber = usrInput.next();
            boolean bool = true;

            while (bool) {
                System.out.print("\n\t\t\tEnter the Name: ");
                name = usrInput.next();
                bool = check(name);
                if (!bool) {
                    System.out.println("\t\t\tPlease Enter Valid String");
                    bool = true;
                } else {
                    bool = false;
                }
            }
            bool = true;
            Date date = new Date();
            while (bool){
                System.out.print("\n\t\t\tEnter the Date: following format DD-MM-YYYY :");
                membershipStartDate = usrInput.next();
                bool =date.matches(membershipStartDate);
                if (!bool) {
                    System.out.println("\t\t\tPlease Enter Valid Date");
                    bool = true;
                } else {
                    bool = false;
                }
            }
            System.out.print("\n\t\t\tEnter the type of Membership ('D'- Default Member 'S' - Student Number  'O' - Over 60 Member) : ");
            String option = usrInput.next();
            DefaultMember defMember = null;
            switch (option) {
                case "D":
                case "d":
                    defMember = new DefaultMember(membershipNumber, name, membershipStartDate);
                    myGymManager.addNewMember(defMember);
                    count++;
                    break;
                case "S":
                case "s":
                    System.out.print("\n\t\t\tEnter the School Name: ");
                    String schoolName = usrInput.next();
                    defMember = new StudentMember(membershipNumber, name, membershipStartDate, schoolName);
                    myGymManager.addNewMember(defMember);
                    count++;
                    break;
                case "O":
                case "o":
                    try {
                        int age = 0;
                        while (age < 60) {
                            System.out.print("\n\t\t\tEnter the Age: ");
                            age = usrInput.nextInt();
                            defMember = new Over60Member(membershipNumber, name, membershipStartDate, age);
                        }
                        myGymManager.addNewMember(defMember);
                        count++;
                    } catch (Exception e) {
                        System.out.println("\t\t\tPls enter integer value");
                        System.out.print("\n\t\t\tEnter the Age: \n");
                    }
                    break;
                default:
                    System.out.print("\t\t\tInvalid Option Select");
                    break;
            }

        }
    }


    public static boolean check(String str) {
        return Pattern.matches("[A-Za-z]+", str); // Checking the name
    }

    public static void delMember() {
        Scanner usrInput = new Scanner(System.in);  // Creating Scanner
        System.out.print("\t\t\tEnter the Membership Number : ");
        String membershipNo = usrInput.next();
        boolean output = myGymManager.delMember(membershipNo);
        if (output) {
            count--;
        }

    }

}
