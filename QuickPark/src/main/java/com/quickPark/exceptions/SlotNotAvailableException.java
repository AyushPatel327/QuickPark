package com.quickPark.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class SlotNotAvailableException extends RuntimeException {

	private String message;
}
