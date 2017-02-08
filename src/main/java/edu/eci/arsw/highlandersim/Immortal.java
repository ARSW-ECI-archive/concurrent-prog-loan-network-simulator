package edu.eci.arsw.highlandersim;

import java.util.List;
import java.util.Random;

public class Immortal implements I_Immortal {

    private int health;
    
    private static final int DEFAULT_DAMAGE_VALUE=10;

    private List<I_Immortal> immortalsPopulation=null;


    private String immortalName;

    @Override
    public void setImmortalName(String immortalName) {
        this.immortalName = immortalName;
    }

    private final Random r = new Random(System.currentTimeMillis());

    boolean pause = false;

    public void pause() {
        pause = true;
    }

    public void cont() {
        pause = false;
    }

    public Immortal() {                
        this.health = 100;
        this.immortalName="NN";
    }

    
    
    /*public Immortal(String name, List<Immortal> immortalsPopulation, int health, int defaultDamageValue) {
        super(name);
        this.name = name;
        this.immortalsPopulation = immortalsPopulation;
        this.health = health;
        this.defaultDamageValue=defaultDamageValue;
    }*/

    @Override
    public void run() {

        while (true) {
            I_Immortal im;

            int myIndex = immortalsPopulation.indexOf(this);

            int nextFighterIndex = r.nextInt(immortalsPopulation.size());

            //avoid self-fight
            if (nextFighterIndex == myIndex) {
                nextFighterIndex = ((nextFighterIndex + 1) % immortalsPopulation.size());
            }

            im = immortalsPopulation.get(nextFighterIndex);

            im.fight(this);            

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void fight(I_Immortal i2) {

        if (i2.getHealth() > 0) {
            i2.changeHealth(i2.getHealth() - DEFAULT_DAMAGE_VALUE);
            this.health += DEFAULT_DAMAGE_VALUE;
            System.out.println("Fight: " + this + " vs " + i2);
        } else {
            System.out.println(this + " says:" + i2 + " is already dead!");
        }

    }

    @Override
    public void setImmortalsPopulation(List<I_Immortal> immortalsPopulation) {
        this.immortalsPopulation = immortalsPopulation;
    }

    @Override
    public void changeHealth(int v) {
        health = v;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {

        return immortalName + "[" + health + "]";
    }

}
