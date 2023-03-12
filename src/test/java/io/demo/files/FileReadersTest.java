package io.demo.files;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FileReadersTest {

	FileReaders fileReaders;
	
	@Before
	public void setUp() throws Exception {
		fileReaders = new FileReaders();
	}

	@Test
	public void ScannerReaderTest() {
//		String currentDir = new File( "." ).getCanonicalPath();	     
//		String path = "resources/testfile.txt";
		String fileName = System.getProperty("user.dir") + File.separator + "myBigFile.txt";
		try {
			FileReaders.scannerReader(fileName);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void bufferReaderToLinkedListTest() {
		String fileName = System.getProperty("user.dir") + File.separator + "myBigFile.txt";

		List<String> list = FileReaders.bufferReaderToLinkedList(fileName);
		
		System.out.println("List: " + list.toString());

	}
}
