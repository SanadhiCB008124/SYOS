package org.example;

public class CustomerFactory {
    public static Customer createCustomer(String type) {
        switch (type.toLowerCase()) {
            case "regular":
                return new RegularCustomer();
            case "loyalty":
                return new LoyaltyCustomer();
            default:
                throw new IllegalArgumentException("Invalid customer type");
        }
    }
}
