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

	public static void printArrayList(ArrayList input){
		ArrayList<Process> in = new ArrayList<Process>(input);
		for(Process p : in){
			System.out.println("PID: " + p.PID + " BurstTime: " + p.burstTime + " waitTime: " + p.waitTime + " arrivalTime: " + p.arrivalTime);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 				(arrival, burst time)
		queue.add(new Process(0,4));
		queue.add(new Process(1,1));
		queue.add(new Process(2,1));
		queue.add(new Process(3,1));
		//queue.add(new Process(5,1));
		
		printArrayList(fcfs(queue));
	}
}
