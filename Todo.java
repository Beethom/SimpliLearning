package project;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

class fileOperation{
	void createFile() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter directory : ");
		String f1 = sc.next();
		System.out.println("Please enter file to be added: ");
		String f = sc.next();
		File files = new File(f1,f);
		files.createNewFile();
		System.out.println("File created successfully");
	}
	void deleteFile() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter directory : ");
		String f1 = sc.next();
		System.out.println("Please enter file to be deleted: ");
		String f = sc.next();
		
		File files = new File(f1,f);
		if (files.exists()) {
			files.delete();
			System.out.println("File was deleted");
		}
		else {
			System.out.println("File deletion unsuccessfull");
		}
	
		
	}
	void searchFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter directory : ");
		String f1 = sc.next();
		System.out.println("Please enter file to be searched: ");
		String f = sc.next();
		File files = new File(f1);
		String [] dir = files.list();
		int flag = 0;
        if (dir == null) {
            System.out.println("Empty directory.");
        }
        else {
  
            // Linear search in the array
            for (int i = 0; i < dir.length; i++) {
                String filename = dir[i];
                if (filename.equalsIgnoreCase(f)) {
                    System.out.println(filename + " is found");
                    flag = 1;
                }
            }
        }
        if (flag == 0) {
            System.out.println("File Not Found");
        }
		
	}
	
	
	}
	


public class Todo {
	public static void main(String[] args) throws IOException {
		
		fileOperation f = new fileOperation();
		Scanner sc = new Scanner(System.in);
		int choice;
		while(true) {
			System.out.println();
		System.out.println("Please choose a menu option:");
        System.out.println("1. Add File");
        System.out.println("2. Delete File");
        System.out.println("3. Search File");
        System.out.println("4. Exit");
        choice = sc.nextInt();
        switch (choice) {
        case 1:
            System.out.println("You chose Option 1");
            f.createFile();
            break;
        case 2:
            System.out.println("You chose Option 2");
            f.deleteFile();
            break;
        case 3:
            System.out.println("You chose Option 3");
            f.searchFile();
            break;
        case 4:
            System.out.println("Exiting.");
            System.exit(0);
            break;
        default:
            System.out.println("Invalid choice. Please try again.");
            break;
	}

}
}
}