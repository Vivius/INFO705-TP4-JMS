import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ReceiverTopic {
    public static void main(String[] args) throws Exception {

        Context context = new InitialContext();

        TopicConnectionFactory qcf = (TopicConnectionFactory) context.lookup("ConnectionFactory");

        Topic q = (Topic) context.lookup("topic1");
        TopicConnection qc = qcf.createTopicConnection();

        TopicSession qs = qc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        TopicSubscriber mc = qs.createDurableSubscriber(q, "sub1");

        qc.start();

        while (true) {
            TextMessage message = (TextMessage) mc.receive();
            System.out.println("Message recu: " + message.getText());
        }
    }
}
