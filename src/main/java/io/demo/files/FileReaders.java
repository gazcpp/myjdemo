package io.demo.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.function.Function;

public class FileReaders {

	public static void scannerReader(String fileName) throws IOException {	
	    System.out.println("Reading file using Scanner....");
	    
		FileInputStream inputStream = null;
		Scanner sc = null;
		
		try {
			inputStream = new FileInputStream(fileName);
			sc = new Scanner(inputStream, "UTF-8");
				
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				System.out.println(line);
			}
					
			if(sc.ioException() != null)
				throw sc.ioException();
			
		} finally {
			if (inputStream != null) inputStream.close();
			if (sc != null) sc.close();
		}
	}
	
	private static List<String> bufferReaderToList(String path, List<String> list) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                list.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
	
    public static List<String> bufferReaderToLinkedList(String path) {
        return bufferReaderToList(path, new LinkedList<>());
    }
	
    public static List<String> bufferReaderToArrayList(String path) {
        return bufferReaderToList(path, new ArrayList<>());
    }
    
    

    private static List<String> readAllLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
    
    private static List<String> randomAccessFile(String path, List<String> list) {
        try {
            RandomAccessFile file = new RandomAccessFile(path, "r");
            String str;
            while ((str = file.readLine()) != null) {
                list.add(str);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    private static List<String> randomAccessFileLinkedList(String path) {
        return randomAccessFile(path, new LinkedList<>());
    }

    private static List<String> randomAccessFileArrayList(String path) {
        return randomAccessFile(path, new ArrayList<>());
    }
    
    private static List<String> scanner(String path, List<String> list) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    private static List<String> scannerLinkedList(String path) {
        return scanner(path, new LinkedList<>());
    }

    private static List<String> scannerArrayList(String path) {
        return scanner(path, new ArrayList<>());
    }
    
    
    
    
    
    private static void measureTime(String name, Function<String, List<String>> fn, String path) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("run: " + name);
        long startTime = System.nanoTime();
        List<String> l = fn.apply(path);
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("lines: " + l.size());
        System.out.println("estimatedTime: " + estimatedTime / 1_000_000_000.);
    }
	
//	
//	public void apacheCommonsReader() throws IOException {	
//	    System.out.println("Reading file using Apache Commons....");
//
//	    ListIterator it = FileUtils.lineIterator(theFile, "UTF-8");
//	    try {
//	        while (it.hasNext()) {
//	            String line = it.nextLine();
//	            // do something with line
//	        }
//	    } finally {
//	        LineIterator.closeQuietly(it);
//	    }
//	}
	
    
    
    public static void main(String[] args) {
//        String path = "resources/testfile.txt";
    	String path = System.getProperty("user.dir") + File.separator + "myBigFile.txt";
        measureTime("BufferedReader.readLine() into ArrayList", FileReaders::bufferReaderToLinkedList, path);
        measureTime("BufferedReader.readLine() into LinkedList", FileReaders::bufferReaderToArrayList, path);
        measureTime("Files.readAllLines()", FileReaders::readAllLines, path);
        measureTime("Scanner.nextLine() into ArrayList", FileReaders::scannerArrayList, path);
        measureTime("Scanner.nextLine() into LinkedList", FileReaders::scannerLinkedList, path);
        measureTime("RandomAccessFile.readLine() into ArrayList", FileReaders::randomAccessFileArrayList, path);
        measureTime("RandomAccessFile.readLine() into LinkedList", FileReaders::randomAccessFileLinkedList, path);
        System.out.println("-----------------------------------------------------------");
	}
}
