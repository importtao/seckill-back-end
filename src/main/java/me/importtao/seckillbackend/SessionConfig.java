package me.importtao.seckillbackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Package me.importtao.usersystem
 * Class SessionConfig
 * Description: TODO
 *
 * @author importtao
 * date 2018/3/10 17:09
 * @version V1.0
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class SessionConfig {
}
