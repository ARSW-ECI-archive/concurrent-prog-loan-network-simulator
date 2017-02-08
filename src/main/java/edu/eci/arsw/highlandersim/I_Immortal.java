/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.highlandersim;

import java.util.List;

/**
 *
 * @author hcadavid
 */
public interface I_Immortal extends Runnable{
    
    public void fight(I_Immortal i2);
    
    public void setImmortalsPopulation(List<I_Immortal> imsl);
    
    public void setImmortalName(String name);
    
    public int getHealth();
    
    public void changeHealth(int h);
    
}
