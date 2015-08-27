package com.sriharrsha.musicbox.schedulers;

import java.util.Date;

import org.hibernate.Session;

import com.sriharrsha.musicbox.helpers.HibernateUtil;
import com.sriharrsha.musicbox.model.Slot;

public class SlotScheduler {
	
	public SlotScheduler(){
		Session session=HibernateUtil.getSessionFactory().openSession();	     
		session.beginTransaction();
		Slot slot1=new Slot();
		slot1.setSlotId(1);
		slot1.setSlotTime("6:00 AM - 7:00 AM");
		session.save(slot1);
		
		Slot slot2=new Slot();
		slot2.setSlotId(2);
		slot2.setSlotTime("7:00 AM - 8:00 AM");
		session.save(slot2);
		
		Slot slot3=new Slot();
		slot3.setSlotId(3);
		slot3.setSlotTime("8:00 AM - 9:00 AM");
		session.save(slot3);
		
		Slot slot4=new Slot();
		slot4.setSlotId(4);
		slot4.setSlotTime("9:00 AM - 10:00 AM");
		session.save(slot4);

		Slot slot5=new Slot();
		slot5.setSlotId(5);
		slot5.setSlotTime("10:00 AM - 11:00 AM");
		session.save(slot5);
		
		Slot slot6=new Slot();
		slot6.setSlotId(6);
		slot6.setSlotTime("11:00 AM - 12:00 PM");
		session.save(slot6);
		
		Slot slot7=new Slot();
		slot7.setSlotId(7);
		slot7.setSlotTime("12:00 PM - 1:00 PM");
		session.save(slot7);
		
		Slot slot8=new Slot();
		slot8.setSlotId(8);
		slot8.setSlotTime("1:00 PM - 2:00 PM");
		session.save(slot8);
		
		Slot slot9=new Slot();
		slot9.setSlotId(9);
		slot9.setSlotTime("2:00 PM - 3:00 PM");
		session.save(slot3);
		
		Slot slot10=new Slot();
		slot10.setSlotId(10);
		slot10.setSlotTime("3:00 PM - 4:00 PM");
		session.save(slot10);
		
		Slot slot11=new Slot();
		slot11.setSlotId(11);
		slot11.setSlotTime("4:00 PM - 5:00 PM");
		session.save(slot11);
		
		Slot slot12=new Slot();
		slot12.setSlotId(12);
		slot12.setSlotTime("5:00 PM - 6:00 PM");
		session.save(slot12);
		
		Slot slot13=new Slot();
		slot13.setSlotId(13);
		slot13.setSlotTime("6:00 PM - 7:00 PM");
		session.save(slot13);
		
		Slot slot14=new Slot();
		slot14.setSlotId(14);
		slot14.setSlotTime("7:00 PM - 8:00 PM");
		session.save(slot14);
		
		Slot slot15=new Slot();
		slot15.setSlotId(15);
		slot15.setSlotTime("8:00 PM - 9:00 PM");
		session.save(slot15);
		
		Slot slot16=new Slot();
		slot16.setSlotId(16);
		slot16.setSlotTime("9:00 PM - 10:00 PM");
		session.save(slot16);
		
		Slot slot17=new Slot();
		slot17.setSlotId(17);
		slot17.setSlotTime("10:00 PM - 11:00 PM");
		session.save(slot17);
		
		Slot slot18=new Slot();
		slot18.setSlotId(18);
		slot18.setSlotTime("11:00 PM - 12:00 AM");
		session.save(slot18);
		
		session.getTransaction().commit();	
		
	}

}
