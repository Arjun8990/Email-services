
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;




public class UtilMethod {
    public static String getPath() {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build().toUri();
        return location.getPath();
    }

    public static String getPath(Long id) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id).toUri();
        return location.getPath();
    }

    public static APIResponse apiResponse(
            Long id,
            int statusCode,
            String status,
            String message) {
        return APIResponse.builder()
                .statusCode(statusCode)
                .status(status)
                .id(id)
                .path(getPath())
                .message(message)
                .timestamp(Instant.now().toString())
                .build();
    }

}
