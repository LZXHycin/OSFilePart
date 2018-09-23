package disk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MyDisk {

	public static Disk disk = new Disk();
	private static String filePath = "disk.dat";

	/**
	 * 保存磁盘内容到disk.dat文件中
	 */
	public static void saveDisk() {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath))){
			output.writeObject(disk);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从disk.dat中读取保存内容
	 * @return
	 * true：读取成功；false：读取失败
	 */
	public static Boolean readDisk() {
		try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))){
			try {
				disk = (Disk) input.readObject();
				return true;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
