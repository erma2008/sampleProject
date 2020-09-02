package API.utils;


import Utils.Driver;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

public class CmdUtil {

    public void execute(String[] commands) throws IOException {
        Process process = Runtime.getRuntime().exec(commands);
        if(process.exitValue() >0){
            throw new RuntimeException("Execution of the cmd command exited with non 0 exit code"+process.exitValue());
        }
        InputStream inputStream = process.getInputStream();
        List<String> output = IOUtils.readLines(inputStream, Charset.defaultCharset());
        System.out.println(output);
    }

    @Test
    public void execCmdMAC() throws IOException, InterruptedException {
//        String[] commands = {"/bin/sh", "-c", "cd C:\\Users\\dddef\\Atlassian\\JIRA\\bin; " +
//                "export JIRA_HOME=/Users/familymac/Downloads/jiraHome/; ./start-jira.sh"};
        String[] commands = {"/bin/sh", "-c", "cd /Users/familymac/Downloads/atlassian-jira-software-8.5.4-standalone/bin; " +
                "export JIRA_HOME=/Users/familymac/Downloads/atlassian-jira-software-8.5.4-standalone/bin; ./start-jira.sh"};

        Process process = Runtime.getRuntime().exec(commands);
        InputStream inputStream = process.getInputStream();
        List<String> output = IOUtils.readLines(inputStream,Charset.defaultCharset());
        System.out.println(output);

        Thread.sleep(10000);
        WebDriver driver = Driver.getDriver();
        driver.get("http://localhost:8080");
    }


    @Test
    public void executeCMDWindows() throws IOException {
        String[] commands = {"/bin/sh", "-c", "cd C:\\Users\\dddef\\Atlassian\\JIRA\\bin;start-jira.bat /fg"};

       // Process process = Runtime.getRuntime().exec(commands);

//        List<String> cmdList = new ArrayList<String>();
//        cmdList.add("cmd");
//        cmdList.add("/c");
//      cmdList.add("cd C:\\Users\\dddef\\Atlassian\\JIRA\\bin");
//      cmdList.add("dir");
       // cmdList.add("cd C:\\Users\\dddef\\Atlassian\\JIRA\\bin;start-jira.bat /fg");
//        ProcessBuilder builder = new ProcessBuilder();
//        builder.command(cmdList);
       // Process process = builder.start();

        Process process=Runtime.getRuntime().exec("cmd /c start-jira.bat",
                null,
                new File("C:\\Users\\dddef\\Atlassian\\JIRA\\bin"));


        InputStream inputStream = process.getInputStream();
        List<String> output = IOUtils.readLines(inputStream,Charset.defaultCharset());
        System.out.println(output);

       // BrowserUtils.waitForSec(1);
        WebDriver driver = Driver.getDriver();


        driver.get("http://localhost:8080");

    }

    @Test
    public void cmdTest() throws IOException {
//        Process p=Runtime.getRuntime().exec("cmd /c start-jira.bat", null,new File("C:\\Users\\dddef\\Atlassian\\JIRA\\bin"));
//        System.out.println(p);
    }


}
