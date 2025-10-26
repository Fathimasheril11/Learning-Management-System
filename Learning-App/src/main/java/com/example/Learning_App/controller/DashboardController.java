package com.example.Learning_App.controller;

import com.example.Learning_App.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        String[] courses = {"HTML", "CSS", "Java", "JS", "MySQL"};
        model.addAttribute("courses", courses);
        return "dashboard";
    }

    @GetMapping("/course/{courseName}")
    public String course(@PathVariable String courseName, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("courseName", courseName);

        // Hardcoded course content
        String basics = getBasics(courseName);
        String functions = getFunctions(courseName);
        String examples = getExamples(courseName);

        model.addAttribute("basics", basics);
        model.addAttribute("functions", functions);
        model.addAttribute("examples", examples);

        return "course";
    }

    private String getBasics(String course) {
        return switch (course) {
            case "HTML" -> "HTML stands for HyperText Markup Language. It is used to create the structure of web pages." +
                    "HTML stands for Hyper Text Markup Language\n" +
                    "HTML is the standard markup language for creating Web pages\n" +
                    "HTML describes the structure of a Web page\n" +
                    "HTML consists of a series of elements\n" +
                    "HTML elements tell the browser how to display the content\n" +
                    "HTML elements label pieces of content such as \"this is a heading\", \"this is a paragraph\", \"this is a link\", etc." +
                    "All HTML documents must start with a document type declaration: <!DOCTYPE html>.\n" +
                    "The HTML document itself begins with <html> and ends with </html>.\n" +
                    "The visible part of the HTML document is between <body> and </body>.";

            case "CSS" -> "CSS is the language we use to style a Web page.\n" +
                    "CSS stands for Cascading Style Sheets.\n" +
                    "CSS describes how HTML elements are to be displayed on screen, paper, or in other media.\n" +
                    "CSS saves a lot of work. It can control the layout of multiple web pages all at once.\n" +
                    "External stylesheets are stored in CSS files.\n" +
                    "CSS is used to define styles for your web pages, including the design, layout and variations in display for different devices and screen sizes.\n" +
                    "The CSS definitions are normally saved in an external .css file.";

            case "Java" -> "Java is a popular and powerful programming language, created in 1995.\n" +
                    "It is owned by Oracle, and more than 3 billion devices run Java.\n" +
                    "It is used for:\n" +
                    "Mobile applications (specially Android apps),\n" +
                    "Desktop applications,\n" +
                    "Web applications,\n" +
                    "Web servers and application servers,\n" +
                    "Games,\n" +
                    "Database connection,\n" +
                    "And much, much more!.\n" +
                    "Java works on different platforms (Windows, Mac, Linux, Raspberry Pi, etc.).\n" +
                    "It is one of the most popular programming languages in the world.\n" +
                    "It has a large demand in the current job market.\n" +
                    "It is easy to learn and simple to use.\n" +
                    "It is open-source and free.\n" +
                    "It is secure, fast and powerful.\n" +
                    "It has huge community support (tens of millions of developers).\n" +
                    "Java is an object oriented language which gives a clear structure to programs and allows code to be reused, lowering development costs.\n";

            case "JS" -> "JavaScript is the programming language of the web.\n" +
                    "It can calculate, manipulate and validate data.\n" +
                    "It can update and change both HTML and CSS.\n" +
                    "JavaScript is one of the 3 languages all web developers must learn:\n" +
                    "   1. HTML to define the content of web pages.\n" +
                    "   2. CSS to specify the layout of web pages.\n" +
                    "   3. JavaScript to program the behavior of web pages.\n";

            case "MySQL" -> "MySQL is a widely used relational database management system (RDBMS).\n" +
                    "MySQL is free and open-source.\n" +
                    "MySQL is ideal for both small and large applications.\n" +
                    "MySQL is very fast, reliable, scalable, and easy to use.\n" +
                    "MySQL is cross-platform.\n" +
                    "MySQL is compliant with the ANSI SQL standard.\n" +
                    "MySQL was first released in 1995.\n" +
                    "MySQL is developed, distributed, and supported by Oracle Corporation.\n" +
                    "MySQL is named after co-founder Ulf Michael \"Monty\" Widenius's daughter: My.\n";

            default -> "No basics available.";
        };
    }

    private String getFunctions(String course) {
        return switch (course) {
            case "HTML" -> "The <!DOCTYPE html> declaration defines that this document is an HTML5 document\n" +
                    "The <html> element is the root element of an HTML page\n" +
                    "The <head> element contains meta information about the HTML page\n" +
                    "The <title> element specifies a title for the HTML page (which is shown in the browser's title bar or in the page's tab)\n" +
                    "The <body> element defines the document's body, and is a container for all the visible contents, such as headings, paragraphs, images, hyperlinks, tables, lists, etc.\n" +
                    "The <h1> element defines a large heading\n" +
                    "The <p> element defines a paragraph";

            case "CSS" -> "The primary function of CSS is to define the styling and layout of web pages, making them more visually appealing, user-friendly, and accessible. " +
                    "CSS provides a way to define and apply styles consistently across all pages of a website, making it easier for developers to maintain and update their designs.\n" +
                    "Helping HTML return to its original intent of markup as well as page formulating, CSS is now widely popular for making web designs appear better, run faster as well as serve a far better user experience than expected.\n" +
                    "Using CSS, we can modify the appearance, placement of the HTML elements, animations, effects, layouts, and everything that can support the website design and development. " +
                    "In fact, CSS makes it simple to link multiple aspects of a website.";

            case "Java" -> "Java was among the first object-oriented programming languages. \n" +
                    "An object-oriented programming language organizes its code around classes and objects, rather than functions and commands. \n" +
                    "Most modern programming languages, including C++, C#, Python, and Ruby are object-oriented.\n" +
                    "Java a versatile and relatively easy-to-learn programming language that's extremely popular with software and technology companies. " +
                    "If you're seeking a career in coding or computer programming, knowing how to write Java is a valuable skill.\n";

            case "JS" -> "Functions are one of the fundamental building blocks in JavaScript. \n" +
                    "A function in JavaScript is similar to a procedure—a set of statements that performs a task or calculates a value, but for a procedure to qualify as a function, \n" +
                    "it should take some input and return an output where there is some obvious relationship between the input and the output. \n" +
                    "To use a function, you must define it somewhere in the scope from which you wish to call it.\n";

            case "MySQL" -> "The functions in MySQL can edit rows and tables, alter strings, and help us to manage organized and easy-to-navigate databases.\n" +
                    "A function is a special type of predefined command set that performs some operation and returns a value. \n" +
                    "Functions operate on zero, one, two, or more values that are provided to them.";

            default -> "No functions available.";
        };
    }

    private String getExamples(String course) {
        return switch (course) {
            case "HTML" -> "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title>Page Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h1>My First Heading</h1>\n" +
                    "<p>My first paragraph.</p>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>";

            case "CSS" -> "body {\n" +
                    "  background-color: lightblue;\n" +
                    "}\n" +
                    "\n" +
                    "h1 {\n" +
                    "  color: white;\n" +
                    "  text-align: center;\n" +
                    "}\n" +
                    "\n" +
                    "p {\n" +
                    "  font-family: verdana;\n" +
                    "  font-size: 20px;\n" +
                    "}";

            case "Java" -> "public class Main {\n" +
                    "  public static void main(String[] args) {\n" +
                    "    System.out.println(\"Hello World\");\n" +
                    "  }\n" +
                    "}\n";

            case "JS" -> "function greet() { " +
                    "console.log('Hello World'); " +
                    "} " +
                    "greet();";

            case "MySQL" -> "SELECT * FROM users WHERE id = 1;";

            default -> "No examples available.";
        };
    }
}
