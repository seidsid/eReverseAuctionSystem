package com.maharishi.may.ereverse.ereverseauctionsystem.organization.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Organization;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.SystemAdmin;

public interface OrganizationService {
    Organization findOrganization(long id);
    void activate(Organization organization,SystemAdmin admin);
}
