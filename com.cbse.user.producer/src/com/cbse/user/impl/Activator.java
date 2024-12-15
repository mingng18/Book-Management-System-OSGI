package com.cbse.user.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.cbse.user.api.IUserService;

public class Activator implements BundleActivator {

	private ServiceRegistration<IUserService> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Starting User Service Bundle...");
		IUserService userService = new UserServiceImpl();
		serviceRegistration = context.registerService(IUserService.class, userService, null);
		System.out.println("User Service Registered.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping User Service Bundle...");
		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
		System.out.println("User Service Unregistered.");
	}

}
