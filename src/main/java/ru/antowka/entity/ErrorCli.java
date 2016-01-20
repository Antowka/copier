package ru.antowka.entity;

/**
 * Created by Anton Nik on 20.01.16.
 */
public class ErrorCli extends Error {

    public void show(){
        System.out.println("*************** Error *****************");
        System.out.println("*           " + header + "            *");
        System.out.println("*           " + title + "             *");
        System.out.println("*           " + description + "       *");
        System.out.println("***************************************");
    }
}
