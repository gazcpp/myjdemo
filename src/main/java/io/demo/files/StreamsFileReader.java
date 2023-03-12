package io.demo.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamsFileReader {

	public static void readFileWithStream() {
    	String path = System.getProperty("user.dir") + File.separator + "myBigFile.txt";

    	try (Stream<String> stream = Files.lines(Paths.get(path))){
    		stream.forEach(System.out::println);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	public static void readFileWithStreamIntoList() {
    	String path = System.getProperty("user.dir") + File.separator + "myBigFile.txt";

    	List<String> list = new ArrayList();
    	
    	try (Stream<String> stream = Files.lines(Paths.get(path))){
    		
//    		list = stream.filter(line -> stream.filter(predicate))
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	
	
	public static void main(String[] args) {
		StreamsFileReader.readFileWithStream();
		
		
		
	}
}
