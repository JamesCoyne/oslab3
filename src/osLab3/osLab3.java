package osLab3;
import java.io.*; 
import java.util.*; 


public class osLab3 {
	public static ArrayList<Process> queue = new ArrayList<Process>();
	
	public static ArrayList<Process> fcfs(ArrayList input){
		ArrayList<Process> in = new ArrayList<Process>(input);
		ArrayList<Process> output = new ArrayList<Process>();
		int waittime = 0;
		
		Process LowestProcess = new Process(1000,1000);
		
		while(!in.isEmpty()){
			for(Process p : in){
				if(LowestProcess.arrivalTime > p.arrivalTime){
					LowestProcess = p;
				}
			}
			in.remove(LowestProcess);
			LowestProcess.waitTime = waittime;
			waittime += LowestProcess.burstTime;
			output.add(LowestProcess);
		}
		return output;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		queue.add(new Process(1,2));
		queue.add(new Process(2,3));
		queue.add(new Process(3,1));
		System.out.println("done");
		System.out.println(queue.size());
	}
}
