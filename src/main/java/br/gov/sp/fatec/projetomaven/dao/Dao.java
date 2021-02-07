package br.gov.sp.fatec.projetomaven.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();

    T getOne(Long id);

    void delete(Long id);
}
