package by.eis.task.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class PropertyDto {
    @Min(1)
    private Long id;

    @NotBlank
    @Pattern(regexp = ".{2,512}")
    private String description;
}
