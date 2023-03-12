package io.demo.json;

import org.json.*;

import java.util.List;
import java.util.Map;

/*
  https://developers.google.com/j2objc/javadoc/android/reference/org/json/JSONTokener

  https://www.baeldung.com/java-org-json

  Although we have a way to serialize a Java object to JSON string, there is no way to convert
  it back using this library.

  If we want that kind of flexibility, we can switch to other libraries such as Jackson.
*/
public class JsonExamples {

  /*
    A JSONTokener takes a source String as input to its constructor and extracts characters
    and tokens from it. It's used internally by classes of this package (like JSONObject, JSONArray)
    to parse JSON Strings.
    There may not be many situations where we'll directly use this class as the same
    functionality can be achieved using other simpler methods (like string.toCharArray()):
   */

  public static JSONObject readStringWithJsonTokener(String s) throws JSONException {
    JSONTokener jt = new JSONTokener(s);

    JSONObject o = new JSONObject();
    int ct = 1;
    while(jt.more()) {
      o.put(String.valueOf(ct), (Character) jt.next());
      ct++;
    }
    return o;
  }

  public static JSONObject convertJsonStringToJson(String json) throws JSONException {
      JSONObject jsonObject = (JSONObject) new JSONTokener(json).nextValue();
      return jsonObject;
  }


  public static JSONObject createJSONObject(Person person) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put("Age", String.valueOf(person.getAge()));
    jo.put("Name", person.getFullName());
    jo.put("DateOfBirth", person.getDateOfBirth());
    return jo;
  }

  public static JSONObject createJSONObjectFromMap(Map<String, String> map){
    return new JSONObject(map);
  }

  public static JSONObject createJSONObjectFromJsonString(String so) throws JSONException {
    return new JSONObject(so);
  }

  public static JSONObject createJSONObjectFromBean(DemoBean bean){
    return new JSONObject(bean);
  }

  public static JSONArray createJSONArray() throws JSONException {
    JSONObject o = new JSONObject();
    o.put("name", "Claudia");
    o.put("age", 15);
    o.put("Date of Birth", "2001-05-05");

    JSONArray a = new JSONArray();
    a.put(Boolean.TRUE);
    a.put("Tour of France");
    a.put(o);

    return a;
  }

  public static JSONArray createJSONArrayFromString(String s) throws JSONException {
   JSONArray a = new JSONArray(s);
   return a;
  }

  public static JSONArray createJSONArrayFromCollection(List list) throws JSONException {
    JSONArray a = new JSONArray(list);
    return a;
  }

  public static JSONArray createJSONArrayFromCDL(JSONTokener t) throws JSONException {
    JSONArray a = CDL.rowToJSONArray(t);

    return a;
  }

  public static String createCDLFromJSONArray(JSONArray a) throws JSONException {
    String s = CDL.rowToString(a);

    return s;
  }

  /*
  To produce a JSONArray of JSONObjects, we'll use a text String containing both
  headers and data separated by commas.
  The different lines are separated using a carriage return (\r) or line feed (\n).

  The first line is interpreted as a list of headers and all the subsequent lines are treated as data
  */
  public static JSONArray createJSONArrayOfJSONObjectsFromCDL(String cdl) throws JSONException {
    JSONArray s = CDL.toJSONArray(cdl);
    return s;
  }

  /*
  Notice that in this example, both data and header were supplied within the same String.
  There's an alternative way of doing this where we can achieve the same functionality by
  supplying a JSONArray that would be used to get the headers and a comma-delimited
  String working as the data.

  Different lines are separated using a carriage return (\r) or line feed (\n):
   */
  public static JSONArray createJSONArrayOfJSONObjectsFromCDL(JSONArray ja, String cdl) throws JSONException {
    JSONArray s = CDL.toJSONArray(ja, cdl);
    return s;
  }

  public static JSONObject convertCookieStringToJSONObject(String cookie) throws JSONException {
    return Cookie.toJSONObject(cookie);
  }

  /*
   Note that while converting an HTTP request header, the JSONObject must contain “Method”, “Request-URI”
  and “HTTP-Version” keys, whereas, for response header, the object must
  contain “HTTP-Version”, “Status-Code” and “Reason-Phrase” parameters.
   */
  public static String convertJSONObjectToHTTPHeaderString(JSONObject jo) throws JSONException {
    String httpstr = HTTP.toString(jo);
    return httpstr;
  }

  public static JSONObject convertHTTPHeaderStringToJSONObject(String httpstr) throws JSONException {
    JSONObject obj = HTTP.toJSONObject(httpstr);
    return obj;
  }
}
