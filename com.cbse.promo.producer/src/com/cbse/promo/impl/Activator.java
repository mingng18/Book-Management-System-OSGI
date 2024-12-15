package com.cbse.promo.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.cbse.promo.api.IPromoService;
import com.cbse.user.api.IUserService;

public class Activator implements BundleActivator {

	private ServiceRegistration<IPromoService> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {

		System.out.println("Starting Promo Service Bundle...");

		// Retrieve IUserService from the BundleContext
		ServiceReference<IUserService> userServiceRef = context.getServiceReference(IUserService.class);
		IUserService userService = context.getService(userServiceRef);

		IPromoService promoService = new PromoServiceImpl(userService);
		serviceRegistration = context.registerService(IPromoService.class, promoService, null);
		System.out.println("Promo Service Registered.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping Promo Service Bundle...");
		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
		System.out.println("Promo Service Unregistered.");
	}

}
