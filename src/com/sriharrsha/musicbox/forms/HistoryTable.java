package com.sriharrsha.musicbox.forms;

import com.sriharrsha.musicbox.helpers.HibernateUtil;
import com.sriharrsha.musicbox.model.Bookings;
import com.vaadin.data.hbnutil.HbnContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Table;

public class HistoryTable extends Table{
	public static final Object[] NATURAL_COL_ORDER = new Object[] {"userId", "groupName", "slotId", "emailId","type"};
	public static final String[] COL_HEADERS_ENGLISH = new String[] {"Reg No", "Name", "Gender", "Phone Number", "Email-Id","Type"};
	
HistoryTable(){
	setCaption("Your Bookings for Today:");
	setContainerDataSource(new HbnContainer<Bookings>(Bookings.class, HibernateUtil.getSessionFactory()));
	
	}

	public void setRemoveButton() {
		this.addGeneratedColumn("Remove",
			      new Table.ColumnGenerator() {
			        public Object generateCell(
			          final Table source,final Object itemId,Object columnId){
			            Button removeButton = new Button("Cancel");
			            removeButton.setIcon(FontAwesome.MINUS);
			            removeButton.setStyleName(ValoTheme.BUTTON_DANGER);
			            removeButton.addClickListener(new ClickListener(){
			              @Override
			            	public void buttonClick(ClickEvent event) {
			                source.removeItem(itemId);
			             }
			          });
			          return removeButton;
			        }
			      });
			  
	}
	
	public void setPassButton() {
		this.addGeneratedColumn("Pass", 
			      new Table.ColumnGenerator() {
			        public Object generateCell(
			          final Table source,final Object itemId,Object columnId){
			        	
			        	
			        	String status=source.getItem(itemId).getItemProperty("approvalStatus").toString();
			      
			        	
			        	if(status.equalsIgnoreCase("Not Approved")) {
			        		Button notApprovedButton = new Button("Generate Pass");
			        		notApprovedButton.setIcon(FontAwesome.CREDIT_CARD);
			        		notApprovedButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
			        		notApprovedButton.setVisible(true);
			        		notApprovedButton.addClickListener(new ClickListener(){
					              @Override
					            	public void buttonClick(ClickEvent event) {
					            	  Notification.show("Your booking is not approved by Admin");
					             }
					          });
					         return notApprovedButton;
			        		
			        	}else if(status.equalsIgnoreCase("Pending")){
				        	 Button pendingDisplay = new Button("Pending");
				        	 pendingDisplay.setStyleName(ValoTheme.BUTTON_BORDERLESS);
				        	 pendingDisplay.setIcon(FontAwesome.CREDIT_CARD);
				        	 pendingDisplay.addClickListener(new ClickListener(){
					              @Override
					            	public void buttonClick(ClickEvent event) {
					                Notification.show("Waiting for Admin Response");
					             }
					          });
				        	 return pendingDisplay;
			        	}else {
			        		 Button generatePassButton = new Button("Generate Pass");
					         generatePassButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
					         generatePassButton.setIcon(FontAwesome.CREDIT_CARD);
					         generatePassButton.setVisible(true);
					         generatePassButton.addClickListener(new ClickListener(){
					              @Override
					            	public void buttonClick(ClickEvent event) {
					                Notification.show("To Print ID Card");
					             }
					          });
					         return generatePassButton;
			        	}
			        }
			      });
			  
	}


}
