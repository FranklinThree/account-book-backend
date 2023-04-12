package com.github.franklinthree.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainApplicationTests {

    @BeforeEach
    void setUp() {
        System.out.println("MainApplicationTests-----------------------------------------------");
    }
    @Test
    void contextLoads() {
        System.out.println("Hello World!");
    }

}
