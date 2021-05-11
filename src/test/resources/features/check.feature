Feature: Check endpoint
  The check endpoint accepts a single IP address (v4 or v6).
  Here you can inspect details regarding the IP address queried,
  such as version, country of origin, usage type, ISP, and domain name.
  Moreover, there is the valuable abusive reports.

  Scenario: Public IP is successfully checked
    Given IP address to check is 216.58.215.78
    When GET request is sent to the Check endpoint with the specified IP address
    Then Response status is 200 and IP address is public

  Scenario: Private IP is successfully checked
    Given IP address to check is 192.168.0.1
    When GET request is sent to the Check endpoint with the specified IP address
    Then Response status is 200 and IP address is private

  Scenario: Invalid IP is not checked
    Given IP address to check is 216.0.0.300
    When GET request is sent to the Check endpoint with the specified IP address
    Then Response status is 422 and response has invalid IP error message