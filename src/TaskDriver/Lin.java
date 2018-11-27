package TaskDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import constants.KeyboardCostants;
import constants.MouseConstants;
import keyboards.Keyboard;
import mouse.Mouse;
import system.Software;

public class Lin extends Thread {
	private ArrayList<String> tasks;
	private Robot lin;
	private boolean occupied;
	
	public Lin() {
		occupied = false;
		tasks = new ArrayList<String>(5);
		initializeLin();
	}

	private void performTasks() throws InterruptedException {
		for (String nextTask : this.tasks) {
			String[] taskDetail = nextTask.split(",");
			String device = taskDetail[0].trim();
			if (device.equalsIgnoreCase("mouse")) {
				Mouse.clickMouse(lin, MouseConstants.left_key);
			}
			else if(device.equalsIgnoreCase("key")) {
				Keyboard.clickKey(lin, KeyboardCostants.No1);
			}
			else if(taskDetail[0].trim().equalsIgnoreCase("sleep")) {
				int interval = Integer.parseInt(taskDetail[1]);
				Software.hold(interval);
			}
		}
	}

	@Override
	public void run() {
		System.out.println("Task starts");
		occupied = true;
		while (occupied) {
			try {
				performTasks();
			} catch (InterruptedException e) {
				return;
			}
		}
		return;
	}

	@Override
	public void interrupt() {
		System.out.println("quit");
		occupied = false;
		return;
	}
	
	private void initializeLin() {
		try {
			lin = new Robot();
		} catch (AWTException e) {
			System.out.println("Cant find lin");
			e.printStackTrace();
		}
	}
	
	public void addTask(String newTask) {
		this.tasks.add(newTask);
	}
	
	public void clearTask() {
		this.tasks.clear();
	}
	
	public String myState() {
		System.out.println("Hi I'm " + this.getState().toString());
		return this.getState().toString();
	}
	
	public String myTasks() {
		String result = "";
		for (String nextTask: this.tasks) {
			result += nextTask;
			result += "\n";
		}
		return result;
	}
}
