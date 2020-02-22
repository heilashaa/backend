package com.haapp.formicary.config;

import com.haapp.formicary.api.message.BaseResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ApiResponses(
        {
                @ApiResponse(code = 400, message = "Bad request", response = BaseResponse.class),
                @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponse.class),
                @ApiResponse(code = 403, message = "Forbidden", response = BaseResponse.class),
                @ApiResponse(code = 404, message = "Not found", response = BaseResponse.class),
                @ApiResponse(code = 409, message = "Conflict", response = BaseResponse.class),
                @ApiResponse(code = 500, message = "Internal service error", response = BaseResponse.class)
        }
)
public @interface ApiService {

}
