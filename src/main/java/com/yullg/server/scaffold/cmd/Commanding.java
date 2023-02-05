package com.yullg.server.scaffold.cmd;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

public final class Commanding implements AutoCloseable {

	private final Process process;

	public Commanding(Process process) {
		this.process = process;
	}

	public boolean isAlive() {
		return process.isAlive();
	}

	public int exitValue() {
		return process.exitValue();
	}

	public int waitFor() throws InterruptedException {
		return process.waitFor();
	}

	public boolean waitFor(long timeout, TimeUnit unit) throws InterruptedException {
		return process.waitFor(timeout, unit);
	}

	public OutputStream getOutputStream() {
		return process.getOutputStream();
	}

	public InputStream getInputStream() {
		return process.getInputStream();
	}

	public InputStream getErrorStream() {
		return process.getErrorStream();
	}

	@Override
	public void close() throws Exception {
		process.destroy();
	}

}