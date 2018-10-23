package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import disk.MyDisk;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import myFile.ExeFile;
import myFile.Folder;
import myFile.TxtFile;

public class StartApplication extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws FileNotFoundException, IOException, ClassNotFoundException {

		VBox pane  = new VBox();
//		MyDisk.disk.getFat().printFat();

////		System.out.println("-------------根目录下创建目录------------");
////		Folder folder = (Folder) MyDisk.disk.getRootFolder().newFile(1);
////		folder.rename("folder1");
////		System.out.println("folder size =" + folder.getSize());
////		System.out.println("磁盘占用:" + MyDisk.disk.getRootFolder().getSize());
////		MyDisk.disk.getFat().printFat();
////
////		System.out.println("-------------folder目录下创建txt----------");
////		TxtFile txtFile = (TxtFile) folder.newFile(2);
////		System.out.println("folder size =" + folder.getSize());
////		txtFile.rename("txt1");
////		System.out.println("磁盘占用:" + MyDisk.disk.getRootFolder().getSize());
////		MyDisk.disk.getFat().printFat();
////
////		System.out.println("----------改变txt1的内容-------------");
////		txtFile.setText("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
////		System.out.println("folder size =" + folder.getSize());
////		System.out.println("txt1 size = " + txtFile.getSize());
////		System.out.println("磁盘占用:" + MyDisk.disk.getRootFolder().getSize());
////		MyDisk.disk.getFat().printFat();
////
////		System.out.println("------------复制txt1--------------");
////		System.out.println("folder size =" + folder.getSize());
////		TxtFile txtFile2 = (TxtFile) txtFile.copy();
////		txtFile2.rename("txt2");
////		System.out.println("磁盘占用:" + MyDisk.disk.getRootFolder().getSize());
////		MyDisk.disk.getFat().printFat();
////
////		System.out.println("-----------粘贴在folder---------------");
////		folder.paste(txtFile2);
////		System.out.println("folder size =" + folder.getSize());
////		System.out.println("磁盘占用:" + MyDisk.disk.getRootFolder().getSize());
////		MyDisk.disk.getFat().printFat();
////
////		System.out.println("----------改变txt1内容---------------");
////		txtFile.setText("112121");
////		System.out.println("txt1 size = " + txtFile.getSize());
////		System.out.println("folder size =" + folder.getSize());
////		System.out.println("磁盘占用:" + MyDisk.disk.getRootFolder().getSize());
////		MyDisk.disk.getFat().printFat();
////
////		System.out.println("----------改变txt1内容---------------");
////		txtFile.setText("1121218888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
////		System.out.println("txt1 size = " + txtFile.getSize());
////		System.out.println("folder size =" + folder.getSize());
////		System.out.println("磁盘占用:" + MyDisk.disk.getRootFolder().getSize());
////		MyDisk.disk.getFat().printFat();
//////
//////		System.out.println("----------------删除txt2---------------");
//////		System.out.println("folder 的文件" + folder.getChildrenFiles());
//////		txtFile2.delete();
//////		System.out.println("folder size =" + folder.getSize());
//////		Disk.fat.printFat();
//////
//////		System.out.println("----------改变txt1内容---------------");
//////		txtFile.setText("1121218888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
//////		System.out.println("txt1 size = " + txtFile.getSize());
//////		System.out.println("folder size =" + folder.getSize());
//////		Disk.fat.printFat();
//////
//////
//////		System.out.println("-----------------删除folder----------------");
//////		folder.delete();
//////		Disk.fat.printFat();
////
////		System.out.println("----------------创建folder2------------------");
////		Folder folder2 = (Folder) folder.newFile(1);
////		folder2.rename("folder2");
////		System.out.println("磁盘占用:" + MyDisk.disk.getRootFolder().getSize());
////		MyDisk.disk.getFat().printFat();
////
////		System.out.println("----------------在folder下创建txt3----------------");
////		TxtFile txtFile3 = (TxtFile) folder2.newFile(2);
////		txtFile3.rename("txt3");
////		System.out.println("磁盘占用:" + MyDisk.disk.getRootFolder().getSize());
////		MyDisk.disk.getFat().printFat();
////
////		System.out.println("------------------------txt3改文本------------------");
////		txtFile3.setText("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
////		System.out.println("磁盘占用:" + MyDisk.disk.getRootFolder().getSize());
////		MyDisk.disk.getFat().printFat();
////
////		System.out.println("--------------------------------");
////		System.out.println(folder.getChildrenFiles());
////		System.out.println("--------------------------------");
//////
////		MyDisk.disk.getFat().printFat();
////
////
////		MyDisk.saveDisk();
//		System.out.println(MyDisk.readDisk());
//		MyDisk.disk.getFat().printFat();
//		System.out.println(MyDisk.disk.getRootFolder().getChildrenFiles());
//
////		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("asd.dat"));
////		output.writeObject(MyDisk.disk);
////		output.close();

//		System.out.println(MyDisk.readDisk());
		MyDisk.getDisk().getFat().printFat();
		Folder folder = MyDisk.getDisk().getRootFolder();
		for (int i = 0; i < folder.getChildrenFiles().size(); i++) {
			ExeFile exeFile = (ExeFile) folder.getChildrenFiles().get(i);
			System.out.println(exeFile.getName());
			System.out.println(exeFile.getText());
			System.out.println(exeFile.getSize());
		}
		System.out.println(MyDisk.getDisk().getRootFolder().getSize());

		Scene scene = new Scene(pane);
		arg0.setScene(scene);
		arg0.show();

	}

}
