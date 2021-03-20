package by.eis.task.exception;

public class ErrorMessage {
    public static final String RESOURCE_NOT_FOUND = "Requested resource not found (id = %s)";
    public static final String ENUM_PARSE_FAILED = "Value '%s' can't be converted to enum. Enum values: %s";
    public static final String PAGE_NOT_FOUND = "Requested page not found (size = %s, page = %s). The last page is %s";
    public static final String OTHER_USERS_PROPERTY = "You can't appropriate other user's property";
    public static final String PROPERTY_STATE_CAN_T_BE_CHANGED =
            "You can't change existing properties. They are should be immutable";

    private ErrorMessage() {
    }
}
