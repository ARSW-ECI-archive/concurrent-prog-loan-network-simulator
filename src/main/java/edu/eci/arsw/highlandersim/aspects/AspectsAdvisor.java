/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.highlandersim.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import edu.eci.arsw.highlandersim.Lender;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author hcadavid
 */
public class AspectsAdvisor {

    ConcurrentLinkedQueue<String> transactions=new ConcurrentLinkedQueue<>();
    //List<String> transactions=new LinkedList<>();
    
    public void intercept(JoinPoint jp){
        transactions.add(jp.getTarget().toString());
        if (((Lender)jp.getArgs()[0]).getBalance()==0){
            System.out.println("bankrupt:");
            Iterator<String> it=transactions.iterator();
            
            while(it.hasNext()){
                System.out.print(it.next());
                System.out.print(",");
            }
            
        }
        
        
    }

    public void intercept2(ProceedingJoinPoint jp) throws Throwable{
        Lender im1=((Lender)jp.getTarget());
        Lender im2=((Lender)jp.getArgs()[0]);
        jp.proceed();
        System.out.println("aaaa");
        
        /*
        
                int before = im1.getBalance() + im2.getBalance();
                jp.proceed();
                int after = im1.getBalance() + im2.getBalance();
                if (before != after) {
                    System.out.println("Wrooong " + im1.toString() + "," + im2.toString());
                    System.out.println("Before:" + before + ", after:" + after);
                    System.exit(0);
                }

            */
        //}

        
        
        
        
    }

    
}
