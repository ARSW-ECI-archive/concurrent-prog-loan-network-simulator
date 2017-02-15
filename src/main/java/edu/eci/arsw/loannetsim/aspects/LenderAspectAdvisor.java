/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.loannetsim.aspects;

import edu.eci.arsw.loannetsim.Lender;
import org.aspectj.lang.JoinPoint;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author hcadavid
 */
public class LenderAspectAdvisor {

    List<String> transactions=new LinkedList<>();
    
    public void intercept(JoinPoint jp){
        transactions.add(jp.getTarget().toString()+" lent to "+jp.getArgs()[0]);
        if (((Lender)jp.getArgs()[0]).getBalance()==0){
            System.out.println("Lender in bankrupt:"+(Lender)jp.getArgs()[0]+". "
                    + "Transactions performed before last bankrupt:");
            Iterator<String> it=transactions.iterator();
            
            while(it.hasNext()){
                System.out.print(it.next());
                System.out.print(",");
            }
            transactions.clear();
            
        }
        
        
        
    }

    
}
