import java.util.*;
import java.io.*;
import java.lang.*;

class SchedulingAlgorithm {
	final int CPU = 2;
	final int SIZE = 20;
	final int PROCESS = 0;
	final int PRIORITY = 3;
	
	public static void main(String[] args) {
		SchedulingAlgorithm sa = new SchedulingAlgorithm();
						//process,arrival,burst time,priority
		int[][] process1 = {{1  ,0  ,20 ,0},
							{2  ,1  ,15 ,0},
							{3  ,2  ,11 ,1},
							{4  ,3  ,9  ,1},
							{5  ,4  ,11 ,2},
							{6  ,4  ,9  ,3},
							{7  ,5  ,12 ,2},
							{8  ,5  ,14 ,4},
							{9  ,6  ,15 ,3},
							{10 ,7  ,19 ,2},
							{11 ,8  ,25 ,0},
							{12 ,9  ,21 ,1},
							{13 ,9  ,8  ,2},
							{14 ,10 ,3  ,5},
							{15 ,10 ,4  ,5},
							{16 ,11 ,14 ,4},
							{17 ,11 ,12 ,4},
							{18 ,12 ,10 ,2},
							{19 ,13 ,10 ,3},
							{20 ,13 ,9  ,2}}; 

		int[][] process2 = {{1  ,0  ,10 ,5},
							{2  ,1  ,3  ,1},
							{3  ,2  ,15 ,0},
							{4  ,3  ,24 ,4},
							{5  ,4  ,6  ,2},
							{6  ,5  ,7  ,0},
							{7  ,6  ,25 ,4},
							{8  ,7  ,14 ,1},
							{9  ,8  ,13 ,2},
							{10 ,9  ,9  ,3},
							{11 ,10 ,7  ,4},
							{12 ,11 ,8  ,3},
							{13 ,12 ,5  ,2},
							{14 ,13 ,4  ,1},
							{15 ,14 ,1  ,0},
							{16 ,15 ,11 ,4},
							{17 ,16 ,16 ,5},
							{18 ,17 ,17 ,5},
							{19 ,18 ,18 ,4},
							{20 ,19 ,20 ,3}};
		
		sa.printProcess(process1, 1);
		sa.printProcess(process2, 2);
	}

	void printProcess(int[][] process, int processNumber) {
		int[] waitingTimeFCFS = new int[SIZE];
		int[] waitingTimeSJF = new int[SIZE];
		int[] waitingTimeSRPT = new int[SIZE];
		int[] waitingTimePRIORITY = new int[SIZE];
		int[] waitingTimeROUNDROBIN = new int[SIZE];
		
		System.out.println("\nWaiting Time for Process " + processNumber);
		System.out.println("--------------------------------------------------------------------------------------------------------");
		System.out.println("PROCESS\t\tFCFS\t\tSJF\t\tSRPT\t\tPRIORITY\tROUND ROBIN");
		System.out.println("--------------------------------------------------------------------------------------------------------");
		
		waitingTimeFCFS = getWaitingTimeFCFS(process);
		waitingTimeSJF =  getWaitingTimeSJF(process);
		waitingTimePRIORITY = getWaitingTimePRIORITY(process);

		for (int i = 0; i < SIZE; i++) {
			System.out.println((i+1) + "\t\t" + waitingTimeFCFS[i] + "\t\t" + waitingTimeSJF[i] + 
										"\t\t" + "-" + "\t\t" + waitingTimePRIORITY[i] + "\t\t" + 
										"-");
		}
		//getWaitingTimeSJF(process);
	}

	int[] getWaitingTimeFCFS(int[][] process) {
		int[] waitingTime = new int[SIZE];
		int prevWaitingTime = 0;
		for (int i = 0; i < SIZE; i++) {
			prevWaitingTime = prevWaitingTime + process[i][CPU];
			waitingTime[i] = prevWaitingTime;
		}
		return waitingTime;
	}

	int[] getWaitingTimeSJF(int[][] process) {
		int[] waitingTime = new int[SIZE];
		int prevWaitingTime = 0;
		int[][] newProcess = new int[SIZE][2];
		
		Arrays.sort(process, java.util.Comparator.comparingDouble(a -> a[CPU]));
		for (int i = 0; i < SIZE; i++) {
			prevWaitingTime = prevWaitingTime + process[i][CPU];
			newProcess[i][0] = process[i][PROCESS];
			newProcess[i][1] = prevWaitingTime;
		}
		Arrays.sort(newProcess, java.util.Comparator.comparingDouble(a -> a[PROCESS]));
		for (int i = 0; i < SIZE; i++) {
			waitingTime[i] = newProcess[i][1];
		}
		return waitingTime;
	}

	int[] getWaitingTimePRIORITY(int[][] process) {
		int[] waitingTime = new int[SIZE];
		int prevWaitingTime = 0;
		int[][] newProcess = new int[SIZE][2];
		
		Arrays.sort(process, java.util.Comparator.comparingDouble(a -> a[PRIORITY]));
		for (int i = 0; i < SIZE; i++) {
			prevWaitingTime = prevWaitingTime + process[i][CPU];
			newProcess[i][0] = process[i][PROCESS];
			newProcess[i][1] = prevWaitingTime;
		}
		Arrays.sort(newProcess, java.util.Comparator.comparingDouble(a -> a[PROCESS]));
		for (int i = 0; i < SIZE; i++) {
			waitingTime[i] = newProcess[i][1];
		}
		return waitingTime;
	}
}