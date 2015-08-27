package com.sriharrsha.musicbox.forms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sriharrsha.musicbox.MusicboxUI;
import com.sriharrsha.musicbox.helpers.HibernateUtil;
import com.sriharrsha.musicbox.layouts.BodyLayout;
import com.sriharrsha.musicbox.model.Bookings;
import com.sriharrsha.musicbox.model.UserDetails;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class BookingForm extends FormLayout{
	
    final	 OptionGroup practiceType=new OptionGroup("Type of Practice");
    PasswordField passwordField  = new PasswordField("Password:");
    PasswordField confPasswordField  = new PasswordField("Confirm Your Password:");
    TextField bandNameField  = new TextField("Band Name:");
    ComboBox instrumentCombo=new ComboBox("Instrument");
    Button  bookButton  = new Button("Book");
    ArrayList<String> instrumentList=new ArrayList<String>();
	Button epassButton=new Button("Get your Pass");
	TwinColSelect memberList;
	
	
  //group  
   public BookingForm(final int id){
	    bookButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
    	Session session=HibernateUtil.getSessionFactory().openSession();	     
		session.beginTransaction();
		Query loginQuery=session.createQuery("from UserDetails");
		final List<UserDetails> usersList=loginQuery.list();
		bookButton.addClickListener(new Button.ClickListener() {	
			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(ClickEvent event) {
				Bookings booking=new Bookings();
				booking.setGroupName(bandNameField.getValue());
				booking.setSlotId(id);
				booking.setInstrumentName("");		
				booking.setApprovalStatus("Pending");
				booking.setCreationTime(new Date());
				doMapping((UserDetails) VaadinSession.getCurrent().getAttribute("user"), booking);
				
//				if(booking.getCreationTime().after(when)
				//TODO IMPLEMENT AFTER 6 O CLOCK
				Date practiceDate=new Date();
				practiceDate.setTime(practiceDate.getTime() + 1 * 24 * 60 * 60 * 1000);
				booking.setPracticeDate(practiceDate);
				booking.setInstrumentName("All");
				booking.getAllottedMembers().addAll((Collection<UserDetails>) memberList.getValue());
				try {
				Session session=HibernateUtil.getSessionFactory().openSession();	     
				session.beginTransaction();
				session.save(booking);
				session.getTransaction().commit();
				Notification.show("Your Booking was Successful.");
				}catch(Exception e) {
					Notification.show("Your Booking was not Successful. This slot might be booked by someone");
				}finally {
					BodyLayout body=MusicboxUI.getCurrent().getPageLayout().getBodyLayout();
					body.removeAllComponents();
					SlotMenu slotMenu=new SlotMenu();
					slotMenu.setSizeUndefined();
					body.addComponent(slotMenu);
					body.setComponentAlignment(slotMenu, Alignment.TOP_CENTER);	
				}
			}
		});
		
					 removeAllComponents();
					 practiceType.select(2);
					 instrumentCombo.setEnabled(false);
					 instrumentCombo.setVisible(false);
					 addComponent(bandNameField);
					 memberList = new TwinColSelect ("Select your Band Members",usersList);
					 memberList.setSizeFull(); 
					 memberList.setMultiSelect(true); 				 
					 addComponent(memberList);
					 addComponent(bookButton);
    }


//solo
	public BookingForm(String string,final int id) {
		  removeAllComponents();
		  String[] instrumentArray = {"Keyboard","Piano","Drums","Guitar","Bass Guitar"};
	      instrumentList.addAll(Arrays.asList(instrumentArray));
		  practiceType.select(1);
		  instrumentCombo.addItems(instrumentList);
		  addComponent(instrumentCombo);
		  instrumentCombo.setImmediate(true);
		  addComponent(bookButton);
		  bookButton.addClickListener(new Button.ClickListener() {	
				@SuppressWarnings("unchecked")
				@Override
				public void buttonClick(ClickEvent event) {
					Bookings booking=new Bookings();
					booking.setSlotId(id);
					booking.setInstrumentName((String)instrumentCombo.getValue());		
					booking.setApprovalStatus("Pending");
					booking.setCreationTime(new Date());
					booking.setGroupName("Solo");
					doMapping((UserDetails) VaadinSession.getCurrent().getAttribute("user"), booking);
//					if(booking.getCreationTime().after(when)
					//TODO IMPLEMENT AFTER 6 O CLOCK
					Date practiceDate=new Date();
					practiceDate.setTime(practiceDate.getTime() + 1 * 24 * 60 * 60 * 1000);
					booking.setPracticeDate(practiceDate);
					try {
					Session session=HibernateUtil.getSessionFactory().openSession();	     
					session.beginTransaction();
					session.save(booking);
					session.getTransaction().commit();
					Notification.show("Your Booking was Successful.");
					}catch(Exception e) {
						Notification.show("Your Booking was not Successful. This slot might be booked by someone");
					}finally {
						BodyLayout body=MusicboxUI.getCurrent().getPageLayout().getBodyLayout();
						body.removeAllComponents();
						SlotMenu slotMenu=new SlotMenu();
						slotMenu.setSizeUndefined();
						body.addComponent(slotMenu);
						body.setComponentAlignment(slotMenu, Alignment.TOP_CENTER);	
					}
				}
			});
	}
	
	private void doMapping(UserDetails user,Bookings booking) {
		user.getBookings().add(booking);
		booking.setUserDetails(user);
	}
}