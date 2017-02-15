/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.highlandersim;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author hcadavid
 */
public class LoanNetworkSimulation {

    public static void main(String args[]) throws InterruptedException, IOException{
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml"); 

        int balancesSum=0;
        
        List<Lender> lenders = setupLoanNetwork(20, ac);

        if (lenders != null) {
            for (Lender im : lenders) {
                new Thread(im).start();
            }
        }

        while (true){
            Thread.sleep(10000);

            FixedMoneyLender.pause();

            System.out.println("*** PRESS ENTER TO VIEW STATISTICS ***");

            System.in.read();

            for (Lender ln : lenders) {
                balancesSum += ln.getBalance();
            }

            System.out.println("Sum of balances:" + balancesSum);

            System.out.println("Press enter to continue simulation or Ctrl+C to abort...");

            System.in.read();

            FixedMoneyLender.resume();
            synchronized (lenders) {
                lenders.notifyAll();
            }

        }
        
        

        
    }


    public static List<Lender> setupLoanNetwork(int ni, ApplicationContext ac) {

        List<Lender> il = new LinkedList<>();

        for (int i = 0; i < ni; i++) {

            Lender i1 = ac.getBean(Lender.class);
            i1.setLoanNetworkPopulation(il);
            i1.setLenderName("Lender #" + i);
            il.add(i1);
        }
        return il;

    }
    
    
}
