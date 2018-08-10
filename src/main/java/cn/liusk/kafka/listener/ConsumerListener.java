/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author liusk
 * @version $Id: ConsumerListener.java, v 0.1 2018/6/11 14:21 liusk Exp $
 */
public class ConsumerListener {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerListener.class);

    @KafkaListener(topics = {"test0611", "liusk-topic"})
    public void listen(ConsumerRecord<?, ?> record){
        if(logger.isInfoEnabled()){
            logger.info("接受到kafka数据: value = [{}], key = [{}], offset = [{}]", record.value(), record.key(), record.offset());
        }
        try {
            String log = record.value().toString();
            if (log==null || "".equals(log)){
                logger.info("String判断为空退出： [{}]", log);
                return ;
            }
            logger.info("[consumer]"+log);
        } catch (Exception e){
            logger.error("从kafka获取消息写入odps异常", e);
        }
    }

}
