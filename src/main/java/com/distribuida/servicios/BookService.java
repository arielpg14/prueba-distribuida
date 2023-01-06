package com.distribuida.servicios;

import com.distribuida.db.Book;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;


import java.util.List;

public interface BookService {


    Book findById(Integer id);
    List<Book> findAll();


    Book crearBook(Book book);


    void borrarBook(Integer id);

    Book actualizarBook(Book book);
}
