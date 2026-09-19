package com.shiptrack.shipment;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="shipments")
public class Shipment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String trackingNumber;
 @Column(nullable=false) private String sender;
 @Column(nullable=false) private String receiver;
 @Column(nullable=false) private String origin;
 @Column(nullable=false) private String destination;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ShipmentStatus status=ShipmentStatus.CREATED;
 private String currentLocation;
 private LocalDateTime estimatedDelivery;
 private LocalDateTime createdAt=LocalDateTime.now();
 public Shipment(){}
 public Long getId(){return id;} public String getTrackingNumber(){return trackingNumber;} public void setTrackingNumber(String v){trackingNumber=v;}
 public String getSender(){return sender;} public void setSender(String v){sender=v;} public String getReceiver(){return receiver;} public void setReceiver(String v){receiver=v;}
 public String getOrigin(){return origin;} public void setOrigin(String v){origin=v;} public String getDestination(){return destination;} public void setDestination(String v){destination=v;}
 public ShipmentStatus getStatus(){return status;} public void setStatus(ShipmentStatus v){status=v;} public String getCurrentLocation(){return currentLocation;} public void setCurrentLocation(String v){currentLocation=v;}
 public LocalDateTime getEstimatedDelivery(){return estimatedDelivery;} public void setEstimatedDelivery(LocalDateTime v){estimatedDelivery=v;} public LocalDateTime getCreatedAt(){return createdAt;}
}