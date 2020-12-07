package com.hallym.SP_Project;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LED extends Thread{
	private final static GpioController gpio = GpioFactory.getInstance();
	private final static GpioPinDigitalOutput redLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, PinState.LOW);
	private static GpioPinDigitalOutput greenLed;
	private final static GpioPinDigitalOutput blueLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, PinState.LOW);

	int seq;

	public LED(int seq) {
		this.seq = seq;
	}
	
	public void run() {
		if(seq == 1)
			red();
		else if(seq == 2)
			greenLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, PinState.HIGH);
		else if(seq == 3)
			blue();
	}
	public void red() {
		redLed.high();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		redLed.low();
	}
	/*
	public void green() {
		try {
			sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		greenLed.low();
	}
	*/
	public void blue() {
		blueLed.high();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		blueLed.low();
	}
}
