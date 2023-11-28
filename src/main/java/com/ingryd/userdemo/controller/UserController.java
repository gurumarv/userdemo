package com.ingryd.userdemo.controller;

import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/user")
public class UserController {
    File file = new File("message.txt");
    File log = new File("log.txt");
    public static int count;

    @GetMapping("/message")
    public String getMessage(){
        StringBuilder read = new StringBuilder();
        String readFrom;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            while ((readFrom = bufferedReader.readLine()) != null){
                read.append(readFrom);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return read.toString();

    }
    @GetMapping("/messagecount")
    public int getMessageCount(){
        return count;
    }

    @PostMapping("/postmessage")
    public String postMessage(@RequestBody String message){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(log, true))){
            bufferedWriter.write(message + "#");
            bufferedWriter2.write("New message created\n");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        count++;

        return "Message posted sucessfully";
    }


}
