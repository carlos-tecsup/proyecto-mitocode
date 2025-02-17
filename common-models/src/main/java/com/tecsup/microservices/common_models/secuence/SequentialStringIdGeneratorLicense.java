package com.tecsup.microservices.common_models.secuence;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SequentialStringIdGeneratorLicense implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        char firstChar = (char) ('A' + new Random().nextInt(26));

        String numericPart = IntStream.range(0, 8)
                .mapToObj(i -> String.valueOf(new Random().nextInt(10)))
                .collect(Collectors.joining());

        // Combinar letra y parte numérica
        return firstChar + numericPart;

    }
}
