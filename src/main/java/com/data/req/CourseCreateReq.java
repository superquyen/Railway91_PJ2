package com.data.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseCreateReq {
    @NotBlank(message = "không được để trống tên khoá hoc")
    private String courseName;
    @Min(value = 1, message = "số buổi phải lớn hơn 1")
    private int soBuoi;
    @Min(value = 1, message = "Số giờ phải lớn hơn 1")
    private int soGio;

}
