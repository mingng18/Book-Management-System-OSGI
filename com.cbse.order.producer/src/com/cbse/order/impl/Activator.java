package com.cbse.order.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.cbse.order.api.IOrderService;
import com.cbse.promo.api.IPromoService;
import com.cbse.user.api.IUserService;

public class Activator implements BundleActivator {

	private ServiceRegistration<IOrderService> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {

		System.out.println("Starting Order Service Bundle...");

		// Retrieve IUserService from the BundleContext
		ServiceReference<IUserService> userServiceRef = context.getServiceReference(IUserService.class);
		IUserService userService = context.getService(userServiceRef);

		ServiceReference<IPromoService> promoServiceRef = context.getServiceReference(IPromoService.class);
		IPromoService promoService = context.getService(promoServiceRef);

		IOrderService orderService = new OrderServiceImpl(userService.getCurrentUser().getId(), promoService);
		serviceRegistration = context.registerService(IOrderService.class, orderService, null);
		System.out.println("Order Service Registered.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping Order Service Bundle...");
		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
		System.out.println("Order Service Unregistered.");
	}
}
