package com.sriharrsha.musicbox.layouts;

import com.sriharrsha.musicbox.MusicboxUI;
import com.sriharrsha.musicbox.forms.BookingForm;
import com.sriharrsha.musicbox.forms.SignInForm;
import com.sriharrsha.musicbox.forms.SignUpForm;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class BodyLayout extends HorizontalLayout {

	private BookingForm bookingForm;
	private SignInForm signInForm;
	private SignUpForm signUpForm;

	public BodyLayout() {
		addComponent(getSignInForm());
		setComponentAlignment(getSignInForm(), Alignment.MIDDLE_CENTER);
		getSignInForm().setWidthUndefined();
        getSignUpForm().setHeightUndefined();
        setStyleName(ValoTheme.LAYOUT_CARD);
	}

	public BookingForm getBookingForm() {
		if (bookingForm == null) {
			setBookingForm(new BookingForm(0));
			return bookingForm;
		} else
			return bookingForm;

	}

	public SignInForm getSignInForm() {
		if (signInForm == null) {
			setSignInForm(new SignInForm());
			return signInForm;
		} else
			return signInForm;
	}

	public SignUpForm getSignUpForm() {
		if (signUpForm == null) {
			setSignUpForm(new SignUpForm());
			return signUpForm;
		} else
			return signUpForm;

	}

	public void setBookingForm(BookingForm bookingForm) {

		this.bookingForm = bookingForm;
	}

	public void setSignInForm(SignInForm signInForm) {
		this.signInForm = signInForm;
	}

	public void setSignUpForm(SignUpForm signUpForm) {
		this.signUpForm = signUpForm;
	}

}
