package com.yullg.server.scaffold.cmd;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public final class CommandBuilder {

	private File dir;
	private String[] envp;
	private final Map<String, String> envs = new LinkedHashMap<>();

	public Command build(String command) {
		StringTokenizer st = new StringTokenizer(command);
		String[] cmdarray = new String[st.countTokens()];
		for (int i = 0; st.hasMoreTokens(); i++) {
			cmdarray[i] = st.nextToken();
		}
		return new Command(dir, envp, cmdarray);
	}

	public Command build(String[] cmdarray) {
		return new Command(dir, envp, cmdarray);
	}

	public File getDir() {
		return dir;
	}

	public CommandBuilder setDir(File dir) {
		this.dir = dir;
		return this;
	}

	public Map<String, String> getEnvs() {
		return Collections.unmodifiableMap(envs);
	}

	public CommandBuilder putEnvs(Map<String, String> envs) {
		this.envs.putAll(envs);
		updateEnvp();
		return this;
	}

	public CommandBuilder putEnv(String name, String value) {
		this.envs.put(name, value);
		updateEnvp();
		return this;
	}

	public CommandBuilder removeEnv(String name) {
		this.envs.remove(name);
		updateEnvp();
		return this;
	}

	public CommandBuilder cleanEnv() {
		this.envs.clear();
		updateEnvp();
		return this;
	}

	private void updateEnvp() {
		if (envs.isEmpty()) {
			this.envp = null;
		} else {
			String[] result = new String[envs.size()];
			int index = 0;
			for (Entry<String, String> entry : envs.entrySet()) {
				result[index++] = entry.getKey() + "=" + entry.getValue();
			}
			this.envp = result;
		}
	}

}