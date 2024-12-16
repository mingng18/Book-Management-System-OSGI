package com.cbse.book.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.cbse.book.api.IBookService;
import com.cbse.user.api.IUserService;

public class Activator implements BundleActivator {

	private ServiceRegistration<IBookService> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Starting Book Service Bundle...");

		ServiceReference<IUserService> userServiceRef = context.getServiceReference(IUserService.class);
		IUserService userService = context.getService(userServiceRef);

		IBookService bookService = new BookServiceImpl(userService);
		serviceRegistration = context.registerService(IBookService.class, bookService, null);
		System.out.println("Book Service Registered.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping Book Service Bundle...");
		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
		System.out.println("Book Service Unregistered.");
	}

}
