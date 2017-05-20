/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

/**
 *
 * @author dell
 */
public class Solution {
    public String item;
    public String fclt;
    public int qtt;
    public double cost;
    public int arrvDay;

    public Solution(String item, String fclt, int qtt, double cost, int arrvDay) {
        this.item = item;
        this.fclt = fclt;
        this.qtt = qtt;
        this.cost = cost;
        this.arrvDay = arrvDay;
    }
    
    
}
