package osLab3;

public class Process {
	public int arrivalTime;
	public int burstTime;
	public int waitTime = 0;
	public int PID;
	public int priority = 5;
	
	Process(int a, int b){
		arrivalTime = a;
		burstTime = b;
		PID = osLab3.currentPID;
		osLab3.currentPID++;
	}
	
	Process(int a, int b, int p){
		arrivalTime = a;
		burstTime = b;
		priority = p;
		PID = osLab3.currentPID;
		osLab3.currentPID++;
	}
	
	Process(int a, int pid, int w, int p){
		arrivalTime = a;
		PID = pid;
		waitTime = (int) w;
		burstTime = 1;
		priority = p;
	}
	
}
