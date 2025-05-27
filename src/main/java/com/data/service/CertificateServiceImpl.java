package com.data.service;

import com.data.entity.Certificate;
import com.data.repository.CertificateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService{
    private final CertificateRepository certificateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public List<Certificate> findAll() {
        return certificateRepository.findAll();
    }
}
