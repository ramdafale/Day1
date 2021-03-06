package com.bank.service;

import java.sql.Timestamp;


import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*import org.springframework.cloud.stream.annotation.EnableBinding;*/
import org.springframework.stereotype.Service;

import com.bank.Exception.ManagedException;
import com.bank.model.AuditLog;
import com.bank.model.Customer;
import com.bank.repository.CustomerRepository;
import com.configrabbit.RabbitConfig;

enum EventName {
	CUSTOMER, ACCOUNT, ATM, BANK
}

enum EventType {
	CREATED, UPDATED
}

@Service("customerService")
@Component
/*@EnableBinding(AuditRegistrationSource.class)*/
public class CustomerServiceImpl implements ICustomerService, Cloneable {
	
	
	final static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	AuditServiceImpl auditService;
	
	@Autowired
	RabbitTemplate template;

	@Override
	public Customer createCustomer(final Customer customer) throws ManagedException {
		final Customer custom = customerRepository.save(customer);
		if (custom != null) {
			return custom;
		} else {
			LOGGER.error("No customer is added");
			throw new ManagedException("no customer is created");
		}
	}
	
	@Override
	public Customer getCustomerDetails(final Long id) throws ManagedException {

		final Customer customer = customerRepository.findById(id).get();
		if (customer != null) {
			return customer;
		} else {
			LOGGER.info("No customer of given id is exist");
			throw new ManagedException("no customer of such id exist");
		}

	}

	@Override
	public Customer updateCustomer(final Long id, final String name, final String userId)
			throws ManagedException, CloneNotSupportedException {
		// TODO Auto-generated method stub
		final Customer customer = customerRepository.findById(id).get();

		Customer oldcustmer = customer.clone();

		if (customer != null) {
			customer.setCustomerName(name);
			customer.setUserId(userId);
			Customer cust = customerRepository.save(customer);
			Timestamp time = Timestamp.valueOf(LocalDateTime.now());
			AuditLog audit = new AuditLog(EventName.CUSTOMER.toString(), EventType.UPDATED.toString(), time,
					cust.getUserId(), oldcustmer, cust);

			//AuditLog ob = auditService.generateAudit(audit);
			
			
			System.out.println("hii");
			
			//template.convertAndSend(RabbitConfig.ROUTING_KEY,audit.toString());
			template.convertAndSend(RabbitConfig.ROUTING_KEY,"message from producer");
			//System.out.println("Is Listner called::"+rabbitTemplate.isReturnListener());
			
			
			
			return cust;
		} else {
			LOGGER.info("No customer is updated as id not exist");
			throw new ManagedException("no customer is updated");
		}

	}

}
