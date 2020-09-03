import com.messagebird.MessageBirdClient;
import com.messagebird.MessageBirdService;
import com.messagebird.MessageBirdServiceImpl;
import com.messagebird.exceptions.GeneralException;
import com.messagebird.exceptions.UnauthorizedException;
import com.messagebird.objects.conversations.ConversationContent;
import com.messagebird.objects.conversations.ConversationContentType;
import com.messagebird.objects.conversations.ConversationFallbackOption;
import com.messagebird.objects.conversations.ConversationSendRequest;
import com.messagebird.objects.conversations.ConversationSendResponse;

public class ExampleConversationSendMessage {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please at least specify your access key, the channel id and destination address.\n" +
                    "Usage : java -jar <this jar file> test_accesskey(Required) channel_id(Required) to(Required) fallback_channel_id(optional)");
            return;
        }

        //First create your service object
        final MessageBirdService wsr = new MessageBirdServiceImpl(args[0]);
        //Add the service to the client
        final MessageBirdClient messageBirdClient = new MessageBirdClient(wsr);



        ConversationFallbackOption fallbackOption = null;
        if (args.length == 4) {
            fallbackOption = new ConversationFallbackOption(args[3], "5m");
        }
        ConversationContent conversationContent = new ConversationContent();
        conversationContent.setText("Hello world from java sdk");

        ConversationSendRequest request = new ConversationSendRequest(
                args[1],
                ConversationContentType.TEXT,
                conversationContent,
                args[2],
                "",
                fallbackOption);

        try {
            ConversationSendResponse sendResponse = messageBirdClient.sendMessage(request);
            System.out.println(sendResponse.toString());

        } catch (GeneralException | UnauthorizedException exception) {
            exception.printStackTrace();
        }
    }
}
