package com.sriharrsha.musicbox.panel;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class StudentPanel extends HorizontalLayout {
	private MainStrip mainStrip;
	private MenuStrip menuStrip;
	private NotificationStrip notificationStrip;
	public StudentPanel(){
		menuStrip=new MenuStrip();
		mainStrip=new MainStrip();
		notificationStrip=new NotificationStrip();		
		addComponents(menuStrip,mainStrip,notificationStrip);
		
		
		
	}
	
	

	
}
