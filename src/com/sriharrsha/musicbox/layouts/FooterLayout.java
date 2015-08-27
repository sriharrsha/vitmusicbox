package com.sriharrsha.musicbox.layouts;

import org.eclipse.jetty.servlets.WelcomeFilter;
import org.vaadin.teemu.VaadinIcons;

import com.vaadin.annotations.Theme;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.themes.ValoTheme;

@Theme("musicbox")
public class FooterLayout  extends HorizontalLayout{
	Link copyright;
	Link  developer;
	
	public FooterLayout(){
		 setWidth("100%");
		 setSpacing(true);
		 copyright=new Link("VIT University",new ExternalResource("http://www.vit.ac.in"));
		 copyright.setIcon(VaadinIcons.COPYRIGHT);
		 developer=new Link("Developer: Sri Harrsha",new ExternalResource("http://www.facebook.com/sri.harrsha"));    
		 developer.setIcon(FontAwesome.FACEBOOK_SQUARE);
		 addComponent(copyright);
		 addComponent(developer);
		 setComponentAlignment(copyright, Alignment.MIDDLE_LEFT);
		 setComponentAlignment(developer, Alignment.MIDDLE_RIGHT);
	
	}
	 
	
}
