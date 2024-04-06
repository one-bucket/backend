<pre>
                                  __                          __                  __      
                                  /\ \                        /\ \                /\ \__   
  ___     ___       __            \ \ \____   __  __    ___   \ \ \/'\       __   \ \ ,_\  
 / __`\ /' _ `\   /'__`\  _______  \ \ '__`\ /\ \/\ \  /'___\  \ \ , <     /'__`\  \ \ \/  
/\ \L\ \/\ \/\ \ /\  __/ /\______\  \ \ \L\ \\ \ \_\ \/\ \__/   \ \ \\`\  /\  __/   \ \ \_ 
\ \____/\ \_\ \_\\ \____\\/______/   \ \_,__/ \ \____/\ \____\   \ \_\ \_\\ \____\   \ \__\
 \/___/  \/_/\/_/ \/____/             \/___/   \/___/  \/____/    \/_/\/_/ \/____/    \/__/
</pre>

# How to describe comment - javadocs...

### Comments for classses
Each class should be accompanied by comments that include the following information:  

- Author: The creator of the class.  
- Last Modified Date: The date of the most recent update (based on commit history).  
- Purpose/Functionality: A brief overview of what the class does or is responsible for.  
- Annotations: Any special annotations that the class may have (e.g., @Entity for database entities in Java).  
- Constructors: Description of the class constructors and their roles.  
- Methods: A brief on the key methods contained within the class.  
- Usage: Situations or conditions under which the class should be used.  

- Example:

```
/**
 * Class Name : 
 * <pre>
 * [description start]
 *
 * </pre>
 * <p>
 * Pattern and Annotation :  
 *   [such as Builder or Getter,Setter]
 * </p>
 * @author SangHyeok
 * @version 24.4.6
 * @see [other class or method that used or using this class]
 */
```
# about source code - SPRING


The source code for our project is meticulously organized within the Java package structure, 
comprising two main categories: `domain` and `global`.

The `domain` package is designed to encapsulate a collection of objects that are utilized locally or within a specific scope. 
This encapsulation ensures that the functionality provided by these objects is limited to particular segments of the project 
where they are most relevant.

On the other hand, the `globa`l package serves as a repository for objects that are fundamental to the project's infrastructure across all levels. 
This includes static objects and interfaces that are crucial 
for security protocols, login mechanisms, and other functionalities that require a global scope within the project's architecture.

## domain package
Within the domain package, each sub-package is curated to encapsulate a coherent set of functionalities, 
adhering to a standardized internal directory structure that typically 
includes api, dao, domain, dto, and service. 
This hierarchy is designed to foster a clear separation of concerns, 
ensuring that each component focuses on a singular aspect of the functionality, thereby simplifying maintenance and enhancing readability.
  
`API`: Acts as the entry point for the corresponding domain functionality, defining the set of available operations and their parameters.  
`DAO (Data Access Object)`: Facilitates communication with the data source, abstracting the underlying data access mechanics.  
`Domain`: Contains the core business logic and entities specific to this domain.  
`DTO (Data Transfer Object)`: Defines the data structure for transferring data between processes, reducing the number of method calls.  
`Service`: Orchestrates the execution of domain-specific business logic, acting as a mediator between the API layer and the domain logic.  
<br>
