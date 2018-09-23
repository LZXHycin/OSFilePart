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
	 * ����������ݵ�disk.dat�ļ���
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
	 * ��disk.dat�ж�ȡ��������
	 * @return
	 * true����ȡ�ɹ���false����ȡʧ��
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
