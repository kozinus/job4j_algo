package ru.job4j.algo.greedy;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int start = -1;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        if (totalGas <= totalCost) {
            int tank;
            boolean done = false;
            for (int i = 0; i < gas.length; i++) {
                tank = 0;
                int j = 0;
                while (tank >= 0) {
                    if (j == gas.length) {
                        done = true;
                        break;
                    }
                    tank += gas[(j + i) % gas.length] - cost[(j + i) % gas.length];
                    j++;
                }
                if (done) {
                    start = i;
                    break;
                }
            }
        }

        return start;
    }
}