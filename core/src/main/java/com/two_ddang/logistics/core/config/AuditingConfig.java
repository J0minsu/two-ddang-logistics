package com.two_ddang.logistics.core.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditingConfig implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {

        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()
        				|| authentication.getPrincipal().equals("anonymousUser")) {
        			return Optional.empty();
        		}

        SecurityUserDetails principal = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Optional.of(principal.getSecurityUserInfo().getId());
        */

        // TODO UPDATE REAL BUSINESS LOGIC
        return Optional.of(1);

    }

}
