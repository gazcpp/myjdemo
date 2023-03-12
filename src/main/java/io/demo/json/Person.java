package io.demo.json;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Person {

//  @JSONField(name="AGE", serialize=false)
  @JSONField(name="AGE", ordinal = 1)
  private int age;

  @JSONField(name="FULL NAME", ordinal = 3)
  private String fullName;

  @JSONField(name="DATE OF BIRTH", format = "dd/MM/yyyy", ordinal = 2)
  private Date dateOfBirth;

  public Person() {
  }

  public Person(int age, String fullName, Date dateOfBirth) {
    this.age = age;
    this.fullName = fullName;
    this.dateOfBirth = dateOfBirth;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
}
