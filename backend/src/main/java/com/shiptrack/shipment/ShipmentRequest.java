package com.shiptrack.shipment;
import jakarta.validation.constraints.NotBlank;
public record ShipmentRequest(@NotBlank String sender,@NotBlank String receiver,@NotBlank String origin,@NotBlank String destination) {}