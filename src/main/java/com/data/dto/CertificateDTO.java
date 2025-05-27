package com.data.dto;

import com.data.entity.Certificate;
import lombok.Data;

@Data
public class CertificateDTO {
    private int id;
    private String name;
    private int courseCount;

    public CertificateDTO(Certificate c) {
        this.id = c.getId();
        this.name = c.getName();
        this.courseCount = (c.getCourses() == null) ? 0 : c.getCourses().size();
    }
}

