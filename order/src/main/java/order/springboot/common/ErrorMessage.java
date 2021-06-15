package order.springboot.common;

import com.alethio.service.common.ErrorType;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorMessage {

    private ErrorType errorType;
    private String dispatcherPath;
    private String details;

    public static ResponseEntity of(ErrorType errorType, WebRequest request, String details){
        ErrorMessage messageBody = ErrorMessage.builder()
                .errorType(errorType)
                .dispatcherPath(((ServletWebRequest) request).getRequest().getRequestURI())
                .details(details)
                .build();

        return new ResponseEntity(
                messageBody,
                HttpStatus.valueOf(errorType.getHttpStatus())
        );
    }

}
