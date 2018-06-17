package com.my.app.command.handler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WsCommandHandler extends TextWebSocketHandler {

	private static final Logger LOG = LoggerFactory.getLogger(WsCommandHandler.class);

	private final BlockingQueue<WebSocketSession> sessionQueue = new LinkedTransferQueue<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOG.info("Session connected -> {}", session);
		sessionQueue.put(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOG.info("Session sent message -> {}: {}", session, message);

		// shell 명령어 수행
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOG.info("Session closed -> {}", session);
		sessionQueue.remove(session);
	}

	public void sendClient(String message) {
		sessionQueue.forEach(session -> {
			try {
				session.sendMessage(new TextMessage(message));
			} catch (Exception e) {
			}
		});
	}

}
