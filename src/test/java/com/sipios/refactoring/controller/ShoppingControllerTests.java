package com.sipios.refactoring.controller;

import com.sipios.refactoring.UnitTest;
import com.sipios.refactoring.model.Body;
import com.sipios.refactoring.model.CustomerType;
import com.sipios.refactoring.model.Item;
import com.sipios.refactoring.service.ShoppingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ShoppingControllerTests extends UnitTest {

    @InjectMocks
    private ShoppingController controller;

    @Mock
    private ShoppingService shoppingService;

    @Test
    void should_not_throw() {
        Assertions.assertDoesNotThrow(
            () -> controller.getPrice(new Body(new Item[] {}, CustomerType.STANDARD_CUSTOMER))
        );
    }
}
