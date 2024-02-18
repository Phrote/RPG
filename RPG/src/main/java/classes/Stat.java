/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author ecsidav
 */
public class Stat {
    public final String name;
    private int value;
    
    public Stat(String name) {
        this.name = name;
        this.value = 1;
    }
    
    public Stat(String name, int value) {
        this.name = name;
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.name + ": " + this.value;
    }
    
}
