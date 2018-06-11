package com.ah8.app.ivservice.exception;

public class MapperException extends Exception {

  private static final long serialVersionUID = 1L;

  private String exceptionMsg;

  public MapperException(String exceptionMsg) {
    super(exceptionMsg);
    this.exceptionMsg = exceptionMsg;
  }

  @Override
  public String toString() {
    return "MapperException [exceptionMsg=" + exceptionMsg + "]";
  }
}
