package com.hsbc.utility;

/**Maps the paths to files across the project
 * @author milos
 */
public class GlobalPaths 
{
	public final class FilePaths
	{
		public static final String CONFIG = "./Configuration/config.property";
	}
	
	public final class WebDriverExePaths
	{
		public static final String CHROME_MAC_EXE = "./Configuration/Drivers/Mac/chromedriver";
		public static final String FIREFOX_MAC_EXE = "./Configuration/Drivers/Mac/geckodriver";
		
		public static final String CHROME_WIN_EXE = "./Configuration/Drivers/Win/chromedriver.exe";
		public static final String FIREFOX_WIN_EXE = "./Configuration/Drivers/Win/geckodriver.exe";
	}
	
	public final class ScenarioConfigPaths
	{
		public static final String SCENARIO_1 = "./Scenarios/Scenario1.property";
		public static final String SCENARIO_2 = "./Scenarios/Scenario2.property";
		public static final String SCENARIO_3 = "./Scenarios/Scenario3.property";
		public static final String SCENARIO_4 = "./Scenarios/Scenario4.property";
		public static final String SCENARIO_5 = "./Scenarios/Scenario5.property";
		public static final String SCENARIO_6 = "./Scenarios/Scenario6.property";
		public static final String SCENARIO_7 = "./Scenarios/Scenario7.property";
	}
}
