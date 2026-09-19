package com.shiptrack.shipment;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/shipments") @CrossOrigin(origins="http://localhost:5173")
public class ShipmentController {
 private final ShipmentRepository repo;
 public ShipmentController(ShipmentRepository repo){this.repo=repo;}
 @GetMapping public List<Shipment> all(){return repo.findAll();}
 @GetMapping("/{trackingNumber}") public ResponseEntity<?> track(@PathVariable String trackingNumber){return repo.findByTrackingNumber(trackingNumber).<ResponseEntity<?>>map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());}
 @PostMapping public ResponseEntity<Shipment> create(@Valid @RequestBody ShipmentRequest r){
   Shipment s=new Shipment(); s.setTrackingNumber("ST"+System.currentTimeMillis()); s.setSender(r.sender()); s.setReceiver(r.receiver()); s.setOrigin(r.origin()); s.setDestination(r.destination()); s.setCurrentLocation(r.origin()); s.setEstimatedDelivery(LocalDateTime.now().plusDays(3)); return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(s));
 }
 @PatchMapping("/{id}/status") public ResponseEntity<?> status(@PathVariable Long id,@RequestParam ShipmentStatus value){return repo.findById(id).map(s->{s.setStatus(value);return ResponseEntity.ok(repo.save(s));}).orElseGet(()->ResponseEntity.notFound().build());}
}