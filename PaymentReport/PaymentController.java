package com.example.paymentreport_att2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PaymentController  {

    private File folder;

    // Main Folder name - can edit name
    String folderName = "SUNRENTER";

    // Main Folder path - can edit location
    String mainRepositoryFolderPath = "C:\\Users\\luisa\\OneDrive - National University\\Desktop\\" + folderName;

    // Scanner exe path - can edit scanner exe app path
    String scannerAppPath = "C:\\Windows\\twain_32\\escndv\\escndv.exe";

    //parent path for file rename
    private String parentPath;
    private String inputFolderPath;
    private String inputNoteFilePath;

    private String renamedImagePath;


    @FXML
    private TextField folderTxt;

    @FXML
    private TextField fileTxt;

    @FXML
    private TextField roomTxt;

    @FXML
    private TextField accountTxt;

    @FXML
    private TextField amountTxt;

    @FXML
    private TextArea noteTxt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox modCombo;

    @FXML
    private TextField comboTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField statusTxt;

    @FXML
    private Button scannerBtn;

    @FXML
    private Button insertImgBtn;

    @FXML
    private Button renameImgBtn;

    @FXML
    private Button saveFinalBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TextField imageTxt;

    @FXML
    private ImageView imageView;


    @FXML
    public void CreateFolder() {

        File mainFolder = new File(mainRepositoryFolderPath);

        // if folder already exist - show allert folder  already exist
        if (mainFolder.exists() && mainFolder.isDirectory()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("The folder already exists. Kindly verify your desktop.");
            alert.showAndWait();
        } else {

            // else create folder on desired path
            folder = new File(mainRepositoryFolderPath);
            if (folder.mkdir()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Folder created in desktop successfully.");
                alert.showAndWait();
                System.out.println("Folder path: " + folder.getAbsolutePath());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Folder not created.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    protected void createFolderNoteFile(){
        // Gets folderName entered by user
        String folderName = folderTxt.getText();
        // Created new path with the entered folderName
        String folderPath = "C:\\Users\\luisa\\OneDrive - National University\\Desktop\\SUNRENTER\\" + folderName; // Customize the folder path here

        folder = new File(folderPath);

        if (folder.mkdir()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Folder created successfully.");
            alert.showAndWait();
            //Checker
            // System.out.println("Folder path: " + folder.getAbsolutePath());
            String noteFileName = fileTxt.getText()+".txt";
            String folder_path = folder.getAbsolutePath();
            // Checker
            // System.out.println("Folder path: "+ folder_path );
            setFolderPath(folder_path);
            try {
                // Create a note file inside the folder path
                // Create a new path for noteFile and inputted note filename
                File noteFile = new File(folder_path+"\\"+noteFileName);

                // Create a new empty note file
                boolean created = noteFile.createNewFile();

                if (created) {

                    // if created show success alert and make other fields editable for users input
                    setNoteFilePath(noteFile.toString());
                    Alert alert_1 = new Alert(Alert.AlertType.INFORMATION);
                    alert_1.setHeaderText(null);
                    alert_1.setContentText("NoteFile created successfully.");
                    alert_1.showAndWait();
                    System.out.println("NoteFile path: " + noteFile.getAbsolutePath());

                    // set status created and green
                    statusTxt.setStyle("-fx-control-inner-background: Green;");
                    statusTxt.setText("Created");

                    // set other text to be editable
                    roomTxt.setEditable(true);
                    accountTxt.setEditable(true);
                    noteTxt.setEditable(true);
                    datePicker.setEditable(true);
                    amountTxt.setEditable(true);
                    comboTxt.setEditable(true);



                } else {
                    Alert alert_1 = new Alert(Alert.AlertType.ERROR);
                    alert_1.setHeaderText(null);
                    alert_1.setContentText("Error: NoteFile not created.");
                    alert_1.showAndWait();

                }
            } catch (IOException e) {
                System.err.println("Error occurred while creating the text file: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            Alert alert_1 = new Alert(Alert.AlertType.ERROR);
            alert_1.setHeaderText(null);
            alert_1.setContentText("Error: Folder not created.");
            alert_1.showAndWait();
        }
    }

    // run the scanner exe
    @FXML
    protected void runScanner(){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(scannerAppPath);
            processBuilder.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    // Select File for Image input
    @FXML
    protected void selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        // Show the file dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(getStage());

        if (selectedFile != null) {
            // Handle the selected file (e.g., read or process the file)
            System.out.println("Selected File: " + selectedFile.getAbsolutePath());
            setImageView(selectedFile.getAbsolutePath());
            // set parent path for rename
            setParentPath(selectedFile.getParent());
            // checker
            System.out.println("Selected File: " + selectedFile.getName());
            // set imageText new text - to show what imagefile has been selected
            imageTxt.setText(selectedFile.getName());
        }
    }

    // use to get the stage or frame of the current stage
    private Stage getStage() {
        return (Stage) insertImgBtn.getScene().getWindow();
    }


    // use to show  in the image view the inputted image from selectFile
    protected void setImageView(String imagePath){
        Image image = new Image("file:"+imagePath);
        imageView.setImage(image);
    }


    // use to rename image
    @FXML
    protected void renameImage(){
        // -- show an input dialogue for renameInput
        TextInputDialog renameDialog = new TextInputDialog("Insert new name.");
        renameDialog.setTitle("Rename Image");
        renameDialog.setHeaderText("Previous Name: "+ imageTxt.getText());
        renameDialog.setContentText("Rename Text:");

        // Show the dialog and wait for the user's response
        Optional<String> renameResult = renameDialog.showAndWait();

        // to remove the Option[] to get the input only - needs to filter using regex
        Pattern pattern = Pattern.compile("Optional\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(""+renameResult);
        // Check if the pattern matches to the input
        if (matcher.matches()) {
            // Extract the content within square brackets
            String content = matcher.group(1);
            File image = new File(getParentPath()+"\\"+imageTxt.getText());

            if (!content.isEmpty()) {
                String parentPath = getParentPath();
                //Checker
                //System.out.println("parent file in rename -"+ getParentPath());

                File renamedImage = new File(parentPath, content + ".jpg");
                //Checker
                //System.out.println("renamed -"+ renamedImage);
                setRenamedImagePath(renamedImage.toString());

                if (image.renameTo(renamedImage)) {
                    Alert alert_1 = new Alert(Alert.AlertType.INFORMATION);
                    alert_1.setHeaderText(null);
                    alert_1.setContentText("File renamed successfully.");
                    alert_1.showAndWait();
                    imageTxt.setText(renamedImage.getName());

                } else {
                    Alert alert_1 = new Alert(Alert.AlertType.ERROR);
                    alert_1.setHeaderText(null);
                    alert_1.setContentText("Failed to rename the file.");
                    alert_1.showAndWait();
                }
            }
        }


    }


    // Setter for parentPath
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    // Getter for parentPath
    public String getParentPath() {
        return parentPath;
    }


    // Setter for folderPath
    public void setFolderPath(String inputFolderPath) {
        this.inputFolderPath = inputFolderPath;
    }

    // Getter for folderPath
    public String getFolderPath() {
        return inputFolderPath;
    }


    // Setter for note filePath
    public void setNoteFilePath(String inputNoteFilePath) {
        this.inputNoteFilePath =  inputNoteFilePath;
    }

    // Getter for note filePath
    public String getNoteFilePath() {
        return  inputNoteFilePath;
    }

    // Setter for RenamedImagePath
    public void setRenamedImagePath(String renamedImagePath) {
        this.renamedImagePath =  renamedImagePath;
    }

    // Getter for renamedImagePath
    public String getRenamedImagePath() {
        return  renamedImagePath;
    }



    @FXML
    protected void setComboTxt(){
        if (modCombo.isEditable()) {
            String selectedValue = modCombo.getValue().toString();
            comboTxt.setText(selectedValue);
        } else if (comboTxt.isEditable()) {
            String selectedValue = modCombo.getValue().toString();
            comboTxt.setText(selectedValue);

        }
    }

    @FXML
    protected void saveFolderFiles() {
        PaymentInformation content = new PaymentInformation(roomTxt.getText(),noteTxt.getText(),amountTxt.getText(),datePicker.getValue().toString(),comboTxt.getText(),accountTxt.getText());
        ArrayList<PaymentInformation> saveContent = new ArrayList<PaymentInformation>();
        saveContent.add(content);

        NoteWriter write = new NoteWriter(getFolderPath(),getNoteFilePath(),saveContent);
        String imagePath = getRenamedImagePath();
        File renamedImage = new File(imagePath);
        try {
            if (renamedImage.exists()) {
                String destinationFolder = getFolderPath();
                File destinationFile = new File(destinationFolder, renamedImage.getName());

                if (renamedImage.renameTo(destinationFile)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Success!");
                    alert.setContentText("File saved successfully.");
                    alert.showAndWait();
                    reset();

                } else {
                    Alert alert_1 = new Alert(Alert.AlertType.ERROR);
                    alert_1.setHeaderText("Error!");
                    alert_1.setContentText("Failed to saved the file.");
                    alert_1.showAndWait();
                    imageTxt.setText(renamedImage.getName());
                    }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            Alert alert_1 = new Alert(Alert.AlertType.ERROR);
            alert_1.setHeaderText("Error!");
            alert_1.setContentText("An error occurred: " + ex.getMessage());
            alert_1.showAndWait();
            imageTxt.setText(renamedImage.getName());

        }

    }

    @FXML
    protected void reset(){

        folderTxt.setText("");
        fileTxt.setText("");
        roomTxt.setText("");
        amountTxt.setText("");
        accountTxt.setText("");
        noteTxt.setText("");
        imageTxt.setText("image.jpeg");
        statusTxt.setText("Not Created");
        statusTxt.setStyle("-fx-control-inner-background: Red;");
        datePicker.setValue(null);
        comboTxt.setText("");
        folderTxt.setText("");
        Image image = new Image("file:/C:/Users/luisa/IdeaProjects/PaymentReport_Att2/src/main/ImageFile/Insert-Image-Here.png");
        imageView.setImage(image);

        roomTxt.setEditable(false);
        amountTxt.setEditable(false);
        accountTxt.setEditable(false);
        datePicker.setEditable(false);
        comboTxt.setEditable(false);
        noteTxt.setEditable(false);

        }

    }


}
