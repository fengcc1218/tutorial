package com.example.myapplication;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class CustomerForm extends FormLayout {

	private TextField firstName = new TextField("firstName");
	private TextField lastName = new TextField("lastName");
	private TextField email = new TextField("email");
	private NativeSelect status = new NativeSelect();
	private PopupDateField birthdate = new PopupDateField("Birthdate");
	private Button save = new Button("Save");
	private Button delete = new Button("Delete");

	private CustomerService service = CustomerService.getInstance();
	private Customer customer;
	private MyUI myUI;

	public CustomerForm(MyUI myUI) {
		this.myUI = myUI;
		status.addItems(CustomerStatus.values());

		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);

		save.addClickListener(e -> save());
		delete.addClickListener(e -> delete());
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, delete);
		buttons.setSpacing(true);

		addComponents(firstName, lastName, email, status, birthdate, buttons);

	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		BeanFieldGroup.bindFieldsBuffered(customer, this);
		delete.setVisible(customer.isPersisted());
		setVisible(true);
		firstName.selectAll();

	}

	private void save() {
service.save(customer);
myUI.updatelist();
setVisible(false);
	}

	private void delete() {
		service.delete(customer);
		myUI.updatelist();
		setVisible(false);

	}

}
