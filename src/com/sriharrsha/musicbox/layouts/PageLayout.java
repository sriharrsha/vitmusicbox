package com.sriharrsha.musicbox.layouts;

import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.vaadin.annotations.Theme;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

@Theme("musicbox")
public class PageLayout extends VerticalLayout{
	
		private HeaderLayout headerLayout;
	    private BodyLayout bodyLayout;
	    private FooterLayout footerLayout;

	    public PageLayout() {
	    	setHeight("100%");
	        setMargin(true);
	        addComponent(getHeaderLayout());
	        addComponent(getBodyLayout());
	        addComponent(getFooterLayout());
	        setExpandRatio(getBodyLayout(), 1);
	        setComponentAlignment(footerLayout, Alignment.BOTTOM_RIGHT);
	        
	    }

	    public HeaderLayout getHeaderLayout() {
	    	if(headerLayout==null){
				setHeaderLayout(new HeaderLayout());
				 return headerLayout;
				 }
			else
				return headerLayout;
	    }

	
		public FooterLayout getFooterLayout() {
	    	if(footerLayout==null){
				setFooterLayout(new FooterLayout());
				 return footerLayout;
	    	}
			else
				return footerLayout;
	       
	    }

		public BodyLayout getBodyLayout() {	
			if(bodyLayout==null){
				setBodyLayout(new BodyLayout());
			return bodyLayout;
			}
			else
				return bodyLayout;
			
		}

		public void setHeaderLayout(HeaderLayout headerLayout) {
			this.headerLayout = headerLayout;
		}

		public void setBodyLayout(BodyLayout bodyLayout) {
			this.bodyLayout = bodyLayout;
	        this.bodyLayout.setSizeFull();
		}

		public void setFooterLayout(FooterLayout footerLayout) {
			this.footerLayout = footerLayout;
		}

}