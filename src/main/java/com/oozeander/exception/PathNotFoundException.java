package com.oozeander.exception;

import java.nio.file.Paths;

public class PathNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PathNotFoundException(String path) {
		super("Path : " + Paths.get(path) + " does not exist");
	}
}