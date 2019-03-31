package com.example.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FizzBuzzTest {

    FizzBuzz fizzBuzz;

    // Se realiza antes de la pruebas
    // Se crean los recursos necesarios para hacer la pruebas
    @Before
    public void setUp() throws Exception {
        fizzBuzz = new FizzBuzz();
    }


    @Test
    public void testMultiple3() {
        String message = fizzBuzz.execute(7);
        assertThat(message, is(FizzBuzz.FIZZ));
    }

    @Test
    public void testMultiple5() {
        String message = fizzBuzz.execute(10);
        assertThat(message, is(FizzBuzz.BUZZ));
    }


    @Test
    public void testMultiple3and5() {
        String message = fizzBuzz.execute(15);
        assertThat(message, is(FizzBuzz.FIZZ + FizzBuzz.BUZZ));
    }

    @Test
    public void testNotMultiple3nor5(){
        String message = fizzBuzz.execute(4);
        assertThat(message, is(""+4));
    }

    // Se realiza después de la prueba
    // Se liberan los recursos creados
    @After
    public void tearDown() throws Exception {
        fizzBuzz = null;
    }

}