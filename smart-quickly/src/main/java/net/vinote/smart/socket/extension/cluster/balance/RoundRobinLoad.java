package net.vinote.smart.socket.extension.cluster.balance;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import net.vinote.smart.socket.logger.RunLogger;
import net.vinote.smart.socket.transport.ClientChannelService;
import net.vinote.smart.socket.transport.TransportSession;

/**
 * 轮循算法
 * <p>
 * 说明:每一次来自网络的请求轮流分配给内部中的每台服务器,从1至N然后重新开始｡
 * </p>
 * <p>
 * 举例:此种负载均衡算法适合于服务器组中的所有服务器都有相同的软硬件配置并且平均服务请求相对均衡的情况;
 * </p>
 * 
 * @author Seer
 *
 */
public class RoundRobinLoad extends AbstractLoadBalancing {
	private static final RunLogger logger = RunLogger.getLogger();
	private AtomicInteger index = new AtomicInteger(0);

	public TransportSession balancing(TransportSession clientSession) {
		ClientChannelService clusterClient = serverList.get(index
				.getAndIncrement() % serverList.size());
		TransportSession session = clusterClient.getSession();
		logger.log(Level.SEVERE,
				"distribute ClusterServer[IP:" + session.getRemoteAddr()
						+ " ,Port:" + session.getRemotePort()
						+ "] to Client[IP:" + clientSession.getRemoteAddr()
						+ " ,Port:" + clientSession.getRemotePort() + "]");
		return session;
	}
}