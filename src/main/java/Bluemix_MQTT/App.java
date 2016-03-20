/**
 * Created by mark on 06/03/16.
 */
package Bluemix_MQTT;
import org.eclipse.paho.client.mqttv3.*;

public class App {

    public static void main(String[] args) {
        MqttClient client;

        try {
            client = new MqttClient(
                    "tcp://xi7mod.messaging.internetofthings.ibmcloud.com:1883",
                    "d:xi7mod:MQTTDevice:thisisthedeviceid"); // d:<org-id>:<type-id>:<device-id>
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName("use-token-auth"); // also tried "token"
            options.setPassword("9KuuaLWin!fFiSe(kC".toCharArray());
            client.connect(options);
            System.out.println("Connected");
            // post a topic
            MqttTopic topic = client.getTopic("Hello/world");
            MqttMessage message = new MqttMessage("message payload".getBytes());
            message.setQos(0);
            MqttDeliveryToken token = topic.publish(message);
            token.waitForCompletion();

        } catch (MqttException me) {
            me.printStackTrace();
        }
    }

}
