/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csc311hw1;

/**
 * This class is the outline for a VideoGame object with getters and setters built in to retrieve and set data
 * 
 * @author paulk
 */
public class VideoGames {
    // Member variables of the VideoGame class
    private String title;
    private double price;
    private String esrb;
    
    /**
     * Returns the game title
     * 
     * @return Game's title
     */
    public String getTitle() 
    {
        return title;
    }

    /**
     * Sets the title of the game
     * 
     * @param title Title of the game
     */
    public void setTitle(String title) 
    {
        this.title = title;
    }

    /**
     * Returns the game price
     * 
     * @return Game's price
     */
    public double getPrice() 
    {
        return price;
    }

    /**
     * Sets the price of the game
     * 
     * @param price Price of the game
     */
    public void setPrice(double price) 
    {
        this.price = price;
    }

    /**
     * Returns the esrb of the game
     * 
     * @return Game's rating
     */
    public String getEsrb() 
    {
        return esrb;
    }

    /**
     * Sets the esrb of the game
     * 
     * @param esrb Esrb of the game
     */
    public void setEsrb(String esrb) 
    {
        this.esrb = esrb;
    }
}
