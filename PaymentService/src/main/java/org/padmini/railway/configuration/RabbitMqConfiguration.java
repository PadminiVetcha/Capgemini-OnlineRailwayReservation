package org.padmini.railway.configuration;
import org.padmini.railway.controller.PaymentController;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConfiguration 
{
	public static final String topicExchangeName = "message_queue_exchange"; 
	public static final String queueName = "message_queue";
	
	@Bean
	Queue queue()
	{
		return new Queue(queueName, false);
	}
	
	@Bean
	TopicExchange exchange() 
	{
		return new TopicExchange(topicExchangeName);
	}
	
	@Bean
	Binding binding(Queue queue,TopicExchange exchange)
	{
		return BindingBuilder.bind(queue).to(exchange).with("message_routing_key");
		
	}
	
	@Bean
	public ConnectionFactory connectionFactory()
	{
		CachingConnectionFactory connFactory=new CachingConnectionFactory("localhost");
		connFactory.setPort(5672);
		connFactory.setUsername("guest");
		connFactory.setPassword("guest");
		return connFactory;
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connFactory,
			MessageListenerAdapter listenerAdapter)
	{
		SimpleMessageListenerContainer container=new SimpleMessageListenerContainer();
		container.setConnectionFactory(connFactory);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(PaymentController jmsReceiver)
	{
		return new MessageListenerAdapter(jmsReceiver,"receiveMessage");
	}
}
