package io.demo.enums;

import org.junit.Test;

public class LevelTest {

  @Test
  public void testLevel(){
    Level level = Level.HIGH;

    System.out.println("Level: " + level.getLevelCode());
  }
}
