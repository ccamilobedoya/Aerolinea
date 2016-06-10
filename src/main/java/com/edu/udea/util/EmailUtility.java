package com.edu.udea.util;

import javax.mail.Message.RecipientType;

import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.codemonkey.simplejavamail.email.Email;
import org.hazlewood.connor.bottema.emailaddress.EmailAddressCriteria;
import org.hazlewood.connor.bottema.emailaddress.EmailAddressValidator;

public class EmailUtility {
	
	
	
	
	public void enviarCorreo(String nombre, String correo, String asunto, String texto) {
		// Si es un correo valido
		if (EmailAddressValidator.isValid(correo, EmailAddressCriteria.RFC_COMPLIANT)) {

			Email email = new Email();
			email.setFromAddress("Aerolinea UdeA", "noreply@aerolinea.udea.edu.com");
			email.addRecipient(nombre, correo, RecipientType.TO);
			email.setSubject(asunto);
			email.setTextHTML(texto);
			
			new Mailer("smtp.gmail.com", 587, "cuentapruebaasd456@gmail.com", "asd456asd", TransportStrategy.SMTP_TLS).sendMail(email);
		}
		
		
	}
		
}
