package io.demo.enums;

public enum Level {
  HIGH (3),
  MEDIUM (2),
  LOW (1);

  private int levelCode;

  Level (int levelCode){
    this.levelCode = levelCode;
  }

  public int getLevelCode() {
    return this.levelCode;
  }
}
