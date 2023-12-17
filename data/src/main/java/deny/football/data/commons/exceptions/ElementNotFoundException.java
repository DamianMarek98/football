package deny.football.data.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(Class<?> clazz, Object id) {
        super("Element of type " + clazz.getName() + " with id: " + id + " was not found");
    }
}
