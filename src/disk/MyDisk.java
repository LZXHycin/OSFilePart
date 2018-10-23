package disk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import myFile.ExeFile;

/**
 * �����ʹ�õķ���</br>
 * newDisk()����ȡ��¼�����´�������</br>
 * saveDisk()�����¼</br>
 * readDisk()��ȡ��¼</br>
 * @author ������
 *
 */
public class MyDisk {

	//-------------------------------------------������-------------------------------------
	/**
	* ����
	*/
	private static Disk disk;
	/**
	 * �������ݵ��ļ���
	 */
	private static String filePath = "disk.dat";

	static{
		File file = new File(filePath);
		if (file.exists()) {
			System.out.println("yes");
			try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))){
				try {
					disk = (Disk) input.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("no");
			disk = new Disk();
		}
	}


	//------------------------------------------�ⲿ��ʹ�õķ���--------------------------------
	public static Disk getDisk(){
		return disk;
	}


//	/**
//	 * ���ñ�������ݣ����¿�ʼ
//	 */
//	public static void newDisk(){
//		ExeFile[] exeFiles = new ExeFile[4];
//		for (int i = 0; i < exeFiles.length; i++) {
//			exeFiles[i] = new ExeFile("BFH" + (i+1), createOrders(), disk.getRootFolder());
//			disk.getRootFolder().paste(exeFiles[i]);
//			System.out.println(exeFiles[i].getText());
//			System.out.println(exeFiles[i].getSize());
//			System.out.println("----------------------------------");
//		}
//	}
//
//	/**
//	 * ����������ݵ�disk.dat�ļ���
//	 */
//	public static void saveDisk() {
//		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath))){
//			output.writeObject(disk);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * ��disk.dat�ж�ȡ��������
//	 * @return
//	 * true����ȡ�ɹ���false����ȡʧ��
//	 */
//	public static Boolean readDisk() {
//		try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))){
//			try {
//				disk = (Disk) input.readObject();
//				return true;
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//				return false;
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return false;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	//--------------------------------------���ڲ�ʹ�õķ���--------------------------------------
//	/**
//	 * ���������������
//	 * @return
//	 */
//	private static String[] createOrders(){
//		int len = (int) (Math.random() * 9);
//		String[] orders = new String[len + 2];
//		orders[0] = initVar();
//		orders[len + 1] = "end";
//		for (int i = 1; i < orders.length-1; i++) {
//			orders[i] = createOrder();
//		}
//		return orders;
//	}
//
//	/**
//	 * �������һ��ָ��
//	 * @return
//	 */
//	private static String createOrder(){
//		int num = (int)(Math.random() * 5);
//		System.out.println(num);
//		switch (num) {
//		//��ʼ������
//		case 0:
//			return initVar();
//		//x++
//		case 1:
//			return "x++";
//		//x--
//		case 2:
//			return "x--";
//		//ʹ���豸
//		default:
//			return useDevice();
//		}
//	}
//
//	/**
//	 * ����һ����ֵ����
//	 * @return
//	 */
//	private static String initVar(){
//		int num = (int) (Math.random() * 21);
//		return "x=" + num;
//	}
//
//	/**
//	 * ����һ��ʹ���豸����
//	 * @return
//	 */
//	private static String useDevice(){
//		char device = (char) ('A' + (int)(Math.random() * 3));
//		int time = (int) (Math.random()*10);
//		return "!" + device + time;
//	}
}
