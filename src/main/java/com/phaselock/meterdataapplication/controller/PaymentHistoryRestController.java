package com.phaselock.meterdataapplication.controller;

import com.phaselock.meterdataapplication.dto.entity.create.PaymentHistoryCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.PaymentHistoryReadDto;
import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import com.phaselock.meterdataapplication.service.PaymentHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payment_history")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class PaymentHistoryRestController {
    private final PaymentHistoryService paymentHistoryService;

    @Operation(
            summary = "Create payment history entity.",
            operationId = "createPaymentHistory",
            description = "Create payment history entity.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = PaymentHistoryReadDto.class))
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = NotFoundException.class))
                            }),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = Exception.class))
                            }
                    )
            }
    )
    @PostMapping("")
    public PaymentHistoryReadDto savePaymentHistory(@RequestBody PaymentHistoryCreateDto paymentHistoryCreateDto) {
        return paymentHistoryService.savePaymentHistory(paymentHistoryCreateDto);
    }

    @Operation(
            summary = "Find all payment history.",
            operationId = "findAllPaymentHistory",
            description = "Find all payment history.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = List.class))
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = NotFoundException.class))
                            }),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = Exception.class))
                            }
                    )
            }
    )
    @GetMapping("")
    public List<PaymentHistoryReadDto> findAllPaymentHistory() {
        return paymentHistoryService.findAll();
    }

    @Operation(
            summary = "Find payment history by id.",
            operationId = "findPaymentHistoryById",
            description = "Find payment history by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = PaymentHistoryService.class))
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = NotFoundException.class))
                            }),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = Exception.class))
                            }
                    )
            }
    )
    @GetMapping("/{id}")
    public Optional<PaymentHistoryReadDto> findPaymentHistoryById(@PathVariable("id") Integer id) {
        return paymentHistoryService.findById(id);
    }

    @Operation(
            summary = "Delete payment history by id.",
            operationId = "deletePaymentHistoryById",
            description = "Delete payment history by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = {
                                    @Content(mediaType = "application/json")
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = NotFoundException.class))
                            }),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = {
                                    @Content(mediaType = "application/json", schema =
                                    @Schema(implementation = Exception.class))
                            }
                    )
            }
    )
    @DeleteMapping("/{id}")
    public void deletePaymentHistory(@PathVariable("id") Integer id) {
        paymentHistoryService.deleteById(id);
    }
}
