package io.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import java.util.Date;
import java.util.List;

/*
https://www.baeldung.com/fastjson
 */
public class FastJsonExamples {

  public static String convertListToJsonString(List<Person> people){
    String jsonOutput = JSON.toJSONString(people);
    System.out.println("convertListToJsonString: " + jsonOutput);
    return jsonOutput;
  }

  public static JSONArray createJSONArray(){
    JSONArray jsonArray = new JSONArray();
    for (int i=0; i<2; i++) {
      JSONObject object = new JSONObject();
      object.put("AGE", 10);
      object.put("NAME", "Joe "+i);
      object.put("DATE OF BIRTH",  "2016/12/12 12:12:12");
      jsonArray.add(object);
    }
    return jsonArray;
  }

  public static Person JsonStringToJavaObject(Person person){
    String jsonObject = JSON.toJSONString(person);
    Person newPerson = JSON.parseObject(jsonObject, Person.class);
    return newPerson;
  }
}
