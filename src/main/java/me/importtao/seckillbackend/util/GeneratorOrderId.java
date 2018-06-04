package me.importtao.seckillbackend.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Package me.importtao.seckillbackend.util
 * Class GeneratorOrderId
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/21 10:13
 * @version V1.0
 */
@Transactional(rollbackFor = Exception.class)
@Service
@Scope("singleton")
public class GeneratorOrderId {
}
