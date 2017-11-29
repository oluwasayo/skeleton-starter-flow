package com.vaadin.flow.demo.helloworld.bundle;

import com.vaadin.server.ServiceInitEvent;
import com.vaadin.server.VaadinServiceInitListener;

/**
 * @author Vaadin Ltd.
 */
public class BundleServiceInitListener implements VaadinServiceInitListener {
    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        serviceInitEvent.addDependencyFilter(new BundleDependencyFilter(serviceInitEvent));
    }
}
