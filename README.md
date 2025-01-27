
# Insecure Deserialization Sandbox

Insecure deserialization ranks as number 8 on the [OWASP Top Ten](https://owasp.org/www-project-top-ten/) list of web application security risks. It is a critical vulnerability in many backend web servers, often leading to remote code execution (RCE).

## What is it?

Deserialization is the process of converting a data structure (e.g., JSON, binary, XML) back into an object in a backend system. For example, a user profile in JSON format might be sent from the front end to an API, which deserializes it into an object.

This process becomes vulnerable when an attacker can manipulate the serialized data sent to the API. By crafting malicious payloads, they can exploit the deserialization process to execute arbitrary code or inject malicious behavior. For instance, an attacker could manipulate an object’s constructor or other critical fields during the deserialization process.

To exploit this vulnerability, attackers typically need knowledge of the backend infrastructure since serialization formats (e.g., JSON, binary) vary. However, brute force techniques can be used to test multiple payloads against various systems until the desired response is achieved.

## What am I doing?

This project involves creating an active scanner rule for [OWASP ZAP (Zed Attack Proxy)](https://owasp.org/www-project-zap/), designed to detect insecure deserialization vulnerabilities. Testing for this vulnerability must be approached with care to avoid unintentionally causing a denial-of-service (DoS) attack.

One effective method for safely testing deserialization vulnerabilities is through DNS resolution, as suggested in [Paranoid Software's article](https://blog.paranoidsoftware.com/triggering-a-dns-lookup-using-java-deserialization/). This approach offers several advantages:

1. **Wide Compatibility**: Many programming languages have native DNS/URL resolution capabilities that are unlikely to be manually blocked.
2. **Firewall Bypass**: DNS traffic (port 53) is rarely restricted by firewalls.
3. **Low Intrusiveness**: This method is less disruptive than alternatives like ICMP pings.

However, the main limitation of this technique is the requirement for access to DNS logs to confirm the vulnerability. This can be achieved using either a local DNS server or a remote DNS service, such as [AWS Route 53](https://aws.amazon.com/route53/).

## Further Reading

1. [OWASP Insecure Deserialization Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Deserialization_Cheat_Sheet.html)  
   A detailed guide to understanding and mitigating insecure deserialization vulnerabilities.

2. [CWE-502: Deserialization of Untrusted Data](https://cwe.mitre.org/data/definitions/502.html)  
   A resource from MITRE providing an in-depth look at the technical implications and examples of insecure deserialization.

3. [PortSwigger's Guide to Insecure Deserialization](https://portswigger.net/web-security/deserialization)  
   A comprehensive breakdown of insecure deserialization, including exploitation examples.

4. [OWASP Top Ten List](https://owasp.org/www-project-top-ten/)  
   Overview of the top ten web application security risks.

5. [Software and Data Integrity Failures (Insecure Deserialization)](https://owasp.org/Top10/A08_2021-Software_and_Data_Integrity_Failures/)  
   Focused details on software and data integrity failures related to deserialization.

6. [OWASP ZAP GitHub](https://github.com/zaproxy/zaproxy)  
   Official GitHub repository for OWASP ZAP.

7. [My Forked OWASP ZAP Repository](https://github.com/Ahxius/zaproxy)  
   The customized version of OWASP ZAP I’m using for this project.
