package com.astro.minij;

import com.astro.minij.models.content.medication.Medication;
import com.astro.minij.models.entity.Matrix;
import com.astro.minij.models.entity.Vector;
import com.astro.minij.models.entity.cpu.CPU;
import com.astro.minij.models.entity.cpu.CPUProcess;
import com.astro.minij.models.kits.FirstAidKit;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to MiniJ project!\n");

        //threadingComparsionOnMatrixVectorMultiply();
        wakeUpCPUWorkers();

        System.out.println("\nThank you for using MiniJ project.");
        System.out.println("Shutting down system...");
    }

    public static void wakeUpCPUWorkers() {
        int cycles = 10;
        int heartbeatRPM = 60;
        int processesStarted = 0;
        int maxInStack = 0;

        CPU cpu1 = new CPU("1");
        CPU cpu2 = new CPU("2");
        cpu1.start();
        cpu2.start();

        ArrayList<CPUProcess> processesStack = new ArrayList<>();

        System.out.println("Starting process generation...");
        for (int i = 0; i < cycles; i++) {
            CPUProcess cpuProcess = new CPUProcess(getRandomInt(0, 5));
            if(!cpu1.isBusy()) {
                cpu1.loadProcess(cpuProcess);
                processesStarted++;
            }
            else if (!cpu2.isBusy()) {
                cpu2.loadProcess(cpuProcess);
                processesStarted++;
            }
            else processesStack.add(cpuProcess);

            if(maxInStack < processesStack.size()) maxInStack = processesStack.size();

            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {}
        }
        System.out.println("Process generation complete.");

        System.out.println("Starting heartbeat...");
        // Heartbeat
        while (processesStarted < cycles) {
            System.out.println("[BEAT] Started " + processesStarted + " of " + cycles);
            if(!cpu1.isBusy()) {
                if(processesStack.size() > 0) {
                    cpu1.loadProcess(processesStack.get(0));
                    processesStack.remove(processesStack.get(0));
                    processesStarted++;
                } else continue;
            }
            if (!cpu2.isBusy()) {
                if(processesStack.size() > 0) {
                    cpu2.loadProcess(processesStack.get(0));
                    processesStack.remove(processesStack.get(0));
                    processesStarted++;
                } else continue;
            }

            try {
                Thread.sleep((long) Math.floor((60 * 1000)/heartbeatRPM));
            }
            catch(InterruptedException e) {}
        }
        System.out.println("Maximum in process stack were " + maxInStack + " processes.");
        System.out.println("Processes stack is empty, heartbeat shut down");
        while (cpu1.isBusy() || cpu2.isBusy()) {
            System.out.println("Awaiting for CPU's to end their work.");
            try {
                Thread.sleep((long) Math.floor((60 * 1000)/heartbeatRPM));
            }
            catch(InterruptedException e) {}
        }
        System.out.println("All processes were complete! Shuttind down CPU's...");
        try {
            cpu1.shutdown();
            cpu1.join();
            cpu2.shutdown();
            cpu2.join();
        } catch (InterruptedException e) {}
    }

    public static void threadingComparsionOnMatrixVectorMultiply() throws InterruptedException {
        int n = 1000;
        int threads = 100;
        Matrix matrix = new Matrix(n, n);
        for (int i = 0; i < matrix.getWidth(); i++) {
            for (int j = 0; j < matrix.getHeight(); j++) {
                matrix.setValue(i, j, getRandomFloat(-n, n));
            }
        }
        Vector vector = new Vector(n);
        for(int i = 0; i < vector.getSize(); i++) {
            vector.setValue(i, getRandomFloat(-n, n));
        }
        long minSingleThreadExecTime = Long.MAX_VALUE;
        long maxSingleThreadExecTime = Long.MIN_VALUE;
        long startSingleTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            long execTime = singleThreadMatrixVectorMultiplier(matrix, vector);
            if (minSingleThreadExecTime > execTime) minSingleThreadExecTime = execTime;
            if (maxSingleThreadExecTime < execTime) maxSingleThreadExecTime = execTime;
        }
        long stopSingleTime = System.nanoTime();
        System.out.println("Single thread:\nMin exec time: " + minSingleThreadExecTime + "\nMax exec time: " + maxSingleThreadExecTime);
        System.out.println("Total measurement time took: " + (stopSingleTime - startSingleTime));

        //singleThreadMatrixVectorMultiplier(matrix, vector);
        System.out.println("==========");
        //multiThreadMatrixVectorMultiplier(matrix, vector, 5);

        long minMultiThreadExecTime = Long.MAX_VALUE;
        long maxMultiThreadExecTime = Long.MIN_VALUE;
        long startMultiTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            long execTime = multiThreadMatrixVectorMultiplier(matrix, vector, threads);
            if (minMultiThreadExecTime > execTime) minMultiThreadExecTime = execTime;
            if (maxMultiThreadExecTime < execTime) maxMultiThreadExecTime = execTime;
        }
        long stopMultiTime = System.nanoTime();
        System.out.println("Multi thread:\nMin exec time: " + minSingleThreadExecTime + "\nMax exec time: " + maxSingleThreadExecTime);
        System.out.println("Total measurement time took: " + (stopMultiTime - startMultiTime));

        System.out.println("==========");

        if((stopMultiTime - startMultiTime) < (stopSingleTime - startSingleTime)) {
            //int percent = (int) (Math.floor(((stopSingleTime - startSingleTime)/(stopMultiTime - startMultiTime))));
            System.out.println("So, multi thread was faster by: " + ((stopSingleTime - startSingleTime) - (stopMultiTime - startMultiTime)));
        } else if ((stopMultiTime - startMultiTime) > (stopSingleTime - startSingleTime)) {
            //int percent = (int) (Math.floor(((stopMultiTime - startMultiTime)/(stopSingleTime - startSingleTime))));
            System.out.println("So, single thread was faster by: " + ((stopMultiTime - startMultiTime) - (stopSingleTime - startSingleTime)));
        } else {
            System.out.println("So, single and multi thread comparsion got same exec time. WOW! IMPOSSIBLE!");
        }
    }

    public static long singleThreadMatrixVectorMultiplier(Matrix matrix, Vector vector) {
        long startTime = System.nanoTime();
        Vector resultVector = new Vector(matrix.getHeight());
        for (int i = 0; i < matrix.getHeight(); i++) {
            float newValue = 0;
            for (int j = 0; j < matrix.getWidth(); j++) {
                newValue += matrix.getValue(j, i)*vector.getValue(j);
            }
            resultVector.setValue(i, newValue);
        }
        long stopTime = System.nanoTime();
        //resultVector.display();
        //System.out.println("Single thread execution time: " + (stopTime - startTime));
        return stopTime - startTime;
    }

    public static long multiThreadMatrixVectorMultiplier(Matrix matrix, Vector vector, int threads) throws InterruptedException {
        long startTime = System.nanoTime();
        ThreadMult[] threadArray = new ThreadMult[threads];
        int rows = matrix.getHeight();

        for (int i = 0; i < threads; i++) {
            threadArray[i] = new ThreadMult(matrix ,vector,
                    rows/threads * i,
                    i==threads-1 ?rows:rows/threads * (i + 1) );
            threadArray[i].start();
        }
        for (int i = 0; i < threads; i++){
            threadArray[i].join();
        }
        Vector resultVector = new Vector(rows);
        int inserted = 0;
        for (int i = 0; i < threads; i++){
            for (int j = 0; j < threadArray[i].resultVector.getSize(); j++) {
                resultVector.setValue(inserted++, threadArray[i].resultVector.getValue(j));
            }
        }
        //resultVector.display();
        long stopTime = System.nanoTime();
        //System.out.println("Single thread execution time: " + (stopTime - startTime));
        return stopTime - startTime;
    }

    public static float getRandomFloat(float min, float max) {
        double random = min + Math.random() * (max - min);
        return (float) random;
    }

    private static Random rand = new Random();
    public static int getRandomInt(int min, int max) {
        int tmp = min + (rand.nextInt() % (max - min + 1));
        return tmp;
    }

    public static void firstAidKit() {
        FirstAidKit firstAidKit = new FirstAidKit("m");
        Medication analgin = new Medication("Анальгин", 1, Medication.medicationType.tablet);
        firstAidKit.addMedication(analgin);
        System.out.println(firstAidKit.medications.size());
        System.out.println(firstAidKit.toString());
    }
}

class ThreadMult extends Thread {
    public Matrix matrix;
    public Vector vector;
    public Vector resultVector;
    public int inserted;

    public int startRow;
    public int endRow;

    public ThreadMult(Matrix matrix, Vector vector, int startRow, int endRow) {
        this.matrix = matrix;
        this.vector = vector;
        this.startRow = startRow;
        this.endRow = endRow;
        resultVector = new Vector(endRow-startRow);
        inserted = 0;
    }

    public Vector getResult() {
        return resultVector;
    }

    @Override
    public void run(){
        for (int i = startRow; i < endRow; i++) {
            //for (int i = 0; i < endRow-startRow; i++) {
            float newValue = 0;
            for (int j = 0; j < matrix.getWidth(); j++) {
                newValue += matrix.getValue(j, i)*vector.getValue(j);
            }
            resultVector.setValue(inserted++, newValue);
        }
    }
}
