/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordini;

/**
 *
 * @author Giulia Evangelisti
 */
class IOExeption extends Exception {
    static void ThrowOne () throws IOExeption{
        System.out.println("All'interno di ThrowOne");

}
}