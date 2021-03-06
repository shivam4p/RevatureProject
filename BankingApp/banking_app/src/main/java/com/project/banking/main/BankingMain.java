package com.project.banking.main;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.project.banking.exception.BusinessException;
import com.project.banking.models.Account_Details;
import com.project.banking.models.Customer_Details;
import com.project.banking.models.Login_Details;
import com.project.banking.models.Transaction_Details;
import com.project.banking.services.AccountService;
import com.project.banking.services.UserService;
import com.project.banking.services.impl.AccountServiceImpl;
import com.project.banking.services.impl.UserServiceImpl;

public class BankingMain {
	private static Logger log = Logger.getLogger(BankingMain.class);

	public static void main(String[] args) {
		log.info("Welcome to the SP Banking App");
		log.info("-----------------------------");
		int choice = 0;
		Scanner scanner = new Scanner(System.in);
		UserService userservice = new UserServiceImpl();
		AccountService accountservice = new AccountServiceImpl();
		do {
			log.info("Please select a choice from below:");
			log.info("1) New User Sign Up");
			log.info("2) Existing User Sign In");
			log.info("3) Exit Banking App");
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
			}
			switch (choice) {
			case 1:
				Customer_Details customer = new Customer_Details();
				Login_Details login = new Login_Details();
				int creation = 0;
				do {
					log.info("Please Create A New Login 1st, Then Create Your Account With Personal Info");
					log.info("1) Create Login Details");
					log.info("2) Create Account With Personal Info");
					log.info("3) Exit");
					try {
						creation = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
					}
					switch (creation) {
					case 1:
						log.info("Please Enter Unique Username. Has to be greater than 5 letters/numbers:");
						login.setUsername(scanner.nextLine());
						log.info("Please Enter Unique Password. Has to be greater than 5 letters/numbers:");
						login.setPassword(scanner.nextLine());
						try {
							if (userservice.getLogin(login) == 1) {
								log.info("Log In Info Successfully Created With Below Details:");
								log.info("User Name:" +login.getUsername()+ "  Password:" + login.getPassword());
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}

						break;
						
					case 2:
						log.info("Please Enter First Name:");
						customer.setFirstname(scanner.nextLine());
						log.info("Please Enter Last Name:");
						customer.setLastname(scanner.nextLine());
						log.info("Please Enter Social Security Number (xxx-xx-xxxx). Include '-':");
						customer.setSsn(scanner.nextLine());
						log.info("Please Enter Street Address:");
						customer.setStreetaddress(scanner.nextLine());
						log.info("Please Enter City:");
						customer.setCity(scanner.nextLine());
						log.info("Please Enter State (CAPITAL Initials):");
						customer.setState(scanner.nextLine());
						log.info("Please Enter Date of Birth (mm/dd/yyyy). Include 0 if needed beforehand and '/' :");
						customer.setDob(scanner.nextLine());
						log.info("Please Enter Created User Name:");
						customer.setUsername(scanner.nextLine());
						
						try {
							if (userservice.newUser(customer) == 1) {
								log.info("New User Created Successfully With Below Details:");
								log.info("Name:"+customer.getFirstname()+" "+customer.getLastname()+"Address: "+customer.getStreetaddress()+","+customer.getCity()+","+customer.getState()+"   SSN:"+customer.getSsn()+"   User Name:" + customer.getUsername());
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}

						break;
					
					case 3:
						log.info("Exiting New User Sign Up.");
						break;
						
					default:
						log.warn("Invalid Selection, Please Choose From 1-3.");
						break;
					}
				}while(creation != 3);
				
				break;

			case 2:
				int user = 0;
				do {
					log.info("Who Are You Signing In As?");
					log.info("1) Customer");
					log.info("2) Employee");
					log.info("3) Exit Sign In Screen");
					try {
						user = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
					}
					switch (user) {
					case 1:// customer
						log.info("Enter Username:");
						String CUser = scanner.nextLine();
						log.info("Enter Password:" );
						String CPass = scanner.nextLine();
						int CLoginValidator = 0;
						int CStatusUValidator = 0;
						int CStatusRValidator = 0;
						int CStatusAValidator = 0;
						
						//check if valid login
						try {
							List<Login_Details> loginList = userservice.getAllLogin();
							for (int i = 0; i < loginList.size(); i++) {
								if((loginList.get(i).getUsername().equals(CUser)) && (loginList.get(i).getPassword().equals(CPass))) {
									CLoginValidator = 1;
								} else {
									CLoginValidator = CLoginValidator;
								}
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						if(CLoginValidator == 0) {
							log.warn("Invalid Login Credentials");
							break;
						}
						
						//check if account under review
						try {
							List<Login_Details> loginList = userservice.getAllLogin();
							for (int i = 0; i < loginList.size(); i++) {
								if((loginList.get(i).getUsername().equals(CUser)) && (loginList.get(i).getPassword().equals(CPass)) && (loginList.get(i).getStatus().equals("U"))) {
									CStatusUValidator = 1;
								} else {
									CStatusUValidator = CStatusUValidator;
								}
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						if(CStatusUValidator == 1) {
							log.warn("Account Currently Under Review. Check Back In Soon");
							break;
						}
						
						//check if account rejected
						try {
							List<Login_Details> loginList = userservice.getAllLogin();
							for (int i = 0; i < loginList.size(); i++) {
								if((loginList.get(i).getUsername().equals(CUser)) && (loginList.get(i).getPassword().equals(CPass)) && (loginList.get(i).getStatus().equals("R"))) {
									CStatusRValidator = 1;
								} else {
									CStatusRValidator = CStatusRValidator;
								}
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						if(CStatusRValidator == 1) {
							log.warn("Account Has Been Rejected.");
							break;
						}
						
						//check if account approved
						try {
							List<Login_Details> loginList = userservice.getAllLogin();
							for (int i = 0; i < loginList.size(); i++) {
								if((loginList.get(i).getUsername().equals(CUser)) && (loginList.get(i).getPassword().equals(CPass)) && (loginList.get(i).getStatus().equals("A"))) {
									CStatusAValidator = 1;
								} else {
									CStatusAValidator = CStatusAValidator;
								}
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						if(CStatusAValidator == 0) {
							log.warn("Please Try Logging Again");
							break;
						}
						
						int UCID = 0;
						
						try {
							List<Customer_Details> custList = userservice.getCID(CUser);
							if (custList.size() > 0) {
								for(Customer_Details cd:custList) {
									UCID = cd.getC_id();
								}
							} 
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						
						int COption = 0;
						do {
							log.info("Welcome Valued Customer! What Would You Like To Do?");
							log.info("1) Check Account Balance");
							log.info("2) Make Withdrawal");
							log.info("3) Make Deposit");
							log.info("4) Tansfer Money");
							log.info("5) Create An Account");
							log.info("6) Exit");
							log.info("Customer ID: "+UCID );
							try {
								COption = Integer.parseInt(scanner.nextLine());
							} catch (NumberFormatException e) {
							}
							switch (COption) {
							case 1:
								log.info("Listed Are All Your Current Account(s)");
								try {
									List<Account_Details> accountList = accountservice.getCustomerAccounts(UCID);
									if (accountList.size() > 0) {
										for(Account_Details ad:accountList) {
											 log.info("Account ID:" + ad.getA_id()+ "  Account Type:" + ad.getA_type());
										}
									} else {
										log.warn("No Accounts Have Been Made.");
										break;
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								
								
								
								log.info("Enter Account Number To See Balance");
								int UAID = 0;
								try {
									UAID = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
								}
								
								try {
									List<Account_Details> accountList = accountservice.getAccountBalance(UAID,UCID);
									if (accountList.size() > 0) {
										for(Account_Details ad:accountList) {
											 log.info("$"+ad.getBalance());
										}
									} else {
										log.warn("Invalid Account Number");
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								
								break;
							case 2:
								log.info("Enter Account Number");
								int UAID2 = 0;
								float UAID2B = 0;
								String type2 = "Withdrawal";
								try {
									UAID2 = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
								}
								
								try {
									List<Account_Details> accountList = accountservice.getAccountBalance(UAID2,UCID);
									if (accountList.size() > 0) {
										for(Account_Details ad:accountList) {
											 UAID2B = ad.getBalance();
										}
									} else {
										log.warn("Invalid Account Number");
										break;
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								
								float UAIDAmount2 = 0;
								log.info("Enter Amount To Withdraw");
								try {
									UAIDAmount2 = Float.parseFloat(scanner.nextLine());
								} catch (NumberFormatException e) {
								}
								
								if(amountChecker(UAIDAmount2) == false) {
									break;
								}
								
						
								float newBalance = UAID2B - UAIDAmount2;
								if(newBalance < 0) {
									log.warn("You Can't Make Withdrawal Greater Than Account Balance. Exiting");
									break;
								}else {
									try {
										if (accountservice.conductTransaction(UAID2, type2, UAIDAmount2) == 1) {
											log.info("Transaction Completed");
										}
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
									try {
										if (accountservice.updateAccount(newBalance, UAID2) == 1) {
											log.info("Account Balance Updated");
										}
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
								
								}
									
								
								break;

							case 3:
								log.info("Enter Account Number");
								int UAID3 = 0;
								float UAID3B = 0;
								String type3 = "Deposit";
								try {
									UAID3 = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
								}
								
								try {
									List<Account_Details> accountList = accountservice.getAccountBalance(UAID3,UCID);
									if (accountList.size() > 0) {
										for(Account_Details ad:accountList) {
											 UAID3B = ad.getBalance();
										}
									} else {
										log.warn("Invalid Account Number");
										break;
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								
								float UAIDAmount3 = 0;
								log.info("Enter Amount To Deposit");
								try {
									UAIDAmount3 = Float.parseFloat(scanner.nextLine());
								} catch (NumberFormatException e) {
								}
								
								if(amountChecker(UAIDAmount3) == false) {
									break;
								}
								
								float newBalance2 = UAID3B + UAIDAmount3;
									try {
										if (accountservice.conductTransaction(UAID3, type3, UAIDAmount3) == 1) {
											log.info("Transaction Completed");
										}
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
									try {
										if (accountservice.updateAccount(newBalance2, UAID3) == 1) {
											log.info("Account Balance Updated");
										}
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
								
								
								break;

							case 4:
								log.info("Enter Your Account Number You Wish To Send Money From");
								int UAID4 = 0;
								float UAID4B = 0;
								String type4 = "Transfer Withdrawal";
								try {
									UAID4 = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
								}
								
								try {
									List<Account_Details> accountList = accountservice.getAccountBalance(UAID4,UCID);
									if (accountList.size() > 0) {
										for(Account_Details ad:accountList) {
											 UAID4B = ad.getBalance();
										}
									} else {
										log.warn("Invalid Account Number");
										break;
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								
								int transAID = 0;
								log.info("Enter Recipient's Account Number");
								try {
									transAID = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
								}
								
								if(UAID4 == transAID) {
									log.warn("Accont ID's Can't Be The Same");
									break;
								}
								int transCID = 0;
								log.info("Enter Recipient's Customer ID Number");
								try {
									transCID = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
								}
								float transBal = 0;
								String transType = "Transfer Deposit";
								try {
									List<Account_Details> accountList = accountservice.getAccountBalance(transAID,transCID);
									if (accountList.size() > 0) {
										for(Account_Details ad:accountList) {
											 transBal = ad.getBalance();
										}
									} else {
										log.warn("Invalid Account Number or Customer ID");
										break;
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								
								
								float UAIDAmount4 = 0;
								log.info("Enter Amount To Send");
								try {
									UAIDAmount4 = Float.parseFloat(scanner.nextLine());
								} catch (NumberFormatException e) {
								}
								
								if(amountChecker(UAIDAmount4) == false) {
									break;
								}
								
								float newBalance4 = UAID4B - UAIDAmount4;
								float transNewBalance = transBal + UAIDAmount4;
								if(newBalance4 < 0) {
									log.warn("You Can't Send Money Greater Than Account Balance");
									break;
								}else {
									try {
										if (accountservice.conductTransaction(UAID4, type4, UAIDAmount4) == 1) {
											log.info("Transaction Completed For Sender");
										}
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
									
									try {
										if (accountservice.conductTransaction(transAID, transType, UAIDAmount4) == 1) {
											log.info("Transaction Completed For Receiver");
										}
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
									
									try {
										if (accountservice.updateAccount(newBalance4, UAID4) == 1) {
											log.info("Account Balance Updated For Sender");
										}
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
									
									try {
										if (accountservice.updateAccount(transNewBalance, transAID) == 1) {
											log.info("Account Balance Updated For Receiver");
										}
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
								
								}
								
								break;

							case 5:
								log.info("Which Type Of Account Would You Like to Make: ");
								log.info("1) Checking");
								log.info("2) Saving");
								int newAccountType = 0;
								String newAccountTypeS;
								try {
									newAccountType = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
								}
								
								if (newAccountType > 2 || newAccountType <= 0) {
									log.warn("Invalid Selection, Exiting.");
									break;
								} else if (newAccountType == 1) {
									newAccountTypeS = "Checking";
								} else {
									newAccountTypeS = "Saving";
								}
								
								log.info("Initial Deposit Amount: ");
								float newAccountInitDepo = 0;
								try {
									newAccountInitDepo = Float.parseFloat(scanner.nextLine());
								} catch (NumberFormatException e) {
								}
								
								if(amountChecker(newAccountInitDepo) == false) {
									break;
								}
								
								try {
									if (accountservice.createAccount(UCID, newAccountTypeS, newAccountInitDepo ) == 1) {
										log.info("New Account Created");
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								
								int maxAID = 0;
								try {
									List<Account_Details> accountList = accountservice.getCustomerAccounts(UCID);
									if (accountList.size() > 0) {
										for(Account_Details ad:accountList) {
											if (ad.getA_id()>maxAID) {
												maxAID = ad.getA_id();
											} else {
												maxAID = maxAID;
											}
												
										}
									} else {
										log.warn("Invalid Customer ID");
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								
								String newAccountInitType = "Initial Deposit";
								try {
									if (accountservice.conductTransaction(maxAID,newAccountInitType,newAccountInitDepo) == 1) {
										log.info("Transaction Completed For New Account");
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								} 
								
								break;

							case 6:
								log.info("Exiting Customer Page. Thank You.");
								break;

							default:
								log.warn("Invalid Selection, Please Choose From 1-6.");
								break;
							}

						} while (COption != 6);
						break;

					case 2:// employee
						
						log.info("Enter Username:");
						String EUser = scanner.nextLine();
						log.info("Enter Password:" );
						String EPass = scanner.nextLine();
						int ELoginValidator = 0;
						
						try {
							List<Login_Details> loginList = userservice.getAllLogin();
							for (int i = 0; i < loginList.size(); i++) {
								if((loginList.get(i).getUsername().equals(EUser)) && (loginList.get(i).getPassword().equals(EPass)) && (loginList.get(i).getStatus().equals("E"))) {
									ELoginValidator = 1;
								} else {
									ELoginValidator = ELoginValidator;
								}
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						if(ELoginValidator == 0) {
							log.warn("Invalid Login Credentials");
							break;
						}
						
						int EOption = 0;
						do {
							log.info("Welcome Employee! What Would You Like To Do?");
							log.info("1) Check Pending Customer Accounts");
							log.info("2) Create New Customer Account");
							log.info("3) Check Customer's Account");
							log.info("4) Exit");
							try {
								EOption = Integer.parseInt(scanner.nextLine());
							} catch (NumberFormatException e) {
							}
							switch (EOption) {
							case 1:
								
								log.info("The Following Accounts Are Pending Approval");
								try {
									List<Login_Details> loginList = userservice.getAllLogin();
									for (int i = 0; i < loginList.size(); i++) {
										if((loginList.get(i).getStatus().equals("U"))) {
											log.info(loginList.get(i).getUsername());
										}
										
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								
								int EAOption = 0;
								do {
									log.info("What Further Action Would You Like To Do?");
									log.info("1) Approve An Account");
									log.info("2) Reject An Account");
									log.info("3) Exit");
									try {
										EAOption = Integer.parseInt(scanner.nextLine());
									} catch (NumberFormatException e) {
									}
									switch(EAOption) {
									case 1:
										log.info("Enter Accounts Username: ");
										String AUsername = scanner.nextLine();
										int AUsernameValidator = 0;
										String AStatus = "A";
										
										try {
											List<Login_Details> loginList = userservice.getAllLogin();
											for (int i = 0; i < loginList.size(); i++) {
												if((loginList.get(i).getUsername().equals(AUsername))) {
													AUsernameValidator = 1;
												} else {
													AUsernameValidator = AUsernameValidator;
												}
											}
										} catch (BusinessException e) {
											log.warn(e.getMessage());
										}
										
										if (AUsernameValidator == 0) {
											log.warn("Invalid Username. Try Again");
											break;
										}
										try {
											if (userservice.updateLoginStatus(AUsername, AStatus ) > 0) {
												log.info("User " + AUsername + " Account Now Approved");
											}
										} catch (BusinessException e) {
											log.warn(e.getMessage());
										}
										
										break;
									case 2:
										log.info("Enter Accounts Username: ");
										String RUsername = scanner.nextLine();
										int RUsernameValidator = 0;
										String RStatus = "R";
										
										try {
											List<Login_Details> loginList = userservice.getAllLogin();
											for (int i = 0; i < loginList.size(); i++) {
												if((loginList.get(i).getUsername().equals(RUsername))) {
													RUsernameValidator = 1;
												} else {
													RUsernameValidator = RUsernameValidator;
												}
											}
										} catch (BusinessException e) {
											log.warn(e.getMessage());
										}
										
										if (RUsernameValidator == 0) {
											log.warn("Invalid Username. Try Again");
											break;
										}
										try {
											if (userservice.updateLoginStatus(RUsername, RStatus ) > 0) {
												log.info("User " + RUsername + " Account Rejected");
											}
										} catch (BusinessException e) {
											log.warn(e.getMessage());
										}
										break;
									case 3:
										log.info("Exiting Page");
										break;
									default:
										log.warn("Invalid Selection, Please Choose From 1-3.");
										break;
									}
								}while(EAOption != 3);
								break;

							case 2:
								int Ecreation = 0;
								do {
									log.info("Please Create A New Login 1st, Then Create Your Account With Personal Info");
									log.info("1) Create Login Details");
									log.info("2) Create Account With Personal Info");
									log.info("3) Exit");
									try {
										Ecreation = Integer.parseInt(scanner.nextLine());
									} catch (NumberFormatException e) {
									}
									switch (Ecreation) {
									case 1:
										Login_Details Elogin = new Login_Details();
									
										log.info("Please Enter Unique Username. Has to be greater than 5 letters/numbers:");
										Elogin.setUsername(scanner.nextLine());
										log.info("Please Enter Unique Password. Has to be greater than 5 letters/numbers:");
										Elogin.setPassword(scanner.nextLine());
										String AStatus = "A";
										Elogin.setStatus(AStatus);
										try {
											if (userservice.getLogin(Elogin) == 1) {
												log.info("Log In Info Successfully Created With Below Details:");
												log.info("User Name:" +Elogin.getUsername()+ "  Password:" + Elogin.getPassword());
											}
										} catch (BusinessException e) {
											log.warn(e.getMessage());
										}

										break;
										
									case 2:
										Customer_Details Ecustomer = new Customer_Details();
										log.info("Please Enter First Name:");
										Ecustomer.setFirstname(scanner.nextLine());
										log.info("Please Enter Last Name:");
										Ecustomer.setLastname(scanner.nextLine());
										log.info("Please Enter Social Security Number (xxx-xx-xxxx). Include '-':");
										Ecustomer.setSsn(scanner.nextLine());
										log.info("Please Enter Street Address:");
										Ecustomer.setStreetaddress(scanner.nextLine());
										log.info("Please Enter City:");
										Ecustomer.setCity(scanner.nextLine());
										log.info("Please Enter State (CAPITAL Initials):");
										Ecustomer.setState(scanner.nextLine());
										log.info("Please Enter Date of Birth (mm/dd/yyyy). Include 0 if needed beforehand and '/' :");
										Ecustomer.setDob(scanner.nextLine());
										log.info("Please Enter Created User Name:");
										Ecustomer.setUsername(scanner.nextLine());
										
										try {
											if (userservice.newUser(Ecustomer) == 1) {
												log.info("New User Created Successfully With Below Details:");
												log.info("Name:"+Ecustomer.getFirstname()+" "+Ecustomer.getLastname()+"Address: "+Ecustomer.getStreetaddress()+","+Ecustomer.getCity()+","+Ecustomer.getState()+"   SSN:"+Ecustomer.getSsn()+"   User Name:" + Ecustomer.getUsername());
											}
										} catch (BusinessException e) {
											log.warn(e.getMessage());
										}

										break;
									
									case 3:
										log.info("Exiting New User Sign Up.");
										break;
										
									default:
										log.warn("Invalid Selection, Please Choose From 1-3.");
										break;
									}
								}while(Ecreation != 3);
								break;

							case 3:
								log.info("Enter Customer's ID");
								int ECID = 0;
								try {
									ECID = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
								}
						
								
								try {
									List<Account_Details> accountList = accountservice.getCustomerAccounts(ECID);
									if (accountList.size() > 0) {
										for(Account_Details ad:accountList) {
											 log.info("Account ID:" + ad.getA_id()+ "        Account Type:" + ad.getA_type()+"        Account Balance:$" +ad.getBalance());
										}
									} else {
										log.warn("Invalid Customer ID");
										break;
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								
								
								int ECOption = 0;
								do {
									log.info("What Would You Like To Further See?");
									log.info("1) Complete Transaction Log");
									log.info("2) Exit");
									try {
										ECOption = Integer.parseInt(scanner.nextLine());
									} catch (NumberFormatException e) {
									}
									switch (ECOption) {
									case 1:
										log.info("Enter Account ID To See Complete Logs");
										int logAID = 0;
										try {
											logAID = Integer.parseInt(scanner.nextLine());
										} catch (NumberFormatException e) {
										}
										
										try {
											List<Transaction_Details> transactionList = accountservice.getTransLog(logAID);
											if (transactionList.size() > 0) {
												for(Transaction_Details td:transactionList) {
													 log.info("Transaction Type:" + td.getT_type()+ "        Transaction Amount:$" + td.getAmount()+ "        Transaction Date:" +td.getDate());
												}
											} else {
												log.warn("Invalid Account ID");
											}
										} catch (BusinessException e) {
											log.warn(e.getMessage());
										}
										
										break;

									case 2:
										log.info("Exiting Customer's Info Page.");
										break;


									default:
										log.warn("Invalid Selection, Please Choose From 1 or 2.");
										break;
									}

								} while (ECOption != 2);
								break;

							case 4:
								log.info("Exiting Employee Page.");
								break;

							default:
								log.warn("Invalid Selection, Please Choose From 1-4.");
								break;
							}

						} while (EOption != 4);
						break;

					case 3:
						log.info("Exiting Sign In Screen");
						break;

					default:
						log.warn("Invalid Selection, Please Choose From 1-3.");
						break;
					}
				} while (user != 3);
				break;

			case 3:
				log.info("Thank You For Using The SP Banking App. Hope To See You Again.");
				break;

			default:
				log.warn("Invalid Selection, Please Choose From 1-3.");
				break;

			}
		} while (choice != 3);

	}
	
	public static boolean amountChecker(float amount) {
		String Checker = Float.toString(amount);
		if(amount < 0) {
			log.warn("Amount Can't Be Negative Money. Exiting");
			return false;
		} else if(!(Checker.matches("\\d+(\\.\\d{1,2})?"))) {
			log.warn("Invalid. Please Enter Up To 2 Decimal Places. Exiting");
			return false;
		} else {
			return true;
		}
	}

}
