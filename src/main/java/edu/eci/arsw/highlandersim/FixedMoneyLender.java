package edu.eci.arsw.highlandersim;

import java.util.List;
import java.util.Random;

public class FixedMoneyLender implements Lender {

    private int balance;

    private static final int DEFAULT_MONEY_AMOUNT = 10;

    private List<Lender> LoanNetworkPopulation = null;

    private String name;

    @Override
    public void setLenderName(String immortalName) {
        this.name = immortalName;
    }

    private final Random r = new Random(System.currentTimeMillis());

    boolean pause = false;

    public void pause() {
        pause = true;
    }

    public void cont() {
        pause = false;
    }

    public FixedMoneyLender() {
        this.balance = 100;
        this.name = "NN";
    }

    @Override
    public void run() {

        while (true) {

            Lender im;

            int myIndex = LoanNetworkPopulation.indexOf(this);

            int nextFighterIndex = r.nextInt(LoanNetworkPopulation.size());

            //avoid self-fight
            if (nextFighterIndex == myIndex) {
                nextFighterIndex = ((nextFighterIndex + 1) % LoanNetworkPopulation.size());
            }

            im = LoanNetworkPopulation.get(nextFighterIndex);

            //synchronized (im) {
            //    synchronized (this) {
            im.lend(this);
            //    }
            // }

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
            System.out.println(this + " says:" + i2 + " is already dead!");
        }

    }

    @Override
    public void setLoanNetworkPopulation(List<Lender> immortalsPopulation) {
        this.LoanNetworkPopulation = immortalsPopulation;
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
