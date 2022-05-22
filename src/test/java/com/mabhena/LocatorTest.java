/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mabhena;

import com.mabhena.Locator.Exchange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Treasure S. Mabhena <treasure.mabhenaATgmail.com>
 */
public class LocatorTest {
    private Locator instance;
    
    //Note that these are active police telephone numbers
    private final String bulawayoTelephone = "0292560029";
    private final String luveveTelephone = "0292520803";
    private final String bulawayoMobile = " 0776097122";
    
    @BeforeEach
    void initialiseInstance(){
        this.instance = new Locator();
    }
    
    @Test
    void locateTelephoneNumber_whenSuppliedWithValidNumber_returnsLocation(){
        String location = instance.locateTelephoneNumber(bulawayoTelephone);
        assertEquals("Bulawayo", location);
    }
    
    @Test
    void locateTelephoneNumber_whenSuppliedWithInvalidNumber_returnsNull(){
        assertNull(
                instance.locateTelephoneNumber(bulawayoMobile)
        );
    }
    
    @Test
    void locateTelephoneNumberExchange_whenSuppliedWithValid_returnsExchange(){
        Exchange exchange = instance.locateTelephoneNumberExchange(luveveTelephone);
        assertEquals("Bulawayo", exchange.getGeneralArea());
        assertEquals("Luveve", exchange.getExchangeName());
    }
    
    @Test
    void locateTelephoneNumberExchange_whenSuppliedWithInvalidNumber_returnsNull(){
        assertNull(
                instance.locateTelephoneNumberExchange(bulawayoMobile)
        );
    }
    
    @Test
    void isValidTelephoneNumber_whenPhoneNumberValid_returnsTrue(){
        assertTrue(
                instance.isValidTelephoneNumber(bulawayoTelephone)
        );
    }
    
    @Test
    void isValidTelephoneNumber_whenPhoneNumberInvalid_returnsFalse(){
        assertFalse(
                instance.isValidTelephoneNumber(bulawayoMobile)
        );
    }
}
