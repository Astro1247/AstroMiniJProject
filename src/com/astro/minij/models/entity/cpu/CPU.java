package com.astro.minij.models.entity.cpu;

public class CPU extends Thread {
    private boolean busy;
    private final String processorName;

    private CPUProcess workingProcess;

    public CPU (String name) {
        this.processorName = name;
    }

    public boolean isBusy(){
        return this.busy;
    }

    public synchronized void loadProcess(CPUProcess process) {
        this.workingProcess = process;
    }

    @Override
    public void run(){
        System.out.println("[INIT] Initialising CPU#" + this.processorName);
        while(true) {
            if (this.workingProcess != null) {
                System.out.println("[START] CPU#" + this.processorName + " Process#" + workingProcess.getId());
                this.busy = true;
                int milstoSleep = Math.abs(workingProcess.getComplexity()) * 1000;
                while(milstoSleep<1000) {
                    milstoSleep += 1000;
                }
                try {
                    sleep(milstoSleep);
                }
                catch ( InterruptedException e ) {}
                this.busy = false;
                System.out.println("[FINISH]: CPU#" + this.processorName + " Process#" + workingProcess.getId());
                this.workingProcess = null;
            }
            try {
                sleep(500);
            } catch ( InterruptedException e ) {}
        }
    }
}
