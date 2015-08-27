package com.sriharrsha.musicbox.forms;

import org.vaadin.teemu.VaadinIcons;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.sriharrsha.musicbox.MusicboxUI;
import com.sriharrsha.musicbox.layouts.BodyLayout;
import com.sriharrsha.musicbox.layouts.HeaderLayout;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class NavigationBar extends HorizontalLayout{
	
	public NavigationBar() {
		Button slotsPageButton=new Button("Slots Page");
		Button bookingHistoryButton=new Button("Booking History");
		Button profileButton=new Button("Profile");
		slotsPageButton.setIcon(FontAwesome.SHARE_SQUARE);
		slotsPageButton.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		bookingHistoryButton.setIcon(FontAwesome.HISTORY);
		bookingHistoryButton.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		profileButton.setIcon(FontAwesome.INFO_CIRCLE);
		profileButton.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		Button signOutButton=new Button("Sign Out");
		signOutButton.setIcon(VaadinIcons.SIGN_OUT);
		signOutButton.setStyleName(ValoTheme.BUTTON_SMALL);
		
		final BodyLayout body=MusicboxUI.getCurrent().getPageLayout().getBodyLayout();
		slotsPageButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				body.removeAllComponents();
				SlotMenu slotMenu=new SlotMenu();
				slotMenu.setSizeUndefined();
				body.addComponent(slotMenu);
				body.setComponentAlignment(slotMenu, Alignment.TOP_CENTER);
				
			}
		});
		
		bookingHistoryButton.addClickListener(new Button.ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			body.removeAllComponents();
			HistoryTable history=new HistoryTable();
			history.setEditable(true);
			history.setRemoveButton();
			history.setPassButton();
			history.setSizeFull();
			history.setEditable(false);
			body.addComponent(history);
			body.setComponentAlignment(history, Alignment.TOP_CENTER);
			
				}
			});
	
		profileButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				body.removeAllComponents();
				ProfilePage profile=new ProfilePage();
				profile.setSizeUndefined();
				body.addComponent(profile);
				body.setComponentAlignment(profile, Alignment.TOP_CENTER);
				
			}
		});
		
		signOutButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				body.removeAllComponents();
				HeaderLayout headerLayout=MusicboxUI.getCurrent().getPageLayout().getHeaderLayout();
				headerLayout.removeAllComponents();
				Button logo=new Button("VIT Music Club");
				logo.setIcon(FontAwesome.MUSIC);
				logo.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
				headerLayout.addComponent(logo);
				VaadinSession.getCurrent().setAttribute("username",null);
				Notification.show("Succesfully Logged Out");
			}
		});
		
		addComponent(slotsPageButton);
		addComponent(bookingHistoryButton);
		addComponent(profileButton);
		addComponent(signOutButton);
		setHeight(null);
	}
	
	public NavigationBar(String admin) {
		Button bookingHistoryButton=new Button("Bookings");
		Button memberDetailsButton=new Button("Members");
		Button profileButton=new Button("Profile");
		memberDetailsButton.setIcon(FontAwesome.SHARE_SQUARE);
		memberDetailsButton.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		bookingHistoryButton.setIcon(FontAwesome.HISTORY);
		bookingHistoryButton.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		profileButton.setIcon(FontAwesome.INFO_CIRCLE);
		profileButton.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		Button signOutButton=new Button("Sign Out");
		signOutButton.setIcon(VaadinIcons.SIGN_OUT);
		signOutButton.setStyleName(ValoTheme.BUTTON_SMALL);
		
		final BodyLayout body=MusicboxUI.getCurrent().getPageLayout().getBodyLayout();
		memberDetailsButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				body.removeAllComponents();
				MemberDetails memberDetails=new MemberDetails();
				memberDetails.setSizeFull();
				body.addComponent(memberDetails);
				body.setComponentAlignment(memberDetails, Alignment.TOP_CENTER);
			}
		});
		
		bookingHistoryButton.addClickListener(new Button.ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			body.removeAllComponents();
			PermissionTable history=new PermissionTable();
			history.setEditable(true);
			history.setRemoveButton();
			history.setSizeFull();
			history.setEditable(false);
			body.addComponent(history);
			body.setComponentAlignment(history, Alignment.TOP_CENTER);
			
				}
			});
	
		profileButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				body.removeAllComponents();
				ProfilePage profile=new ProfilePage();
				profile.setSizeUndefined();
				body.addComponent(profile);
				body.setComponentAlignment(profile, Alignment.TOP_CENTER);
				
			}
		});
		
		signOutButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				body.removeAllComponents();
				HeaderLayout headerLayout=MusicboxUI.getCurrent().getPageLayout().getHeaderLayout();
				headerLayout.removeAllComponents();
				Button logo=new Button("VIT Music Club");
				logo.setIcon(FontAwesome.MUSIC);
				logo.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
				headerLayout.addComponent(logo);
				VaadinSession.getCurrent().setAttribute("username",null);
				Notification.show("Succesfully Logged Out");
			}
		});	

		addComponent(bookingHistoryButton);
		addComponent(memberDetailsButton);
		addComponent(profileButton);
		addComponent(signOutButton);
		setHeight(null);
	}

}
