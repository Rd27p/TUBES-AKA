/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_aka;

/**
 *
 * @author Raka Darma
 */
import java.util.List;

public class SortingUtils {

    // Bubble sort for sorting by memory bus
    public static void bubbleSort(List<GPU> gpuList) {
        int n = gpuList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Assuming you want to sort by memSize, change this to memoryBus if needed
                if (gpuList.get(j).memSize > gpuList.get(j + 1).memSize) {
                    // Swap
                    GPU temp = gpuList.get(j);
                    gpuList.set(j, gpuList.get(j + 1));
                    gpuList.set(j + 1, temp);
                }
            }
        }
    }
}


