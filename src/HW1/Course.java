package HW1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Course implements Serializable, Comparable<Course>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name,instructor,sec,loca,id;
	private int max,cur;
	private ArrayList<String> stu = new ArrayList<String>(); //student list
	
	Course(){}
	Course(String name,String id,int max,int cur,ArrayList<String> stu,String instructor,String sec,String loca){
		if(max>=0&&cur>=0) {
		this.name=name;
		this.id=id;
		this.max=max;
		this.cur=cur;
		this.stu=stu;
		this.instructor=instructor;
		this.sec=sec;
		this.loca=loca;
		}
		else System.out.println("Invalid Course Information!");
	}
	
	//in order to sort the courses
	public int compareTo(Course x) {
		if (cur < x.getCurrent())
			return 1;
		else if (cur > x.getCurrent())
			return -1;
		else return 0;
	}
	
	//show information about a specific course
	public void display() {
		System.out.printf("%42s",name);
		System.out.printf("%15s", id);
		System.out.printf("%3d", max);
		System.out.printf("%3d", cur);
		System.out.println();
	}
	
	//collect information about course from user
	public Course collect() {
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the course name:");
		String a= in.nextLine();
		System.out.print("Please enter the course id:");
		String b= in.nextLine();
		System.out.print("Please enter the maximum number of students registered in the course:");
		int c= in.nextInt();
		System.out.print("Please enter the current number of registered students:");
		int d= in.nextInt();
		String temp = in.nextLine();
		System.out.print("Please enter the list of names of students being registered in this course:");
		ArrayList<String> e= new ArrayList<String>();
		String Line = in.nextLine();
		if(!Line.equals("")) {
		String[] line = Line.split(",");
		for(String x:line) {
			e.add(x);
		}
		}
		else {e=null;}
		System.out.print("Please enter the course instructor:");
		String f = in.next();
		System.out.print("Please enter the course section number:");
		String g= in.next();
		System.out.print("Please enter the course location:");
		String h= in.next();
		Course x = new Course(a,b,c,d,e,f,g,h);
		return x;
	}
	
	
	public void setName(String name) {
		this.name=name;
	}
	public void setInstructor(String instructor) {
		this.instructor=instructor;
	}
	public void setSectionNum(String sec) {
		this.sec=sec;
	}
	public void setLocation(String loca) {
		this.loca=loca;
	}
	public void setId(String id) {
		this.id=id;
	}
	public void setMax(int max) {
		this.max=max;
	}
	public void setCurrent(int cur) {
		this.cur=cur;
	}
	public void setList(ArrayList<String> stu) {
		this.stu=stu;
	}
	
	public String getName() {
		return name;
	}
	public String getInstructor() {
		return instructor;
	}
	public String getSectionNum() {
		return sec;
	}
	public String getLocation() {
		return loca;
	}
	public String getId() {
		return id;
	}
	public int getMax() {
		return max;
	}
	public int getCurrent() {
		return cur;
	}
	public ArrayList<String> getList(){
		return stu;
	}
}