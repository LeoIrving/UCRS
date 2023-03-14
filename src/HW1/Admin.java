package HW1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Admin extends User implements adminable{
	Admin(){
		username="Admin";
		password="Admin001";
		fn="Leo"; //admin's first name
		ln="Bian"; //admin's last name
	}
	
	//create a course to the list and check if it is valid
	public void create() {
		Course x= new Course();
		x=x.collect();
		boolean test =true;
		int num =0;
		try {
			num=x.getList().size();
		}
		catch(NullPointerException e) {
			num=0;
		}
		for(Course y:list) {
		if(x.getId().equals(y.getId())&&x.getSectionNum().equals(y.getSectionNum())) {
			test=false;}
		}
		if(test!=true) {
			System.out.println("The course has already existed!");
		}
		else if(x.getCurrent()>x.getMax()) {
			System.out.println("Invalid current or maxmium number of students!");
		}
		else if(num!=x.getCurrent()) {
			System.out.println("The student list does not match with current student number");
		}
		else {
			list.add(x);
		}
	}
	//delete a course from the list
	public void delete() {
		String id = askId();
		String sec = askSec();
		Course target=null;
		for(Course x:list) {
			if(x.getId().equals(id)&&x.getSectionNum().equals(sec)) {
				target=x;
			}
		}
		list.remove(target);
	}
	
	//edit a course on only the instructor and location to ensure the stability of the system
	public void edit() {
		String id = askId();
		String sec = askSec();
		Course t= null;
		for(Course x:list) {
			if(x.getId().equals(id)&&x.getSectionNum().equals(sec)) {
				t = x;
			}
		}
		if(t==null) {
			System.out.println("The course is not existed!");
		}
		else {
			boolean a = true;
			Scanner input = new Scanner(System.in);
			while(a) {
			System.out.print("Please indicate the term you want to edit:");
			String in = input.nextLine();
			switch(in) {
			case "instructor":
				System.out.print("Please enter the new instructor's name:");
				String newName = input.nextLine();
				t.setInstructor(newName);
				a=false;
				break;
			case "location":
				System.out.print("Please enter the new Location:");
				String newLoca = input.nextLine();
				t.setLocation(newLoca);
				a=false;
				break;
			default:
				System.out.println("Please choose from either instructor or location!");
				break;
			}
			}
		}
	}
	
	//show all the courses in the list
	public void display() {
		String id = askId();
		for(Course x:list) {
			if(x.getId().equals(id)) {
				System.out.println(x.getName());
				System.out.println(x.getId());
				System.out.println(x.getMax());
				System.out.println(x.getCurrent());
				System.out.println(x.getList());
				System.out.println(x.getInstructor());
				System.out.println(x.getSectionNum());
				System.out.println(x.getLocation());
			}
		}
	}
	//register a student for only one account for each username
	public void register() {
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the username:");
		String a= in.nextLine();
		System.out.print("Please enter the password:");
		String b= in.nextLine();
		System.out.print("Please enter the first name of the student:");
		String c= in.nextLine();
		System.out.print("Please enter the last name of the student:");
		String d= in.nextLine();
		Student x = new Student(a,b,c,d);
		boolean test = true;
		for(Student y:st) {
			if(y.username.equals(a)&&y.password.equals(b)) {
				test=false;
			}
		if(test==true) {
		st.add(x);}
		else {
			System.out.println("The username has existed");
		}}
	}
	//view all courses that are full
	public void viewF() {
		for(Course x:list) {
			if(x.getCurrent()==x.getMax()) {
				System.out.printf("%15s", x.getId());
				System.out.printf("%3s", x.getSectionNum());
				System.out.println();
			}
		}
	}
	//write the full courses to a text
	public void outputFull() throws IOException,FileNotFoundException {
		File obj = new File("src/fullclass.txt");
		if(obj.exists()) {
			obj.delete();
		}
		obj.createNewFile();
		PrintWriter output = new PrintWriter(obj);
		for(Course x:list) {
			if(x.getCurrent()==x.getMax()) {
				output.printf("%15s", x.getId());
				output.printf("%3s",x.getSectionNum() );
				output.println();
			}
		}
		output.close();
	}
	//show the student list of a specific course
	public void viewCourse() {
		Course t= new Course();
		t=find();
		if(t!=null)
		System.out.println(t.getList());
	}
	//show the course list of a specific student
	public void viewSt() {
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the first name of the student:");
		String c= in.nextLine();
		System.out.print("Please enter the last name of the student:");
		String d= in.nextLine();
		ArrayList<Course> x=new ArrayList<Course>();
		if(find(c,d)!=null) {
		x = find(c,d).getStList();
		System.out.println(x);
		}
		else {
			System.out.println("The student is not existed!");
		}
	}
	//sort the course list by the current registered student number
	public void sort() {
		Collections.sort(list);
	}
	
}
