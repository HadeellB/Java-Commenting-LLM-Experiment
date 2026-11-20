package com.thealgorithms.scheduling;

import com.thealgorithms.devutils.entities.ProcessDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author [Bama Charan Chhandogi](https://www.github.com/BamaCharanChhandogi)
 */
public class PreemptivePriorityScheduling {
    // Processes waiting to be transferred to the ready queue when they arrive.
    protected final List<ProcessDetails> processes;

    // Records the process ID or "Idle" for each simulated time unit.
    protected final List<String> ganttChart;

    public PreemptivePriorityScheduling(Collection<ProcessDetails> processes) {
        // Copies the collection; the ProcessDetails objects themselves remain shared.
        this.processes = new ArrayList<>(processes);
        this.ganttChart = new ArrayList<>();
    }

    public void scheduleProcesses() {
        // Larger priority values run first; equal priorities favor earlier arrivals.
        PriorityQueue<ProcessDetails> readyQueue = new PriorityQueue<>(Comparator.comparingInt(ProcessDetails::getPriority).reversed().thenComparingInt(ProcessDetails::getArrivalTime));

        int currentTime = 0;
        List<ProcessDetails> arrivedProcesses = new ArrayList<>();

        // Continue until no processes are waiting to arrive or ready to execute.
        while (!processes.isEmpty() || !readyQueue.isEmpty()) {
            updateArrivedProcesses(currentTime, arrivedProcesses);
            readyQueue.addAll(arrivedProcesses);
            arrivedProcesses.clear();

            if (!readyQueue.isEmpty()) {
                ProcessDetails currentProcess = readyQueue.poll();
                ganttChart.add(currentProcess.getProcessId());

                // Execute one time unit, updating the process's remaining burst time.
                currentProcess.setBurstTime(currentProcess.getBurstTime() - 1);

                // Requeue unfinished work so priority is reconsidered each time unit,
                // allowing newly arrived higher-priority processes to preempt it.
                if (currentProcess.getBurstTime() > 0) {
                    readyQueue.add(currentProcess);
                }
            } else {
                ganttChart.add("Idle");
            }

            currentTime++;
        }
    }

    private void updateArrivedProcesses(int currentTime, List<ProcessDetails> arrivedProcesses) {
        // Transfer all processes that have arrived out of the waiting list.
        processes.removeIf(process -> {
            if (process.getArrivalTime() <= currentTime) {
                arrivedProcesses.add(process);
                return true;
            }
            return false;
        });
    }
}