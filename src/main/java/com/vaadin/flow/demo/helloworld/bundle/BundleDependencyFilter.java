package com.vaadin.flow.demo.helloworld.bundle;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

import com.vaadin.function.DeploymentConfiguration;
import com.vaadin.server.DependencyFilter;
import com.vaadin.server.ServiceInitEvent;
import com.vaadin.server.VaadinServletService;
import com.vaadin.shared.ApplicationConstants;
import com.vaadin.shared.ui.Dependency;
import com.vaadin.shared.ui.LoadMode;

/**
 * @author Vaadin Ltd.
 */
public class BundleDependencyFilter implements DependencyFilter {
    private static final String BUNDLE_FILE = "vaadin-flow-bundle.html";

    private final List<Dependency> bundleDependencies;

    public BundleDependencyFilter(ServiceInitEvent serviceInitEvent) {
        DeploymentConfiguration deploymentConfiguration = serviceInitEvent.getSource().getDeploymentConfiguration();
        if (deploymentConfiguration.isProductionMode()) {
            String frontendDirectory = resolveFrontendDirectory(deploymentConfiguration);
            ServletContext servletContext = ((VaadinServletService) serviceInitEvent.getSource()).getServlet().getServletContext();
            if (servletContext.getResourcePaths(frontendDirectory).stream().anyMatch(path -> path.endsWith(BUNDLE_FILE))) {
                bundleDependencies = Collections.singletonList(new Dependency(Dependency.Type.HTML_IMPORT, ApplicationConstants.FRONTEND_PROTOCOL_PREFIX + BUNDLE_FILE, LoadMode.EAGER));
            } else {
                bundleDependencies = Collections.emptyList();
            }
        } else {
            bundleDependencies = Collections.emptyList();
        }
    }

    private String resolveFrontendDirectory(DeploymentConfiguration deploymentConfiguration) {
        String frontendDirectory = deploymentConfiguration.getEs6FrontendPrefix()
                // TODO kirill method for this in framework?
                // TODO kirill check es5 also?
                .replace(ApplicationConstants.FRONTEND_PROTOCOL_PREFIX, "")
                .replace(ApplicationConstants.CONTEXT_PROTOCOL_PREFIX, "")
                .replace(ApplicationConstants.BASE_PROTOCOL_PREFIX, "");
        if (!frontendDirectory.startsWith("/")) {
            frontendDirectory = '/' + frontendDirectory;
        }
        return frontendDirectory;
    }

    @Override
    public List<Dependency> filter(List<Dependency> list, FilterContext filterContext) {
        return bundleDependencies.isEmpty() ? list : bundleDependencies;
    }
}
