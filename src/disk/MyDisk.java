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
 * 界面可使用的方法</br>
 * newDisk()不读取记录，重新创建磁盘</br>
 * saveDisk()保存记录</br>
 * readDisk()读取记录</br>
 * @author 风信子
 *
 */
public class MyDisk {

	//-------------------------------------------数据域-------------------------------------
	/**
	* 磁盘
	*/
	private static Disk disk;
	/**
	 * 保存数据的文件名
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


	//------------------------------------------外部可使用的方法--------------------------------
	public static Disk getDisk(){
		return disk;
	}


//	/**
//	 * 不用保存的数据，重新开始
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
//	 * 保存磁盘内容到disk.dat文件中
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
//	 * 从disk.dat中读取保存内容
//	 * @return
//	 * true：读取成功；false：读取失败
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
//	//--------------------------------------类内部使用的方法--------------------------------------
//	/**
//	 * 随机生成命令数组
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
//	 * 随机生成一条指令
//	 * @return
//	 */
//	private static String createOrder(){
//		int num = (int)(Math.random() * 5);
//		System.out.println(num);
//		switch (num) {
//		//初始化变量
//		case 0:
//			return initVar();
//		//x++
//		case 1:
//			return "x++";
//		//x--
//		case 2:
//			return "x--";
//		//使用设备
//		default:
//			return useDevice();
//		}
//	}
//
//	/**
//	 * 生成一条赋值命令
//	 * @return
//	 */
//	private static String initVar(){
//		int num = (int) (Math.random() * 21);
//		return "x=" + num;
//	}
//
//	/**
//	 * 生成一条使用设备命令
//	 * @return
//	 */
//	private static String useDevice(){
//		char device = (char) ('A' + (int)(Math.random() * 3));
//		int time = (int) (Math.random()*10);
//		return "!" + device + time;
//	}
}
