package com.sriharrsha.musicbox.layouts;

import org.vaadin.teemu.VaadinIcons;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;


@Theme("musicbox")
public class HeaderLayout extends HorizontalLayout  {
	public String user="";
	
	public HeaderLayout(){
		setWidth("100%");
		setSpacing(true);
		Button logo=new Button("VIT Music Club");
		logo.setIcon(FontAwesome.MUSIC);
		logo.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		addComponent(logo);
		
		Button welcomeHeader=new Button("Welcome "+user);
		welcomeHeader.setIcon(FontAwesome.USER);		
		welcomeHeader.setWidthUndefined();
		welcomeHeader.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		addComponent(welcomeHeader);
		setComponentAlignment(welcomeHeader, Alignment.MIDDLE_RIGHT);
	}
	
	public HeaderLayout(String userFirstName){
		setWidth("100%");
		setSpacing(true);
		Button logo=new Button("VIT Music Club");
		logo.setIcon(FontAwesome.MUSIC);
		logo.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		addComponent(logo);
		
		Button welcomeHeader=new Button("Welcome "+userFirstName);
		welcomeHeader.setIcon(FontAwesome.USER);		
		welcomeHeader.setWidthUndefined();
		welcomeHeader.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		addComponent(welcomeHeader);
		setComponentAlignment(welcomeHeader, Alignment.MIDDLE_RIGHT);
	}
}
