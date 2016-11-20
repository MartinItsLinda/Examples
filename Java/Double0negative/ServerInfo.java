package org.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class ServerInfo {
	
	/* Example By Double0negative @ mc-sg.org */
	
	public static final String HOST = "http://mcapi.ca/query/{0}:{1}/info";
	public static final Gson gson = new Gson();
	
	public static MinecraftPing ping(String host) throws Exception{
		return ping(host, 25565);
	}
	
	
	public static MinecraftPing ping(String host, int port) throws Exception{
		String json = request(replaceVars(HOST, host, port));
		return gson.fromJson(json, MinecraftPing.class);
	}
	
	
	public static class MinecraftPing{
		public boolean status;
		public String error; //only exist if status = false;
		public String motd;
		public String version;
		public Players players;
		public long ping;
		
		public class Players{
			public int online, max;
		}
		
	}
	
	
	private static String request(String urls) throws Exception{
		StringBuilder sb = new StringBuilder();

			URL url = new URL(urls.replace(" ", "%20"));
			URLConnection con = url.openConnection();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			char[] buff = new char[512];

			while (true) {
				int len = br.read(buff, 0, buff.length);
				if (len == -1) {
					break;
				}
				sb.append(buff, 0, len);
			}
			br.close();
	
		return sb.toString();

	}
	
	private static String replaceVars(String str, Object ... args){
		int a = 0;
		for(Object ob: args){
			str = str.replace("{"+a+"}", ob.toString());
			a++;
		}
		return str;

	}

}