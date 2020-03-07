/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg;

/**
 *
 * @author Maria
 */
public class Articles {
    private String name = new String();
    private float price;
    
    public Articles(String nume, float price)
    {
        this.name = nume;
        this.price = price;
    }
    public float getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
    public void setPrice(float price){
        this.price = price;
    }
    public void setName(String name){
        this.name = name;
    }
}
