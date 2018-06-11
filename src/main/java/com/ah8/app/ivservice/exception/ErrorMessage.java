package com.ah8.app.ivservice.exception;

import java.util.List;

public interface ErrorMessage {

  List<String> getErrors();

  void setErrors(List<String> errors);
}
