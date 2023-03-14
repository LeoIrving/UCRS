package HW1;

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable, studentable{
	private ArrayList<Course> stList = new ArrayList<Course>(); // the array list records the courses that the student has registered in
	Student(String username,String password,String fn,String ln){
		this.username=username;
		this.password=password;
		this.fn=fn;
		this.ln=ln;
	}
	Student(){
	}
	//register in a course that is not full and ensure you cannot enroll in same course twice
	public void register() {
		Course t =find();
		if(t!=null) {
			if(t.getCurrent()==t.getMax()) {
				System.out.println("The course is full!");
			}
			else {
				if(t.getList().contains(fn+ln)) {
					System.out.println("you have already enrolled in this course!");
				}
				else {
			stList.add(t);
			ArrayList<String> newList = t.getList();
			newList.add(fn+ln);
			t.setList(newList);
			t.setCurrent(t.getCurrent()+1);
		}}}
		else {
			System.out.println("Cannot find this course!");
		}
	}
	//show all the courses the student has registered in
	public void viewCourse() {
		for(Course x:stList) {
			System.out.printf("%s   %s",x.getId(),x.getSectionNum());
			System.out.println();
		}
	}
	//view courses that are not full
	public void viewF() {
		for(Course x:list) {
			if(x.getCurrent()!=x.getMax()) {
				System.out.printf("%15s", x.getId());
				System.out.printf("%3s", x.getSectionNum());
				System.out.println();
			}
		}
		
	}
	// withdraw from a course
	public void withdraw() {
		Course t =find();
		if(t!=null) {
			stList.remove(t);
			ArrayList<String> newList = t.getList();
			newList.remove(fn+ln);
			t.setList(newList);
		}
	}
	//get the student's course list from other class
	public ArrayList<Course> getStList() {
		return stList;
	}
	

}
