# 📮 Via CEP Java Client

A simple Java application that fetches Brazilian address information using the `ViaCEP public API`.  
The project validates a postal code CEP, calls the API, and maps the response into a Java object.

## 🚀 Features

* Validates Brazilian postal codes CEP
* Normalizes CEP input automatically
* Consumes the ViaCEP public API
* Maps JSON response to a Java model
* Uses modern Java HttpClient
* Logs execution details with SLF4J

## 🛠️ Technologies Used

* ☕ Java 17+
* 🌱 Spring Framework
* 📦 Lombok
* 🔄 Gson
* 🌐 Java HTTP Client
* 📝 SLF4J

## 📦 Project Structure

```
via-cep_java  
└── src  
└── main  
└── java  
└── com  
└── api  
└── via  
└── cep  
├── model  
│   └── Address.java  
└── service  
└── ViaCepService.java
```

## 📄 Domain Model

### Address

Represents the address returned by the ViaCEP API.

Mapped fields from JSON

* postalCode
* street
* neighborhood
* region
* state
* stateCode

## ⚙️ How It Works

1. Prompts the user to enter a postal code
2. Validates the CEP format
3. Normalizes the CEP to digits only
4. Sends a request to the ViaCEP API
5. Checks HTTP response status
6. Parses JSON into an Address object
7. Logs address details

## ▶️ How to Run

### Prerequisites

* Java 17 or higher
* Maven or any Java compatible IDE

### Steps

1. Clone the repository to your local machine

```
git clone https://github.com/willaevangelista/via-cep_java.git
```

2. Navigate to the project root directory

```
cd via-cep_java
```

3. Open the project in your preferred IDE
   Examples:
- IntelliJ IDEA
- Eclipse
- VS Code

4. Make sure all dependencies are correctly resolved
   If using Maven, refresh or reload the Maven project.

5. Run the ViaCepService main class
   The application is a console-based program and does not start a web server.

6. When prompted, enter a valid Brazilian postal code (CEP)
   Both formatted and unformatted values are accepted.

Example input:

```
- 01001-000

or

- 01001000
```

7. The application will validate the CEP, call the ViaCEP API, and print the address details in the console.

Example

01001-000

## 🔍 CEP Validation Rules

Accepted formats

* 12345-678
* 12345678

Invalid or empty inputs will stop execution.

## 📌 Example Output

```
Validated Postal Code: 01001000  
Postal Code: 01001-000  
Street: Praça da Sé  
Neighborhood: Sé  
State: São Paulo  
State Code: SP
```

## 📚 API Reference

*ViaCEP Public API:*
- https://viacep.com.br

## 📜 License

This project is open source and available under the MIT License.

## 🤝 Contributing

Pull requests and suggestions are welcome 🚀  
Feel free to open an issue if you find a bug or want to improve the project.