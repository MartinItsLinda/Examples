package me.joezwet.mcapi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Request {

	/**
	 * @author JoeZwet
	 * @param requestType
	 * @param ip
	 */

	public Request(RequestType requestType, String ip) {
		String type = null;
		if (requestType == RequestType.STATUS) {
			type = "status";
		} else if (requestType == RequestType.MOTD) {
			type = "motd";
		} else if (requestType == RequestType.PLAYERS) {
			type = "players";
		} else if (requestType == RequestType.MCSTATUS){
			type = "mcstatus";
		} else {
			Error error = new Error("Type \"" + requestType + "\" not found.");
			System.err.println(error);
			return;
		}

		try {
			URI uri = new URI("https://mcapi.ca/query/" + ip + "/" + type);
			JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
			JSONObject obj = new JSONObject(tokener);
			boolean status = obj.getBoolean("status");
			if (requestType == RequestType.STATUS) {
				if (status) {
					System.out.printf("Status: %s\n", obj.getBoolean("status"));
					System.out.printf("Hostname: %s\n", obj.getString("hostname"));
					System.out.printf("Port: %s\n", obj.getInt("port"));
					System.out.printf("Ping: %s\n", obj.getInt("ping"));
				} else {
					System.out.printf("Status: %s\n", obj.getBoolean("status"));
					System.out.printf("Hostname: %s\n", obj.getString("hostname"));
					System.out.printf("Port: %s\n", obj.getInt("port"));
					System.out.printf("Error: %s\n", obj.getString("error"));
				}
			} else if (requestType == RequestType.MOTD) {
				if (status) {
					System.out.printf("Status: %s\n", obj.getBoolean("status"));
					System.out.printf("Hostname: %s\n", obj.getString("hostname"));
					System.out.printf("Port: %s\n", obj.getInt("port"));
					System.out.printf("Ping: %s\n", obj.getInt("ping"));
					System.out.printf("MOTD: %s\n", obj.getString("motd"));
					
				} else {
					System.out.printf("Status: %s\n", obj.getBoolean("status"));
					System.out.printf("Hostname: %s\n", obj.getString("hostname"));
					System.out.printf("Port: %s\n", obj.getInt("port"));
					System.out.printf("Error: %s\n", obj.getString("error"));
				}
			} else if (requestType == RequestType.PLAYERS) {
				if (status) {
					JSONObject p = obj.getJSONObject("players");
					System.out.printf("Status: %s\n", obj.getBoolean("status"));
					System.out.printf("Hostname: %s\n", obj.getString("hostname"));
					System.out.printf("Port: %s\n", obj.getInt("port"));
					System.out.printf("Ping: %s\n", obj.getInt("ping"));
					System.out.printf("Players: %s/%s (online/max)\n",p.getInt("online"), p.getInt("max"));
					
				} else {
					System.out.printf("Status: %s\n", obj.getBoolean("status"));
					System.out.printf("Hostname: %s\n", obj.getString("hostname"));
					System.out.printf("Port: %s\n", obj.getInt("port"));
					System.out.printf("Error: %s\n", obj.getString("error"));
				}
			} else if (requestType == RequestType.MCSTATUS) {
				String minecraft = obj.getJSONObject("minecraft.net").getString("status");
				String session = obj.getJSONObject("session.minecraft.net").getString("status");
				String account = obj.getJSONObject("account.mojang.com").getString("status");
				String auth = obj.getJSONObject("auth.mojang.com").getString("status");
				String skins = obj.getJSONObject("skins.minecraft.net").getString("status");
				String authserver = obj.getJSONObject("authserver.mojang.com").getString("status");
				String sessionserver = obj.getJSONObject("sessionserver.mojang.com").getString("status");
				String api = obj.getJSONObject("api.mojang.com").getString("status");
				String textures = obj.getJSONObject("textures.minecraft.net").getString("status");
				String mojang = obj.getJSONObject("mojang.com").getString("status");
				
				System.out.printf("Minecraft.net: %s\n", minecraft);
				System.out.printf("Session.Minecraft.net: %s\n", session);
				System.out.printf("Account.Mojang.com: %s\n", account);
				System.out.printf("Auth.Mojang.com: %s\n", auth);
				System.out.printf("Skins.Minecraft.net: %s\n", skins);
				System.out.printf("AuthServer.Mojang.com: %s\n", authserver);
				System.out.printf("SessionServer.Mojang.com: %s\n", sessionserver);
				System.out.printf("API.Mojang.com: %s\n", api);
				System.out.printf("Textures.Minecraft.net: %s\n", textures);
				System.out.printf("Mojang.com: %s\n", mojang);
			} else {
				System.err.println("Request type: \"" + type + "\" not found.");
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
