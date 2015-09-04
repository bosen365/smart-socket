package net.vinote.smart.socket.protocol.p2p.processor;

import net.vinote.smart.socket.logger.RunLogger;
import net.vinote.smart.socket.protocol.DataEntry;
import net.vinote.smart.socket.protocol.p2p.message.HeartMessageReq;
import net.vinote.smart.socket.protocol.p2p.message.HeartMessageResp;
import net.vinote.smart.socket.service.process.AbstractServiceMessageProcessor;
import net.vinote.smart.socket.service.session.Session;

/**
 * 心跳消息处理器
 * 
 * @author Seer
 * @version HeartMessageProcessor.java, v 0.1 2015年8月27日 上午9:54:35 Seer Exp.
 */
public class HeartMessageProcessor extends AbstractServiceMessageProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.vinote.smart.socket.service.process.AbstractServiceMessageProcessor
	 * #processor(net.vinote.smart.socket.service.session.Session,
	 * net.vinote.smart.socket.protocol.DataEntry)
	 */
	public void processor(Session session, DataEntry message) {
		HeartMessageReq req = (HeartMessageReq) message;
		HeartMessageResp rspMsg = new HeartMessageResp(req.getHead());
		try {
			session.sendWithoutResponse(rspMsg);
		} catch (Exception e) {
			RunLogger.getLogger().log(e);
		}
	}

}