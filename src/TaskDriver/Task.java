package TaskDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import constants.KeyboardCostants;
import keyboards.Keyboard;
import system.Software;

public class Task extends Thread {
	private ArrayList<String> tasks;
	private Robot lin;
	private boolean occupied;
	
	public Task() {
		occupied = false;
		tasks = new ArrayList<String>(5);
		initializeLin();
		tasks.add("mouse, click, 10");
	}

	private void performTasks() throws InterruptedException {
		for (String nextTask : this.tasks) {
			String[] taskDetail = nextTask.split(", ");
			if (taskDetail[0].equals("mouse")) {
				//System.out.println("Clicking mouse");
				// Mouse.clickMouse(lin, MouseConstants.left_key);
				Keyboard.clickKey(lin, KeyboardCostants.No1);
				Software.hold(100);
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
}
