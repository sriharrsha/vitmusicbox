package com.sriharrsha.musicbox;

import javax.servlet.annotation.WebServlet;

import com.sriharrsha.musicbox.layouts.PageLayout;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("musicbox")
public class MusicboxUI extends UI {
	

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MusicboxUI.class, widgetset = "com.sriharrsha.musicbox.widgetset.MusicboxWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		pageLayout = getPageLayout();
		setContent(pageLayout);
		
	}

	private PageLayout pageLayout;

	public PageLayout getPageLayout() {
		if(pageLayout==null)
			pageLayout=setPageLayout(new PageLayout());
		else
			return pageLayout;
		return pageLayout;
	}

	private PageLayout setPageLayout(PageLayout pageLayout) {
		return pageLayout;
	}

	public static MusicboxUI getCurrent() {
		return (MusicboxUI) UI.getCurrent();
	}

}