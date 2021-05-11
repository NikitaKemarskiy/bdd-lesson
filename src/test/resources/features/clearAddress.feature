Feature: Clear-address endpoint
  The clear-address endpoint accepts a single IP address (v4 or v6).
  The only property it returns is the number of reports deleted from your account.

  ClearIpAddressRecordSuccessfullyWithPublicIp(

  Scenario: Public IP address record is successfully deleted from account
    Given IP address to delete is 216.58.215.78
    When DELETE request is sent to the Clear-address endpoint with the specified IP address
    Then Response status is 200 and does not have deleted addresses
