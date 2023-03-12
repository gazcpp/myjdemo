package io.demo.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class VariousQuestions {

	public static void convertMapToList() {
		Map<String, String> wordMap = new HashMap<String, String>();
		wordMap.put("Dine","Today");
		wordMap.put("Movie","Tomorrow");
		wordMap.put("Call","Yesterday");
		wordMap.put("Party","Next Week");

		Set<Entry<String, String>> set = wordMap.entrySet();
		List<Entry<String, String>> list = new ArrayList<Entry<String, String>>(set);
		
		System.out.println(list);
	}
	
	
	public static void main(String[] args) {
		convertMapToList();
	}
}
