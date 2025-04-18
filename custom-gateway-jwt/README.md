```markdown
# Custom Gateway JWT Generator for WSO2 API Manager 4.3.0

This project demonstrates how to create and deploy a custom Gateway JWT generator for WSO2 API Manager 4.3.0. By implementing a custom generator, you gain fine-grained control over the claims included in the JSON Web Tokens (JWTs) issued by the API Gateway.

This README provides instructions on how to build, deploy, and configure the custom JWT generator. It follows the steps outlined in the Medium post "[Level Up Your API Security: Creating a Custom JWT Generator in WSO2 API Manager 4.3.0 (Beginner-Friendly)](YOUR_MEDIUM_POST_LINK_HERE)".

## Table of Contents

* [Prerequisites](#prerequisites)
* [Project Structure](#project-structure)
* [Setup](#setup)
    * [1. Create Maven Project](#1-create-maven-project)
    * [2. Write Custom JWT Generator](#2-write-custom-jwt-generator)
    * [3. Build with Maven](#3-build-with-maven)
    * [4. Deploy to WSO2 API Manager](#4-deploy-to-wso2-api-manager)
    * [5. Configure API Manager](#5-configure-api-manager)
* [Customization](#customization)
* [Contributing](#contributing)
* [License](#license)

## Prerequisites

Ensure you have the following installed and configured on your system:

* **Java SDK (1.8+):** Required for compiling the Java code.
* **Maven (3.5+):** Used for building and managing the project dependencies.
* **WSO2 API Manager 4.3.0:** A running instance of WSO2 API Manager where the custom generator will be deployed.
* **Basic Maven Knowledge:** Familiarity with `groupId`, `artifactId`, `packaging`, and Maven plugins.
* **Access to the WSO2 API Manager server's file system.**

## Project Structure

```

custom-gateway-jwt/
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── example/
│                   └── CustomGatewayJWTGenerator.java
├── pom.xml
└── README.md

````

* `src/main/java/org/example/CustomGatewayJWTGenerator.java`: Contains the source code for the custom JWT generator.
* `pom.xml`: Maven project configuration file, defining dependencies and build instructions.
* `README.md`: This file, providing information about the project.

## Setup

Follow these steps to set up and deploy the custom JWT generator:

### 1. Create Maven Project

If you haven't already, create the Maven project structure using the following command in your terminal:

```bash
mvn archetype:generate \
  -DgroupId=org.example \
  -DartifactId=custom-gateway-jwt \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false

cd custom-gateway-jwt
````

Then, replace the content of the generated `pom.xml` file with the configuration provided in the Medium post.

### 2\. Write Custom JWT Generator

Create the directory `src/main/java/org/example/` and the file `CustomGatewayJWTGenerator.java` within it. Copy and paste the Java code for the custom JWT generator from the Medium post into this file.

This example demonstrates how to extend `APIMgtGatewayJWTGeneratorImpl` and override the `populateStandardClaims` method to modify the `enduser` claim by removing the "@carbon.super" suffix. You can further customize this class to add or modify other standard or custom claims as needed.

### 3\. Build with Maven

Navigate to the root directory of your `custom-gateway-jwt` project in your terminal and run the Maven build command:

```bash
mvn clean package
```

This command will compile the Java code and package it as an OSGi bundle (`custom-gateway-jwt-1.0.0.jar`) in the `target` directory.

### 4\. Deploy to WSO2 API Manager

Deploy the generated OSGi bundle to your WSO2 API Manager 4.3.0 instance by following these steps:

1.  Copy the `custom-gateway-jwt-1.0.0.jar` file from the `target` directory of your Maven project.
2.  Paste this JAR file into the `<API-M_HOME>/repository/components/dropins` directory of your WSO2 API Manager installation.
3.  Restart your WSO2 API Manager server.

### 5\. Configure API Manager

To instruct WSO2 API Manager to use your custom JWT generator, you need to configure the `deployment.toml` file:

1.  Open the `<API-M_HOME>/repository/conf/deployment.toml` file in a text editor.

2.  Locate or add the `[apim.jwt]` section and the `[apim.jwt.gateway_generator]` subsection.

3.  Set the `impl` property to the fully qualified name of your custom JWT generator class:

    ```toml
    [apim.jwt]
    enable = true

    [apim.jwt.gateway_generator]
    impl = "org.example.CustomGatewayJWTGenerator"
    ```

4.  Save the `deployment.toml` file.

5.  Restart your WSO2 API Manager server again for the configuration changes to take effect.

After these steps, your WSO2 API Manager instance will use the `org.example.CustomGatewayJWTGenerator` to generate JWTs for API invocations.

## Customization

You can further customize the `CustomGatewayJWTGenerator.java` file to implement your specific requirements for JWT claim generation. Explore the `JWTInfoDto` object passed to the `populateStandardClaims` and `populateCustomClaims` methods to access information about the API invocation context, subscriber details, application information, and more. Use this information to populate the JWT claims according to your security policies.

## Contributing

Contributions to this project are welcome. Feel free to submit pull requests with bug fixes or enhancements. Please ensure that your code adheres to the existing style and includes appropriate tests.

## License

This project is licensed under the [Specify your license here, e.g., Apache License 2.0](https://www.google.com/search?q=LICENSE).

```

**Remember to replace `YOUR_MEDIUM_POST_LINK_HERE` with the actual link to your Medium post.** You might also want to create a `LICENSE` file if you intend to open-source your code.
```
