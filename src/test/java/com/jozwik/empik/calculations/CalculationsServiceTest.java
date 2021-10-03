package com.jozwik.empik.calculations;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculationsServiceTest {
    private CalculationsService calculationsService = new CalculationsService();

    @Test
    void shouldReturnCorrectValue() {
        //given
        int followers = 6;
        int repositories = 12;
        //when
        final float result = calculationsService.calculate(followers, repositories);
        //then
        assertThat(result).isEqualTo(14f);
    }
}
