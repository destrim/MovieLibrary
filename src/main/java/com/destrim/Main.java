package com.destrim;

import com.destrim.exception.BadApikeyException;
import com.destrim.service.CommandService;

public class Main {
    public static void main(String[] args) {
        try {
            new CommandService().start();
        } catch (BadApikeyException e) {
            System.out.println(e.getMessage());
        }
    }
}
