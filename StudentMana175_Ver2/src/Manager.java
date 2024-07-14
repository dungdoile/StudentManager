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
public class Manager {

    ArrayList<Student> ls = new ArrayList<>();

    public void generateStudent() {
        ls.add(new Student("A1", "Nguyen Van A ", 1, "Java"));
        ls.add(new Student("A2", "Nguyen Van B ", 2, "Java"));
        ls.add(new Student("A3", "Nguyen Van C ", 3, "Java"));
        ls.add(new Student("A1", "Nguyen Van A ", 3, "Java"));
        ls.add(new Student("A1", "Nguyen Van A ", 2, "C/C++"));
        ls.add(new Student("A2", "Nguyen Van B ", 3, ".Net"));
        ls.add(new Student("A2", "Nguyen Van B ", 4, ".Net"));
        ls.add(new Student("A1", "Nguyen Van A ", 2, ".Net"));
        ls.add(new Student("A3", "Nguyen Van C ", 2, ".Net"));
    }

    public void createStudent(Student newStudent) {
        if (newStudent != null) {
            ls.add(newStudent);
        }

    }

    public ArrayList<Student> findStudentByID(String id) throws Exception {
        if (ls.isEmpty()) {
            throw new Exception("List is empty");
        }
        ArrayList<Student> newList = new ArrayList<>();
        boolean check = false;
        for (Student st : ls) {
            if (id.equalsIgnoreCase(st.getId())) {
                newList.add(st);
                check = true;
            }
        }
        if (check == false) {
            throw new Exception("ID not exist!!");
        }
        return newList;
    }

    public ArrayList<Student> findStudentByName(String str) throws Exception {
        if (ls.isEmpty()) {
            throw new Exception("List is empty");
        }
        ArrayList<Student> newList = new ArrayList<>();
        boolean check = false;
        for (Student st : ls) {
            if (st.getName().contains(str)) {
                newList.add(st);
                check = true;
            }
        }
        if ( check == false ){
            throw new Exception("Not found in the list");
        }
        return newList;
    }

    public void displayStudent(ArrayList<Student> list) throws Exception{
        if ( list.isEmpty()){
            throw new Exception("List is empty");
        }

        System.out.println("---------- Student List ----------");
        System.out.printf("%-10s%-15s%-15s%-15s\n", "ID", "Student Name", "Semester", "Course Name");
        for (Student st : list) {
            System.out.printf("%-10s%-15s%-15d%-15s\n", st.getId(), st.getName(), st.getSemester(), st.getCourseName());
        }
    }

    public ArrayList<Student> sortListByName(ArrayList<Student> listName) {
        Collections.sort(listName, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }

        });
        return listName;
    }

    public Student findInList(String id, int index) throws Exception {
        ArrayList<Student> list = findStudentByID(id);
        for (int i = 0; i < list.size(); i++) {
            if (index == i + 1) {
                return list.get(i);
            }
        }
        System.err.println("Not found!");
        return null;
    }

    public Student checkIDExist(String id) {
        for (Student st : ls) {
            if (st.getId().equalsIgnoreCase(id)) {
                System.out.println("There is already ID " + id + " in the list.");
                System.out.println("ID of Student Name " + st.getName() + ".");
                return st;
            }
        }
        return null;
    }

    public Student findOneStudent(String id) {
        for (Student st : ls) {
            if (st.getId().equalsIgnoreCase(id)) {
                return st;
            }
        }
        return null;
    }

    public void updateStudent(Student st, String id, String name, int semester, String course) {
        st.setId(id);
        st.setName(name);
        st.setSemester(semester);
        st.setCourseName(course);
    }

    public void deleteStudent(Student st) {
        ls.remove(st);
    }

    public void report() throws Exception{
        if (ls.isEmpty()){
            throw new Exception("List is empty!!!");
        }
        ArrayList<Student> list = new ArrayList<>(ls);
        System.out.printf("%-15s%-15s%-15s\n", "Student Name", "Course", "Total");
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getName();
            String course = list.get(i).getCourseName();
            int courseNum = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (name.equalsIgnoreCase(list.get(j).getName()) && course.equalsIgnoreCase(list.get(j).getCourseName())) {
                    courseNum++;
                    list.remove(j);
                    j--;
                }
            }
            System.out.printf("%-15s%-15s%-15s\n", name, course, courseNum);
        }
    }
}
