package com.customer.demo.view;

import com.customer.demo.model.Customer;
import com.customer.demo.service.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import javax.inject.Inject;

@Route("")
@PWA(name = "Project demo for use Vaadin and Java EE features", shortName = "Project demo")
public class MainView extends VerticalLayout{
    private TextField filterText = new TextField();
    private Grid<Customer> grid = new Grid<>(Customer.class);

    private CustomerService service;

    @Inject
    public MainView(CustomerService service) {

        service.ensureTestData();

        CustomerForm form = new CustomerForm(this, service);

        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);

        Button addCustomerBtn = new Button("Add new customer");
        addCustomerBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCustomer(new Customer());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addCustomerBtn);

        grid.setColumns("firstName", "lastName", "status", "personalId");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        grid.setItems(service.findAll(filterText.getValue()));

        form.setCustomer(null);

        grid.asSingleSelect().addValueChangeListener(event ->
                form.setCustomer(grid.asSingleSelect().getValue()));
    }

}
