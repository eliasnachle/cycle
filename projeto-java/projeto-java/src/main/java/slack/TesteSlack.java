package slack;

import java.io.IOException;
import org.json.JSONObject;

public class TesteSlack {
    public static void main(String[]arg) throws IOException, InterruptedException {
        Monitoration slack = new Monitoration();
        OpenRequest openRequest = new OpenRequest();
        
        JSONObject message = new JSONObject();
        message.put("text", "Testando mensagem");
        
        JSONObject messageRequest = new JSONObject();
        messageRequest.put("text", "Testando abertura de chamado");
        
        slack.sendMessage(message);
        
        openRequest.sendMessage(messageRequest);
    }
}
