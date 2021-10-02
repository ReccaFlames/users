package com.jozwik.empik.calculations;

import org.springframework.stereotype.Service;

@Service
public class CalculationsService {

    public float calculate(int followers, int publicRepos) {
        return (float) (6.0 / followers * (2 + publicRepos));
    }
}
