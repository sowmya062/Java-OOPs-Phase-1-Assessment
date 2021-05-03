package fileOperationsAssesment;
import java.io.File;
import java.io.IOException;
import java.util.*;

class FileOperations{
	 /**
    Scanner object to take input from user
    */
   public static Scanner sc = new Scanner(System.in);
   /**  To store the path variable.*/
   public static String path;
   /**   Instance of File Object*/
   public static File f;
   public static void fileOperations() throws IOException {
		char ch;
		do{
			System.out.println("a. Display files in ascending order.");
            System.out.println("b. Perform operation on files.");
            System.out.println("c. Change Folder/Directory");
            System.out.println("d. Exit The Program.");
            System.out.print("Please Select any option from above choices : ");
			ch = sc.next().charAt(0);
			switch (ch) {
			case 'a':
				displayFiles();
				break;
			case 'b':
				System.out.println("File operations:");
				System.out.println("a. Create file");
	            System.out.println("b. delete file");
	            System.out.println("c. search file");
	            System.out.print("Please Select any option from above choices : ");
	            Scanner scopp = new Scanner(System.in);
	            char chop= scopp.next().charAt(0);
	            switch(chop) {
	            case 'a': 
	            	createFile();
	            	break;
	            case 'b':
	            	deleteFile();
	            	break;
	            case 'c':
	            	searchFile();
	            	break;  
	            }
				
				break;
			case 'c':
				changeFolder();
				break;
			case 'd':
				exit();
				break;
				
			}
		}while(ch!='e');
		
			
	}
	public static void displayFiles() throws IOException {
	       String[] list = f.list();
	       if (!f.isDirectory()) System.out.println("It is not a folder");
	       else if (list.length==0||list==null) System.out.println(" \t!!!! "+f.getAbsolutePath()+" Directory is empty");
	       else {
	           Arrays.sort(list);
	           for (String file : list)
		        {
		            System.out.println(file);
		        }
	       }
	   }
	 
	public static String stringreader() {
	        String str = "";
	        try {
	            str = sc.nextLine();
	        } catch (Exception var3) {
	            System.out.println("Invalid Input!");
	        }
	        return str;
	    }
	public static void createFile(){
		System.out.print("enter the file name to create:");
		Scanner filescan = new Scanner(System.in);
		String fileName = filescan.nextLine();
        File newFile = new File(path+"/" + fileName);
        if (newFile.exists()) System.out.println("\t\t"+newFile + " --> Already Exists at "+newFile.getAbsolutePath());
        else{
            try {
            if (!newFile.exists()) {
            	newFile.createNewFile();
                System.out.println("file got created sucessfully");
               }
            } catch (Exception e) {
                System.out.println("\t!!! Unable to create file due to some exceptions\n");
            }
        }        
	}
	public static void deleteFile() {
		  
		System.out.print("Specify The File Name to delete with its .dot extension else program will cause problem --> ");
		Scanner filescan = new Scanner(System.in);
		String fileName = filescan.nextLine();
        File df = new File(path + "/" + fileName);
        if(df.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
		
	}
	public static void printFile(List<File> file) throws IOException {
        int j=0;
        System.out.printf("| %-5s  | %-100s  |  %-10s | %-10s \n","No.","File Name","Type","Path");
        for (File i:file) {
            System.out.printf("| %-5s  | %-100s  |  %-20s | %-10s",++j,i.getName(),typeOfFile(i), i.getAbsolutePath());
        }
        System.out.println(" ");
    }
	public static void searchFile() throws IOException {
		int j = 0;
        System.out.print("Enter File Name to Search : ");
        //sc.nextLine();
        Scanner filescan = new Scanner(System.in);
		String fileName = filescan.nextLine();
        File[] list = f.listFiles();
        if (list.length == 0 && f.isDirectory()) {
            System.out.println("Directory is Empty");
        } else if (!f.isDirectory()) {
            System.out.println(f.getName() + " not a Directory");
        } else if (f.isDirectory() && list.length > 0) {
            List<File> foundList = new ArrayList<>();
            boolean found = false;
            //int i = list.length;
            File searchedFile = new File(path + "/" + fileName);
            for (File i : list) {
                if (i.getName().matches(fileName + "[.][0-9|a-z|A-Z]*") || i.getName().equals(fileName)) {
                    foundList.add(i);
                    ++j;
                    found = true;
                }
            }
            if (found && j > 0) {
                System.out.println("The " + foundList.size() + " files have been founded of name " + fileName);
                printFile(foundList);
            } else if (!found || j == 0) { System.out.println("File Not Found"); }
        }
	}
	public static void changeFolder() throws IOException,InputMismatchException {
        
        System.out.print("Please Give Path of Folder to perform operations :  ");
        path = stringreader();
        f = new File(path);
        while (!f.isDirectory()) {
            System.out.print("\t!!! Please Give Path of valid Folder/Directory : ");
            path = stringreader();
            f = new File(path);
        }
        
    }
	public static String typeOfFile(File i){
	        if (i.isDirectory()){
	            return "Folder";
	        }
	        else if (i.isFile()){
	            String []name = i.getName().split("\\.");
	            return name[name.length-1];
	        }return "none";
	    }

	 public static void exit() {
	        System.out.printf("\n\n%-50sThank you for using our application\tQuitting..."," ");
	        System.exit(0);
	    }
	public static void main(String args[]) throws IOException,InputMismatchException {
		 changeFolder();
		fileOperations();
	}
}
