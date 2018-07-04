package com.hsbc.testRunner;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.hsbc.utility.FileReader;
import com.hsbc.utility.GlobalPaths.FilePaths;


public class SuiteGenerator
{
	public static void main(String[] args)
	{
		runXMLSuite();
	}
	
	/**Generates xml file as a test suite and runs the bundle of scenarios defined
	 *  in the config property file
	 *  @author milos
	 */
	public static void runXMLSuite()
	{
		
		//Create Test suite xml node
		XmlSuite suite = new XmlSuite();
		suite.setName("HSBCSuite");
		
		//Create Test xml node inside the suite node
		XmlTest test = new XmlTest(suite);
		test.setName("ScenarioRun");
		
		//Create Test classes (scenarios) nodes inside the Test node		
		test.setXmlClasses(getScenarioList());
		
		//Generate xml file and run the tests
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();
	}
	
	
	/**Reads users scenario list choice from the config file and adds a list of classes to the xml classes node
	 * @return list of class nodes to be used in xml
	 * @author milos
	 */
	private static List<XmlClass> getScenarioList()
	{
		
		List<XmlClass> classes = new ArrayList<XmlClass>();
		String userScenarioChoice = FileReader.read(FilePaths.CONFIG, "TESTS_TO_RUN");
		String[] scenariosParsed = userScenarioChoice.split(",");
		for (String scenarioId : scenariosParsed) 
		{
			classes.add(new XmlClass("com.hsbc.tests.Scenario"+scenarioId));
		}
		return classes;
	}
}
