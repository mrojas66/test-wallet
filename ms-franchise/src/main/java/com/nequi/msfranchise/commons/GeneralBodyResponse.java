package com.nequi.msfranchise.commons;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Respuesta general del API")
public class GeneralBodyResponse<T> {
    @Schema(description = "Mensaje de la respuesta")
    private String message;

    @Schema(description = "Contenido de la respuesta")
    private T data;
}
