package com.my.app.command.service;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.my.app.command.handler.ChannelHandler;
import com.my.app.command.vo.ConnectionInfoVo;
import com.my.app.command.vo.SessionVo;

@Service
public class CommandService {

	public SessionVo connect(HttpSession session, ConnectionInfoVo connectionInfoVo) {
		SessionVo sessionVo = getCliVo(connectionInfoVo);
		session.setAttribute("sessionVo", sessionVo);
		return sessionVo;
	}

	public SessionVo command(HttpSession session, String command) {
		SessionVo sessionVo = (SessionVo) session.getAttribute("sessionVo");

		if (sessionVo == null) {
			throw new RuntimeException("Session not found.");
		}

		try {
			command = command.replace("command=", "").replace("+", " ");
			System.out.println("command -> " + command);
			sessionVo.getOs().write(command.concat("\n").getBytes());
			sessionVo.getOs().flush();
		} catch (IOException e) {
			throw new RuntimeException("Session write fail.");
		}

		return sessionVo;
	}

	private SessionVo getCliVo(ConnectionInfoVo connectionInfoVo) {
		String host = connectionInfoVo.getHost();
		int port = connectionInfoVo.getPort();
		String username = connectionInfoVo.getUsername();
		String password = connectionInfoVo.getPassword();

		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(username, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(5000);

			ChannelShell channel = (ChannelShell) session.openChannel("shell");
			channel.setInputStream(null);
			ChannelHandler channelHandler = new ChannelHandler(channel);
			channelHandler.start();
			channel.setPtyType("dumb");
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
