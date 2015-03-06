package aly.kafka.examples.consumer.group;

/**
 * 
 * https://cwiki.apache.org/confluence/display/KAFKA/Consumer+Group+Example
 *
 */

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import aly.kafka.play.tools.ConfPlay;

//https://cwiki.apache.org/confluence/display/KAFKA/Consumer+Group+Example

public class GroupConsumer
{
	private final ConsumerConnector consumer;
	private final String topic;
//	private ExecutorService executor;

	final static Logger logger = Logger.getLogger(GroupConsumer.class);
	
	public GroupConsumer(String zooConnStr, String groupId, String a_topic)
	{
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig(
				zooConnStr, groupId));
		this.topic = a_topic;
	}

	public static void main(String[] args)
	{
		String zooKeeper = ConfPlay.LOCAL_BROKER_DEF;
		String groupId = "grp_" + 1;
		String topic = "cat";
		int threads = 1;

		GroupConsumer example = new GroupConsumer(zooKeeper, groupId, topic);
		example.run(threads);

		try
		{
			Thread.sleep(10000);
		}
		catch (InterruptedException ie)
		{

		}
		example.shutdown();
	}

	public void shutdown()
	{
		if (consumer != null)
			consumer.shutdown();
//		if (executor != null)
//			executor.shutdown();
	}

	public void run(int a_numThreads)
	{
		logger.debug("run() on entry");
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(a_numThreads));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
				.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

		// now launch all the threads
		//
//		executor = Executors.newFixedThreadPool(a_numThreads);

		// now create an object to consume the messages
		//
		int threadNumber = 0;
		for (final KafkaStream stream : streams)
		{
//			executor.submit(new ConsumerGroupTest(stream, threadNumber));
			ConsumerWorker groupTest = new ConsumerWorker(stream, threadNumber);
			groupTest.run();
			threadNumber++;
		}
		logger.debug("run() on exit");
	}

	private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId)
	{
		Properties props = new Properties();
		props.put("zookeeper.connect", a_zookeeper);
		props.put("group.id", a_groupId);
		props.put("zookeeper.session.timeout.ms", "400");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");

		return new ConsumerConfig(props);
	}

}
