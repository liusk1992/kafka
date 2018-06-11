/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.kafka.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liusk
 * @version $Id: KafkaController.java, v 0.1 2018/6/11 11:21 liusk Exp $
 */
@RestController
@RequestMapping(value = "/kfk")
public class KafkaController {

    private final static Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public String sendKafka(HttpServletRequest request){
        try {
            //logger.info("kafka的消息为: [{}]", JSONObject.toJSONString(requestVO));
            kafkaTemplate.send("liusk-topic", "key", "kfk test");
            logger.info("[producer]kafka发送成功");
            return "发送成功";
        } catch (Exception e){
            logger.error("kafka发送失败", e);
            return "发送失败";
        }

    }
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "哈哈哈哈~~~";
    }


}
