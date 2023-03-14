package HW1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

// start the program with the Login class
public class Login {
	public static void main(String[] args) throws IOException {
		Scanner input =new Scanner(System.in);
		User one = new User();
		try {
			//try to read in the saved database if existed
			FileInputStream fis = new FileInputStream("src/myCourseList");
			ObjectInputStream ois = new ObjectInputStream(fis);
			one.setList((ArrayList<Course>)ois.readObject());
			FileInputStream fis1 = new FileInputStream("src/myStudentList");
			ObjectInputStream ois1 = new ObjectInputStream(fis1);
			one.setSt((ArrayList<Student>)ois1.readObject());
			ois.close();
			ois1.close();
			fis.close();
			fis1.close();
		}
		catch(FileNotFoundException e) {
		one.load();} // load in the original database
		catch(IOException er) {
			er.printStackTrace();
		}
		catch(ClassNotFoundException err) {
			System.out.println("Class Not Found!");
			err.printStackTrace();
		}
		boolean again = true;
			while(again) {
				System.out.print("You are Admin or Student?");
				String type = input.next();
				switch(type) {
					case "Admin":
						again = false;
						System.out.print("Please enter the username:");
						String username=input.next();
						System.out.print("Please enter the password:");
						String password=input.next();
						if(one.check(username, password)) {
							boolean re = true;
							Admin two = new Admin();
							while(re) {
								System.out.println("1.Course Management");
								System.out.println("2.Reports");
								System.out.println("3.Exit");
								System.out.print("Please enter the number:");
								int choice = Integer.parseInt(input.next());
								switch(choice) {
									case 1:
										boolean re2=true;
										while(re2) {
										System.out.println("1.Create a new course");
										System.out.println("2.Delete a course");
										System.out.println("3.Edit a course");
										System.out.println("4.Display information for a given course");
										System.out.println("5.Register a student");
										System.out.println("6.Exit");
										System.out.print("Please enter the number:");
										int choice2 = Integer.parseInt(input.next());
										switch(choice2) {
											case 1:
												two.create();
												break;
											case 2:
												two.delete();
												break;
											case 3:
												two.edit();
												break;
											case 4:
												two.display();
												break;
											case 5:
												two.register();
												break;
											case 6:
												re2=false;
												break;
										}}
										break;
									case 2:
										boolean re3 = true;
										while(re3) {
										System.out.println("1.View all courses");
										System.out.println("2.View all course that are Full");
										System.out.println("3.Write to a file the list of courses that are Full");
										System.out.println("4.View the names of the students being registered in a specific course");
										System.out.println("5.View the list of courses that a given student is being registered on");
										System.out.println("6.Sort");
										System.out.println("7.Exit");
										System.out.print("Please enter the number:");
										int choice3 = Integer.parseInt(input.next());
										switch(choice3) {
											case 1:
												two.view();
												break;
											case 2:
												two.viewF();
												break;
											case 3:
												two.outputFull();
												break;
											case 4:
												two.viewCourse();
												break;
											case 5:
												two.viewSt();
												break;
											case 6:
												two.sort();
												break;
											case 7:
												re3=false;
												break;
										}}
										break;
									case 3:
										re=false;
										break;
								}
							}
						}
						else System.out.println("Invalid Username or Password!");
						break; 
					case "Student":
						again = false;
						System.out.print("Please enter the username:");
						username=input.next();
						System.out.print("Please enter the password:");
						password=input.next();
						if(one.check(username, password)) {
							Student three=new Student();
							for(Student student:User.st) {
								if(username.equals(student.username)&&password.equals(student.password)) {
									three=student;
								}
							}
							boolean re2=true;
							while(re2) {
								System.out.println("Course Management");
								System.out.println("1.View all courses");
								System.out.println("2.View all courses that are not Full");
								System.out.println("3.Register on a course");
								System.out.println("4.Withdraw from a course");
								System.out.println("5.View all courses that the current student is being registered in");
								System.out.println("6.Exit");
								System.out.print("Please enter the number:");
								int choice4 = Integer.parseInt(input.next());
								switch(choice4) {
									case 1:
										three.view();
										break;
									case 2:
										three.viewF();
										break;
									case 3:
										three.register();
										break;
									case 4:
										three.withdraw();;
										break;
									case 5:
										three.viewCourse();;
										break;
									case 6:
										re2=false;
								}
							}
						}
						else System.out.println("Invalid Username or Password!");
						break;
					default:
						System.out.println("Please enter Admin or Student!");
						break;
				}
			}
			System.out.println("See you next time!");
			input.close();
			one.serialize();
	}
}
