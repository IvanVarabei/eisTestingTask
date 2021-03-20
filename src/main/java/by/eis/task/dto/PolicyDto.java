package by.eis.task.dto;

import by.eis.task.entity.CoverageType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class PolicyDto {
    @Min(1)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @NotNull
    @Positive
    @Max(1_000)
    private Integer duration;

    @NotNull
    @Valid
    private PropertyDto property;

    private CoverageType coverageType;
}
