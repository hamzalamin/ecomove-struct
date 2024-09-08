package com.wora.exceptions;

public class PartnerNotFoundException extends RuntimeException {
    public PartnerNotFoundException(String id) {
        super("partner with id " + id + "not found");
    }
}
