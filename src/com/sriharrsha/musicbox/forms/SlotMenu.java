package com.sriharrsha.musicbox.forms;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.vaadin.hene.popupbutton.PopupButton;

import com.sriharrsha.musicbox.MusicboxUI;
import com.sriharrsha.musicbox.helpers.HibernateUtil;
import com.sriharrsha.musicbox.layouts.BodyLayout;
import com.sriharrsha.musicbox.model.Slot;
import com.sriharrsha.musicbox.schedulers.SlotScheduler;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class SlotMenu extends GridLayout{
	
	SlotMenu(){
		this.setMargin(true);
		this.setColumns(7);
		this.setRows(7);
		this.setSpacing(true);
		this.setWidth(null);
		this.removeAllComponents();
//     	SlotScheduler slotScheduler=new SlotScheduler();
		Session session=HibernateUtil.getSessionFactory().openSession();	
		session.beginTransaction();
		Query slotQuery=session.createQuery("from Slot as sl where not (sl.slotId) in (select booking.slotId from Bookings as booking)");
		List<Slot> slotsAvailable=slotQuery.list();
		Notification.show("Total Slots Available are "+slotsAvailable.size());
		
		Iterator<Slot> iterator = slotsAvailable.iterator();

			for(int i=1;i<this.getColumns();i++) {
				for(int j=1;j<this.getRows();j++) {
				if(iterator.hasNext()) {
					Slot slot = (Slot) iterator.next();
					if(slot.getSlotId()<11) {
						addComponent(createSoloCard(slot),j,i);
					}else {
						addComponent(createGroupCard(slot),j,i);
					}
				}
			}
		}
		session.getTransaction().commit();	
	}	
	
	
	private VerticalLayout createSoloCard(Slot slot) {
		Button time=new Button(slot.getSlotTime());
		time.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		Button formButton=new Button("Book");
		formButton.setIcon(FontAwesome.USER);
		formButton.setWidth("100%");
		formButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		BodyLayout body=MusicboxUI.getCurrent().getPageLayout().getBodyLayout();
		final BookingForm form=new BookingForm("Solo",slot.getSlotId());
		formButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Window formWindow=new Window();
				formWindow.setModal(true);
				formWindow.setContent(form);
				formWindow.setDraggable(true);
				formWindow.setPosition(10, 10);
				MusicboxUI.getCurrent().addWindow(formWindow);	
				}
			});
			VerticalLayout slotCard=new VerticalLayout();
			slotCard.setWidth("100%");
			slotCard.setStyleName(ValoTheme.LAYOUT_CARD);
			slotCard.addComponent(time);
			slotCard.addComponent(formButton);
			slotCard.setComponentAlignment(formButton, Alignment.BOTTOM_CENTER);
			return slotCard;
	}
	
	private VerticalLayout createGroupCard(Slot slot) {
		Button time=new Button(slot.getSlotTime());
		time.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		Button formButton=new Button("Book");
		formButton.setIcon(FontAwesome.USERS);
		formButton.setWidth("100%");
		formButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
		BodyLayout body=MusicboxUI.getCurrent().getPageLayout().getBodyLayout();
		final BookingForm form=new BookingForm(slot.getSlotId());
		formButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Window formWindow=new Window();
				formWindow.setModal(true);
				formWindow.setContent(form);
				formWindow.setDraggable(true);
				formWindow.setPosition(10, 10);
				MusicboxUI.getCurrent().addWindow(formWindow);	
				}
			});
			VerticalLayout slotCard=new VerticalLayout();
			slotCard.setWidth("100%");
			slotCard.setStyleName(ValoTheme.LAYOUT_CARD);
			slotCard.addComponent(time);
			slotCard.addComponent(formButton);
			slotCard.setComponentAlignment(formButton, Alignment.BOTTOM_CENTER);
			return slotCard;
	}
}






