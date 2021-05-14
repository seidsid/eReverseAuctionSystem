package com.maharishi.may.ereverse.ereverseauctionsystem.organization.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Organization;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.SystemAdmin;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImp implements OrganizationService{
    private OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationServiceImp(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization findOrganization(long id) {
        return organizationRepository.findById(id).orElse(null);
    }

    @Override
    public void activate(Organization organization, SystemAdmin admin) {
        organization=organizationRepository.findById(organization.getId()).orElse(null);
        organization.setSysAdmin(admin);
        organizationRepository.save(organization);
    }
}
