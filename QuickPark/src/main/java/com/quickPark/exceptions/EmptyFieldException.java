package com.quickPark.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class EmptyFieldException extends RuntimeException {

	private String message;
}
