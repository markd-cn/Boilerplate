package net.boblog.app.config;

import net.boblog.app.config.assembly.RedisSessionAuthenticationStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

import java.util.Collections;

/**
 * Created by dave on 16/5/16.
 */
@Configuration
@EnableRedisHttpSession
@EnableSpringHttpSession
public class HttpSessionConfig extends AbstractHttpSessionApplicationInitializer {
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;

    public HttpSessionConfig() {
        super(HttpSessionConfig.class);
    }

    @Bean
    public RedisSessionAuthenticationStrategy getStrategy(RedisOperationsSessionRepository sessionRepository) {
        return new RedisSessionAuthenticationStrategy(sessionRepository);
    }

    @Bean
    public CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy(RedisSessionAuthenticationStrategy strategy) {
        CompositeSessionAuthenticationStrategy composite = new CompositeSessionAuthenticationStrategy(Collections.singletonList(strategy));
        return composite;
    }

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(2);
        config.setMaxIdle(5);
        config.setTestOnBorrow(true);
        config.setMaxTotal(8);
        config.setMaxWaitMillis(5000);

        JedisShardInfo shardInfo = new JedisShardInfo(redisHost, redisPort);
        JedisConnectionFactory factory = new JedisConnectionFactory(config);
        factory.setShardInfo(shardInfo);
        return factory;
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("sessionid");
        serializer.setCookiePath("/");
        serializer.setCookieMaxAge(1800);
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return serializer;
    }
}
