package com.googlecode.common.remote.pool.resource;

import java.util.EmptyStackException;
import java.util.Stack;

import org.apache.commons.pool.PoolableObjectFactory;

public class DefaultResourceFactory implements PoolableObjectFactory<Object> {

	//public static List<Object> backupList = new ArrayList<Object>();
 	private static Stack<Object> stack = new Stack<Object>();

//	private static final String CONFIG_FILE = "resource.txt";


	static{
/**
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(DefaultResourceFactory.class
					.getResourceAsStream(CONFIG_FILE)));

			String json;
			while ((json = bufferedReader.readLine()) != null) {
 				ObjectMapper objectMapper = new ObjectMapper();
				Object object = objectMapper.readValue(json,
						Object.class);

				stack.push(object);
				backupList.add(object);
   			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
*/
	}

	public DefaultResourceFactory(){
              //add only for demo.
  	}

	@Override
	public Object makeObject() throws Exception {
		try {
			return stack.pop();
		} catch (EmptyStackException e) {
		    return null;
 		}
	}

	public static Stack<Object> getStack() {
		return stack;
	}

	@Override
	public void destroyObject(Object object) throws Exception {
		stack.push(object);
	}

	@Override
	public boolean validateObject(Object object) {
		return object!=null;
	}

	@Override
	public void activateObject(Object object) throws Exception {

	}

	@Override
	public void passivateObject(Object object) throws Exception {

	}

}
