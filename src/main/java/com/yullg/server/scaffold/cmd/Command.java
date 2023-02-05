package com.yullg.server.scaffold.cmd;

import java.io.File;
import java.io.IOException;

public final class Command {

	private final File dir;
	private final String[] envp;
	private final String[] cmdarray;

	public Command(File dir, String[] envp, String[] cmdarray) {
		this.dir = dir;
		this.envp = envp;
		this.cmdarray = cmdarray;
	}

	public Commanding exec() throws SecurityException, IOException, NullPointerException, IndexOutOfBoundsException {
		Process process = Runtime.getRuntime().exec(cmdarray, envp, dir);
		return new Commanding(process);
	}

}