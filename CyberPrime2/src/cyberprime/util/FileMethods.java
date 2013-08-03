package cyberprime.util;

import java.io.File;


public class FileMethods {

	private static File folder= new File(Constants.SAMUEL_PATH);
	
	public static void fileDelete(String fileName){
		File[] listOfFiles = folder.listFiles();
		for(int i=0; i < listOfFiles.length; i++){
			if(listOfFiles[i].isFile()){
				String originalFileName = listOfFiles[i].getName();
				System.out.println(originalFileName);
				System.out.println(fileName);
				if(originalFileName.equals(fileName)){
					if(listOfFiles[i].delete()){
						System.out.println("File deleted");
					}
				}	
				else{
					System.out.println("Failed");
					listOfFiles[i].deleteOnExit();
				}
			}
		}	
	}
}
