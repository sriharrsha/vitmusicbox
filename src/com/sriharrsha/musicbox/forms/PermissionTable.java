package com.sriharrsha.musicbox.forms;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gwt.user.client.ui.Label;
import com.sriharrsha.musicbox.helpers.HibernateUtil;
import com.sriharrsha.musicbox.model.Bookings;
import com.sriharrsha.musicbox.model.UserDetails;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.hbnutil.HbnContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;

public class PermissionTable extends Table {

	public PermissionTable() {
		setCaption("Bookings for Today:");
		setContainerDataSource(new HbnContainer<Bookings>(Bookings.class,
				HibernateUtil.getSessionFactory()));
		
		
	}

	 @Override
	    protected String formatPropertyValue(Object rowId, Object colId, Property property) {
	        if ("userDetails".equals(colId)) {
	        	 Bookings booking=(Bookings)((HbnContainer.EntityItem.EntityProperty) property).getPojo();
	        	 UserDetails user=booking.getUserDetails();
				 return user.getName().getFirstName()+" "+user.getName().getLastName();   
	        }
	        if("allottedMembers".equals(colId)) {
	        	 String listOfStudents="";
	        	 Bookings booking=(Bookings)((HbnContainer.EntityItem.EntityProperty) property).getPojo();
	        	 List<UserDetails> utilizingStudents=booking.getAllottedMembers();
	        	 if(utilizingStudents.size()==0) {
	        		 UserDetails user=booking.getUserDetails();
					 return user.getName().getFirstName()+" "+user.getName().getLastName();
	        	 }
	        	 Iterator<UserDetails> iterator =utilizingStudents.iterator();
	        	 while(iterator.hasNext()) {
	        		 UserDetails student=iterator.next();
	        		 listOfStudents+=student.getName().getFirstName()+" "+student.getName().getLastName()+"; ";
	        	 }
				 return listOfStudents;   
	        }
	        return super.formatPropertyValue(rowId, colId, property);
	    }
	 
	
	public void setRemoveButton() {
		this.addGeneratedColumn("Permission Toggle", new Table.ColumnGenerator() {
			public Object generateCell(final Table source, final Object itemId,
					Object columnId) {

				String status = source.getItem(itemId)
						.getItemProperty("approvalStatus").toString();
				Button permissionToggle = new Button();
				if (status.equalsIgnoreCase("Not Approved")) {
					permissionToggle.setStyleName(ValoTheme.BUTTON_DANGER);
					permissionToggle.setIcon(FontAwesome.MINUS);
					permissionToggle.addClickListener(new ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							source.getItem(itemId)
									.getItemProperty("approvalStatus")
									.setValue("Approved");
							event.getButton().setCaption("Approved");
							event.getButton().setStyleName(ValoTheme.BUTTON_FRIENDLY);
							Notification.show("You Approved");
						}
					});
					return permissionToggle;
				} else	if (status.equalsIgnoreCase("Approved")) {
					permissionToggle.setStyleName(ValoTheme.BUTTON_FRIENDLY);
					permissionToggle.setIcon(FontAwesome.CHECK);
					permissionToggle.addClickListener(new ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							source.getItem(itemId)
									.getItemProperty("approvalStatus")
									.setValue("Not Approved");
							event.getButton().setCaption("Not Approved");
							event.getButton().setStyleName(ValoTheme.BUTTON_DANGER);
							Notification.show("You Disapproved");
						}
					});
					return permissionToggle;
				} else {
					permissionToggle.setCaption("Pending");
					permissionToggle.setStyleName(ValoTheme.BUTTON_BORDERLESS);
					permissionToggle.addClickListener(new ClickListener() {
						
						@Override
						public void buttonClick(ClickEvent event) {
							source.getItem(itemId)
							.getItemProperty("approvalStatus")
							.setValue("Approved");
					event.getButton().setCaption("Approved");
					event.getButton().setStyleName(ValoTheme.BUTTON_FRIENDLY);
					Notification.show("You Approved");
							
						}
					});
					return permissionToggle;
				}
			}
		});
	}
	



}
