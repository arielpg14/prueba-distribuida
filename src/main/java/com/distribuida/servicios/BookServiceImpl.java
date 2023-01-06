package com.distribuida.servicios;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
@ApplicationScoped
public class BookServiceImpl implements BookService {
    @Inject private DbConfig dbConfig;
    @Inject private DbConfig jdbi;
    private List<Book> lista = new ArrayList<>();

    public Book findById(Integer id) {
        Book book = new Book();
       /* ret.setId(id);
        ret.setIsbn("isbn"+id);
        ret.setTitle("title"+id);
        ret.setAuthor("author"+id);
        ret.setPrice(id*30.24);
        return ret;
        for (Book book: lista){
            if (book.getId()== id){
                return book;
            }
        }*/
        try  {
            Handle handle = dbConfig.conexion();
            book = handle.createQuery("SELECT * FROM books WHERE id =" + id).mapToBean(Book.class).one();
            handle.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> findAll() {
        /*Handle handle = jdbi.open();
            try (Query query = handle.createQuery("SELECT * from books")) {
                List<Book>
            }
        return lista; */
        List<Book> lista = null;
        try {
            Handle handle = dbConfig.conexion();
            lista = handle.createQuery("SELECT * FROM books").mapToBean(Book.class).list();
            handle.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;

    }

    /*public Book crearBook(Book book) {

        //lista.add(book);

        try {
            Handle h = dbConfig.conexion();
            h.execute("INSERT INTO books(isbn, title, author, price) VALUES (?, ?, ?, ?)", book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPrice());
            h.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }*/

    public Book crearBook(Book book) {

        //lista.add(book);

        try {
            Handle handle = dbConfig.conexion();
            //h.execute("INSERT INTO books(isbn, title, author, price) VALUES (?, ?, ?, ?)", book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPrice());
            handle.createUpdate("INSERT INTO books (isbn, title, author, price) VALUES (:isbn, :title, :author, :price)").bind("isbn", book.getIsbn()).bind("title", book.getTitle()).bind("author", book.getAuthor()).bind("price", book.getPrice()).execute();
            handle.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }


    public void borrarBook(Integer id){

        /*for (int i=0;i<=lista.size();i++){
            if (lista.get(i).getId() == id){
                lista.remove(i);
            }
        }*/
        try {
            Handle handle = dbConfig.conexion();
            handle.createUpdate("DELETE FROM books where id = :id").bind("id",id).execute();
            handle.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /*private int posicion (Book book){
        for (int i=0; i<lista.size();i++){
            if (lista.get(i).getId()==book.getId()){
                return i;
            }
        }
        return -1;
    }*/

    public Book actualizarBook (Book book){
       /* int aux = posicion(book);
        try {
            lista.set(aux, book);
        } catch (IndexOutOfBoundsException ioobe) {
            return null;
        }
        return book; */
        try {
            Handle handle = dbConfig.conexion();
            handle.createUpdate("UPDATE books SET isbn = :isbn, title = :title, author =:author, price = :price WHERE id = :id").bind("isbn", book.getIsbn()).bind("title",book.getTitle()).bind("author", book.getAuthor()).bind("price", book.getPrice()).bind("id",book.getId()).execute();
            handle.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return book;

    }


}
