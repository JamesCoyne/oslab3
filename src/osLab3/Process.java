package osLab3;

public class Process {
	public int arrivalTime;
	public int burstTime;
	public int waitTime = 0;
	public int PID;
	
	Process(int a, int b){
		arrivalTime = a;
		burstTime = b;
		PID = osLab3.currentPID;
		osLab3.currentPID++;
	}
	
}
