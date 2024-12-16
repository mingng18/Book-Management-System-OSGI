package com.cbse.analysis.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.cbse.analysis.api.IAnalysisService;
import com.cbse.book.api.IBookService;
import com.cbse.user.api.IUserService;

public class Activator implements BundleActivator {

	private ServiceRegistration<IAnalysisService> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {

		System.out.println("Starting Analysis Service Bundle...");

		// Retrieve IUserService from the BundleContext
		ServiceReference<IUserService> userServiceRef = context.getServiceReference(IUserService.class);
		IUserService userService = context.getService(userServiceRef);

		ServiceReference<IBookService> bookServiceRef = context.getServiceReference(IBookService.class);
		IBookService bookService = context.getService(bookServiceRef);

		IAnalysisService analysisService = new AnalysisServiceImpl(bookService, userService);
		serviceRegistration = context.registerService(IAnalysisService.class, analysisService, null);
		System.out.println("Analysis Service Registered.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping Analysis Service Bundle...");
		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
		System.out.println("Analysis Service Unregistered.");
	}

}
