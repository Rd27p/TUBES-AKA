/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_aka;

/**
 *
 * @author Raka Darma
 */
public class GPU {
    String name;
    int memSize;
    int tmu;

    public GPU(String name, int memSize, int tmu) {
        this.name = name;
        this.memSize = memSize;
        this.tmu = tmu;
    }

    @Override
    public String toString() {
        return "GPU{name='" + name + "', memSize=" + memSize + ", tmu=" + tmu + "}";
    }
}


