package HW1;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static ArrayList<Course> list = new ArrayList<Course>(); // course list
	protected static ArrayList<Student> st = new ArrayList<Student>(); // student list
	protected String username,password,fn,ln;
	
	// check if user name and password match with each other and if they are existed in the database
	public boolean check(String username,String password) {
		if(username.equals("Admin")&&password.equals("Admin001")){
			return true;
		}
		else {
			for(Student x:st) {
				if(x.username.equals(username)&&x.password.equals(password)) {
					return true;
				}
			}
			return false;
		}
	}
	
	// load in the original database from cvs file
	public void load() throws FileNotFoundException {
		File obj = new File("src/MyUniversityCourses.csv");
		Scanner in = new Scanner(obj);
		in.nextLine();
		while(in.hasNext()) {
			String[] line = in.nextLine().split(",");
			Course x = new Course(line[0],line[1],Integer.parseInt(line[2]),Integer.parseInt(line[3]),new ArrayList<String>(),line[5],line[6],line[7]);
			list.add(x);
		}
		in.close();
	}
	// to input the course list
	public void setList(ArrayList<Course> list) {
		User.list=list;
	}
	// to input the student list
	public void setSt(ArrayList<Student> st) {
		User.st=st;
	}
	//serialize the database
	public void serialize() {
		try {
			FileOutputStream fos = new FileOutputStream("src/myCourseList");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(User.list);
			FileOutputStream fos1 = new FileOutputStream("src/myStudentList");
			ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
			oos1.writeObject(User.st);
			oos.close();
			oos1.close();
			fos.close();
			fos1.close();
		}
		catch(IOException e) {
		e.printStackTrace();}
	}
	
	//show all the courses in the list
	public void view() {
		for (Course x:list) {
			x.display();
		}
	}
	//ask the user for course id
	public String askId() {
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the course id:");
		String b= in.nextLine();
		return b;
	}
	//ask the user for course section number
	public String askSec() {
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the course section number:");
		String b= in.nextLine();
		return b;
	}
	
	//let user input id and section number and return the course if existed ot return null if not existed in the list
	public Course find() {
		String id = askId();
		String sec = askSec();
		boolean existed = false;
		Course x = new Course();
		for(Course a:list) {
			if(id.equals(a.getId())&&sec.equals(a.getSectionNum())) {
				existed=true;
				x = a;
			}
		}
		if(!existed) {
			System.out.println("The couse is not existed!");
			return null;
		}
		else {
			return x;
		}
	}
	
	//view student list in courses: polymorphism
	public void viewCourse() {
	}
	
	//find student with first name and last name
	public Student find(String fn,String ln) {
		for(Student x:st) {
			if(x.fn.equals(fn)&&x.ln.equals(ln)) {
				return x;
			}
		}
		return null;
	}
}
