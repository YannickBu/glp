package ipint.glp.domain.util;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author hequet
 *
 */
public class Mail {
	private static final String MessageDefautDeDebut = "Bonjour, \n \n";
	private static final String MessageDefautAccepter = "Votre demande d'inscription a été validée.\n\nVoici vos informations de connexion :\n \n ";
	private static final String MessageDefautRefus = "Votre demande d'inscription a été refusée.\n\nVos informations ont été supprimées de nos bases.\n";
	private static final String MessageDefautComplement = "\nMessage additionel du modérateur :  ";
	private static final String MessageDefautDeFin = "\n\nCordialement votre,\n\nL'équipe de modération de L1nk.";

	private static String messageAcceptationSansMessageAditionnel(String motDePasse){
		String messageRenvoyer;
		messageRenvoyer = MessageDefautDeDebut + MessageDefautAccepter + "Votre mot de passe : "+ motDePasse  + MessageDefautDeFin; 
		return messageRenvoyer;	
	}

	private static String messageAcceptationAvecMessageAditionnel(String motDePasse, String messageAditionnel){
		String messageRenvoyer;
		messageRenvoyer = MessageDefautDeDebut + MessageDefautAccepter + "Votre mot de passe : " + motDePasse  + MessageDefautComplement + messageAditionnel + MessageDefautDeFin;
		return messageRenvoyer;	
	}
	private static String messageRefusSansMessageAditionnel(){
		String messageRenvoyer;
		messageRenvoyer = MessageDefautDeDebut + MessageDefautRefus + MessageDefautDeFin;
		return messageRenvoyer;	
	}
	private static String messageRefusAvecMessageAditionnel(String messageAditionnel){
		String messageRenvoyer;
		messageRenvoyer = MessageDefautDeDebut + MessageDefautRefus + MessageDefautComplement + messageAditionnel + MessageDefautDeFin;
		return messageRenvoyer;	
	}

	/**
	 * Fonction qui appelle une des 4 fonctions qui fabrique le message du mail
	 * 
	 * @param mail
	 * @param type (1 = valider sans message, 2 = valider avec message, 3 = refuser sans message, 4 = refuser avec message)
	 * @param motDePasse
	 * @param messageAditionnel
	 */
	public static String construireMail(int type, String messageAditionnel, String motDePasse) {
		String messageObtenu;
		
		if(type==1){
			messageObtenu = messageAcceptationSansMessageAditionnel(motDePasse);
		}
		else if(type==2){
			messageObtenu = messageAcceptationAvecMessageAditionnel(motDePasse,messageAditionnel);
		}
		else if(type==3){
			messageObtenu = messageRefusSansMessageAditionnel();
		}
		else if(type==4){
			messageObtenu = messageRefusAvecMessageAditionnel(messageAditionnel);
		}
		else{
			messageObtenu = "Va ne devrais jamais passer ici";
		}
		
		return messageObtenu;
	}
	
	public static void envoyerMail(String mail,String messageMail){
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtps.univ-lille1.fr");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.setProperty("proxySet","true");
        props.setProperty("socksProxyHost","cache.univ-lille1.fr");
        props.setProperty("socksProxyPort","3821");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("pierre-louis.hequet","Gmail321");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pierre-louis.hequet@etudiant.univ-lille1.fr"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mail));
			message.setSubject("L1nk - votre demande d'inscription à été traitée");
			message.setText(messageMail);

			Transport.send(message);

//			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}