package disk;

import java.io.Serializable;

import myFile.ExeFile;
import myFile.Folder;

/**
 * ����ɵ��õķ���</br>
 * getFat()��ȡFAT��</br>
 * getRootFolder()��ȡ��Ŀ¼</br>
 * getDiskSize()��ȡ������ʹ�ô�С</br>
 * @author ������
 *
 */
public class Disk implements Serializable{

	//-------------------------------------������--------------------------------
	/**
	 * FAT��
	 */
	private Fat fat;
	/**
	 * ��Ŀ¼
	 */
	private Folder rootFolder;

	//-----------------------------------���췽��------------------------------------
	/**
	 * ���̹��췽��
	 */
	public Disk(){
		fat = new Fat();
		rootFolder = new Folder();
	}

	//-----------------------------------get����-------------------------------------
	/**
	 * ��ȡFAT��
	 * @return
	 */
	public Fat getFat(){
		return fat;
	}

	/**
	 * ��ȡ��Ŀ¼
	 * @return��Ŀ¼
	 */
	public Folder getRootFolder(){
		return rootFolder;
	}

	/**
	 * ��ȡ����ռ�ÿռ�
	 * @return������ʹ�ÿռ��С
	 */
	public int getDiskSize(){
		return 256 + rootFolder.getSize();
	}

}
