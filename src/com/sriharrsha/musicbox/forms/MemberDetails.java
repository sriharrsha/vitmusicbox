package com.sriharrsha.musicbox.forms;

import com.sriharrsha.musicbox.helpers.HibernateUtil;
import com.sriharrsha.musicbox.model.UserDetails;
import com.vaadin.data.Property;
import com.vaadin.data.hbnutil.HbnContainer;
import com.vaadin.ui.Table;

public class MemberDetails extends Table {
	public static final Object[] NATURAL_COL_ORDER = new Object[] {"regNo", "name", "gender", "phoneNumber", "emailId","type"};
	public static final String[] COL_HEADERS_ENGLISH = new String[] {"Reg No", "Name", "Gender", "Phone Number", "Email-Id","Type"};
	
	MemberDetails()
		{	
			setCaption("All Members Details:");
			setContainerDataSource(new HbnContainer<UserDetails>(UserDetails.class, HibernateUtil.getSessionFactory()));
			setVisibleColumns(NATURAL_COL_ORDER);
			setColumnHeaders(COL_HEADERS_ENGLISH);
//			this.setVisibleColumns(visibleColumns);
			}
	
	 @Override
	    protected String formatPropertyValue(Object rowId, Object colId, Property property) {
	        if ("name".equals(colId)) {
	        	 UserDetails user=(UserDetails)((HbnContainer.EntityItem.EntityProperty) property).getPojo();
				 return user.getName().getFirstName()+" "+user.getName().getLastName();   
	        }
	        if ("phoneNumber".equals(colId)) {
	        	 UserDetails user=(UserDetails)((HbnContainer.EntityItem.EntityProperty) property).getPojo();
				 return Long.toString(user.getPhoneNumber());   
	        }
	        return super.formatPropertyValue(rowId, colId, property);
	    }
}
