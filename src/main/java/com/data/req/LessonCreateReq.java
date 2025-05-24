package com.data.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LessonCreateReq {
    @NotBlank(message = "Không được để trống tên môn học")
    private String lessonName;
    @Min(value = 1,message = "số giờ học phải lớn hơn 1")
    private int soGio;
    private String moTa;
    private int courseId;
}
