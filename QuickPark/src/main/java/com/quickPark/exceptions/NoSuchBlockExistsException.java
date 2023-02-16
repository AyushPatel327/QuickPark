package com.quickPark.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class NoSuchBlockExistsException extends RuntimeException {

	private String messageString;
}
