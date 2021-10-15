package ordermicroservice.email;

import lombok.Data;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Ankit Srivastava.
 */
@Data
public class Mail implements Serializable{
	
    private static final long serialVersionUID = 1307940177495496567L;

    private String toEmail;
    private String fromEmail;
    private String content;
    private String senderName;
    private String replyTo;
    private String subject;
    private String message;
    private boolean htmlFormat = Boolean.TRUE;
    private String cc;
    private String bcc;
    private List<File> attachments;
}
