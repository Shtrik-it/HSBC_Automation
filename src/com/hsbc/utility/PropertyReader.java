package com.hsbc.utility;

import java.util.HashMap;
import java.util.Properties;

import com.hsbc.utility.FileReader;

public class PropertyReader 
{

	HashMap<String, String> map;
	
	// Getting Started
	public String legalStructureOfBusiness;
	
	//Fast Track The Application
	public String companiesHouseNo;
	public String fastTrackSearchResultLink;
	public String selectYourself;
	
	//Business Overview
	public String title;
	public String forenames;
	public String surname;
	public String businessStartDate;
	public String whatInfluencedYourDecision;
	public String searchForActivity;
	public String searchActivityResultLink;
	public String whatYourBusinessDoes;
	public String detailsOfTheProducts;
	public String detailsOfTheService;
	public String whereBussinesOperatesFrom;
	public String postcode;
	public String findAddressResultLink;
	public String emailAddress;
	public String mobileNumber;
	public String birthDate;
	public String companyName;
	
	//Business Details
	public String howManyEmployees;
	public String licenceToOperate;
	public String vatNumber;
	public String businessEmailAddress;
	public String businessContactNumber;
	public String personalTitle;
	public String personalForenames;
	public String personalSurname;
	public String mainTitle;
	public String mainForenames;
	public String mainSurname;
	public String mainPhoneNumber;
	public String mainEmailAddress;
	public String countryMajorityBasedIn;
	
	//Business Finance
	public String turnoverForNext12Months;
	public String forcastProfitNextYear;
	public String initiallyInvested;
	public String sourceType;
	public String amountInGBP1;
	public String amountInGBP2;
	public String investorTitle;
	public String investorForenames;
	public String investorSurname;
	public String investedAmount;
	public String investorMobilePhone;
	public String investorEmailAddress;
	public String howMuchInvestmentWillBeDeposited;
	public String howWillThisBePaidIn;
	public String cashAmountEachYear;
	public String howOftenCashDeposits;
	public String typicalCashPayment;
	public String infoOnCashPayments;
	public String numOfMembers;
	
	//About You
	public String meritalStatus;
	public String residentialStatus;
	public String grossIncomeLastYear;
	public String yearsWithExistingBank;
	public String dateYouMovedIn;
	public String sourceOfInvesment1;
	public String sourceOfInvesment2;
	public String sourceAmount1;
	public String sourceAmount2;
	public String ocupationType;
	public String ocupation;
	public String employersName;
	public String howManyYearsEmployed;
	public String furtherDetails;
	
	//Related persons
	public String rolesInBusiness;
	public String position1;
	public String position2;
	public String position3;
	public String percentageShareholding;
	public String contactToAdd;
	public String realatedPersonsTitle;
	public String relatedPersonsForenames;
	public String relatedPersonsSurname;
	public String relatedPersonsPhoneNumber;
	public String relatedPersonsEmailAddress;
	public String relatedPersonsPosition;
	public String percentageOwnership;
	
	//Managing Your Account
	public String howToReceiveActivationCode;
	public String securityPassNumber;
	public String confirmSecurityPassNumber;
	public String memorableWord;
	public String memorablePlace;
	public String creditCardLimit;
	public String howWillYouUseCard;
	public String memorableWordSecurity;
	public String overdraftAmount;
	public String howWillYouUseOverdraft;
	public String chairpersonOfTheMeeting;
	public String resolutionsDate;
	
	//Tax Declaration
	public String activeTrade;
	
	
	/**Maps all text and dropdown fields with texts from the scenario property files.
	 * @param pathToFile
	 * @author milos
	 */
	public PropertyReader(String pathToFile) 
	{
		map = new HashMap<String, String>();
		Properties properties = FileReader.readFile(pathToFile);
		for (final String name: properties.stringPropertyNames())
		    map.put(name, properties.getProperty(name));
		
		//Getting Started
		legalStructureOfBusiness = map.get("LEGAL_STRUCTURE_OF_BUSINESS");
		
		//Fast Track The Application
		companiesHouseNo = map.get("COMPANIES_HOUSE_NO");
		fastTrackSearchResultLink = map.get("FT_SEARCH_RESULT_LINK");
		selectYourself = map.get("SELECT_YOURSELF");
		
		//Business Overview
		title = map.get("TITLE");
		forenames = map.get("FORENAMES");
		surname = map.get("SURNAME");
		businessStartDate = map.get("BUSINESS_START_DATE");
		whatInfluencedYourDecision = map.get("WHAT_INFLUENCED_YOUR_DECISION");
		searchForActivity = map.get("SEARCH_FOR_ACTIVITY");
		searchActivityResultLink = map.get("SEARCH_ACTIVITY_RESULT_LINK");
		whatYourBusinessDoes = map.get("WHAT_YOUR_BUSINESS_DOES");
		detailsOfTheProducts = map.get("DETAILS_OF_THE_PRODUCT");
		detailsOfTheService = map.get("DETAILS_OF_THE_SERVICE");
		whereBussinesOperatesFrom = map.get("WHERE_BUSINESS_OPERATES_FROM");
		postcode = map.get("POSTCODE");
		findAddressResultLink = map.get("FIND_MY_ADDRESS_LINK");
		emailAddress = map.get("EMAIL_ADDRESS");
		mobileNumber = map.get("MOBILE_NUMBER");
		birthDate = map.get("BIRTH_DATE");
		companyName = map.get("COMPANY_NAME");

		
		//Business Details
		howManyEmployees = map.get("HOW_MANY_EMPLOYEES");
		licenceToOperate =map.get("LICENCE_TO_OPERATE");
		vatNumber = map.get("VAT_NUMBER");
		businessEmailAddress = map.get("BUSINESS_EMAIL_ADDRESS");
		businessContactNumber = map.get("BUSINESS_CONTACT_NUMBER");
		personalTitle = map.get("PERSONAL_TITLE");
		personalForenames = map.get("PERSONAL_FORENAMES");
		personalSurname = map.get("PERSONAL_SURNAME");
		mainTitle = map.get("MAIN_TITLE");
		mainForenames = map.get("MAIN_FORENAMES");
		mainSurname = map.get("MAIN_SURNAME");
		mainPhoneNumber = map.get("MAIN_PHONE_NUMBER");
		mainEmailAddress = map.get("MAIN_EMAIL_ADDRESS");
		countryMajorityBasedIn = map.get("COUNTRY_MAJORITY_BASED_IN");
		
		//Business Finance
		turnoverForNext12Months = map.get("TURNOVER_FOR_NEXT_12_MONTHS");
		forcastProfitNextYear = map.get("FORCAST_PROFIT_NEXT_YEAR");
		initiallyInvested = map.get("INITIALLY_INVESTED");
		sourceType = map.get("SOURCE_TYPE");
		amountInGBP1 = map.get("AMOUNT_IN_GBP_1");
		amountInGBP2 = map.get("AMOUNT_IN_GBP_2");
		investorTitle = map.get("INVESTOR_TITLE");
		investorForenames = map.get("INVESTOR_FORENAMES");
		investorSurname = map.get("INVESTOR_SURNAME");
		investedAmount = map.get("INVESTED_AMOUNT");
		investorMobilePhone = map.get("INVESTOR_MOBILE_PHONE");
		investorEmailAddress = map.get("INVESTOR_EMAIL_ADDRESS");
		howMuchInvestmentWillBeDeposited = map.get("HOW_MUCH_WILL_BE_DEPOSITED");
		howWillThisBePaidIn = map.get("HOW_WILL_THIS_BE_PAID_IN");
		cashAmountEachYear = map.get("CASH_AMOUNT_EACH_YEAR");
		howOftenCashDeposits = map.get("HOW_OFTEN_CASH_DEPOSITS");
		typicalCashPayment = map.get("TYPICAL_CASH_PAYMENT");
		infoOnCashPayments = map.get("INFO_ON_CASH_PAYMENTS");
		numOfMembers = map.get("NUMBER_OF_MEMBERS");
		
		//About You
		meritalStatus = map.get("MERITAL_STATUS");
		residentialStatus = map.get("RESIDENTIAL_STATUS");
		grossIncomeLastYear = map.get("GROSS_INCOME_LAST_YEAR");
		yearsWithExistingBank = map.get("YEARS_WITH_EXISTING_BANK");
		dateYouMovedIn = map.get("DATE_MOVED_IN");
		sourceOfInvesment1 = map.get("SOURCE_OF_INVESTMENT_1");
		sourceOfInvesment2 = map.get("SOURCE_OF_INVESTMENT_2");
		sourceAmount1 = map.get("SOURCE_AMOUNT_1");
		sourceAmount2 = map.get("SOURCE_AMOUNT_2");
		ocupationType = map.get("OCCUPATION_TYPE");
		ocupation = map.get("OCCUPATION");
		employersName = map.get("EMPLOYERS_NAME");
		howManyYearsEmployed = map.get("HOW_MANY_YEARS_EMPLOYED");
		furtherDetails = map.get("FURTHER_DETAILS");
		
		//Related persons
		rolesInBusiness = map.get("ROLES_IN_BUSINESS");
		position1 = map.get("POSITION_1");
		position2 = map.get("POSITION_2");
		position3 = map.get("POSITION_3");
		percentageShareholding = map.get("PERCENTAGE_SHAREHOLDING");
		contactToAdd = map.get("CONTACT_TO_ADD");
		realatedPersonsTitle = map.get("RELATED_PERSONS_TITLE");
		relatedPersonsForenames = map.get("RELATED_PERSONS_FORENAMES");
		relatedPersonsSurname = map.get("RELATED_PERSONS_SURNAME");
		relatedPersonsPhoneNumber = map.get("RELATED_PERSONS_PHONE");
		relatedPersonsEmailAddress = map.get("RELATED_PERSONS_EMAIL_ADDRESS");
		relatedPersonsPosition = map.get("RELATED_PERSONS_POSITION");
		percentageOwnership = map.get("PERCENTAGE_OWNERSHIP");
		
		//Managing Your Account
		howToReceiveActivationCode = map.get("HOW_TO_RECEIVE_ACTIVATION_CODE");
		securityPassNumber = map.get("SECURITY_PASS_NUMBER");
		confirmSecurityPassNumber = map.get("CONFIRM_SECURITY_PASS_NUMBER");
		memorableWord = map.get("MEMORABLE_WORD");
		memorablePlace = map.get("MEMORABLE_PLACE");
		creditCardLimit = map.get("CREDIT_CARD_LIMIT");
		howWillYouUseCard = map.get("HOW_WILL_YOU_USE_CARD");
		memorableWordSecurity = map.get("MEMORABLE_WORD_SECURITY");
		overdraftAmount = map.get("OVERDRAFT_AMOUNT");
		howWillYouUseOverdraft = map.get("HOW_WILL_YOU_USE_OVERDRAFT");
		chairpersonOfTheMeeting = map.get("CHAIRPERSON_OF_THE_MEETING");
		resolutionsDate = map.get("RESOLUTION_DATE");
		
		//Tax Declaration
		activeTrade = map.get("ACTIVE_TRADE");
	}
}

