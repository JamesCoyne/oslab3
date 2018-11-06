package osLab3;
import java.io.*; 
import java.util.*; 


public class osLab3 {
	
	public static int currentPID;
	public static ArrayList<Process> queue = new ArrayList<Process>();
	
	public static ArrayList<Process> fcfs(ArrayList input){
		ArrayList<Process> in = new ArrayList<Process>(input);
		ArrayList<Process> output = new ArrayList<Process>();
		int waittime = 0;
		
		
		
		while(!in.isEmpty()){
			int LowestProcessIndex = 0;
			int LowestArrivalTime = 0;
			int LowestPID = -1;
			
			for(int i = 0; i > in.size(); i++){
				//race checking for same arrival time. will finish later
//				if (in.get(i).arrivalTime == LowestArrivalTime){
//					if(LowestPID == -1){
//						LowestPID = in.get(i).PID;
//						LowestProcessIndex = i;
//						LowestArrivalTime = in.get(i).arrivalTime;
//					}
//					else{
//						if(LowestPID > in.get(i).PID){
//							
//						}
//						else{
//							
//						}
//					}
//				}
				if(in.get(i).arrivalTime < LowestArrivalTime){
					LowestProcessIndex = i;
					LowestArrivalTime = in.get(i).arrivalTime;
				}
				
			}
			Process LowestProcess = in.get(LowestProcessIndex);
			LowestProcess.waitTime = waittime;
			waittime += LowestProcess.burstTime;
			
			output.add(LowestProcess);
			in.remove(LowestProcessIndex);
		}
		return output;
	}

	public static ArrayList<Process> srt(ArrayList input){
		ArrayList<Process> in = new ArrayList<Process>(input);
		
		//calculate how much time it will take for each process to execute
		int totalTime = 0;
		for(Process p : in) totalTime+= p.burstTime;
		
		ArrayList<Process> currentProcesses = new ArrayList<Process>();
		ArrayList<Process> output = new ArrayList<Process>();
		
		for(int i = 0; i < totalTime; i++){
			//which processes exist at this point in time
			for(Process p : in) if(p.arrivalTime == i) currentProcesses.add(p);
			
			int smallestRemainingTime = Integer.MAX_VALUE;
			int smallestRemainingIndex = 0;
			
			//which process has the shortest remaining time at this point
			for(int x = 0; x < currentProcesses.size(); x++){
				if(currentProcesses.get(x).burstTime < smallestRemainingTime){
					smallestRemainingTime = currentProcesses.get(x).burstTime;
					smallestRemainingIndex = x; 
				}
			}
			currentProcesses.get(smallestRemainingIndex).burstTime--;
			//if that process has the shortest remaining time, decrease it's time by one, and add it to the out queue
			output.add(new Process(
					currentProcesses.get(smallestRemainingIndex).arrivalTime, 
					currentProcesses.get(smallestRemainingIndex).PID, 
					i - currentProcesses.get(smallestRemainingIndex).arrivalTime, 5));
			
			//if a process has zero remaining time, remove it comepletely
			if(currentProcesses.get(smallestRemainingIndex).burstTime == 0) currentProcesses.remove(smallestRemainingIndex);
		}
		return output;
	}
	
	public static ArrayList<Process> priority(ArrayList input){
		ArrayList<Process> in = new ArrayList<Process>(input);
		ArrayList<Process> output = new ArrayList<Process>();
		
		//calculate how much time it will take for each process to execute
		int totalTime = 0;
		for(Process p : in) totalTime+= p.burstTime;		
		ArrayList<Process> currentProcesses = new ArrayList<Process>();
		
		int currentProcessLength = 0;
		
		//iterate through the total length of time
		for(int i = 0; i < totalTime + 1; i++){
			
			//add processes as they come in
			for(Process p : in) if(p.arrivalTime == i) currentProcesses.add(p);
			
			//if the current process is done executing
			if(currentProcessLength == 0){
				//get next process
				
				//find the process with the lowest (highest) priority
				int lowestpriority = Integer.MAX_VALUE;
				int lowestPriorityIndex = 0;
				
				for(int x = 0; x < currentProcesses.size(); x++){
					if(currentProcesses.get(x).priority < lowestpriority){
						lowestpriority = currentProcesses.get(x).priority;
						lowestPriorityIndex = x;
					}
				}
				
				currentProcessLength = currentProcesses.get(lowestPriorityIndex).burstTime;
				
				//add it to the output arrayList
				output.add(currentProcesses.get(lowestPriorityIndex));
				//remove it from the currentProcess arrayList
				currentProcesses.remove(lowestPriorityIndex);
			}
			
			//if not, continue progressing
			else{
				currentProcessLength--;
			}
		}
		
		return output;
	}
	
	public static void printArrayList(ArrayList input){
		ArrayList<Process> in = new ArrayList<Process>(input);
		for(Process p : in){
			System.out.println("PID: " + p.PID + " BurstTime: " + p.burstTime + " waitTime: " + p.waitTime + " arrivalTime: " + p.arrivalTime + " priority: " + p.priority);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 				(arrival, burst time)
		queue.add(new Process(0,4,5));
		queue.add(new Process(1,5,1));
		queue.add(new Process(1,4,2));
		queue.add(new Process(1,3,3));
		//queue.add(new Process(5,1));
		
		printArrayList(srt(queue));
	}
}
