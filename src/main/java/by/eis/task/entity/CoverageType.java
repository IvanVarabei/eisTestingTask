package by.eis.task.entity;


import by.eis.task.exception.EnumValidationException;
import by.eis.task.exception.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum CoverageType {
    COMPREHENSIVE("similar to Russian “avtocasco”"),
    COLLISION("similar to Russian “avtograzdanka”");

    private final String name;
    private final String description;

    CoverageType(String description) {
        this.description = description;
        name = super.name();
    }

    @JsonCreator
    public static CoverageType create(String passedValue) {
        for (CoverageType currentValue : values()) {
            if (passedValue.equalsIgnoreCase(currentValue.name)) {
                return currentValue;
            }
        }
        throw new EnumValidationException(String.format(
                ErrorMessage.ENUM_PARSE_FAILED, passedValue, Arrays.toString(values())));
    }
}
