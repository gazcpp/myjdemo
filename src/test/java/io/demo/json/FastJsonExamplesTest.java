package io.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class FastJsonExamplesTest {

    List<Person> people = new ArrayList<Person>();

    @Before
    public void setup(){
    Calendar calendar = Calendar.getInstance();
    calendar.set(2016, 6, 24);
    System.out.println("Calendar time " + calendar.getTime());
    people.add(new Person(15, "John Doe", calendar.getTime()));
    people.add(new Person(20, "Janette Doe", calendar.getTime()));
  }

  @Test
  public void convertListToJsonStringTest(){
    String peopleJSON =
      "[{\"AGE\":15,\"DATE OF BIRTH\":\"24/07/2016\",\"FULL NAME\":\"John Doe\"}," +
        "{\"AGE\":20,\"DATE OF BIRTH\":\"24/07/2016\",\"FULL NAME\":\"Janette Doe\"}]";
    String result = FastJsonExamples.convertListToJsonString(people);
    System.out.println(result);
    Assert.assertEquals(peopleJSON, result);
  }

  @Test
  public void createJsonArray(){
      String jsonOutput = FastJsonExamples.createJSONArray().toJSONString();
      System.out.println("Fast Json Array: " + jsonOutput);
  }

  @Test
  public void JsonStringToJavaObjectTest(){
    Person person = new Person(15, "John Doe", new Date());

    Person newPerson = FastJsonExamples.JsonStringToJavaObject(person);
    Assert.assertEquals(person.getAge(), newPerson.getAge());
    Assert.assertEquals(person.getFullName(), newPerson.getFullName());
  }

  @Test
  public void givenContextFilter_whenJavaObject_thanJsonCorrect() {
    ContextValueFilter valueFilter = new ContextValueFilter() {
      public Object process(BeanContext context, Object object, String name, Object value) {
        if (name.equals("DATE OF BIRTH")) {
          return "NOT TO DISCLOSE";
        }
        if (value.equals("John") || value.equals("Doe")) {
          return ((String) value).toUpperCase();
        } else {
          return null;
        }
      }
    };
    JSON.toJSONString(people, valueFilter);
  }

  @Test
  public void givenSerializeConfig_whenJavaObject_thanJsonCorrect() {
    NameFilter formatName = new NameFilter() {
      public String process(Object object, String name, Object value) {
        return name.toLowerCase()
          .replace(" ", "_");
      }
    };
    SerializeConfig.getGlobalInstance()
      .addFilter(Person.class, formatName);
    String jsonOutput = JSON.toJSONStringWithDateFormat(people, "yyyy-MM-dd");
    assertEquals(jsonOutput, "[{\"age\":15," + "\"date_of_birth\":\"2016-07-24\",\"full_name\":\"John Doe\"},{\"age\":20,\"date_of_birth\":\"2016-07-24\",\"full_name\":\"Janette Doe\"}]");
    // resetting custom serializer
    SerializeConfig.getGlobalInstance()
      .put(Person.class, null);
  }
}
