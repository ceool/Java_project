package com.hallym.SP_Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordLog {
	private static FileWriter writer = null; //파일 출력
	private static FileWriter buywriter = null;
	private static FileWriter sellwriter = null;
	private static FileWriter depositwriter = null;
	private static FileWriter holdwriter = null;
	private static FileWriter valuewriter = null;
	private static File dir = null;
	String fileName = null;
	String filePath = null;
	String buyfilePath = null;
	String sellfilePath = null;
	String depositfilePath = null;
	String holdfilePath = null;
	String valuefilePath = null;
	
	public void present(String logPath, int value) {
		//현재가
		SimpleDateFormat ymdFmt = new SimpleDateFormat("yyyyMMdd");
		
		String ymd = ymdFmt.format(new Date());
		
		dir = new File(logPath);
		if(!dir.isDirectory()) { //디렉토리가 없으면 생성
			dir.mkdirs();
		}

		valuefilePath = logPath + File.separator + ymd + "_present.log";
		
		try {
			valuewriter = new FileWriter(valuefilePath);
			valuewriter.write(Integer.toString(value));
			valuewriter.write("\r\n");
			valuewriter.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void buyLog(String logPath, int money, float mybit, int price, float bit, int total, float avg) {
		//경로, 보유금, 보유량, 매매단가, 매매량, 총매매가, 매매평균가, 매매종류
		SimpleDateFormat ymdFmt = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat ymdhmsFmt = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		
		String ymd = ymdFmt.format(new Date());
		String ymdhms = ymdhmsFmt.format(new Date());
		
		dir = new File(logPath);
		if(!dir.isDirectory()) { //디렉토리가 없으면 생성
			dir.mkdirs();
		}
		
		buyfilePath = logPath + File.separator + ymd + "_buying.txt"; //로그 파일 생성
		filePath = logPath + File.separator + ymd + "_total.log";
		depositfilePath = logPath + File.separator + ymd + "_deposit.txt";
		holdfilePath = logPath + File.separator + ymd + "_hold.txt";
		valuefilePath = logPath + File.separator + ymd + "_value.txt";
		
		String description = "[" + ymdhms + "] - 매수\n" + 
				"[보유 자금]: " + money + ", [보유량]: " + mybit +
				"\n[매수 가격]: " + price + ", [매수량]: " + bit +
				"\n[매수평균가격]: " + (int)avg +
				"\n[총 매수가격]: " + total +
				"\n"; //로그 내용
		
		try {
			buywriter = new FileWriter(buyfilePath, true);
			buywriter.write(description);
			buywriter.write("\r\n");
			buywriter.flush();
			writer = new FileWriter(filePath, true);
			writer.write(description);
			writer.write("\r\n");
			writer.flush();
			depositwriter = new FileWriter(depositfilePath);
			depositwriter.write(Integer.toString(money));
			depositwriter.write("\r\n");
			depositwriter.flush();
			holdwriter = new FileWriter(holdfilePath);
			holdwriter.write(Float.toString(mybit));
			holdwriter.write("\r\n");
			holdwriter.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fileName = null;
				filePath = null;
				dir = null;
				if(writer != null) {
					writer.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sellLog(String logPath, int money, float mybit, int price, float bit, int total) {
		//경로, 보유금, 보유량, 매매단가, 매매량, 총매매가, 매매평균가, 매매종류
		SimpleDateFormat ymdFmt = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat ymdhmsFmt = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		
		String ymd = ymdFmt.format(new Date());
		String ymdhms = ymdhmsFmt.format(new Date());
		
		dir = new File(logPath);
		if(!dir.isDirectory()) { //디렉토리가 없으면 생성
			dir.mkdirs();
		}
		
		sellfilePath = logPath + File.separator + ymd + "_sell.txt"; //로그 파일 생성
		filePath = logPath + File.separator + ymd + "_total.log";
		depositfilePath = logPath + File.separator + ymd + "_deposit.txt";
		holdfilePath = logPath + File.separator + ymd + "_hold.txt";
		valuefilePath = logPath + File.separator + ymd + "_value.txt";
		
		String description = "[" + ymdhms + "] - 매도\n" + 
				"[보유 자금]: " + money + ", [보유량]: " + mybit +
				"\n[매도 가격]: " + price + ", [매도량]: " + bit +
				"\n[총 매도가격]: " + total +
				"\n"; //로그 내용
		
		try {
			sellwriter = new FileWriter(sellfilePath, true);
			sellwriter.write(description);
			sellwriter.write("\r\n");
			sellwriter.flush();
			writer = new FileWriter(filePath, true);
			writer.write(description);
			writer.write("\r\n");
			writer.flush();
			depositwriter = new FileWriter(depositfilePath);
			depositwriter.write(Integer.toString(money));
			depositwriter.write("\r\n");
			depositwriter.flush();
			holdwriter = new FileWriter(holdfilePath);
			holdwriter.write(Float.toString(mybit));
			holdwriter.write("\r\n");
			holdwriter.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fileName = null;
				filePath = null;
				dir = null;
				if(writer != null) {
					writer.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}