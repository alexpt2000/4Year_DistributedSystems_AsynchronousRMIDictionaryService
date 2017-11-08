package gmit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class FileServiceImpl extends UnicastRemoteObject implements FileService {
	private static final long serialVersionUID = 1L;

	public FileServiceImpl() throws RemoteException{
		super();
	}

	@Override
	public byte[] getFile(String fileName) throws RemoteException {
		byte[] bytes = null;
		ArrayList<String> fileNames = getFileNames();
		if(fileNames.contains(fileName)) {			
			try {
				bytes = Files.readAllBytes(new File("serverFiles/"+fileName).toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;

	}

	@Override
	public ArrayList<String> getFileNames() throws RemoteException {
		ArrayList<String> fileNames = new ArrayList<String>();
		File folder = new File("serverFiles");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				fileNames.add(listOfFiles[i].getName());
			} 
		}
		return fileNames;
	}

	@Override
	public void uploadFile(String fileName, byte[] bytes) throws RemoteException {
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream("serverFiles/"+fileName);
			stream.write(bytes);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

}
