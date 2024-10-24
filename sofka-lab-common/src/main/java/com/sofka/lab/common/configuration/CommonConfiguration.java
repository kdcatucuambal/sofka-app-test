package com.sofka.lab.common.configuration;

import com.sofka.lab.common.exceptions.models.BusinessToHttpErrorImpl;
import com.sofka.lab.common.exceptions.models.interfaces.BusinessToHttpFacade;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Configuration
public class CommonConfiguration implements CommandLineRunner {

    @Bean
    public Map<String, String> environmentMapping() {
        return Map.ofEntries(
                Map.entry("dev", "Desarrollo"),
                Map.entry("qa", "Calidad"),
                Map.entry("prod", "Producción")
        );
    }

    @Bean
    public Map<HttpStatus, String> httpCodeToReasonMapping() {
        return Map.<HttpStatus, String>ofEntries(
                Map.entry(HttpStatus.CONTINUE, "Continuar"),
                Map.entry(HttpStatus.SWITCHING_PROTOCOLS, "Cambiando protocolos"),
                Map.entry(HttpStatus.PROCESSING, "Procesando"),
                Map.entry(HttpStatus.OK, "OK"),
                Map.entry(HttpStatus.CREATED, "Creado"),
                Map.entry(HttpStatus.ACCEPTED, "Aceptado"),
                Map.entry(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "Información no autorizada"),
                Map.entry(HttpStatus.NO_CONTENT, "El proceso es satisfactorio pero no ha devuelto nada."),
                Map.entry(HttpStatus.RESET_CONTENT, "Restablecer contenido"),
                Map.entry(HttpStatus.PARTIAL_CONTENT, "Contenido parcial"),
                Map.entry(HttpStatus.MULTI_STATUS, "Estado múltiple"),
                Map.entry(HttpStatus.ALREADY_REPORTED, "Ya informado"),
                Map.entry(HttpStatus.IM_USED, "IM usado"),
                Map.entry(HttpStatus.MULTIPLE_CHOICES, "Múltiples opciones"),
                Map.entry(HttpStatus.MOVED_PERMANENTLY, "Movido permanentemente"),
                Map.entry(HttpStatus.FOUND, "Encontrado"),
                Map.entry(HttpStatus.SEE_OTHER, "Ver otro"),
                Map.entry(HttpStatus.NOT_MODIFIED, "No modificado"),
                Map.entry(HttpStatus.TEMPORARY_REDIRECT, "Redirección temporal"),
                Map.entry(HttpStatus.PERMANENT_REDIRECT, "Redirección permanente"),
                Map.entry(HttpStatus.BAD_REQUEST, "La solicitud realizada es incorrecta."),
                Map.entry(HttpStatus.UNAUTHORIZED, "La solicitud no está autorizada."),
                Map.entry(HttpStatus.PAYMENT_REQUIRED, "Pago requerido"),
                Map.entry(HttpStatus.FORBIDDEN, "Recurso prohibido."),
                Map.entry(HttpStatus.NOT_FOUND, "Recurso no encontrado."),
                Map.entry(HttpStatus.METHOD_NOT_ALLOWED, "Método no permitido."),
                Map.entry(HttpStatus.NOT_ACCEPTABLE, "No aceptable"),
                Map.entry(HttpStatus.PROXY_AUTHENTICATION_REQUIRED, "Autenticación de proxy requerida"),
                Map.entry(HttpStatus.REQUEST_TIMEOUT, "Tiempo de espera de la solicitud"),
                Map.entry(HttpStatus.CONFLICT, "La solicitud presentó un error."),
                Map.entry(HttpStatus.GONE, "Desaparecido"),
                Map.entry(HttpStatus.LENGTH_REQUIRED, "Longitud requerida"),
                Map.entry(HttpStatus.PRECONDITION_FAILED, "Precondición fallida"),
                Map.entry(HttpStatus.PAYLOAD_TOO_LARGE, "Carga útil demasiado grande"),
                Map.entry(HttpStatus.URI_TOO_LONG, "URI demasiado larga"),
                Map.entry(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Tipo de medio no soportado"),
                Map.entry(HttpStatus.EXPECTATION_FAILED, "Expectativa fallida"),
                Map.entry(HttpStatus.I_AM_A_TEAPOT, "Soy una tetera"),
                Map.entry(HttpStatus.UNPROCESSABLE_ENTITY, "Entidad no procesable"),
                Map.entry(HttpStatus.LOCKED, "Bloqueado"),
                Map.entry(HttpStatus.FAILED_DEPENDENCY, "Dependencia fallida"),
                Map.entry(HttpStatus.TOO_EARLY, "Demasiado pronto"),
                Map.entry(HttpStatus.UPGRADE_REQUIRED, "Actualización requerida"),
                Map.entry(HttpStatus.PRECONDITION_REQUIRED, "Precondición requerida"),
                Map.entry(HttpStatus.TOO_MANY_REQUESTS, "Demasiadas solicitudes"),
                Map.entry(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE, "Campos de encabezado de solicitud demasiado grandes"),
                Map.entry(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, "No disponible por razones legales"),
                Map.entry(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor"),
                Map.entry(HttpStatus.NOT_IMPLEMENTED, "No implementado"),
                Map.entry(HttpStatus.BAD_GATEWAY, "Puerta de enlace incorrecta"),
                Map.entry(HttpStatus.SERVICE_UNAVAILABLE, "Servicio no disponible"),
                Map.entry(HttpStatus.GATEWAY_TIMEOUT, "Tiempo de espera de la puerta de enlace"),
                Map.entry(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, "Versión HTTP no soportada"),
                Map.entry(HttpStatus.VARIANT_ALSO_NEGOTIATES, "La variante también negocia"),
                Map.entry(HttpStatus.INSUFFICIENT_STORAGE, "Almacenamiento insuficiente"),
                Map.entry(HttpStatus.LOOP_DETECTED, "Bucle detectado"),
                Map.entry(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, "Límite de ancho de banda excedido"),
                Map.entry(HttpStatus.NOT_EXTENDED, "No extendido"),
                Map.entry(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, "Autenticación de red requerida")
        );
    }


    @Bean
    @ConditionalOnMissingBean
    public BusinessToHttpFacade defaultBusinessToHttpFacade() {
        return new BusinessToHttpErrorImpl();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommonConfiguration.run method executed");
    }
}
