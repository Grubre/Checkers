package com.checkers.controller;

/*
 * Interfejs kontrolera sceny
 */
public interface StageController {
    /*
     * Nadpisz ją, jeżeli twój obiekt wymaga jakiejś akcji po uruchomieniu sceny
     */
    default void activate() {}
    /*
     * Nadpisz ją, jeżeli twój obiekt wymaga jakiejś akcji przed wyjściem ze sceny
     */
    default void deactivate() {}
}
