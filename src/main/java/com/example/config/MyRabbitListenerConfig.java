package com.example.config;//package com.example.config;
//
//import com.example.listener.MyRabbitListener;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableRabbit
//public class MyRabbitListenerConfig {
//
//    @Bean
//    public MyRabbitListener myRabbitListener() {
//        return new MyRabbitListener();
//    }
//
//
//    @Bean
//    public RabbitListenerContainerFactory<?> containerFactory1011(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory1011());
//        return factory;
//    }
//
//    @Bean
//    public RabbitListenerContainerFactory<?> containerFactory1012(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory1012());
//        return factory;
//    }
//
//    public CachingConnectionFactory connectionFactory1011() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1", 5672);
//        connectionFactory.setUsername("yyh");
//        connectionFactory.setPassword("123456");
//        connectionFactory.setVirtualHost("1011");
//        return connectionFactory;
//    }
//
//    public CachingConnectionFactory connectionFactory1012() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1", 5673);
//        connectionFactory.setUsername("yyh");
//        connectionFactory.setPassword("123456");
//        connectionFactory.setVirtualHost("1012");
//        return connectionFactory;
//    }
//
//    public String test(){
//        return "yes";
//    }
//
//    @Bean
//    public String testBean(){
//        return "yes bean";
//    }
//
//}
