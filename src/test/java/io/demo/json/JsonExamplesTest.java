package io.demo.json;

import org.json.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class JsonExamplesTest {

  @Test
  public void JsonTokenExampleTest() throws JSONException {
    String json = "{"
      + "  \"query\": \"Pizza\", "
      + "  \"locations\": [ 94043, 90210 ] "
      + "}";

    JSONObject jsonObject = JsonExamples.convertJsonStringToJson(json );
    String query = jsonObject.getString("query");
    JSONArray locations = jsonObject.getJSONArray("locations");
    System.out.println("Json Token Example: " + jsonObject);
    System.out.println("Query: " + query);
    System.out.println("Locations: " + locations);

    Assert.assertEquals("Pizza", query);
    JSONArray locationsResult = new JSONArray();
    locationsResult.put(94043);
    locationsResult.put(90210);
    Assert.assertArrayEquals(locationsResult.join(",").split(","), locations.join(",").split(",") );
  }

  @Test
  public void createJSONObjectTest() throws JSONException {
    Person person = new Person(15, "John Doe", new Date());
    JSONObject newPerson =  JsonExamples.createJSONObject(person);
    System.out.println("Person: " + newPerson);
    Assert.assertEquals(person.getFullName(), newPerson.get("Name"));
  }

  @Test
  public void createJSONObjectFromMapTest() throws JSONException {
    Map<String, String> map = new HashMap<>();
    map.put("Name", "John Joe");
    map.put("Age","15");
    map.put("Date of Birth", new Date().toString());

    JSONObject jo = JsonExamples.createJSONObjectFromMap(map);
    System.out.println(jo);
    Assert.assertEquals(map.get("Name"), jo.get("Name"));
  }

  @Test
  public void createJSONObjectFromJsonStringTest() throws JSONException {
    String so = "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}";

    JSONObject jo = JsonExamples.createJSONObjectFromJsonString(so);
    System.out.println(jo);
    Assert.assertEquals("jon doe", jo.get("name"));
  }

  @Test
  public void createJSONObjectFromJsonBeanTest() throws JSONException {
    DemoBean bean = new DemoBean();
    bean.setActive(true);
    bean.setId(1);
    bean.setName("Joe John");
    JSONObject jo = JsonExamples.createJSONObjectFromBean(bean);
    System.out.println(jo);
    Assert.assertEquals(bean.getName(), jo.get("name"));
  }

  @Test
  public void createJSONArrayTest() throws JSONException {
    JSONArray a = JsonExamples.createJSONArray();
    System.out.println(a);
    Assert.assertEquals(3, a.length());
  }

  @Test
  public void createJSONArrayFromStringTest() throws JSONException {
    String s = "[true, \"lorem ipsum\", 215]";
    JSONArray a = JsonExamples.createJSONArrayFromString(s);
    System.out.println(a);
    Assert.assertEquals(3, a.length());
  }

  @Test
  public void createJSONArrayFromCollectionTest() throws JSONException {

    List<String> list = new ArrayList<>();
    list.add("California");
    list.add("Arizona");
    list.add("Texas");
    list.add("Nevada");

    JSONArray a = JsonExamples.createJSONArrayFromCollection(list);
    System.out.println(a);
    Assert.assertEquals(4, a.length());
  }

  @Test
  public void convertJsonStringToJsonTest() throws JSONException {
    JSONObject s = JsonExamples.readStringWithJsonTokener("Clau");
    System.out.println(s);
    Assert.assertEquals(4, s.length() );
  }

  @Test
  public void createJSONArrayFromCDLTest() throws JSONException {
    JSONTokener t = new JSONTokener("England, USA, Canada");

    JSONArray a = JsonExamples.createJSONArrayFromCDL(t);
    System.out.println(a);
    Assert.assertEquals(3, a.length());
  }

  @Test
  public void createCDLFromJSONArrayTest() throws JSONException {
    JSONArray ja = new JSONArray("[\"England\",\"USA\",\"Canada\"]");

    String a = JsonExamples.createCDLFromJSONArray(ja);
    System.out.println(a);
    Assert.assertEquals(19, a.length());
  }

  @Test
  public void createJSONArrayOfJSONObjectsFromCDLTest() throws JSONException {
    String string = "name, city, age \n" +
      "john, chicago, 22 \n" +
      "gary, florida, 35 \n" +
      "sal, vegas, 18";

    JSONArray a = JsonExamples.createJSONArrayOfJSONObjectsFromCDL(string);
    System.out.println(a);
    Assert.assertEquals(3, a.length());
  }

  @Test
  public void createJSONArrayOfJSONObjectsFromCDL2Test() throws JSONException {

    JSONArray ja = new JSONArray();
    ja.put("name");
    ja.put("city");
    ja.put("age");

    String string = "john, chicago, 22 \n" +
      "gary, florida, 35 \n" +
      "sal, vegas, 18";

    JSONArray a = JsonExamples.createJSONArrayOfJSONObjectsFromCDL(ja, string);
    System.out.println(a);
    Assert.assertEquals(3, a.length());
  }

  @Test
  public void convertCookieStringToJSONObjectTest() throws JSONException {
    String cookie = "username=John Doe; expires=Thu, 18 Dec 2013 12:00:00 UTC; path=/";
    JSONObject o = JsonExamples.convertCookieStringToJSONObject(cookie);
    String ncookie = Cookie.toString(o);

    System.out.println(ncookie);
    Assert.assertEquals(62, ncookie.length());
  }

  @Test
  public void convertJSONObjectToHTTPHeaderStringTest() throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put("Method", "POST");
    jo.put("Request-URI", "http://www.example.com/");
    jo.put("HTTP-Version", "HTTP/1.1");
    String httpstr = JsonExamples.convertJSONObjectToHTTPHeaderString(jo);
    System.out.println("httpstr" + httpstr);

    String str = "POST \"http://www.example.com/\" HTTP/1.1";
    System.out.println("str: " + str);

    JSONObject obj = JsonExamples.convertHTTPHeaderStringToJSONObject(str);
    System.out.println("obj: " + obj);

  }
}
