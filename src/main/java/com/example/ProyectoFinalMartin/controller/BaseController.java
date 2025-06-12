package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.model.Base;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface BaseController<E extends Base, Id extends Serializable> {
    public ResponseEntity<?> getAll();
    public ResponseEntity<?> getOne(@PathVariable Id id);
    public ResponseEntity<?> save(@RequestBody E entity);
    public ResponseEntity<?> update(@PathVariable Id id, @RequestBody E entity);
    public ResponseEntity<?> delete(@PathVariable Id id);
}
