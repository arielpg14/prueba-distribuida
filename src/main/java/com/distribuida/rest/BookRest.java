package com.distribuida.rest;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Path("/books")
public class BookRest {

    /* * GET /books/{id}            buscar 1
       * GET/books                  listar todos
       * POST /books                insertar
       * PUT/PATCH /books{id}       actualizar
       * DELETE /books{i}           eliminar

    */

    @Inject private DbConfig dbConfig;


    @Inject private BookService bookService;
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findById(@PathParam("id") Integer id){
        dbConfig.test();
        return bookService.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book crearBook(Book book) {
        return bookService.crearBook(book);
    }

    @DELETE
    @Path("/{id}")
    public void deleteBook(@PathParam("id") Integer id){
        bookService.borrarBook(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book updateBook(@PathParam("id") Integer id, Book book){
        book.setId(id);
        return bookService.actualizarBook(book);
    }

}
