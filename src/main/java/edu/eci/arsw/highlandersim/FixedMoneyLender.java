package edu.eci.arsw.highlandersim;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FixedMoneyLender implements Lender {

    private int balance;

    private static final int DEFAULT_MONEY_AMOUNT = 10;

    private List<Lender> loanNetworkPopulation = null;

    private String name;

    @Override
    public void setLenderName(String name) {
        this.name = name;
    }

    private final Random r = new Random(System.currentTimeMillis());

    private static boolean pause = false;

    public static void pause() {
        pause = true;
    }

    public static void resume() {
        pause = false;
    }

    public FixedMoneyLender() {
        this.balance = 100;
        this.name = "NN";
    }

    @Override
    public void run() {

        while (true) {
            
            if (pause){
                synchronized(loanNetworkPopulation){
                    try {
                        loanNetworkPopulation.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FixedMoneyLender.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                
            }

            Lender im;

            int myIndex = loanNetworkPopulation.indexOf(this);

            int nextFighterIndex = r.nextInt(loanNetworkPopulation.size());

            //avoid self-loan
            if (nextFighterIndex == myIndex) {
                nextFighterIndex = ((nextFighterIndex + 1) % loanNetworkPopulation.size());
            }

            im = loanNetworkPopulation.get(nextFighterIndex);

            im.lend(this);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void lend(Lender i2) {

        if (i2.getBalance() > 0) {
            i2.changeBalance(i2.getBalance() - DEFAULT_MONEY_AMOUNT);
            this.balance += DEFAULT_MONEY_AMOUNT;
            System.out.println("Fight: " + this + " vs " + i2);
        } else {
            System.out.println(this + " says:" + i2 + " is already in bankrupt!");
        }

    }

    @Override
    public void setLoanNetworkPopulation(List<Lender> population) {
        this.loanNetworkPopulation = population;
    }

    @Override
    public void changeBalance(int v) {
        balance = v;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {

        return name + "[" + balance + "]";
    }

}
