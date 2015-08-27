package com.sriharrsha.musicbox.forms;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.vaadin.teemu.VaadinIcons;

import com.sriharrsha.musicbox.MusicboxUI;
import com.sriharrsha.musicbox.helpers.HibernateUtil;
import com.sriharrsha.musicbox.layouts.BodyLayout;
import com.sriharrsha.musicbox.layouts.HeaderLayout;
import com.sriharrsha.musicbox.model.UserDetails;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.data.util.sqlcontainer.query.generator.MSSQLGenerator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class SignInForm extends FormLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TextField userNameField;
	PasswordField passwordField;
	Button signInButton;
	Button signUpButton;
	HorizontalLayout buttonRow;
	SQLContainer sql;
	private String firstName;

	public SignInForm() {
		userNameField = new TextField("Teacher ID / Student ID");
		userNameField.addValidator(new NullValidator(
	                "Must not be Empty", false));
		userNameField.addValidator(new StringLengthValidator("Must be 9 characters length. You typed {0}",9,9,false));
//		userNameField.addValidator(new RegexpValidator("^[0-9]{2}[a-z][A-Z]{3}[0-9]{4}$", "Enter Valid Registration Number"));
		userNameField.setValidationVisible(false);
		
		passwordField = new PasswordField("Password");
		passwordField.addValidator(new NullValidator(
                "Must not be Empty", false));
		passwordField.addValidator(new StringLengthValidator("Must be 6 characters length. You typed {0}",6,32,false));
		passwordField.setValidationVisible(false);
		signInButton = new Button("Sign In");
		signUpButton = new Button("Sign Up");
		Button forgotPassword=new Button("forgot your password?");
		forgotPassword.setStyleName(ValoTheme.BUTTON_LINK);
		
		
		forgotPassword.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Window formWindow=new Window();
				formWindow.setModal(true);
				formWindow.setContent(new ForgotPasswordForm());
				formWindow.setDraggable(true);
				formWindow.setPosition(50, 50);
				MusicboxUI.getCurrent().addWindow(formWindow);	
				
			}
		});
		
		buttonRow = new HorizontalLayout();
		signInButton.setClickShortcut(KeyCode.ENTER);
		signInButton.setIcon(FontAwesome.SIGN_IN);
		signInButton.setStyleName(ValoTheme.BUTTON_PRIMARY);

		signInButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
			        try {
//			            userNameField.validate();
			            passwordField.validate();
			            String uname = userNameField.getValue().toLowerCase();
						String pass = passwordField.getValue().toLowerCase();
						Session session=HibernateUtil.getSessionFactory().openSession();	     
						session.beginTransaction();
						Query loginQuery=session.createQuery("from UserDetails where regNo='"+uname+"'");
						List records=loginQuery.list();
						session.getTransaction().commit();	
						if(records.size()==1) { 	
							VaadinSession.getCurrent().setAttribute("username", uname);
							UserDetails details=(UserDetails)records.get(0);
							if (BCrypt.checkpw(pass,details.getPassword() ))
							{
							firstName=details.getName().getFirstName();
							VaadinSession.getCurrent().setAttribute("user", details);
							if(uname.equalsIgnoreCase("admin")) {
								setAdminPanelBody();
							}else {
								setStudentPanelBody();
							}
							}
						}else {
							Notification.show("Check Your Credentials",Notification.TYPE_ERROR_MESSAGE);
						}
			        } catch (InvalidValueException e) {
			            userNameField.setValidationVisible(true);
			            passwordField.setValidationVisible(true);
			        }
				
				
			}
		});
		
		signUpButton.setIcon(FontAwesome.HAND_O_UP);
		signUpButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				BodyLayout body=MusicboxUI.getCurrent().getPageLayout().getBodyLayout();
				body.removeAllComponents();
                body.addComponent(body.getSignUpForm());
                body.setComponentAlignment(body.getSignUpForm(), Alignment.MIDDLE_CENTER);
                body.getSignUpForm().setWidthUndefined();
                body.getSignUpForm().setHeightUndefined();
				}
		});
		buttonRow.setSpacing(true);
		buttonRow.addComponents(signInButton, signUpButton);

		addComponent(userNameField);
		addComponent(passwordField);
		addComponent(buttonRow);
		addComponent(forgotPassword);
	}

	private void setStudentPanelBody() {
		HeaderLayout header=MusicboxUI.getCurrent().getPageLayout().getHeaderLayout();
		MusicboxUI.getCurrent().getPageLayout().getHeaderLayout().user=firstName;
		NavigationBar navigationBar=new NavigationBar();
		navigationBar.setWidthUndefined();
		navigationBar.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		header.addComponent(navigationBar);
		header.setComponentAlignment(navigationBar, Alignment.MIDDLE_RIGHT);		
		BodyLayout body=MusicboxUI.getCurrent().getPageLayout().getBodyLayout();
		body.removeAllComponents();
		SlotMenu slotMenu=new SlotMenu();
		slotMenu.setSizeUndefined();
		body.addComponent(slotMenu);
		body.setComponentAlignment(slotMenu, Alignment.TOP_CENTER);		
		Notification.show("Logged in as: "+firstName+" "+VaadinSession.getCurrent().getAttribute("username"),Notification.TYPE_TRAY_NOTIFICATION);
	}
	
	private void setAdminPanelBody() {
		HeaderLayout header=MusicboxUI.getCurrent().getPageLayout().getHeaderLayout();
		MusicboxUI.getCurrent().getPageLayout().getHeaderLayout().user=firstName;
		NavigationBar navigationBar=new NavigationBar("admin");
		navigationBar.setWidthUndefined();
		navigationBar.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		header.addComponent(navigationBar);
		header.setComponentAlignment(navigationBar, Alignment.MIDDLE_RIGHT);		
		BodyLayout body=MusicboxUI.getCurrent().getPageLayout().getBodyLayout();
		body.removeAllComponents();
		PermissionTable permissionTable=new PermissionTable();
		permissionTable.setEditable(true);
		permissionTable.setRemoveButton();
		permissionTable.setSizeFull();
		permissionTable.setEditable(false);
		body.addComponent(permissionTable);
		body.setComponentAlignment(permissionTable, Alignment.TOP_CENTER);
		body.addComponent(permissionTable);
		body.setComponentAlignment(permissionTable, Alignment.TOP_CENTER);		
		Notification.show("Logged in as: "+firstName+" "+VaadinSession.getCurrent().getAttribute("username"),Notification.TYPE_TRAY_NOTIFICATION);
	}

}
