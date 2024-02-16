# Portfolio Automation

Welcome to the Portfolio Automation repository! This project aims to automate the generation and maintenance of a portfolio using two main features: API-based automation and Web-based automation. It leverages technologies such as Selenium, Java, TestNG, Extent Report, and Cucumber to provide a robust and efficient solution.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

### 1. API Automation
The API automation feature enables the retrieval of data from external sources to dynamically update the portfolio. It streamlines the process of fetching information such as project details, GitHub statistics, or any other relevant data through API calls.

### 2. Web Automation
The Web automation feature focuses on automating the interaction with web interfaces to update and customize the portfolio. It uses Selenium for web browser automation, allowing for the dynamic modification of the portfolio website's content.

## Prerequisites
Ensure you have the following prerequisites installed before setting up and using the portfolio automation tool:

- Java
- Maven
- Selenium WebDriver
- TestNG
- Extent Report
- Cucumber

## Setup

1. **Clone the Repository:**
   ```bash
   https://github.com/Fikriazrbni/MyPortfolio-Automation.git
   cd portfolio-automation
   ```

2. **Install Dependencies:**
   ```bash
   mvn clean install
   ```

## Usage

1. **Configure API Settings:**
   Update the API configurations in `src/main/resources/config/api-config.properties` to match your desired endpoints and authentication settings.

2. **Configure Web Settings:**
   Adjust the web automation settings in `src/main/resources/config/web-config.properties` based on your portfolio website's structure and requirements.

3. **Run Tests:**
   Execute the automation tests using the following Maven command:
   ```bash
   mvn test
   ```

4. **View Reports:**
   Access the Extent Report generated after each test run in the `target/reports` directory for detailed insights.

## Contributing
Contributions are welcome! If you would like to contribute to the development of this project, please follow our [contribution guidelines](CONTRIBUTING.md).

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Feel free to reach out if you have any questions or need further assistance. Happy coding!
