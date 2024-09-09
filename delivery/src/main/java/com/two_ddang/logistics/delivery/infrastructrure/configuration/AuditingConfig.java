package com.two_ddang.logistics.delivery.infrastructrure.configuration;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditingConfig implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {

        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()
        				|| authentication.getPrincipal().equals("anonymousUser")) {
        			return Optional.empty();
        		}

        SecurityUserDetails principal = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();*/

        return Optional.of(null);
    }

}
