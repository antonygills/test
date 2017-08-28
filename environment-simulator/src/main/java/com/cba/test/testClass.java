package com.cba.test;

import java.text.ParseException;
import java.util.Date;

import com.cba.simulator.exception.UtilsException;
import com.cba.simulator.util.DateUtils;

public class testClass {
	public static void main(String[] args) throws ParseException, UtilsException {
//		System.out.println(Utils.getFormattedCurrentDate());
////		String condition = "a>5";
//		int a=9;
		Date date = new Date();
		
		Date startDate = DateUtils.getFormattedDateFromString("2013-08-28");
		if (startDate.before(DateUtils.getFormattedDate(date)))  {
			System.out.println("Please enter future date");
		}
		if (startDate.equals(date))  {
			System.out.println("equal");
		}
		
		
		
//		int dayOfYear = 112;
//		Date da = Utils.getDateFromDayOfYear(dayOfYear);
//		System.out.println(da);
//		System.out.println(Utils.getFormattedeDateTime(da));
		// Utils utils = new Utils();
		// String date = "2014-2-2";
		// Date beginDate = utils.getFormattedDate(date);
		// int theDay = utils.getDayOfYear(beginDate);
		// System.out.println(theDay);

		// Map<Integer, List<testDO>> weatherDetailsMap = new HashMap<Integer,
		// List<testDO>>();
		//
		//
		// testDO test1 = new testDO();
		// test1.setOne(1);
		// test1.setTwo("1_21");
		// test1.setThree("1_31");
		//
		// testDO test11 = new testDO();
		// test11.setOne(1);
		// test11.setTwo("1_22");
		// test11.setThree("1_33");
		//
		// testDO test2 = new testDO();
		// test2.setOne(2);
		// test2.setTwo("2_2");
		// test2.setThree("2_3");
		//
		// testDO test3 = new testDO();
		// test3.setOne(3);
		// test3.setTwo("3_2");
		// test3.setThree("3_3");
		//
		// List<testDO> testDOList = new ArrayList<testDO>();
		// testDOList.add(test1);
		// testDOList.add(test2);
		// testDOList.add(test3);
		// testDOList.add(test11);
		//
		//// for(testDO test : testDOList) {
		//// System.out.println(test.getOne() +"=="+test.getTwo());
		//// List<testDO> inp = new ArrayList<testDO>();
		//// inp.add(test);
		//// weatherDetailsMap.put(test1.getOne(), inp);
		//// }
		//
		// for(testDO test : testDOList) {
		// System.out.println(test.getOne() +"=="+test.getTwo());
		// if(!weatherDetailsMap.containsKey(test.getOne())){
		// weatherDetailsMap.put(test1.getOne(), new ArrayList<testDO>());
		// }
		// weatherDetailsMap.get(test1.getOne()).add(test);
		// }

		// System.out.println(weatherDetailsMap.get(1));

		// System.out.println(weatherDetailsMap.get(1).getTwo());

		// if (null != "src/main/resources/InputHistoricalData") {
		// File[] fileNames = {};
		// File file = new File("src/main/resources/InputHistoricalData");
		// if (file.isDirectory()) {
		// fileNames = file.listFiles();
		// }
		//
		// for(File fil :fileNames) {
		// String[] split = fil.getName().split(".");
		// String locationName = split[0];
		// }
		// } else {
		// }
		//
		//
		// try {
		//
		// CsvReader products = new
		// CsvReader("src/main/resources/InputHistoricalData/test.csv");
		//
		// products.readHeaders();
		//
		// while (products.readRecord()) {
		// String date = products.get("Date");
		// String pressure = products.get("Pressure");
		// String temperature = products.get("Teperature");
		// String humidity = products.get("Humidity");
		// }
		//
		// products.close();
		//
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}
}
