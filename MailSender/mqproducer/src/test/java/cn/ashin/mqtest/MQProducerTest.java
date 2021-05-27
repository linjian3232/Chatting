package cn.ashin.mqtest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ashin.mq.MQProducer;
import cn.ashin.mq.params.MailParam;

/**
 * @author Chyson
 * @version 2016年11月15日 下午2:28:15 类说明
 */
public class MQProducerTest {
	private static final Log log = LogFactory.getLog(MQProducerTest.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:spring/spring-context.xml");
			context.start();

			MQProducer mqProducer = (MQProducer) context.getBean("mqProducer");
				MailParam mail = new MailParam();
				mail.setTo("1134747717@qq.com");
				mail.setSubject("ActiveMQ测试" );
				mail.setContent("Hello,本座来测试一下邮件发送是否成功，第三次");
				mqProducer.sendMessage(mail);
				System.out.println("发送完成");
			context.stop();
		} catch (Exception e) {
			log.error("--->MQ context start error:", e);
			System.exit(0);
		} finally {
			log.info("--->System.exit");
			System.exit(0);
		}
	}
}
