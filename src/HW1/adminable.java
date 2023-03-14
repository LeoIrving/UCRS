package HW1;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface adminable {
	public void create();
	public void delete();
	public void edit();
	public void display();
	public void outputFull() throws IOException,FileNotFoundException;
	public void viewSt();
	public void sort();
}
