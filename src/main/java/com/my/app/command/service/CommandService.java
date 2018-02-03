package com.my.app.command.service;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.my.app.command.handler.ChannelHandler;
import com.my.app.command.vo.SessionVo;

@Service
public class CommandService {

	public SessionVo command(HttpSession session, String command) {
		SessionVo sessionVo = (SessionVo) session.getAttribute("sessionVo");

		if (sessionVo == null) {
			sessionVo = getCliVo();
			session.setAttribute("sessionVo", sessionVo);
		}

		try {
			command = command.replace("command=", "").replace("+", " ");
			System.out.println("command -> " + command);
			sessionVo.getOs().write(command.concat("\n").getBytes());
			sessionVo.getOs().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sessionVo;
	}

	public SessionVo getCliVo() {
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession("stack", "192.168.1.30", 22);
			session.setPassword("admin123");
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(5000);

			Channel channel = session.openChannel("shell");
			channel.setInputStream(null);
			ChannelHandler channelHandler = new ChannelHandler(channel);
			channelHandler.start();
			channel.connect(5000);

			SessionVo sessionVo = new SessionVo();
			sessionVo.setSession(session);
			sessionVo.setChannel(channel);
			sessionVo.setOs(channel.getOutputStream());
			sessionVo.setCliHandler(channelHandler);
			return sessionVo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
