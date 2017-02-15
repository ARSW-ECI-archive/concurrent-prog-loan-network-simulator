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
public interface Lender extends Runnable{
    
    public void lend(Lender i2);
    
    public void setLoanNetworkPopulation(List<Lender> imsl);
    
    public void setLenderName(String name);
    
    public int getBalance();
    
    public void changeBalance(int h);
    
}
