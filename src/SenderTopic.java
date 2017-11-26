import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class SenderTopic {
    public static void main(String[] args) throws Exception {
        Context context = new InitialContext();

        TopicConnectionFactory qcf = (TopicConnectionFactory) context.lookup("ConnectionFactory");
        Topic q = (Topic) context.lookup("topic1");
        TopicConnection qc = qcf.createTopicConnection();

        TopicSession qs = qc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        TopicPublisher mp = qs.createPublisher(q);
        qc.start();

        for(int i = 1; i <= 10; i++)
        {
            Thread.sleep(1000);
            TextMessage message = qs.createTextMessage();
            message.setText("Contenu du message " + i);
            mp.send(message);
            System.out.println(message.getText());
        }

    }
}
