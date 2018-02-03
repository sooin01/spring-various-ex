package com.my.app.command.vo;

import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;
import com.my.app.command.handler.ChannelHandler;

public class SessionVo {

	private Session session;
	private Channel channel;
	private ChannelHandler channelHandler;
	private OutputStream os;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public ChannelHandler getCliHandler() {
		return channelHandler;
	}

	public void setCliHandler(ChannelHandler channelHandler) {
		this.channelHandler = channelHandler;
	}

	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	public String getResponse() {
		return channelHandler.getResponse();
	}

}
