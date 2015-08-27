package com.sriharrsha.musicbox.forms;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import com.sriharrsha.musicbox.helpers.HibernateUtil;
import com.sriharrsha.musicbox.model.UserDetails;

public class ForgotPasswordForm extends FormLayout implements Component {
	
	public ForgotPasswordForm(){
		final TextField regNoField=new TextField("Registration Number");
		final TextField phoneNumberField=new TextField("Phone Number");
		Button submitPassword=new Button("Reset");
		
		submitPassword.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Session session;
				try {
					session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();
					Query query=session.createQuery("from UserDetails where regNo='"+regNoField.getValue()+"' and phoneNumber='"+phoneNumberField.getValue()+"'");
					List<UserDetails> records=query.list();
					String emailId="";
					if(records.size()!=0)
					emailId=records.get(0).getEmailId();
					 SendGrid sendgrid = new SendGrid("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");

					    SendGrid.Email email = new SendGrid.Email();
					    email.addTo(emailId);
					    email.setFrom("support@vitmusicbox.com");
					    email.setSubject("VIT MusicBox: Reset Your Password");
					    email.setText("My first email with SendGrid Java VIT MusicBox!");

					    try {
					      SendGrid.Response response = sendgrid.send(email);
					      System.out.println(response.getMessage()+"to:"+emailId);
					    }
					    catch (SendGridException e) {
					      System.err.println(e);
					    }
					
					session.getTransaction().commit();	
				} catch (HibernateException e) {
					Notification.show("No such Account / Check the phone number.");
				}	
				Notification.show("Reset mail will be sent if credentials match.");
			}
		});
		
		addComponents(regNoField,phoneNumberField,submitPassword);
	}
	
	
}
