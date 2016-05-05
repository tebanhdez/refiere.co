package co.refiere.resources.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EmailRequest {

	private List<String> recipients;
	private String subject;
	private String body;

    private List<String> attachments;
	
	public List<String> getRecipients() {
		return (recipients != null) ? recipients : new LinkedList<String>();
	}
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
	public String[] getRecipientAsArray(){
	    return Arrays.copyOf(recipients.toArray(), recipients.size(), String[].class);
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public List<String> getAttachments() {
		return (attachments != null ) ? attachments : new LinkedList<String>();
	}
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}
	
	public String[] getAttachmentsAsArray(){
	    return Arrays.copyOf(attachments.toArray(), attachments.size(), String[].class);
	}
	
	@Override
    public String toString() {
        return "EmailRequest [recipients=" + recipients + ", subject=" + subject + ", body=" + body + ", attachments="
                + attachments + "]";
    }
}
