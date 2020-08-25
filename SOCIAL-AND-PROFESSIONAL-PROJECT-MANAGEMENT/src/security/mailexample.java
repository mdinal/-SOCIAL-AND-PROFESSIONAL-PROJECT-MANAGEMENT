package security;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.event.*;
import javax.mail.Authenticator;
import java.net.*;
import java.util.Properties;
import java.util.Random;
public class mailexample 
    {
  public static void set (String a,int code) throws Exception {

	 
    String from = "madurangadinal@gmail.com";
    String to = a;
    int Code=code;
try
{
Properties props=new Properties();


props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


props.put("mail.smtp.starttls.enable","true");
props.put("mail.transport.protocol", "smtp");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port", "25");
props.put("mail.smtp.auth", "true");
javax.mail.Authenticator authenticator = new javax.mail.Authenticator()
    {
    protected javax.mail.PasswordAuthentication getPasswordAuthentication() 
        {
        return new javax.mail.PasswordAuthentication("madurangadinal@gmail.com", "uresha449100");
    }
};
Session sess=Session.getDefaultInstance(props,authenticator);
sess.setDebug (true);
Transport transport =sess.getTransport ("smtp");
Message msg=new MimeMessage(sess);
msg.setFrom(new InternetAddress(from));
msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
msg.setSubject("Reset  Code");
msg.setText("your reste code is:"+Code);
transport.connect();
transport.send(msg);

}
catch(Exception e)
{
System.out.println("err"+e);
}
}
}