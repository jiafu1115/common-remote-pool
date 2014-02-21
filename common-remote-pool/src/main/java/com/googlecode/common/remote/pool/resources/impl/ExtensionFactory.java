package com.googlecode.common.remote.pool.resources.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;

import org.apache.commons.pool.PoolableObjectFactory;

public class ExtensionFactory implements PoolableObjectFactory<Extension> {

	public static final String CONFIG_FILE = "./conf" + File.separator + "extension.txt";

	private static Stack<Extension> stack = new Stack<Extension>();

	static {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ExtensionFactory.class
				.getClassLoader().getResourceAsStream(CONFIG_FILE)));
		try {
			String extensionLine;
			while ((extensionLine = bufferedReader.readLine()) != null) {
				String[] split = extensionLine.split(",");
				stack.push(new Extension(split[0], split[1], split[2], split[3]));

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	@Override
	public Extension makeObject() throws Exception {
		try {
			return stack.pop();
		} catch (EmptyStackException e) {
			throw new RuntimeException("no phone can be used: " + e.getMessage());
		}
	}

	@Override
	public void destroyObject(Extension phone) throws Exception {
		stack.push(phone);
	}

	@Override
	public boolean validateObject(Extension paramT) {
		return false;
	}

	@Override
	public void activateObject(Extension paramT) throws Exception {

	}

	@Override
	public void passivateObject(Extension paramT) throws Exception {

	}


}
