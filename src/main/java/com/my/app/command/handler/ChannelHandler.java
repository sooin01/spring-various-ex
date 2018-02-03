package com.my.app.command.handler;

import java.io.InputStream;

import com.jcraft.jsch.Channel;

public class ChannelHandler extends Thread {

	private Channel channel;

	private String response;

	private Object lock = new Object();

	public ChannelHandler(Channel channel) {
		this.channel = channel;
	}

	public String getResponse() {
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
			}
		}

		return response;
	}

	@Override
	public void run() {
		try {
			InputStream is = channel.getInputStream();
			byte[] b = new byte[1024];

			while (!Thread.currentThread().isInterrupted()) {
				while (is.available() > 0) {
					int i = is.read(b, 0, 1024);
					if (i < 0) {
						break;
					}

					this.response = new String(b, 0, i);
					System.out.println("response: [" + response + "]");

					synchronized (lock) {
						lock.notify();
					}
				}

				if (channel.isClosed()) {
					if (is.available() > 0) {
						continue;
					}

					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}

				try {
					Thread.sleep(100);
				} catch (Exception ee) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
