package io.demo.files;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {

	/**
	 * 	https://www.careercup.com/page?pid=shutterfly-interview-questions
	Read large file
	Sol: https://www.baeldung.com/java-read-lines-large-file
	Streaming Through the File
	Let's now look at a solution � we're going to use a java.util.Scanner to run 
	through the contents of the file and retrieve lines serially, one by one:
	This solution will iterate through all the lines in the file � allowing 
	for processing of each line � without keeping references to them � and in conclusion, 
	without keeping them in memory: (~150 Mb consumed)	
	 * @throws IOException 
		**/
	public static void readLargeFile(String fileName) throws IOException {
		
		FileInputStream fileInputStream=null;
		Scanner scanner=null;
		
		try {
			fileInputStream = new FileInputStream(fileName);
			scanner = new Scanner(fileInputStream);
			
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				System.out.println(line);		
			}		
			
			if (scanner.ioException() != null)
				throw scanner.ioException();
			
		} finally {
			if(fileInputStream != null)
				fileInputStream.close();
			if(scanner != null)
				scanner.close();
		}
		
	}
	
	/*
	 * 	https://www.careercup.com/page?pid=shutterfly-interview-questions
	 */

	
//	Hadoop/Spark
	public void hadoop() {
		
	}
	
	
	public static void writeDataOutputStream(String fileName) throws IOException {
		
		int value=0;
		FileOutputStream file = new FileOutputStream(fileName);
		DataOutputStream outStream = new DataOutputStream(file);
		for (int i=0; i<1000; i++) {
			value = i;
			outStream.write(value);
		}
		outStream.close();
	}
	
	public static void writeBufferedWritter(String fileName) throws IOException {
		int value=0;
		
		FileWriter file = new FileWriter(fileName);
		BufferedWriter writer = new BufferedWriter(file);
		for (int i=0; i<10000; i++) {
			value = i;
			writer.write(String.valueOf(value)+",");
		}
		
		writer.close();
	}
	
	public static void readFilledFile(String fileName) throws IOException {
		FileInputStream file = new FileInputStream(fileName);
		DataInputStream inStream = new DataInputStream(file);
		String result = inStream.readUTF();
		System.out.println(result);
		inStream.close();
	}
	
	
	public static void main(String[] args)  {
		
    	String path = System.getProperty("user.dir") + File.separator + "myBigFile1.txt";

    	try {
    	
    		writeBufferedWritter(path);
//    		writeDataOutputStream(path);
    	
//		ReadFile.readLargeFile(path);
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
		
    	
    }

	
}
