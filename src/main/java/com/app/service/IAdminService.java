package com.app.service;

import java.util.List;

import com.app.pojos.Admin;
import com.app.pojos.Customer;
import com.app.pojos.DeliveryPartner;
import com.app.pojos.Orders;


public interface IAdminService {
	Admin authenticateAdmin(String email , String password);
	List<Customer> getAllCustomers();
	List<DeliveryPartner> getAllPartners();
	List<Orders>getAllOrders();
	String deleteCustomer(int cid);
	String deletePartner(int did);
	Admin findByEmail(String email);
	Admin updateAdmin(Admin admin);

}
