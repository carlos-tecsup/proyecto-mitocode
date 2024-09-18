package com.tecsup.microservices.authentication_server_jwt.secuence;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class SequentialStringIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        // Obtén el siguiente valor de la secuencia
        Long nextId = ((Number) session.createNativeQuery("SELECT nextval('user_id_seq')").getSingleResult()).longValue();
        // Convierte el valor a String y lo devuelve
        return nextId.toString();
    }
}
