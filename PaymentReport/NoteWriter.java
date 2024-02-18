package com.example.paymentreport_att2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.Desktop;

public class NoteWriter {

    ArrayList<PaymentInformation> content;
    String note;
    String folder;

    NoteWriter(String folderPath_, String noteFile_, ArrayList<PaymentInformation> content_){
        this.content = content_;
        this.note = noteFile_;
        this.folder = folderPath_;

        try {
            // Create a FileWriter object with the existing file name
            FileWriter fileWriter = new FileWriter(note);

            fileWriter.write("\n");
            fileWriter.write("Room: "+ content.getFirst().getRoom()+"\n");
            fileWriter.write("Note: "+ content.getFirst().getNote()+"\n");
            fileWriter.write("\n");
            fileWriter.write("Amount: "+ content.getFirst().getAmount()+"\n");
            fileWriter.write("Date: "+ content.getFirst().getDate()+"\n");
            fileWriter.write("Method of Payment: "+ content.getFirst().getMOP()+"\n");
            fileWriter.write("Account: "+ content.getFirst().getAccount()+"\n");

            // Close the file writer to save the changes
            fileWriter.close();

            System.out.println("Data written to the file successfully!");
        } catch (IOException e) {
            System.err.println("Error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }

        String folderPath = folder;
        try {
            File openFolder = new File(folderPath);

            // Check if the folder exists before trying to open it
            if (openFolder.exists()) {
                // Open the folder using the default system application
                Desktop.getDesktop().open(openFolder);
            } else {
                System.out.println("Folder does not exist: " + folderPath);
            }
        } catch (IOException e) {
            System.err.println("Error occurred while opening the folder: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
