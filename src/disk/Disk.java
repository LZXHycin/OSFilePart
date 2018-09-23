package disk;

import java.io.Serializable;

import myFile.Folder;

public class Disk implements Serializable{

	//FAT��
	private Fat fat;
	//��Ŀ¼
	private Folder rootFolder;

	public Disk(){
		fat = new Fat();
		rootFolder = new Folder();
	}

	/**
	 * ��ȡFAT��
	 * @return
	 */
	public Fat getFat(){
		return fat;
	}

	/**
	 * ��ȡ��Ŀ¼
	 * @return
	 */
	public Folder getRootFolder(){
		return rootFolder;
	}

	/**
	 * ��ȡ����ռ�ÿռ�
	 * @return
	 */
	public int getDiskSize(){
		return 256 + rootFolder.getSize();
	}

}
