/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.highlandersim;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author hcadavid
 */
public class LoanNetworkSimulation {

    public static void main(String args[]){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml"); 

        List<Lender> immortals = setupInmortals(20, ac);

        if (immortals != null) {
            for (Lender im : immortals) {
                new Thread(im).start();
            }
        }

        

        
    }


    public static List<Lender> setupInmortals(int ni, ApplicationContext ac) {

        List<Lender> il = new LinkedList<>();

        for (int i = 0; i < ni; i++) {

            Lender i1 = ac.getBean(Lender.class);
            i1.setLoanNetworkPopulation(il);
            i1.setLenderName("Immortal #" + i);
            il.add(i1);
        }
        return il;

    }
    
    
}
