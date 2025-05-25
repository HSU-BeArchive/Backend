package com.likelion.moamoa.domain.reference.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class SaveReferenceReq {

    @NotBlank(message = "사진 이름은 필수값입니다.")
    private String name;

    @NotBlank(message = "사진 설명은 필수값입니다.")
    private String description;

    @NotNull(message = "사진은 필수입니다.")
    private MultipartFile img;

    /*
        | 어노테이션   | null 허용 | "" 허용 | " " 허용 |
        | ----------- | --------- | ------- | -------- |
        | `@NotNull`  | ❌       | ✅     | ✅       |
        | `@NotEmpty` | ❌       | ❌     | ✅       |
        | `@NotBlank` | ❌       | ❌     | ❌       |
    */
}
