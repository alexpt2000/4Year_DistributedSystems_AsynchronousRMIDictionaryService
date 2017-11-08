package gmit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.Naming;
import java.util.ArrayList;

public class Client {

	public static void main(String[] args) throws Exception {
		//Ask the registry running on localhost and listening in port 1099 for the instance of
		//the FileService object that is bound to the RMI registry with the name fileService.
		FileService fs = (FileService) Naming.lookup("rmi://127.0.0.1:1099/fileService");

		//Make a remote method invocation to ask for the list of files
		//The ArrayList of file names is transferred over the network in serialized form
		ArrayList<String> fileNames = fs.getFileNames();

		//print the list of file names on the server to the console
		System.out.print("Files currently on Server: ");
		for(String s : fileNames) {
			System.out.print(s + " ");
		}
		System.out.println();

		//make a remote invocation to the service to get the first file listed
		//and write that file out to disk in the clientFiles folder
		byte[] bytes1 = fs.getFile(fileNames.get(0));
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream("clientFiles/"+fileNames.get(0));
			stream.write(bytes1);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//first read the file to be uploaded as a byte[]
		//then make a remote invocation to the service to upload a file as a byte[]
		String uploadName = "uploadTest.txt";
		byte[] bytes2 = Files.readAllBytes(new File("clientFiles/" + uploadName).toPath());
		fs.uploadFile(uploadName,bytes2);

		
		//print the list of file names on the server to the console again
		fileNames = fs.getFileNames();
		System.out.print("Files currently on Server: ");
		for(String s : fileNames) {
			System.out.print(s + " ");
		}


	}

}
