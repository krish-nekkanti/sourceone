package com.ah8.app.ivservice.exception;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenericErrorMessage implements ErrorMessage {

  private List<String> errors;

  public GenericErrorMessage() {
  }

  public GenericErrorMessage(final List<String> errors) {
    this.errors = errors;
  }

  public GenericErrorMessage(final String error) {
    this(Collections.singletonList(error));
  }

  public GenericErrorMessage(final String... errors) {
    this(Arrays.asList(errors));
  }

  @Override
  public List<String> getErrors() {
    return errors;
  }

  @Override
  public void setErrors(final List<String> errors) {
    this.errors = errors;
  }
}
