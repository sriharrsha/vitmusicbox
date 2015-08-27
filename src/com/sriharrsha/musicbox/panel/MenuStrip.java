package com.sriharrsha.musicbox.panel;

import com.sriharrsha.musicbox.forms.BookingForm;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class MenuStrip extends VerticalLayout {
		public MenuStrip(){
		Button bookingFormLink=new Button("Booking Page");
		Button recordLink=new Button("Practice Record");
		addComponents(bookingFormLink,recordLink);
		bookingFormLink.setEnabled(true);
		}
}