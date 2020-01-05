package com.hairvision.booking.controller;


import com.hairvision.booking.model.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.hairvision.booking.service.BookingDataService.readBookingFromCsv;

@Slf4j
@RestController
@RequestMapping(path = "/booking")
public class BookingController {

    String customerBookingFile = "customer-booking.csv";

    @GetMapping(path = "/")
    public Booking getBookingDetails(@RequestParam String email) {

        Booking bookingInfoForCustomer = null;
        List<Booking> bookingsRecords = readBookingFromCsv(customerBookingFile);
        for (Booking booking : bookingsRecords) {
            if (booking.getCustomerEmail().equals(email)) {
                log.info("Booking for customer " + email + " found as " + booking.getBookingDateTime()
                        + " with type " + booking.getHairCutType());
                bookingInfoForCustomer = booking;
            }
        }
        return bookingInfoForCustomer;
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> setBookingDetails(@RequestBody Booking booking) {
        String retStatus = null;
        Booking getAvailableBooking = getBookingDetails(booking.getCustomerEmail());
        if (getAvailableBooking == null) {
            writeData(customerBookingFile, booking);
            retStatus =  "Booking requested accepted !";
        } else {
            retStatus = "Customer is already having a booking at " + getAvailableBooking.getBookingDateTime()
                    + "for hair cut type " + getAvailableBooking.getHairCutType();

        }
        return Collections.singletonMap("response", retStatus);
    }

    private void writeData(String customerBookingFile, Booking booking) {

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(customerBookingFile), true))) {
            String row = booking.getCustomerName() + ','
                    + booking.getCustomerEmail() + ','
                    + booking.getBookingDateTime()+ ','
                    + booking.getHairCutType();
            writer.write(row + System.getProperty("line.separator"));

            log.info("Booking Done!");

        } catch (Exception exp) {
            log.info("Could Not Save The Record!");
        }


    }
}
