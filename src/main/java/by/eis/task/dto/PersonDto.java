package by.eis.task.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class PersonDto {
    @Min(1)
    private Long id;

    @Email
    @NotNull
    private String email;

    private List<@Valid PropertyDto> propertyList = new ArrayList<>();
}
