package com.sriharrsha.musicbox.forms;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.mindrot.jbcrypt.BCrypt;

import com.google.gwt.user.client.ui.RadioButton;
import com.sriharrsha.musicbox.MusicboxUI;
import com.sriharrsha.musicbox.helpers.HibernateUtil;
import com.sriharrsha.musicbox.layouts.BodyLayout;
import com.sriharrsha.musicbox.model.Name;
import com.sriharrsha.musicbox.model.UserDetails;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class SignUpForm extends FormLayout{
    
    
    public SignUpForm() {
        final TextField firstNameField  = new TextField("First Name:");
        firstNameField.addValidator(new StringLengthValidator("Enter First Name",2,30,false));
        firstNameField.setValidationVisible(false);
        final TextField lastNameField  = new TextField("Last Name:");
        lastNameField.addValidator(new StringLengthValidator("Enter Last Name",2,30,false));
        lastNameField.setValidationVisible(false);
        
        final TextField regNoField  = new TextField("Registration Number:");
        regNoField.addValidator(new StringLengthValidator("Enter Valid Registration Number",9,9,false));
        regNoField.addValidator(new RegexpValidator("^\\d[0-9]{2}\\w[a-z][A-Z]{3}\\d[0-9]{4}$", "Enter Valid Registration Number"));
        regNoField.setValidationVisible(false);
        final OptionGroup genderField=new OptionGroup("Gender:");
        genderField.addItem("Male");
    	genderField.addItem("Female");

    	final DateField dateField = new DateField("Date of Birth:");
    	
    	dateField.setValue(new Date());
    	dateField.setDateFormat("dd-MM-yyyy");
        final PasswordField passwordField  = new PasswordField("Password:");
        passwordField.addValidator(new NullValidator(
                "Must not be Empty", false));
		passwordField.addValidator(new StringLengthValidator("Must be 6 characters length. You typed {0}",6,32,false));
		passwordField.setValidationVisible(false);
        final PasswordField confPasswordField  = new PasswordField("Confirm Your Password:");
        confPasswordField.addValidator(new NullValidator(
                "Must not be Empty", false));
		confPasswordField.addValidator(new StringLengthValidator("Must be 6 characters length. You typed {0}",6,32,false));
		confPasswordField.setValidationVisible(false);
        final TextField phoneField  = new TextField("Phone Number:");
        phoneField.addValidator(new StringLengthValidator("Must be 10 characters length. You typed {0}",10,10,false));
        phoneField.addValidator(new RegexpValidator("^[0-9]$", "Enter A valid Number"));
        phoneField.setValidationVisible(false);
        
        final TextField emailField  = new TextField("Email ID:");
        emailField.addValidator(new RegexpValidator("^[A-Z0-9._%+-]+@vit.ac.in$", "Not a VIT Authorized Email"));
        emailField.setValidationVisible(false);
        final ComboBox typeField=new ComboBox("You are an .......");
        typeField.addItem("Vocalist");
        typeField.addItem("Guitarist");
        typeField.addItem("Bassist");
        typeField.addItem("Pianist");
        typeField.addItem("Drummer");
        final Button  signUpButton   = new Button("Join");
        final Button hasAccount=new Button("Already has an Account?");   
    
    	signUpButton.setClickShortcut(KeyCode.ENTER);
    	signUpButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
    	signUpButton.setIcon(FontAwesome.HAND_O_UP);
    	hasAccount.setStyleName(ValoTheme.BUTTON_LINK);
    	signUpButton.setWidth("100%");
    	dateField.setIcon(FontAwesome.CALENDAR);
    	regNoField.setDescription("ex:11MSE1140");
    	dateField.setDescription("Format: Date-Month-Year");
    	
    	addComponent(firstNameField);
    	addComponent(lastNameField);
    	addComponent(genderField);
    	addComponent(dateField);
    	addComponent(regNoField);
    	addComponent(passwordField);
    	addComponent(confPasswordField);
    	addComponent(phoneField);
    	addComponent(emailField);
    	addComponent(typeField);
        addComponent(signUpButton);
        addComponent(hasAccount);
        
        signUpButton.addClickListener(new Button.ClickListener() {		
			@Override
			public void buttonClick(ClickEvent event) {
				
				try {
					firstNameField.validate();
					lastNameField.validate();
					regNoField.validate();
					passwordField.validate();
					confPasswordField.validate();
					emailField.validate();
				}catch(InvalidValueException e) {
					firstNameField.setValidationVisible(true);
					lastNameField.setValidationVisible(true);
					regNoField.setValidationVisible(true);
					passwordField.setValidationVisible(true);
					confPasswordField.setValidationVisible(true);
					emailField.setValidationVisible(true);
				}
			

				UserDetails user=new UserDetails();
				Name fullName=new Name();
				fullName.setFirstName(firstNameField.getValue());
				fullName.setLastName(lastNameField.getValue());
				
				user.setName(fullName);
				user.setEmailId(emailField.getValue());
				user.setGender((String) genderField.getValue());
				user.setPhoneNumber(Long.parseLong(phoneField.getValue()));
				user.setBirthDate(dateField.getValue());
				user.setPassword(BCrypt.hashpw(passwordField.getValue(), BCrypt.gensalt()));
				user.setRegNo(regNoField.getValue());
				user.setType((String) typeField.getValue());
				
				Session session=HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				try {
					session.save(user);
					Notification.show("Registered Succesfully",Notification.TYPE_HUMANIZED_MESSAGE);
					session.getTransaction().commit();
				} catch(ConstraintViolationException e) {
				    Notification.show("Account Already Exists");
				    session.getTransaction().rollback();
				} catch(JDBCConnectionException e) {
					Notification.show("Contact your Adminstrator");
					session.getTransaction().commit();
				}   
				session.getTransaction().commit();
			
			}
		});
        
        hasAccount.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				BodyLayout body=MusicboxUI.getCurrent().getPageLayout().getBodyLayout();
				body.removeAllComponents();
                body.addComponent(body.getSignInForm());
                body.setComponentAlignment(body.getSignInForm(), Alignment.MIDDLE_CENTER);
                body.getSignInForm().setWidthUndefined();
                body.getSignInForm().setHeightUndefined();
			}
		});
        
        addComponent(signUpButton);
        addComponent(hasAccount);
    }
}