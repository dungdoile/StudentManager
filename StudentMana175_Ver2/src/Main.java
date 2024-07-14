/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;

/**
 *
 * @author asus
 */
public class Main {

    static Manager mana = new Manager();

    public int menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=========== STUDENT MANAGERMENT =========");
        System.out.println("1. Create");
        System.out.println("2. Find and Sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        while (true) {
            try {
                int choice = Integer.parseInt(sc.nextLine().trim());
                if (choice < 1 || choice > 5) {
                    System.err.println("Must be between 1 to 5");
                    continue;
                }
                return choice;
            } catch (NumberFormatException e) {
                System.err.println("Invalid Input");
                System.out.println("Enter again");
            }
        }
    }

    public String checkInputStr() {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            try {
                str = sc.nextLine();
                if (str.equals("")) {
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.err.println("Cannot be empty!!");
                System.out.print("Enter again: ");
            }

        }

    }

    public int checkInputInt() {
        Scanner sc = new Scanner(System.in);
        int input;
        while (true) {
            try {
                input = Integer.parseInt(sc.nextLine().trim());
                return input;

            } catch (NumberFormatException e) {
                System.err.println("Invalid !");
                System.out.print("Enter again: ");
            }
        }
    }

    public boolean checkYN() {
        System.out.print("Do you want to continue(Y/N)?: ");
        while (true) {
            String input = checkInputStr();
            if (input.equalsIgnoreCase("Y")) {
                return true;
            }
            if (input.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please enter Y/y or N/n!!!");
            System.out.print("Enter again: ");
        }
    }

    public boolean checkUD() {
        System.out.print("Do you want to update or delete(U/D)?: ");
        while (true) {
            String input = checkInputStr();
            if (input.equalsIgnoreCase("U")) {
                return true;
            }
            if (input.equalsIgnoreCase("D")) {
                return false;
            }
            System.err.println("Please enter U/u or D/d!!!");
            System.out.print("Enter again: ");
        }
    }

    public String checkCourse() {
        while (true) {
            String input = checkInputStr();
            if (input.equalsIgnoreCase("Java")) {
                return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
            }
            if (input.equalsIgnoreCase(".Net")) {
                return input.substring(0, 2).toUpperCase() + input.substring(2).toLowerCase();

            }
            if (input.equalsIgnoreCase("C/C++")) {
                return input.substring(0, 3).toUpperCase() + input.substring(3);

            }
            System.err.println("Please input Java, .Net or C/C++!!!");
            System.out.print("Enter again: ");
        }
    }

    public Student addStudent() {
        System.out.print("Enter ID: ");
        String id = checkInputStr();
        if (mana.checkIDExist(id) != null) {
            if (checkYN()) {
                System.out.print("Enter semester: ");
                int semester = checkInputInt();
                System.out.print("Enter course name: ");
                String course = checkCourse();
                return new Student(id, mana.findOneStudent(id).getName(), semester, course);
            } else {
                return null;
            }
        }
        System.out.print("Enter name: ");
        String name = checkInputStr();
        System.out.print("Enter semester: ");
        int semester = checkInputInt();
        System.out.print("Enter course name: ");
        String course = checkCourse();
        return new Student(id, name, semester, course);

    }

    public static void main(String[] args) throws Exception {
        Main m = new Main();
        int choice = 0;
        mana.generateStudent();
        while (true) {
            choice = m.menu();
            switch (choice) {
                case 1:
                    if (mana.ls.size() == 10) {
                        if (m.checkYN()) {
                            mana.createStudent(m.addStudent());
                        } else {
                            break;
                        }
                    } else {
                        mana.createStudent(m.addStudent());
                    }

                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String str = m.checkInputStr();
                    try {
                        mana.displayStudent(mana.sortListByName(mana.findStudentByName(str)));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 3:
                    System.out.print("Enter ID: ");
                    String id = m.checkInputStr();
                    try {
                        mana.displayStudent(mana.findStudentByID(id));
                        System.out.print("Enter position you want to U/D: ");
                        int index = m.checkInputInt();
                        Student st = mana.findInList(id, index);
                        if (m.checkUD()) {
                            System.out.println("--------UPDATING-------");
                            System.out.print("Enter name: ");
                            String name = m.checkInputStr();
                            System.out.print("Enter semester: ");
                            int semester = m.checkInputInt();
                            System.out.print("Enter course name: ");
                            String course = m.checkCourse();
                            mana.updateStudent(st, id, name, semester, course);
                            System.out.println("UPDATED!!!");
                        } else {
                            mana.deleteStudent(st);
                            System.out.println("DELETED!!!");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        mana.report();

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}
