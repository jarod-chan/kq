package cn.fyg.easser.service;

public class NetException extends Exception {

	private static final long serialVersionUID = 1L;

	public NetException(String message, Throwable cause) {
		super(message, cause);
	}

	public NetException(String message) {
		super(message);
	}

}
